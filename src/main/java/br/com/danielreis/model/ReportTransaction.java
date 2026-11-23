package br.com.danielreis.model;

import java.math.BigDecimal;

public record ReportTransaction(BigDecimal amount, boolean isFraud) {
}
