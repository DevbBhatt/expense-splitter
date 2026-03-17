package com.expensesplitter.expense_splitter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "balances")
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;

    private Double amount;

}