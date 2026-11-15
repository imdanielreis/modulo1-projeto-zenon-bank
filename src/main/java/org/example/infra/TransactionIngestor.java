package org.example.infra;

import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> getListTransactionsForFile(String fileName) throws IOException {

        List<Transaction> transactions = new ArrayList<>();
        Path path = Path.of("data", fileName).toAbsolutePath();

        try(BufferedReader input = Files.newBufferedReader(path)) {
            List<String> lines = input.readAllLines();

            for (int i = 1; i <= 1000; i++) {
                String[] lineWithCommas = lines.get(i).split(",", -1);

                transactions.add(parseToTransactioItem(lineWithCommas));
            }
        } catch (IOException e) {
            throw new IOException(e);
        }

        return transactions;
    }

    private static Transaction parseToTransactioItem(String[] lineWithCommas) {

        if(lineWithCommas.length < 10) {
            throw new IllegalArgumentException("Era esperado 10 colunas e vieram: " + lineWithCommas.length);
        }

        return new Transaction(
                        Integer.parseInt(lineWithCommas[0].strip()),
                        TransactionType.valueOf(lineWithCommas[1]),
                        new BigDecimal(lineWithCommas[2].strip()),
                        lineWithCommas[3].strip(),
                        new BigDecimal(lineWithCommas[4].strip()),
                        new BigDecimal(lineWithCommas[5].strip()),
                        lineWithCommas[6].strip(),
                        new BigDecimal(lineWithCommas[7].strip()),
                        new BigDecimal(lineWithCommas[8].strip()),
                        Boolean.parseBoolean(lineWithCommas[9].strip()),
                        Boolean.parseBoolean(lineWithCommas[10].strip())
        );
    }
}
