package com.jsp.smartexpensetracker.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.smartexpensetracker.dto.ExpenseRequest;
import com.jsp.smartexpensetracker.dto.ExpenseResponse;
import com.jsp.smartexpensetracker.dto.MonthlySummary;
import com.jsp.smartexpensetracker.entity.Expense;
import com.jsp.smartexpensetracker.entity.User;
import com.jsp.smartexpensetracker.exception.ResourceNotFoundException;
import com.jsp.smartexpensetracker.exception.UnauthorizedException;
import com.jsp.smartexpensetracker.repository.ExceptionRepository;
import com.jsp.smartexpensetracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExceptionRepository expenseRepository;
    private final UserRepository userRepository;

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private ExpenseResponse toResponse(Expense e) {
        return ExpenseResponse.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .amount(e.getAmount())
                .category(e.getCategory())
                .date(e.getDate())
                .userEmail(e.getUser().getEmail())
                .userName(e.getUser().getName())
                .build();
    }

    // ─── CRUD ─────────────────────────────────────────────────────────────────

    public ExpenseResponse createExpense(ExpenseRequest request) {
        User user = getCurrentUser();
        Expense expense = Expense.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .amount(request.getAmount())
                .category(request.getCategory())
                .date(request.getDate())
                .user(user)
                .build();
        return toResponse(expenseRepository.save(expense));
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepository.findByUserOrderByDateDesc(getCurrentUser())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ExpenseResponse getExpenseById(Long id) {
        User user = getCurrentUser();
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to view this expense");
        }
        return toResponse(expense);
    }

    public ExpenseResponse updateExpense(Long id, ExpenseRequest request) {
        User user = getCurrentUser();
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to update this expense");
        }
        expense.setTitle(request.getTitle());
        expense.setDescription(request.getDescription());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
        return toResponse(expenseRepository.save(expense));
    }

    public void deleteExpense(Long id) {
        User user = getCurrentUser();
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
        if (!expense.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException("You are not authorized to delete this expense");
        }
        expenseRepository.delete(expense);
    }

    // ─── Filters ──────────────────────────────────────────────────────────────

    public List<ExpenseResponse> filterExpenses(Expense.Category category, LocalDate startDate, LocalDate endDate) {
        User user = getCurrentUser();
        List<Expense> expenses;

        if (category != null && startDate != null && endDate != null) {
            expenses = expenseRepository.findByUserAndCategoryAndDateBetweenOrderByDateDesc(user, category, startDate, endDate);
        } else if (category != null) {
            expenses = expenseRepository.findByUserAndCategoryOrderByDateDesc(user, category);
        } else if (startDate != null && endDate != null) {
            expenses = expenseRepository.findByUserAndDateBetweenOrderByDateDesc(user, startDate, endDate);
        } else {
            expenses = expenseRepository.findByUserOrderByDateDesc(user);
        }

        return expenses.stream().map(this::toResponse).collect(Collectors.toList());
    }

    // ─── Monthly Summary ──────────────────────────────────────────────────────

    public MonthlySummary getMonthlySummary(int year, int month) {
        User user = getCurrentUser();
        return MonthlySummary.builder()
                .year(year)
                .month(month)
                .monthName(Month.of(month).name())
                .totalAmount(expenseRepository.sumAmountByUserAndYearAndMonth(user, year, month))
                .totalExpenses(expenseRepository.countByUserAndYearAndMonth(user, year, month))
                .build();
    }

    // ─── Admin ────────────────────────────────────────────────────────────────

    public List<ExpenseResponse> getAllExpensesAdmin() {
        return expenseRepository.findAllByOrderByDateDesc()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }
}
