package com.saopaulo.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.saopaulo.app.model.DailyBilling;
import com.saopaulo.app.model.UfMonthBilling;

import java.io.File;
import java.time.Month;
import java.util.List;

public class BillingService {

    public void getBillingDetails() {
        var data = getData();

        var lowerApril = getLowerBillingOnMonth(data, Month.APRIL);
        var biggerApril = getBiggerBillingOnMonth(data, Month.APRIL);
        var daysBiggerThanAverageApril = getDaysThatBillingBiggerThanMonthAverage(data, Month.APRIL);

        System.out.println("Menor faturamento de abril: " + lowerApril);
        System.out.println("Maior faturamento de abril: " + biggerApril);
        System.out.println("Dias com faturamento em abril maior que a média: " + daysBiggerThanAverageApril);
        System.out.println("-------------------------------------");

        var lowerMay = getLowerBillingOnMonth(data, Month.MAY);
        var biggerMay = getBiggerBillingOnMonth(data, Month.MAY);
        var daysBiggerThanAverageMay = getDaysThatBillingBiggerThanMonthAverage(data, Month.MAY);

        System.out.println("Menor faturamento de maio: " + lowerMay);
        System.out.println("Maior faturamento de maio: " + biggerMay);
        System.out.println("Dias com faturamento em maio maior que a média: " + daysBiggerThanAverageMay);
        System.out.println("-------------------------------------");

        var lowerJune = getLowerBillingOnMonth(data, Month.JUNE);
        var biggerJune = getBiggerBillingOnMonth(data, Month.JUNE);
        var daysBiggerThanAverageJune = getDaysThatBillingBiggerThanMonthAverage(data, Month.JUNE);

        System.out.println("Menor faturamento de junho: " + lowerJune);
        System.out.println("Maior faturamento de junho: " + biggerJune);
        System.out.println("Dias com faturamento em junho maior que a média: " + daysBiggerThanAverageJune);
        System.out.println("-------------------------------------");
    }

    public void getPercentualByUf() {
        var ufData = getUfData();
        var totalBilling = getTotalBilling(ufData);
        for (UfMonthBilling billing : ufData) {
            double percentual = (billing.getValue() / totalBilling) * 100;
            System.out.printf("Percentual de %s: %.2f%%%n", billing.getUf(), percentual);
        }
    }

    private DailyBilling getLowerBillingOnMonth(List<DailyBilling> dailyBillings, Month month) {
        List<DailyBilling> filteredBillings = filterByMonth(dailyBillings, month);
        if (filteredBillings.isEmpty()) {
            return null;
        }

        DailyBilling dailyBillingToReturn = new DailyBilling();
        dailyBillingToReturn.setValue(filteredBillings.get(0).getValue());
        filteredBillings.forEach(db -> {
            if (db.getValue() < dailyBillingToReturn.getValue() && db.getValue() > 0) {
                dailyBillingToReturn.setDate(db.getDate());
                dailyBillingToReturn.setValue(db.getValue());
            }
        });

        return dailyBillingToReturn;
    }

    private DailyBilling getBiggerBillingOnMonth(List<DailyBilling> dailyBillings, Month month) {
        List<DailyBilling> filteredBillings = filterByMonth(dailyBillings, month);
        if (filteredBillings.isEmpty()) {
            return null;
        }

        DailyBilling dailyBillingToReturn = new DailyBilling();
        dailyBillingToReturn.setValue(filteredBillings.get(0).getValue());
        filteredBillings.forEach(db -> {
            if (db.getValue() > dailyBillingToReturn.getValue()) {
                dailyBillingToReturn.setDate(db.getDate());
                dailyBillingToReturn.setValue(db.getValue());
            }
        });

        return dailyBillingToReturn;
    }

    private int getDaysThatBillingBiggerThanMonthAverage(List<DailyBilling> dailyBillings, Month month) {
        List<DailyBilling> filteredBillings = filterByMonth(dailyBillings, month);
        double average = getBillingAverage(filteredBillings);
        System.out.println("Média do mês: " + month + " " + average);
        int totalDays = 0;
        for (DailyBilling db: filteredBillings) {
            if (db.getValue() > average) {
                totalDays++;
            }
        }
        return totalDays;
    }

    private List<DailyBilling> filterByMonth(List<DailyBilling> list, Month month) {
        return list.stream()
                .filter(db -> db.getDate().getMonth().getValue() == month.getValue())
                .toList();
    }

    private double getBillingAverage(List<DailyBilling> dailyBillings) {
        double totalValidDb = 0;
        double accumulator = 0;

        for (DailyBilling db : dailyBillings) {
            if (db.getValue() > 0) {
                totalValidDb++;
                accumulator += db.getValue();
            }
        }
        return (accumulator/totalValidDb);
    }

    private List<DailyBilling> getData() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        List<DailyBilling> dailyBillings = null;

        try {
            File file = new File("data.json");
            dailyBillings = objectMapper.readValue(file, new TypeReference<List<DailyBilling>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dailyBillings;
    }

    private List<UfMonthBilling> getUfData() {
        ObjectMapper objectMapper = new ObjectMapper();

        List<UfMonthBilling> ufMonthBillings = null;

        try {
            File file = new File("ufdata.json");
            ufMonthBillings = objectMapper.readValue(file, new TypeReference<List<UfMonthBilling>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ufMonthBillings;
    }

    private double getTotalBilling(List<UfMonthBilling> ufMonthBillings) {
        double total = 0;
        for (UfMonthBilling billing : ufMonthBillings) {
            total += billing.getValue();
        }
        return total;
    }
}
