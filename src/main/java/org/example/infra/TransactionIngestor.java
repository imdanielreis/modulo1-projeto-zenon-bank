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

    public List<Transaction> getListTransactionsForFile(String fileName)
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

    private static Transaction parseToTransactionItem(String[] fields) {

        return new Transaction(
                        Integer.parseInt(requiredField(fields, 0, "step", false)),
                        validateParseType(requiredField(fields, 1, "type", false)),
                        new BigDecimal(requiredField(fields, 2, "amount", true)),
                        requiredField(fields, 3, "nameOrig", false),
                        new BigDecimal(requiredField(fields, 4, "oldbalanceOrg", true)),
                        new BigDecimal(requiredField(fields, 5, "newbalanceOrig", true)),
                        requiredField(fields, 6, "nameDest", false),
                        new BigDecimal(requiredField(fields, 7, "oldbalanceDest", true)),
                        new BigDecimal(requiredField(fields, 8, "newbalanceDest", true)),
                        "1".equals(requiredField(fields, 9, "isFraud", false).trim()),
                        "1".equals(requiredField(fields, 9, "isFlaggedFraud", false).trim())
        );
    }

    private static String requiredField(String[] fields, int index, String name, boolean numeric) throws IllegalArgumentException {

        if (fields[index] == null || fields[index].isBlank()) {
            if (numeric) {
                throw new NumberFormatException("");
            }

            throw new IllegalArgumentException(name + " should not be empty");
        }

        return fields[index].strip();
    }

    private static TransactionType  validateParseType(String value) throws IllegalArgumentException {

        try {
            return TransactionType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "No enum constant: " + value);
        }
    }

    private static BigDecimal parseDecimal(String value, String name) {

        if (value.isBlank()) {
            throw new NumberFormatException(name + " should not be empty");
        }

        return new BigDecimal(value.strip());
    }
}
