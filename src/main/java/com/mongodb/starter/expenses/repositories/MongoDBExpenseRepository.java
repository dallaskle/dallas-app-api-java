package com.mongodb.starter.expenses.repositories;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.starter.expenses.models.Expense;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.ReturnDocument.AFTER;

@Repository
public class MongoDBExpenseRepository implements ExpenseRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
            .readPreference(ReadPreference.primary())
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build();
    private final MongoClient client;
    private MongoCollection<Expense> expenseCollection;
    private MongoCollection<Document> docExpenseCollection;

    public MongoDBExpenseRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        expenseCollection = client.getDatabase("budget").getCollection("expenses", Expense.class);
        docExpenseCollection = client.getDatabase("budget").getCollection("expenses", Document.class);
    }

    @Override
    public Expense save(Expense expense) {
        expenseCollection.insertOne(expense);
        return expense;
    }

    @Override
    public void save(Document doc) {
        docExpenseCollection.insertOne(doc);
    }

    @Override
    public void deleteAll() {
        docExpenseCollection.deleteMany(new Document());
    }

    @Override
    public Expense update(Expense expense) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        expenseCollection.findOneAndReplace(eq("_id", expense.getId()),expense, options);
        return expense;
    }

    @Override
    public List<Expense> findAll() {
        return expenseCollection.find().into(new ArrayList<>());
    }
}
