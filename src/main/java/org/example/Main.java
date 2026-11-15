package org.example;

import org.example.infra.TransactionIngestor;
import org.example.model.Transaction;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final System.Logger logger =
            System.getLogger(Main.class.getName());

    static void main() {

        try {

            TransactionIngestor transactionIngestor = new TransactionIngestor();

            List<Transaction> listTransactionsForFile = transactionIngestor.getListTransactionsForFile("PS_20174392719_1491204439457_log.csv");

            for (int i = 0; i < 10; i++) {
                System.out.println(listTransactionsForFile.get(i));
            }
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "O arquivo não pode ser aberto, confira o nome.");
        }
    }
}
