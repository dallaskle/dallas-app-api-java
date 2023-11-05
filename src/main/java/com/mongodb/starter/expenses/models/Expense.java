package com.mongodb.starter.expenses.models;
import java.util.Objects;

public class Expense {

    private String id;
    private double amount;
    private String user;
    private String category;
    private String timestamp;
    private String merchant;
    private String description;

    public Expense() {
    }

    public Expense(String id, double amount, String user, String category, String timestamp, String merchant, String description) {
        this.id = id;
        this.amount = amount;
        this.user = user;
        this.category = category;
        this.timestamp = timestamp;
        this.merchant = merchant;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.amount, amount) == 0 && Objects.equals(id, expense.id) && Objects.equals(user, expense.user) && Objects.equals(category, expense.category) && Objects.equals(timestamp, expense.timestamp) && Objects.equals(merchant, expense.merchant) && Objects.equals(description, expense.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, user, category, timestamp, merchant, description);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", user='" + user + '\'' +
                ", category='" + category + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", merchant='" + merchant + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
