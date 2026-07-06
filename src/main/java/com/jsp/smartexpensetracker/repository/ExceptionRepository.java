package com.jsp.smartexpensetracker.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jsp.smartexpensetracker.entity.Expense;
import com.jsp.smartexpensetracker.entity.User;

@Repository
public interface ExceptionRepository extends JpaRepository<Expense, Long> {

    // All expenses for a user
    List<Expense> findByUserOrderByDateDesc(User user);

    // Filter by category
    List<Expense> findByUserAndCategoryOrderByDateDesc(User user, Expense.Category category);

    // Filter by date range
    List<Expense> findByUserAndDateBetweenOrderByDateDesc(User user, LocalDate startDate, LocalDate endDate);

    // Filter by category and date range
    List<Expense> findByUserAndCategoryAndDateBetweenOrderByDateDesc(
            User user, Expense.Category category, LocalDate startDate, LocalDate endDate);

    // Monthly summary query
    @Query("SELECT e FROM Expense e WHERE e.user = :user AND YEAR(e.date) = :year AND MONTH(e.date) = :month ORDER BY e.date DESC")
    List<Expense> findByUserAndYearAndMonth(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month);

    // Total amount for a user in a given month
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user = :user AND YEAR(e.date) = :year AND MONTH(e.date) = :month")
    BigDecimal sumAmountByUserAndYearAndMonth(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month);

    // Count expenses for a user in a given month
    @Query("SELECT COUNT(e) FROM Expense e WHERE e.user = :user AND YEAR(e.date) = :year AND MONTH(e.date) = :month")
    long countByUserAndYearAndMonth(
            @Param("user") User user,
            @Param("year") int year,
            @Param("month") int month);

    // Admin: all expenses
    List<Expense> findAllByOrderByDateDesc();
}
