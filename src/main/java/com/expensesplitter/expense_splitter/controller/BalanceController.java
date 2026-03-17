package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;


    @GetMapping("/groups/{groupId}/balances")
    public Map<User,Double> getGroupBalance(@PathVariable Long groupId){
            return balanceService.getGroupBalance(groupId);
    }

    @GetMapping("/groups/{groupId}/balances/{userId}")
    public  Double getUserBalanceInGroup(@PathVariable Long groupId,
                                                   @PathVariable Long userId){
        return balanceService.getUserBalanceInGroup(groupId,userId);
    }



}
