package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionListRepositoryInterface {

    private final Map<String, Transaction> transactionByOriginName;

    public TransactionMapRepository(List<Transaction> transactions) {

        Objects.requireNonNull(transactions);

        transactionByOriginName = transactions.stream()
                .collect(Collectors.toMap(Transaction::nameOrig, Function.identity()));
    }

    @Override
    public Optional<Transaction> findByName(String name) {
        return Optional.ofNullable(transactionByOriginName.get(name));
    }
}
