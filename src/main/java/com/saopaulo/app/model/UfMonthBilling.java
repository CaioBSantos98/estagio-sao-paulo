package com.saopaulo.app.model;

public class UfMonthBilling {
    private Uf uf;
    private double value;

    public UfMonthBilling() {}

    public UfMonthBilling(Uf uf, double value) {
        this.uf = uf;
        this.value = value;
    }

    public Uf getUf() {
        return uf;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UfMonthBilling{" +
                "uf=" + uf +
                ", value=" + value +
                '}';
    }
}
