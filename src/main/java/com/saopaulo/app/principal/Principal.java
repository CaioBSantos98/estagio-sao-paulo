package com.saopaulo.app.principal;

import com.saopaulo.app.service.BillingService;
import com.saopaulo.app.service.ChallengeCodeSnippetService;
import com.saopaulo.app.service.FibonacciService;
import com.saopaulo.app.service.StringService;

import java.util.Scanner;

public class Principal {
    private final Scanner scanner = new Scanner(System.in);
    private final FibonacciService fibonacciService = new FibonacciService();
    private final StringService stringService = new StringService();
    private final ChallengeCodeSnippetService codeSnippetService = new ChallengeCodeSnippetService();
    private final BillingService billingService = new BillingService();

    public void showMenu() {
        System.out.println("\n##### BOAS VINDAS AO CONSOLE DO TESTE PRATICO ESTAGIO - RIBEIRAO PRETO #####");
        try {
            int chosenItem = 0;
            while (chosenItem != 9) {
                showMenuItens();
                String typedText = scanner.nextLine();
                chosenItem = Integer.parseInt(typedText);

                switch (chosenItem) {
                    case 1 -> codeSnippetService.verifyCodeProvided();
                    case 2 -> fibonacciService.verify();
                    case 3 -> billingService.getBillingDetails();
                    case 4 -> billingService.getPercentualByUf();
                    case 5 -> stringService.reverse();
                    case 9 -> System.out.println("Saindo...");
                    default -> {
                        System.out.println("Opção selecionada inválida, tente novamente");
                        chosenItem = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMenuItens() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Verificar trecho de codigo do desafio");
        System.out.println("2 -> Verificar se número digitado pertence à sequência de Fibonacci");
        System.out.println("3 -> Obter dados sobre faturamento");
        System.out.println("4 -> Obter percentual do faturamento por estado");
        System.out.println("5 -> Reverter string");
        System.out.println("9 -> Sair");
    }
}
