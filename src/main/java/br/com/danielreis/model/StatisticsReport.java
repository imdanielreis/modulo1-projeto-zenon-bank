package br.com.danielreis.model;

import java.math.BigDecimal;

public record StatisticsReport(long totalTransactios, long totalFrauds, BigDecimal totalAmount) {
    // Começa com os totais zerados
    public static final StatisticsReport ZERO = new StatisticsReport(0, 0, BigDecimal.ZERO);

    // Inclui uma transação nos totais
    public StatisticsReport add(ReportTransaction rt) {
        return new StatisticsReport(
                totalTransactios + 1,
                totalFrauds + (rt.isFraud() ? 1 : 0),
                totalAmount.add(rt.amount()));
    }

    // Combina relatórios parciais em paralelo se usar lines.parallel(),
    // uma vez que nesse caso pode-se abrir mais de uma thread em paralelo
    // o merge junta todos e entrega para o reduce
    public StatisticsReport merge(StatisticsReport statisticsReport) {
        return new StatisticsReport(totalTransactios + statisticsReport.totalTransactios,
                totalFrauds + statisticsReport.totalFrauds,
                totalAmount.add(statisticsReport.totalAmount));
    }
}
