package br.com.danielreis;

import br.com.danielreis.database.ConnectionFactory;
import br.com.danielreis.database.TransactionListRepository;
import br.com.danielreis.database.TransactionMapRepository;
import br.com.danielreis.database.TransactionSQLRepository;
import br.com.danielreis.infra.FraudAnalyzer;
import br.com.danielreis.infra.TransactionIngestor;
import br.com.danielreis.infra.TransactionReport;
import br.com.danielreis.model.StatisticsReport;
import br.com.danielreis.model.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class Main {

    private static final System.Logger logger = System.getLogger(Main.class.getName());

    private static final String correctFile = "PS_20174392719_1491204439457_log.csv";
    private static final String brokenFile = "paysim_with_bad_data.csv";
    private static final long totalNumberLines = 6362620;

    static void main(String[] args) {
        try {
//            IO.println("------------------ Begin  ------------------");
//            IO.println("------------------ Locale ------------------");
//
//            String language = (args.length > 0 ? args[0] : "pt");
//            var locale = Locale.of(language);
//
//            var integerFormat = NumberFormat.getIntegerInstance(locale);
//            var currencyFormat = DecimalFormat.getCurrencyInstance(locale);
//            currencyFormat.setCurrency(Currency.getInstance("USD"));
//            TransactionReport transactionReport = new TransactionReport();
//
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("report", locale);
//
//            StatisticsReport sReport = transactionReport.generateReportWithFile(correctFile);
//
//            String totalTransactions = integerFormat.format(sReport.totalTransactios());
//            String totalFrauds = integerFormat.format(sReport.totalFrauds());
//            String totalAmount = currencyFormat.format(sReport.totalAmount());
//
//            String msgTotalTransactions = resourceBundle.getString("label.total.transactions");
//            String msgTotalFrauds = resourceBundle.getString("label.total.frauds");
//            String msgTotalAmount = resourceBundle.getString("label.total.amount");
//
//            IO.println("""
//                    %s: %s
//                    %s: %s
//                    %s: %s
//                    """.formatted(
//                            msgTotalTransactions, totalTransactions,
//                            msgTotalFrauds, totalFrauds,
//                            msgTotalAmount, totalAmount
//                    )
//            );
//
//            IO.println("------------------ Locale ------------------");
//            IO.println("------------------   END  ------------------\n");

//            IO.println("------------------     Begin     ------------------");
//            IO.println("------------------ Get file Lazy ------------------");
//
//            TransactionReport transactionReport = new TransactionReport();
//
//            StatisticsReport sReport = transactionReport.generateReportWithFile(correctFile);
//
//            IO.println("""
//                    Total de linhas %d
//                    Total de fraudes: %d
//                    Valor total transacionado: %.2f
//                    """.formatted(
//                            sReport.totalTransactios(),
//                            sReport.totalFrauds(),
//                            sReport.totalAmount()
//                    )
//            );
//
//            IO.println("------------------ Get file Lazy ------------------");
//            IO.println("------------------      END      ------------------\n");

//            IO.println("------------------  Begin   ------------------");
//            IO.println("------------------ Get file ------------------");
//
//            TransactionIngestor streamIngestor = new TransactionIngestor();
//
//            List<Transaction> correctedTransactions = streamIngestor
//                    .getListTransactionsForFileWithStream(correctFile, totalNumberLines);
//
//            correctedTransactions.forEach(System.out::println);
//
//            IO.println("------------------ Get file ------------------");
//            IO.println("------------------    END   ------------------\n");

//            IO.println("------------------           Begin          ------------------");
//            IO.println("------------------ Get broken items in file ------------------");
//
//            TransactionIngestor transactionIngestorStream = new TransactionIngestor();
//
//            List<Transaction> listTransactionsForFile = transactionIngestorStream
//                    .getListTransactionsForFileWithStream(brokenFile, 17);
//
//            listTransactionsForFile.forEach(System.out::println);
//
//            IO.println("------------------ Get broken items in file ------------------");
//            IO.println("------------------            END           ------------------\n");

//            IO.println("------------------  Begin  ------------------");
//            IO.println("------------------ Streams ------------------");
//
//            TransactionIngestor transactionIngestorStream = new TransactionIngestor();
//            long initStream = System.nanoTime();
//
//            List<Transaction> listTransactionsForFile = transactionIngestorStream
//                    .getListTransactionsForFileWithStream(correctFile, totalNumberLines);
//
//            long finishStream = System.nanoTime();
//            double secStream = (finishStream - initStream) / 1_000_000_000.0;
//
//            System.out.printf("com stream: %,d transações em %.3f segundos%n",
//                    listTransactionsForFile.size(), secStream);
//
//            IO.println("------------------ Streams ------------------");
//            IO.println("------------------   END   ------------------\n");
//
//            IO.println("------------------ Begin ------------------");
//            IO.println("------------------ While ------------------");
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
//            IO.println("------------------ While ------------------");
//            IO.println("------------------  END  ------------------\n");

//            IO.println("------------------    Begin   ------------------");
//            IO.println("------------------ Map vs List------------------");
//
//            TransactionIngestor transactionIngestor = new TransactionIngestor();
//
//            List<Transaction> listTransactions = transactionIngestor
//                    .getListTransactionsForFileWithStream(correctFile, 100000);
//
//            String name = "C1868032458"; //the last line with limitMax: 100000
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
//            System.out.printf("Busca na lista    %,.3f ms%n", listSearchNs / 1_000_000.0);
//            System.out.printf("Busca no mapa     %,.3f ms%n", mapSearchNs / 1_000_000.0);
//            IO.println("Encontrou o mesmo registro? "
//                    + (listResult.isPresent() && listResult.equals(mapResult) ? "Sim" : "Não") + "\n");
//
//            mapResult.ifPresentOrElse(
//                    System.out::println,
//                    () -> IO.println("Transação não encontrada para o cliente " + name + "\n"));
//
//            IO.println("------------------ Map vs List------------------");
//            IO.println("------------------     End    ------------------\n");

//            IO.println("------------------    Begin   ------------------");
//            IO.println("------------------FraudAnalyzer------------------");
//
//            TransactionIngestor transactionIngestorFraudAnalyzer = new TransactionIngestor();
//
//            List<Transaction> fiftyThousandTransactions = transactionIngestorFraudAnalyzer
//                    .getListTransactionsForFileWithStream(correctFile, 50000);
//
//            FraudAnalyzer analyzer = new FraudAnalyzer(fiftyThousandTransactions);
//
//            analyzer.printCountFraudTransactions();
//            analyzer.printValueThreeBiggestFraudsters();
//            analyzer.printNameOrigFiveBiggestFraudsters();
//            analyzer.printTotalLoss();
//            analyzer.printCountFraudsByType();
//
//            IO.println("------------------FraudAnalyzer------------------");
//            IO.println("------------------     End    ------------------\n");
//
            IO.println("------------------           Begin         ------------------");
            IO.println("------------------ SQL Create Transactions ------------------");

            var transactionSQLRepository = new TransactionSQLRepository();
            TransactionIngestor transactionIngestor = new TransactionIngestor();

            List<Transaction> listTransactions = transactionIngestor
                    .getListTransactionsForFileWithStream(correctFile, 10000);

            listTransactions.forEach(transactionSQLRepository::save);

            IO.println("------------------ SQL Create Transactions ------------------");
            IO.println("------------------            END           ------------------");
//
//            IO.println("------------------      Begin       ------------------");
//            IO.println("------------------ SQL Find by name ------------------");
//
//            String name = "C1231006815";
//            var transactionSQLRepositoryRepository = new TransactionSQLRepository();
//            Optional<Transaction> transaction = transactionSQLRepositoryRepository.findByName(name);
//
//            transaction.ifPresentOrElse(
//                    IO::println,
//                    () -> IO.println("Transação não encontrada para o cliente " + name + "\n"));
//
//            IO.println("------------------ SQL Find by name ------------------");
//            IO.println("------------------        End       ------------------\n");
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Um erro inesperado aconteceu.");
        }
    }
}
