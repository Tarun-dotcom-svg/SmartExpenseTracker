package com.jsp.smartexpensetracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.smartexpensetracker.dto.ApiResponse;
import com.jsp.smartexpensetracker.dto.ExpenseResponse;
import com.jsp.smartexpensetracker.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ExpenseService expenseService;

    @GetMapping("/expenses")
    public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getAllExpenses() {
        List<ExpenseResponse> expenses = expenseService.getAllExpensesAdmin();
        return ResponseEntity.ok(ApiResponse.success("All expenses fetched (admin)", expenses));
    }
}
