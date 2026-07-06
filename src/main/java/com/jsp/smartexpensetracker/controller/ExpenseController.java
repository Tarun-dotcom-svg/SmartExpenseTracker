package com.jsp.smartexpensetracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.smartexpensetracker.dto.ApiResponse;
import com.jsp.smartexpensetracker.dto.ExpenseRequest;
import com.jsp.smartexpensetracker.dto.ExpenseResponse;
import com.jsp.smartexpensetracker.dto.MonthlySummary;
import com.jsp.smartexpensetracker.entity.Expense;
import com.jsp.smartexpensetracker.service.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // POST /api/expenses — create expense
    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponse>> createExpense(@Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse response = expenseService.createExpense(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Expense created successfully", response));
    }

    // GET /api/expenses — get all expenses for current user
    @GetMapping
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAllExpenses() {
        List<ExpenseResponse> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(ApiResponse.success("Expenses fetched successfully", expenses));
    }

    // GET /api/expenses/{id} — get expense by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> getExpenseById(@PathVariable Long id) {
        ExpenseResponse response = expenseService.getExpenseById(id);
        return ResponseEntity.ok(ApiResponse.success("Expense fetched successfully", response));
    }

    // PUT /api/expenses/{id} — update expense
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponse>> updateExpense(
            @PathVariable Long id, @Valid @RequestBody ExpenseRequest request) {
        ExpenseResponse response = expenseService.updateExpense(id, request);
        return ResponseEntity.ok(ApiResponse.success("Expense updated successfully", response));
    }

    // DELETE /api/expenses/{id} — delete expense
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(ApiResponse.success("Expense deleted successfully", null));
    }

    // GET /api/expenses/filter — filter by category and/or date range
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> filterExpenses(
            @RequestParam(required = false) Expense.Category category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<ExpenseResponse> expenses = expenseService.filterExpenses(category, startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success("Filtered expenses fetched", expenses));
    }

    // GET /api/expenses/summary?year=2025&month=3 — monthly summary
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<MonthlySummary>> getMonthlySummary(
            @RequestParam int year,
            @RequestParam int month) {
        MonthlySummary summary = expenseService.getMonthlySummary(year, month);
        return ResponseEntity.ok(ApiResponse.success("Monthly summary fetched", summary));
    }
}
