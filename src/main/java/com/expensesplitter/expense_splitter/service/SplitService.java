package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.dto.SplitRequest;
import com.expensesplitter.expense_splitter.entity.*;
import com.expensesplitter.expense_splitter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;

    @Autowired
    private UserRepository userRepository;

    public void splitExpense(Expense expense, List<SplitRequest> splits){

        if ("EQUAL".equalsIgnoreCase(expense.getSplitType())) {
            equalSplit(expense);
        }
        else if ("EXACT".equalsIgnoreCase(expense.getSplitType())) {

            if (splits == null || splits.isEmpty()) {
                throw new RuntimeException("Splits required for EXACT split type");
            }

            exactSplit(expense, splits);
        }
        else {
            throw new RuntimeException("Invalid split type");
        }
    }

    private void equalSplit(Expense expense) {

        Group group = expense.getGroup();
        List<GroupMember> members = groupMemberRepository.findByGroup(group);
        int totalMembers = members.size();
        double amount = expense.getAmount();

        // Check Members is not 0
        if(totalMembers == 0){
            throw new RuntimeException("No members in group");
        }

        double share = amount / totalMembers;

        for(GroupMember member:members){
            ExpenseSplit split = new ExpenseSplit();

            split.setExpense(expense);
            split.setAmount(share);
            split.setUser(member.getUser());

            expenseSplitRepository.save(split);
        }
    }


    private void exactSplit(Expense expense,List<SplitRequest> splits) {


        double totalAmount = expense.getAmount();

        double sum = 0;

        for(SplitRequest split : splits){
            sum += split.getAmount();
        }

        if(Math.abs(sum - totalAmount) > 0.01){
            throw new RuntimeException("Split amount do not match Expense amount");
        }

        for(SplitRequest split:splits) {
            User user = userRepository.findById(split.getUserId())
                    .orElseThrow(() -> new RuntimeException("User Not found"));


            ExpenseSplit expenseSplit = new ExpenseSplit();

            expenseSplit.setExpense(expense);
            expenseSplit.setUser(user);
            expenseSplit.setAmount(split.getAmount());

            expenseSplitRepository.save(expenseSplit);

        }

    }
}