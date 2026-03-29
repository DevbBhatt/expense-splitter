package com.expensesplitter.expense_splitter.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class SplitRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Split amount must be positive")
    private Double amount;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
