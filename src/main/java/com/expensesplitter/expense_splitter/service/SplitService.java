package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.entity.*;
import com.expensesplitter.expense_splitter.repository.ExpenseRepository;
import com.expensesplitter.expense_splitter.repository.ExpenseSplitRepository;
import com.expensesplitter.expense_splitter.repository.GroupMemberRepository;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;



    public void splitExpense(Long groupId,Long expenseId){

        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("Group Not Found"));
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense Not Found"));

        if("EQUAL".equals(expense.getSplitType())){
            equalSplit(expense);
        }
        else if("EXACT".equals(expense.getSplitType())){
            exactSplit(expense);
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


    private void exactSplit(Expense expense) {
        Group group = expense.getGroup();
        List<GroupMember> members = groupMemberRepository.findByGroup(group);
        int totalMembers = members.size();
        double amount = expense.getAmount();


    }
}