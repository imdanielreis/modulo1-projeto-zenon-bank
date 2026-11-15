package org.example;

import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        List<Transaction> transactions = new ArrayList<>();

        Transaction transasctionOne = new Transaction(
                                                1,
                                                TransactionType.PAYMENT,
                                                new BigDecimal("9839.64"),
                                                "C1231006815",
                                                new BigDecimal("170136.0"),
                                                new BigDecimal("160296.36"),
                                                "M1979787155",
                                                new BigDecimal("0.0"),
                                                new BigDecimal("0.0"),
                                                false
                                                , false
                                        );

        Transaction transasctionTwo = new Transaction(
                743,
                TransactionType.CASH_OUT,
                new BigDecimal("850002.52"),
                "C1280323807",
                new BigDecimal("850002.52"),
                new BigDecimal("0.0"),
                "C873221189",
                new BigDecimal("6510099.11"),
                new BigDecimal("7360101.63"),
                true
                , false
        );

        transactions.add(transasctionOne);
        transactions.add(transasctionTwo);

        transactions.forEach(System.out::println);
    }
}
