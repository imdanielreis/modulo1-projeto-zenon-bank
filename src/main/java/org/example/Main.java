package org.example;

import org.example.infra.FraudAnalyzer;
import org.example.infra.TransactionIngestor;
import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final System.Logger logger =
            System.getLogger(Main.class.getName());

    static void main() {
        try {

            //Test with stream
            // com stream: 6,362,620 transações em 3.146 segundos
            // com stream: 6,362,620 transações em 3.055 segundos
            // com stream: 6,362,620 transações em 3.055 segundos
            // com stream: 6,362,620 transações em 2.937 segundos

//            TransactionIngestor transactionIngestorStream = new TransactionIngestor();
//            long initStream = System.nanoTime();

//            List<Transaction> listTransactionsForFile = transactionIngestorStream.getListTransactionsForFileWithStream("PS_20174392719_1491204439457_log.csv");
//
//            long finishStream = System.nanoTime();
//            double secStream = (finishStream - initStream) / 1_000_000_000.0;
//
//            System.out.printf("com stream: %,d transações em %.3f segundos%n",
//                    listTransactionsForFile.size(), secStream);

            //Test with while
            // com while: 6,362,620 transações em 2.872 segundos
            // com while: 6,362,620 transações em 2.874 segundos
            // com while: 6,362,620 transações em 2.831 segundos
            // com while: 6,362,620 transações em 2.772 segundos
            TransactionIngestor transactionIngestorWhile = new TransactionIngestor();

            long initWhile = System.nanoTime();
            List<Transaction> listTransactionsForFileWhile = transactionIngestorWhile.getListTransactionsForFileWhitWhile("PS_20174392719_1491204439457_log.csv");

            long finishWhile = System.nanoTime();
            double secWhile = (finishWhile - initWhile) / 1_000_000_000.0;

            System.out.printf("com while: %,d transações em %.3f segundos%n",
                    listTransactionsForFileWhile.size(), secWhile);
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "O arquivo não pode ser aberto, confira o nome.");
        }
    }
}
