package com.jsp.smartexpensetracker.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlySummary {
    private int year;
    private int month;
    private String monthName;
    private BigDecimal totalAmount;
    private long totalExpenses;
}
