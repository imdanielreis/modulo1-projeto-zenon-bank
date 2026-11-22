package org.example.infra;

import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TransactionIngestor {

    public List<Transaction> getListTransactionsForFileWithStream(String fileName)
            throws IOException {

        List<Transaction> transactions = new ArrayList<>();

        Path path = Path.of("data", fileName).toAbsolutePath();

        try (BufferedReader input = Files.newBufferedReader(path)) {

            return input.lines()
                    .skip(1)
                    .map(line -> {
                        try {
                            return parseToTransactionItem(line.split(",", -1));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + line + " " + e.getClass().getName() + " " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();

        } catch (Exception e) {
            System.err.println("Erro: Arquivo não encontrado.");
        }

        return transactions;
    }

    public List<Transaction> getListTransactionsForFileWhitWhile(String fileName)
            throws IOException {

        List<Transaction> transactions = new ArrayList<>();

        Path path = Path.of("data", fileName).toAbsolutePath();

        try (BufferedReader input = Files.newBufferedReader(path)) {

            input.readLine();

            String line;
            while ((line = input.readLine()) != null) {
                try {
                    transactions.add(
                            parseToTransactionItem(line.split(",", -1)));
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println("Erro: " + line + " " + e.getClass().getName() + " " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: Arquivo não encontrado.");
        }

        return transactions;
    }

    private Transaction parseToTransactionItem(String[] fields) {

        return new Transaction(
                Integer.parseInt(fields[0]),
                validateParseType(fields[1]),
                new BigDecimal(fields[2]),
                fields[3],
                new BigDecimal(fields[4]),
                new BigDecimal(fields[5]),
                fields[6],
                new BigDecimal(fields[7]),
                new BigDecimal(fields[8]),
                "1".equals(fields[9].trim()),
                "1".equals(fields[10].trim())
        );
    }

    private static TransactionType validateParseType(String value) throws IllegalArgumentException {
        try {
            return TransactionType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "No enum constant: " + value);
        }
    }
}
