package com.mongodb.starter.expenses.services;

import com.mongodb.starter.example.dtos.PersonDTO;
import com.mongodb.starter.expenses.models.Expense;
import com.mongodb.starter.expenses.repositories.ExpenseRepository;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void saveExpense(Expense expense) {
        System.out.println(expense);
        System.out.println(expense.getId());
        if (expense.getId() == null || expense.getId() == "") {
            UUID uuid = UUID.randomUUID();
            expense.setId(uuid.toString());
        }
        expenseRepository.save(expense);
    }

    public void saveDoc(Document doc) {
        expenseRepository.save(doc);
    }

    public void deleteDocs() {
        expenseRepository.deleteAll();
    }

    public List<Expense> getExpenses() { return expenseRepository.findAll();}

    public Expense update(Expense expense) {return expenseRepository.update(expense);}
}
