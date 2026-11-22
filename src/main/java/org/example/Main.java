package org.example;

import org.example.infra.TransactionIngestor;
import org.example.model.Transaction;

import java.io.IOException;
import java.util.*;

public class Main {

    private static final System.Logger logger =
            System.getLogger(Main.class.getName());

    private static final String correctFile = "PS_20174392719_1491204439457_log.csv";
    private static final String brokenFile = "paysim_with_bad_data.csv";

    static void main() {
        try {
//            System.out.println("------------------  Begin   ------------------");
//            System.out.println("------------------ Get file ------------------");
//
//            TransactionIngestor streamIngestor = new TransactionIngestor();
//
//            List<Transaction> correctedTransactions = streamIngestor
//                    .getListTransactionsForFileWithStream(correctFile).stream()
//                    .limit(1000)
//                    .toList();
//
//            correctedTransactions.forEach(System.out::println);
//
//            System.out.println("------------------ Get file ------------------");
//            System.out.println("------------------    END   ------------------\n");

            System.out.println("------------------           Begin          ------------------");
            System.out.println("------------------ Get broken items in file ------------------");

            TransactionIngestor transactionIngestorStream = new TransactionIngestor();

            List<Transaction> listTransactionsForFile = transactionIngestorStream
                    .getListTransactionsForFileWithStream(brokenFile);

            listTransactionsForFile.forEach(System.out::println);

            System.out.println("------------------ Get broken items in file ------------------");
            System.out.println("------------------            END           ------------------\n");

//            System.out.println("------------------  Begin  ------------------");
//            System.out.println("------------------ Streams ------------------");
//
//            TransactionIngestor transactionIngestorStream = new TransactionIngestor();
//            long initStream = System.nanoTime();
//
//            List<Transaction> listTransactionsForFile = transactionIngestorStream
//                    .getListTransactionsForFileWithStream(correctFile);
//
//            long finishStream = System.nanoTime();
//            double secStream = (finishStream - initStream) / 1_000_000_000.0;
//
//            System.out.printf("com stream: %,d transações em %.3f segundos%n",
//                    listTransactionsForFile.size(), secStream);
//
//            System.out.println("------------------ Streams ------------------");
//            System.out.println("------------------   END   ------------------\n");

//            System.out.println("------------------ Begin ------------------");
//            System.out.println("------------------ While ------------------");
//
//            TransactionIngestor transactionIngetorWhile = new TransactionIngestor();
//
//            long initWhile = System.nanoTime();
//            List<Transaction> listTransactionsForFileWhile = transactionIngetorWhile
//                    .getListTransactionsForFileWhitWhile(correctFile);
//
//            long finishWhile = System.nanoTime();
//            double secWhile = (finishWhile - initWhile) / 1_000_000_000.0;
//
//            System.out.printf("com while: %,d transações em %.3f segundos%n",
//                    listTransactionsForFileWhile.size(), secWhile);
//
//            System.out.println("------------------ While ------------------");
//            System.out.println("------------------  END  ------------------\n");

//            System.out.println("------------------    Begin   ------------------");
//            System.out.println("------------------ Map vs List------------------");
//
//            TransactionIngestor transactionIngestor = new TransactionIngestor();
//
//            List<Transaction> listTransactions = transactionIngestor
//                    .getListTransactionsForFileWithStream(correctFile);
//
//            String name = "C1868032458";
//            var listRepository = new TransactionListRepository(listTransactions);
//
//            long start = System.nanoTime();
//            var mapRepository = new TransactionMapRepository(listTransactions);
//            long mapBuildNs = System.nanoTime() - start;
//
//            start = System.nanoTime();
//            var listResult = listRepository.findByName(name);
//            long listSearchNs = System.nanoTime() - start;
//
//            start = System.nanoTime();
//            var mapResult = mapRepository.findByName(name);
//            long mapSearchNs = System.nanoTime() - start;
//
//            System.out.printf("Montagem do mapa  %,.3f ms%n", mapBuildNs / 1_000_000.0);
//            System.out.printf("Busca na lista    %,d ns%n", listSearchNs);
//            System.out.printf("Busca no mapa     %,d ns%n", mapSearchNs);
//            System.out.println("Encontrou o mesmo registro?"
//                    + (listResult.isPresent() && listResult.equals(mapResult)) + "\n");
//
//            mapResult.ifPresentOrElse(
//                    System.out::println,
//                    () -> System.out.println("Transação não encontrada para o cliente " + name + "\n"));
//
//            System.out.println("------------------ Map vs List------------------");
//            System.out.println("------------------     End    ------------------\n");

//            System.out.println("------------------    Begin   ------------------");
//            System.out.println("------------------FraudAnalyzer------------------");
//
//            TransactionIngestor transactionIngestorFraudAnalyzer = new TransactionIngestor();
//
//            List<Transaction> fiftyThousandTransactions = transactionIngestorFraudAnalyzer
//                    .getListTransactionsForFileWithStream(correctFile)
//                    .stream()
//                    .limit(50000)
//                    .toList();
//
//            FraudAnalyzer analyzer = new FraudAnalyzer();
//
//            analyzer.printCountFraudTransactions(fiftyThousandTransactions);
//            analyzer.printValueThreeBiggestFraudsters(fiftyThousandTransactions);
//            analyzer.printNameOrigFiveBiggestFraudsters(fiftyThousandTransactions);
//            analyzer.printTotalLoss(fiftyThousandTransactions);
//            analyzer.printCountFraudsByType(fiftyThousandTransactions);
//
//            System.out.println("------------------FraudAnalyzer------------------");
//            System.out.println("------------------     End    ------------------\n");
        } catch (IOException e) {
            logger.log(System.Logger.Level.ERROR, "O arquivo não pode ser aberto, confira o nome.");
        }
    }
}
