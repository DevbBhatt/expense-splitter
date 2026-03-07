package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group,Long> {

}
