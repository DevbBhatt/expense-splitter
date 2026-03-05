package com.expensesplitter.expense_splitter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "expense_splits")
@Data
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double amount;
}
