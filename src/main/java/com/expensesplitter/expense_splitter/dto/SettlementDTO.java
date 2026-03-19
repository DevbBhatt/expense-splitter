package com.expensesplitter.expense_splitter.dto;

import lombok.Data;

@Data
public class SettlementDTO {

   private Long fromUserId;

   private Long toUserId;

   private String fromUserName;

   private String toUserName;

    private Double amount;


}
