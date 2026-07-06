package com.jsp.smartexpensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jsp.smartexpensetracker.entity.Expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse {
    private Long id;
    private String title;
    private String description;
    private BigDecimal amount;
    private Expense.Category category;
    private LocalDate date;
    private String userEmail;
    private String userName;
}
