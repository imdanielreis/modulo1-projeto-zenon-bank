package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;

import java.util.Optional;

public interface TransactionListRepositoryInterface {
    void save(Transaction transaction);
    Optional<Transaction> findByName(String name);
}
