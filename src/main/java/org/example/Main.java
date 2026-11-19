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

            List<Transaction> listTransactionsForFile = transactionIngestor.getListTransactionsForFile("paysim_with_bad_data.csv");

            for (int i = 0; i < listTransactionsForFile.size(); i++) {
                System.out.println(listTransactionsForFile.get(i));
            }
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "O arquivo não pode ser aberto, confira o nome.");
        }
    }
}
