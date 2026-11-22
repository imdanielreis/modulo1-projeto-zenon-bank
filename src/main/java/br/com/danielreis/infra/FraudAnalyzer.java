package br.com.danielreis.infra;

import br.com.danielreis.model.Transaction;
import br.com.danielreis.model.TransactionType;



import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


//Stream Exercise
public class FraudAnalyzer {

    // Apenas transações onde isFraud == true, imprima o tamanho da lista.
    public void printCountFraudTransactions(List<Transaction> transactions) {

        long count = transactions.stream()
                        .filter(Transaction::isFraud)
                        .count();

        System.out.println("1. Total de Fraudes: " + count);
    }

    // Imprima as 3 fraudes de maior valor (amount).
    public void printValueThreeBiggestFraudsters(List<Transaction> transactions) {

        List<BigDecimal> topThree = transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();

        System.out.println("2. Top 3 Fraudes de Maior Valor: ");
        topThree.forEach(value -> System.out.println(value.toPlainString()));
    }

    // Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.
    public void printNameOrigFiveBiggestFraudsters(List<Transaction> transactions) {

        List<String> topFive = transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(Transaction::nameOrig)
                .distinct()
                .limit(5)
                .toList();

        System.out.println("3. Clientes Suspeitos: ");
        topFive.forEach(System.out::println);
    }

    // Calcule o prejuízo total causado pelas fraudes (soma dos amount).
    public void printTotalLoss(List<Transaction> transactions) {

        BigDecimal totalLoss = transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(new BigDecimal("0.0"), BigDecimal::add);

        System.out.println("4. Prejuízo Total: " + totalLoss.toPlainString());
    }

    // Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...).
    public void printCountFraudsByType(List<Transaction> transactions) {

        Map<TransactionType, Long> fraudCountByType = transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.counting()
                ));

        System.out.println("5. Fraudes por Tipo: ");
        fraudCountByType.forEach((entry, value) -> System.out.println(entry + " "+ value));
    }
}
