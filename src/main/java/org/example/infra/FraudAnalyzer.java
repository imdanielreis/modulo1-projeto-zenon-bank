package org.example.infra;

import org.example.model.Transaction;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Stream Exercise
public class FraudAnalyzer {

    public void printCountFraudTransactions(List<Transaction> transactions) {
        // Apenas transações onde isFraud == true, imprima o tamanho da lista.
        long count = transactions.stream()
                .filter(Transaction::isFraud)
                .count();

        System.out.println("1. Total de Fraudes: " + count);
    }

    public void printValueThreeBiggestFraudsters(List<Transaction> transactions) {
        // Imprima as 3 fraudes de maior valor (amount).
        List<BigDecimal> topThree = transactions.stream()
                .map(Transaction::amount)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();

        System.out.println("2. Top 3 Fraudes de Maior Valor:");
        for (BigDecimal value : topThree) {
            System.out.println(value.toPlainString());
        }
    }

    public void printNameOrigFiveBiggestFraudsters(List<Transaction> transactions) {
        // Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.
        List<String> topFiveNames = transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::nameOrig)
                .distinct()
                .limit(5)
                .toList();

        System.out.println("3. Clientes Suspeitos::");
        for (String name : topFiveNames) {
            System.out.println(name);
        }
    }

    public void printTotalLoss(List<Transaction> transactions) {
        // Calcule o prejuízo total causado pelas fraudes (soma dos amount).
        BigDecimal totalLoss = transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("4. Prejuízo Total: " + totalLoss.toPlainString());
    }

    public void printCountFraudsByType(List<Transaction> transactions) {
        // Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...).
        var fraudCountByType = transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));

        System.out.println("5. Fraudes por Tipo: ");
        for (var entry : fraudCountByType.entrySet()) {
            System.out.printf(" - %s: %d%n", entry.getKey(), entry.getValue());
        }
    }
}
