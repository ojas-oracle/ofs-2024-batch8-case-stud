package com.ofss.main.inb.repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import com.ofss.main.inb.domain.Account;

public class TransactionRepoImpl implements TransactionRepo{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private AccountRepo accountRepo = new AccountRepoImpl();
    private final String CREATE_ONE_TXN = "INSERT INTO Transaction (payee_account_id , payer_account_id , transaction_amount , transaction_status , transaction_remarks , transaction_created_at , transaction_completed_at , transfer_type) VALUES (? , ? , ? , ? , ? , ? , ? , ?)";


    @Override
    public boolean createTransaction(double amount, int fromAcc , int toAcc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            Account from = accountRepo.geAccountByID(fromAcc);
            Account to = accountRepo.geAccountByID(toAcc);

            if(from==null || to==null){
                System.out.println("Invalid Acc Number");
                return false;
            }

            if(from.getBalance() < amount){
                System.out.println("Insufficient Balance");
                return false;
            }

            from.withdraw(amount);
            to.deposit(amount);

            accountRepo.updateAccBalance(from);
            accountRepo.updateAccBalance(to);

            preparedStatement = connection.prepareStatement(CREATE_ONE_TXN);
            preparedStatement.setInt(1, fromAcc);
            preparedStatement.setInt(2, toAcc);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, "completed");
            preparedStatement.setString(5, "");
            preparedStatement.setTimestamp(6, Timestamp.from(Instant.now()));
            preparedStatement.setTimestamp(7, Timestamp.from(Instant.now()));            
            preparedStatement.setString(8, "NEFT");

            int res = preparedStatement.executeUpdate();
            if(res>=1){
                return true;
            }
            return false;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    
}
