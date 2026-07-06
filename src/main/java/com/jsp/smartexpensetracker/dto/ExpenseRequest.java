package com.jsp.smartexpensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jsp.smartexpensetracker.entity.Expense;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotNull(message = "Category is required")
    private Expense.Category category;

    @NotNull(message = "Date is required")
    private LocalDate date;
}
