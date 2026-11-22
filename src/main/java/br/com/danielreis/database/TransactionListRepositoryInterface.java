package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;

import java.util.Optional;

public interface TransactionListRepositoryInterface {
    Optional<Transaction> findByName(String name);
}
