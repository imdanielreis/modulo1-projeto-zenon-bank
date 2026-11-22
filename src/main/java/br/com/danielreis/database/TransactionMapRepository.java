package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionMapRepository implements TransactionListRepositoryInterface {

    Map<String, Transaction> transactionMap = new HashMap<>();

    public TransactionMapRepository(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transactionMap.putIfAbsent(transaction.nameOrig(), transaction);
        }
    }

    @Override
    public Optional<Transaction> findByName(String name) {
        return Optional.ofNullable(transactionMap.get(name));
    }
}
