package com.expensesplitter.expense_splitter.service;


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


    public Expense addExpense(Long groupId,Long userId,Expense expense) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("Group Not Found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        groupMemberRepository.findByGroupAndUser(group,user)
                .orElseThrow(()-> new RuntimeException("Member Not found"));

        expense.setCreatedAt(LocalDateTime.now());
        expense.setGroup(group);
        expense.setPaidBy(user);

        return expenseRepository.save(expense);
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

    pub


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
