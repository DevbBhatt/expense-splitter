package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit,Long> {
        List<ExpenseSplit> findByExpense(Expense expense);
}
