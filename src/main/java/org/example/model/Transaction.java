package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TransactionType type, BigDecimal amount, String nameOrig, BigDecimal oldBalanceOrg,
                          BigDecimal newBalanceOrig, String nameDest, BigDecimal oldBalanceDest,
                          BigDecimal newBalanceDest, boolean isFraud, boolean isFlaggedFraud) {

    public Transaction {

        Objects.requireNonNull(type, "type should not be empty");
        Objects.requireNonNull(amount, "amount should not be empty");
        Objects.requireNonNull(nameOrig, "nameOrig should not be empty");
        Objects.requireNonNull(oldBalanceOrg, "oldBalanceOrg should not be empty");
        Objects.requireNonNull(newBalanceOrig, "newBalanceOrig should not be empty");
        Objects.requireNonNull(nameDest, "nameDest should not be empty");
        Objects.requireNonNull(oldBalanceDest, "oldBalanceDest should not be empty");
        Objects.requireNonNull(newBalanceDest, "newBalanceDest should not be empty");

        if (step < 1) {
            throw new IllegalArgumentException("step should be positive: " + step);
        }

        if (nameOrig.isEmpty()) {
            throw new IllegalArgumentException("nameOrig should not be empty");
        }

        validateNonNegative(amount, "amount");
        validateNonNegative(oldBalanceOrg, "oldBalanceOrg");
        validateNonNegative(newBalanceOrig, "newBalanceOrig");
        validateNonNegative(oldBalanceDest, "oldBalanceDest");
        validateNonNegative(newBalanceDest, "newBalanceDest");
    }

    private static void validateNonNegative(BigDecimal value, String field) throws IllegalArgumentException {
        if (value.signum() < 0) {
            throw new IllegalArgumentException(field + " should be positive: " + value);
        }
    }
}

