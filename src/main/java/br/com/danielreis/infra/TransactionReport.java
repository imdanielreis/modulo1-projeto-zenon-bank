package br.com.danielreis.infra;

import br.com.danielreis.model.ReportTransaction;
import br.com.danielreis.model.StatisticsReport;

import java.math.BigDecimal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {


    public StatisticsReport generateReportWithFile(String fileName)  {

        Path path = Path.of("data", fileName).toAbsolutePath();

        try (Stream<String> lines = Files.lines(path)) {
            return  lines
                    .skip(1)
                    .map(line -> line.split(",", -1))
                    .flatMap(fields -> parseToReportTransaction(fields).stream())
                    // StatisticsReport concentra as regras de soma do relatório
                    // utilizando de absatração, diminuindo a complexidade do código
                    .reduce(
                            // Começa com os totais zerados
                            StatisticsReport.ZERO,
                            // Inclui uma transação nos totais
                            StatisticsReport::add,
                            // Combina relatórios parciais em paralelo se usar lines.parallel(),
                            // uma vez que nesse caso pode-se abrir mais de uma thread em paralelo
                            // o merge junta todos e entrega para o reduce
                            StatisticsReport::merge
                    );
        } catch (Exception e) {
            throw new RuntimeException("Erro: Arquivo não encontrado.");
        }
    }

//    public StatisticsReport generateReportWithFile(String fileName)  {
//
//        Path path = Path.of("data", fileName).toAbsolutePath();
//
//        try (Stream<String> lines = Files.lines(path)) {
//             return  lines
//                     .skip(1)
//                     .map(line -> line.split(",", -1))
//                     .flatMap(fields -> parseToReportTransaction(fields).stream())
//                     .reduce(
//                             new StatisticsReport(0, 0, BigDecimal.ZERO),
//                             (StatisticsReport acc, ReportTransaction rt) -> {
//                                 return new StatisticsReport(
//                                         acc.totalTransactios() + 1,
//                                         acc.totalFrauds() + (rt.isFraud() ? 1 : 0),
//                                         acc.totalAmount().add(rt.amount()));
//                             }, (StatisticsReport s1, StatisticsReport s2) -> s1);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro: Arquivo não encontrado.");
//        }
//    }

    private Optional<ReportTransaction> parseToReportTransaction(String[] fields) {

        return Optional.of(new ReportTransaction(
                new BigDecimal(fields[2]),
                "1".equals(fields[9].trim()))
        );
    }
}
