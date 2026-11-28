package br.com.danielreis.infra;

import br.com.danielreis.model.Transaction;
import br.com.danielreis.model.TransactionType;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class EfficientTransactionIngestor {

    private static final int LINE_BATCH_SIZE = 2500;

    public void getListTransactionsForFileWithBatch(String fileName,
                                                    long limit,
                                                    Consumer<List<Transaction>> batchConsumer)
            throws Exception {


        Path path = Path.of("data", fileName).toAbsolutePath();

        try (ExecutorService executor = Executors.newFixedThreadPool(10);
             Stream<String> lines = Files.lines(path).skip(1).limit(limit)) {

            var iterator = lines.iterator();

            List<String> lineBatch = new ArrayList<>(LINE_BATCH_SIZE);

            while (iterator.hasNext()) {

                String line = iterator.next();
                lineBatch.add(line);

                if (lineBatch.size() >= LINE_BATCH_SIZE) {
                    List<String> lineBatchCopy = List.copyOf(lineBatch);
                    executor.submit(() -> executeBatch(lineBatchCopy, batchConsumer));
                    lineBatch.clear();
                }
            }

            if (!lineBatch.isEmpty()) {
                executor.submit(() -> executeBatch(lineBatch, batchConsumer));
            }
        } catch (Exception e) {
            throw new Exception("Erro: Arquivo não encontrado.");
        }
    }

    private void executeBatch(List<String> lineBatch, Consumer<List<Transaction>> batchConsumer) {

        List<Transaction> transactionsBatch = lineBatch.stream()
                .map(line -> {
                    try {
                        return parseToTransactionItem(line.split(",", -1));
                    } catch (IllegalArgumentException e) {
                        IO.println("Erro: " + line + " " + e.getClass().getName() + " " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        batchConsumer.accept(transactionsBatch);
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
            throw new IllegalArgumentException("No enum constant: " + value);
        }
    }
}
