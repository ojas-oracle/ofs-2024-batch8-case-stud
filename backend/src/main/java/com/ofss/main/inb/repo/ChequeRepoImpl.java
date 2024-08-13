package com.ofss.main.inb.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.ofss.main.inb.domain.Cheque;

public class ChequeRepoImpl implements ChequeRepo{

    TransactionRepo transactionRepo = new TransactionRepoImpl();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private final String UPDATE_CHEQUE = "UPDATE Cheque SET cheque_status=? WHERE cheque_id = ?";
    private final String GET_ALL_PENDING = "SELECT * FROM CHEQUE WHERE cheque_status='issued'";
    private final String CREATE_ONE_CHEQUE = "INSERT INTO Cheque (cheque_status , payee_account_id , payer_account_id , cheque_amount , cheque_date) VALUES (? , ? , ? , ? , ? )";
    private ResultSet resultset = null;

    @Override
    public boolean issueNewCheck(double amount, int from, int to) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(CREATE_ONE_CHEQUE);
            preparedStatement.setString(1, "issued");
            preparedStatement.setInt(2, to);
            preparedStatement.setInt(3, from);
            preparedStatement.setDouble(4, amount);
            preparedStatement.setTimestamp(5, Timestamp.from(Instant.now()));            

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

    @Override
    public boolean updateCheckByID(Cheque cheque) {
        int cheque_id = cheque.getId();
        boolean success = transactionRepo.createTransaction(cheque.getAmount(), cheque.getPayer_acc(), cheque.getPayer_acc());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
        preparedStatement = connection.prepareStatement(UPDATE_CHEQUE);
        preparedStatement.setInt(2, cheque_id);
        preparedStatement.setString(1, success ? "cleared" : "bounced");
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            return true;
          }else{
              return false;
          }
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Cheque> getAllPendingChecks() {
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
               // System.out.println("Connected to db.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_PENDING);
            resultset = preparedStatement.executeQuery();
            List<Cheque> cheques = new ArrayList<Cheque>();
            while (resultset.next()){
                int id = resultset.getInt("cheque_id");
                double amt = resultset.getDouble("cheque_amount");
                int from_id = resultset.getInt("payer_account_id");
                int to_id = resultset.getInt("payee_account_id");
                Cheque cheque = new Cheque();
                cheque.setAmount(amt);
                cheque.setId(id);
                cheque.setPayer_acc(from_id);
                cheque.setPayee_acc(to_id);
                cheques.add(cheque);
            }
            return cheques;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        }
    }
    
}
