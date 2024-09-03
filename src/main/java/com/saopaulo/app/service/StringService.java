package com.saopaulo.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringService {

    private final Scanner scanner = new Scanner(System.in);

    public void reverse() {
        System.out.println("Informe uma string para ser revertida");
        String typedString = scanner.nextLine();

        int lastChar = typedString.length() - 1;
        List<Character> characterList = new ArrayList<>();

        while (lastChar >= 0) {
            characterList.add(typedString.charAt(lastChar));
            lastChar--;
        }

        StringBuilder reversedString = new StringBuilder();
        for (Character ch : characterList) {
            reversedString.append(ch);
        }

        System.out.println(reversedString);
    }
}
