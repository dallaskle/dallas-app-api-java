package com.mongodb.starter.expenses.services.singles;

import com.mongodb.starter.expenses.services.ExpenseService;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ExcelToMongo_CC_to_Expense {

    ExpenseService expenseService;
    String excelFilePath = "/Users/dallasklein/code/byDallas/dallas-app-api-java/src/main/resources/CreditCards_2023.csv";

    public ExcelToMongo_CC_to_Expense(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public void run() throws IOException {
        Path path = Paths.get(excelFilePath);
        boolean exist = Files.exists(path);
        if (exist) {
            System.out.println("it's true");
        }
        try {
            Files.lines(path, StandardCharsets.UTF_8).skip(1).forEach(line -> {
                // Your code here
                System.out.println("line");
            });
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause().toString());
        }
        try {



            Files.lines(path).skip(1).forEach(s -> {
                System.out.println("first line");

                String[] values = s.split(","); // Assuming tab-separated values

                String transactionDate = values[0];
                String postDate = values[1];
                String description = values[2];
                String category = values[3];
                String type = values[4];
                String account = values[5];
                double amount = Double.parseDouble(values[6]);
                String memo = values.length > 7 ? values[7] : "";

                Document expenseDocument = new Document("amount", amount)
                        .append("category", category)
                        .append("description", memo)
                        .append("_id", UUID.randomUUID().toString())
                        .append("merchant", description)
                        .append("timestamp", transactionDate)
                        .append("account", account)
                        .append("amount", amount)
                        .append("type", type)
                        .append("user", "dallas");

                System.out.println("Document");
                System.out.println(expenseDocument);

                expenseService.saveDoc(expenseDocument);

            });
        } catch (IOException ex) {
            // do something or re-throw...
        }
    }
}
