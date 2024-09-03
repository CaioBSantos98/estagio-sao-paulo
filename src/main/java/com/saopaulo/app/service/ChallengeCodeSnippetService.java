package com.saopaulo.app.service;

public class ChallengeCodeSnippetService {

    public void verifyCodeProvided() {
        int index = 13;
        var sum = 0;
        int k = 0;

        while (k < index) {
            k = k + 1;
            sum = sum + k;
        }
        System.out.println("O valor da variavel SOMA é: " + sum);
    }
}
