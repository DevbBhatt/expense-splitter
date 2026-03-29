package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GroupRepository extends JpaRepository<Group,Long> {

    List<Group> findByIsDeletedFalse();
}
