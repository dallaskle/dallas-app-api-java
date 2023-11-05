package com.mongodb.starter.expenses.services;

import com.mongodb.starter.example.dtos.PersonDTO;
import com.mongodb.starter.expenses.models.Expense;
import com.mongodb.starter.expenses.repositories.ExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void saveExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> getExpenses() { return expenseRepository.findAll();}

    public Expense update(Expense expense) {return expenseRepository.update(expense);}
}
