package com.expensesplitter.expense_splitter.service;


import com.expensesplitter.expense_splitter.dto.ExpenseRequest;
import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.ExpenseRepository;
import com.expensesplitter.expense_splitter.repository.GroupMemberRepository;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
