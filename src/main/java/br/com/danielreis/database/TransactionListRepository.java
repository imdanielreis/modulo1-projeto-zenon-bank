package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionListRepositoryInterface {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public Optional<Transaction> findByName(String name) {
        return transactions.stream()
                .filter(transaction -> transaction.nameOrig().equals(name))
                .findFirst();
    }
}
