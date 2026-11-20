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
            TransactionIngestor transactionIngestor = new TransactionIngestor();

            List<Transaction> listTransactionsForFile = transactionIngestor.getListTransactionsForFile("PS_20174392719_1491204439457_log.csv");

//            for (int i = 0; i < listTransactionsForFile.size(); i++) {
//                System.out.println(listTransactionsForFile.get(i));
//            }

            List<Transaction> fiftyThousandTransactions = listTransactionsForFile.stream().limit(50000).toList();

            FraudAnalyzer analyzer = new FraudAnalyzer();

            analyzer.printCountFraudTransactions(fiftyThousandTransactions);
            analyzer.printValueThreeBiggestFraudsters(fiftyThousandTransactions);
            analyzer.printNameOrigFiveBiggestFraudsters(fiftyThousandTransactions);
            analyzer.printTotalLoss(fiftyThousandTransactions);
            analyzer.printCountFraudsByType(fiftyThousandTransactions);
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "O arquivo não pode ser aberto, confira o nome.");
        }
    }
}
