package com.mongodb.starter.expenses.repositories;
import com.mongodb.starter.expenses.models.Expense;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository {

    Expense save(Expense expense);

    Expense update(Expense expense);

    List<Expense> findAll();
}
