package com.mongodb.starter.expenses.controllers;

import com.mongodb.starter.expenses.models.Expense;
import com.mongodb.starter.expenses.services.ExpenseService;
import com.mongodb.starter.expenses.services.singles.ExcelToMongo_CC_to_Expense;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    private ExpenseService expenseService;

    private ExcelToMongo_CC_to_Expense excelToMongoCcToExpense;

    public ExpenseController(ExpenseService expenseService, ExcelToMongo_CC_to_Expense excelToMongoCcToExpense) {
        this.expenseService = expenseService;
        this.excelToMongoCcToExpense = excelToMongoCcToExpense;
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

    @PostMapping("expenses/excel")
    public String uploadExcel() throws IOException {
        ExcelToMongo_CC_to_Expense excelToMongoCcToExpense = new ExcelToMongo_CC_to_Expense(expenseService);
        excelToMongoCcToExpense.run();
        return "Response :-)";
    }

    @DeleteMapping("expenses/delete")
    public String deleteDocs() {
        expenseService.deleteDocs();
        return "Response :-)";
    }

}
