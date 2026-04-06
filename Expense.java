package ExpenseTracker;

import java.time.LocalDate;

public class Expense {
    private Integer id;
    private LocalDate date;
    private String description;
    private Double amount;

    public Expense() {
    }

    public Expense(Integer id, LocalDate date, String description, Double amount) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + "," + date + "," + description + "," + amount;
    }
}
