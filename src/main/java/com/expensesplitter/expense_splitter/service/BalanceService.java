package com.expensesplitter.expense_splitter.service;


import com.expensesplitter.expense_splitter.dto.SplitRequest;
import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.entity.ExpenseSplit;
import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.ExpenseRepository;
import com.expensesplitter.expense_splitter.repository.ExpenseSplitRepository;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseSplitRepository expenseSplitRepository;


    @Autowired
    private UserRepository userRepository;


    public Map<User,Double> getGroupBalance(Long groupId) {

        return calculateBalance(groupId);
    }

    private Map<User, Double> calculateBalance(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("Group Not Found"));

        List<Expense> expenses = expenseRepository.findByGroup(group);

        Map<User,Double> map = new HashMap<>();

        for(Expense expense:expenses){

        List<ExpenseSplit> expenseSplits = expenseSplitRepository.findByExpense(expense);

            User user = expense.getPaidBy();
            Double totalAmount = expense.getAmount();

            if(map.containsKey(user)) map.put(user,map.get(user)+(totalAmount));
            else map.put(user,totalAmount);

        for(ExpenseSplit expenseSplit:expenseSplits){

            User user1 = expenseSplit.getUser();
            Double amount = expenseSplit.getAmount();

            map.put(user1, map.getOrDefault(user1, 0D) - amount);

        }
        }

        return map;
    }


    public Double getUserBalanceInGroup(Long groupId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

       Map<User,Double> map = calculateBalance(groupId);

        return map.getOrDefault(user,0d);

    }
}
