package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.dto.ExpenseRequest;
import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/groups/{groupId}/expenses/{userId}")
    public Expense addExpense(@PathVariable Long groupId,
                              @PathVariable Long userId,
                              @RequestBody ExpenseRequest request){
        return expenseService.addExpense(groupId,userId,request);
    }


    @GetMapping("/groups/{groupId}/expenses")
    public List<Expense> getGroupExpenses(@PathVariable Long groupId){
        return expenseService.getGroupExpenses(groupId);
    }

    @GetMapping("/{expenseId}")
    public Expense getExpenseById(@PathVariable Long expenseId){
        return expenseService.getExpenseById(expenseId);
    }

    // Later
//    @PutMapping("/{expenseID}")
//    public Expense updateExpense(@PathVariable Long expenseId){
//        return expenseService.updateExpense(expenseId);
//    }

    @DeleteMapping("{expenseId}")
    public Expense deleteExpense(@PathVariable Long expenseId){
        return expenseService.deleteExpense(expenseId);
    }

}
