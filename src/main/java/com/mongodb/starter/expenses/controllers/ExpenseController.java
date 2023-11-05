package com.mongodb.starter.expenses.controllers;

import com.mongodb.starter.example.dtos.PersonDTO;
import com.mongodb.starter.expenses.models.Expense;
import com.mongodb.starter.expenses.services.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("expenses")
    public List<Expense> getExpenses() {
        return expenseService.getExpenses();
    }

    @PostMapping("expense")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
    }

    @PutMapping("expense")
    public Expense putPerson(@RequestBody Expense expense) {
        return expenseService.update(expense);
    }

}
