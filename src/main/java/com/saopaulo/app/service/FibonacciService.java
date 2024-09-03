package com.saopaulo.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FibonacciService {

    private final Scanner scanner = new Scanner(System.in);

    public void verify() {
        System.out.println("Informe um número para saber se ele pertence à sequência de Fibonacci");
        int number = scanner.nextInt();
        boolean exist = existsOnFibonacciSequence(number);
        if (exist) {
            System.out.println("Esse número pertence à sequência de Fibonacci");
        } else {
            System.out.println("Esse número não pertence à sequência de Fibonacci");
        }
    }

    private boolean existsOnFibonacciSequence(int number) {
        if (number < 0) return false;
        if (number == 0) return true;
        if (number == 1) return true;
        List<Integer> fibonacciSequence = getFibonacciSequence(number);
        System.out.println(fibonacciSequence);
        return fibonacciSequence.contains(number);
    }

    private List<Integer> getFibonacciSequence(int number) {
        List<Integer> fibonacciSequence = new ArrayList<>();
        fibonacciSequence.add(0);
        fibonacciSequence.add(1);

        var lowerThanLastElement = true;
        while (lowerThanLastElement) {
            var lastElement = fibonacciSequence.get(fibonacciSequence.size() - 1);
            var secondLastElement = fibonacciSequence.get(fibonacciSequence.size() - 2);
            var elementToAdd = lastElement + secondLastElement;

            fibonacciSequence.add(elementToAdd);
            if (elementToAdd >= number) lowerThanLastElement = false;
        }

        return fibonacciSequence;
    }
}
