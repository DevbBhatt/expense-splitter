package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit,Long> {

}
