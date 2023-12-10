package com.mongodb.starter.expenses.repositories;
import com.mongodb.starter.expenses.models.Expense;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository {

    Expense save(Expense expense);

    void save(Document doc);

    void deleteAll();

    Expense update(Expense expense);

    List<Expense> findAll();
}
