package org.example.database;

import org.example.model.Transaction;

import java.util.Optional;

public interface TransactionListRepositoryInterface {
    Optional<Transaction> findByName(String name);
}
