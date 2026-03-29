package com.expensesplitter.expense_splitter.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;


public class ExpenseRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;


    private Long paidBy;

    @NotBlank(message = "Split type is required")
    private String splitType;

    private List<@Valid SplitRequest> splits;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Long paidBy) {
        this.paidBy = paidBy;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<SplitRequest> getSplits() {
        return splits;
    }

    public void setSplits(List<SplitRequest> splits) {
        this.splits = splits;
    }
}
