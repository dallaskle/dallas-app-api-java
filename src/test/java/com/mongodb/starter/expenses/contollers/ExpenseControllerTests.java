package com.mongodb.starter.expenses.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.starter.expenses.controllers.ExpenseController;
import com.mongodb.starter.expenses.models.Expense;
import com.mongodb.starter.expenses.repositories.ExpenseRepository;
import com.mongodb.starter.expenses.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExpenseControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ExpenseService expenseService;
    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseController expenseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    void testSaveExpense() throws Exception {
        //given
        Expense expense = new Expense();
        expense.setId("1");
        expense.setAmount(123.45);
        expense.setUser("dallas");
        expense.setCategory("groceries");
        expense.setTimestamp("2023-11-04");
        expense.setMerchant("Trader Joes");
        expense.setDescription("Weekly Groceries");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonExpense = objectMapper.writeValueAsString(expense);

        //when - then
        mockMvc.perform(post("/api/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonExpense))
                .andExpect(status().isCreated());
        verify(expenseService).saveExpense(expense);
    }

    @Test
    void testGetExpenses() throws Exception {
        Expense expense1 = new Expense("1", 100.0, "user1", "category1", "2023-11-01", "merchant1", "description1");
        Expense expense2 = new Expense("2", 200.0, "user2", "category2", "2023-11-02", "merchant2", "description2");
        List<Expense> expenses = Arrays.asList(expense1, expense2);

        when(expenseService.getExpenses()).thenReturn(expenses);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/expenses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

        verify(expenseService, times(1)).getExpenses();
    }
}
