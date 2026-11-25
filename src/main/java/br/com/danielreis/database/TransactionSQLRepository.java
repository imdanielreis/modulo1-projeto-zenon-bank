package br.com.danielreis.database;

import br.com.danielreis.model.Transaction;
import br.com.danielreis.model.TransactionType;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionListRepositoryInterface {

    @Override
    public Optional<Transaction> findByName(String name) {

        String sql = """
                SELECT id, step, `type`, amount, 
                       name_orig, old_balance_org,
                       new_balance_orig, name_dest,
                       old_balance_dest, new_balance_dest, 
                       is_fraud, is_flagged_fraud 
                FROM zenon_frauds.transactions
                WHERE name_orig = ?
                LIMIT 1
                """;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);

            try(ResultSet resultSet = ps.executeQuery()) {

                if(resultSet.next()) {
                    return mapResultToTransaction(resultSet);
                }else{
                    return Optional.empty();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("erro na query get transactions " + e);
        }
    }

    private Optional<Transaction> mapResultToTransaction(ResultSet rs) {

        try {
            int step = rs.getInt("step");
            TransactionType type = TransactionType.valueOf(rs.getString("type"));
            BigDecimal amount = rs.getBigDecimal("amount");
            String nameOrig = rs.getString("name_orig");
            BigDecimal oldBalanceOrg = rs.getBigDecimal("old_balance_org");
            BigDecimal newBalanceOrig = rs.getBigDecimal("new_balance_orig");
            String nameDest = rs.getString("name_dest");
            BigDecimal oldBalanceDest = rs.getBigDecimal("old_balance_dest");
            BigDecimal newBalanceDest = rs.getBigDecimal("new_balance_dest");
            boolean isFraud = rs.getBoolean("is_fraud");
            boolean isFlaggedFraud = rs.getBoolean("is_flagged_fraud");

            return Optional.of(new Transaction(step, type, amount, nameOrig, oldBalanceOrg, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Transaction transaction) {

        String sql = """
                insert into zenon_frauds.transactions (step, type, amount, name_orig, 
                                                       old_balance_org, new_balance_orig,name_dest,
                                                       old_balance_dest, new_balance_dest, is_fraud, 
                                                       is_flagged_fraud) 
                values (?,?,?,?,?,?,?,?,?,?,?)
                """;

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaction.step());
            ps.setString(2, transaction.type().toString());
            ps.setBigDecimal(3, transaction.amount());
            ps.setString(4, transaction.nameOrig());
            ps.setBigDecimal(5, transaction.oldBalanceOrg());
            ps.setBigDecimal(6, transaction.newBalanceOrig());
            ps.setString(7, transaction.nameDest());
            ps.setBigDecimal(8, transaction.oldBalanceDest());
            ps.setBigDecimal(9, transaction.newBalanceDest());
            ps.setBoolean(10, transaction.isFraud());
            ps.setBoolean(11, transaction.isFlaggedFraud());

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("erro na query insert transactions " + e);
        }
    }
}
