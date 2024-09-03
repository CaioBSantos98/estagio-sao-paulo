package com.saopaulo.app.model;

import java.time.LocalDate;

public class DailyBilling {
    private LocalDate date;
    private double value;

    public DailyBilling() {}

    public DailyBilling(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DailyBilling {" +
                "date=" + date +
                ", value=" + value +
                '}';
    }
}
