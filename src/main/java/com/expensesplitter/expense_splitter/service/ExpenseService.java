package com.expensesplitter.expense_splitter.service;


import com.expensesplitter.expense_splitter.dto.ExpenseRequest;
import com.expensesplitter.expense_splitter.dto.SplitRequest;
import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.GroupMember;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.ExpenseRepository;
import com.expensesplitter.expense_splitter.repository.GroupMemberRepository;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private SplitService splitService;



        @Transactional
        public Expense addExpense(Long groupId, Long userId, ExpenseRequest request) {

            Group group = groupRepository.findById(groupId)
                    .orElseThrow(()-> new RuntimeException("Group Not Found"));
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));

            groupMemberRepository.findByGroupAndUser(group,user)
                    .orElseThrow(()-> new RuntimeException("Member Not found"));


            if (!"EQUAL".equalsIgnoreCase(request.getSplitType()) &&
                    !"EXACT".equalsIgnoreCase(request.getSplitType())) {
                throw new RuntimeException("Invalid split type");
            }


            // Important Validation
            List<GroupMember> members = groupMemberRepository.findByGroup(group);
            if("EXACT".equalsIgnoreCase(request.getSplitType()) &&
                    members.size() != request.getSplits().size()) {
                throw new RuntimeException("Splits size must match number of users in the group for EXACT type");
            }

            // ✅ VALIDATION FIRST
            validateSplits(request);

            Expense expense = new Expense();

            expense.setCreatedAt(LocalDateTime.now());
            expense.setDescription(request.getDescription());
            expense.setAmount(request.getAmount());
            expense.setSplitType(request.getSplitType());

            expense.setGroup(group);
            expense.setPaidBy(user);



            expense = expenseRepository.save(expense);

            splitService.splitExpense(expense, request.getSplits());


            return expense;
        }

    private void validateSplits(ExpenseRequest request) {
            if("EXACT".equalsIgnoreCase(request.getSplitType())){

             if(request.getSplits() == null || request.getSplits().isEmpty()){
                 throw new RuntimeException("Splits Required For EXACT type ");
             }

                // duplicate users
                Set<Long> userIds = new HashSet<>();
                for (SplitRequest s : request.getSplits()) {
                    if (!userIds.add(s.getUserId())) {
                        throw new RuntimeException("Duplicate user in splits: " + s.getUserId());
                    }
                }


             double sum = 0.0;

             for(SplitRequest s: request.getSplits()) {
                 sum += s.getAmount();
             }


             if(sum != request.getAmount()){
                 throw new RuntimeException("Split Total must MATCH Expense amount");
             }
        }
    }



    public List<Expense> getGroupExpenses(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("Group Not Found"));

        return expenseRepository.findByGroup(group);

    }

    public Expense getExpenseById(Long expenseId) {

        return expenseRepository.findById(expenseId)
                .orElseThrow(()-> new RuntimeException("Expense Not Found"));

    }




    public Expense deleteExpense(Long expenseId) {

            Expense expense = expenseRepository.findById(expenseId)
                    .orElseThrow(()->new RuntimeException("Expense Not Found"));

             expenseRepository.delete(expense);
             return expense;
    }


//    public Expense updateExpense(Long expenseId) {
//
//        // Later
//
//    }
}
