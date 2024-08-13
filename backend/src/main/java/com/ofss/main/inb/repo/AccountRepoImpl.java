package com.ofss.main.inb.repo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.ofss.main.inb.domain.Account;

public class AccountRepoImpl implements AccountRepo {

    
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private final String ACTIVATE_ACCOUNT = "UPDATE Account SET account_status='active' WHERE account_id = ?";
    private final String CREATE_ONE_ACCOUNT = "INSERT INTO Account (customer_id , account_type , account_rate , account_balance , account_minimum_balance , account_status , withdrawal_limit) VALUES (? , ? , ? , ? , ? , ? , ?)";
   // private final String GET_SAVINGS_LIST = "SELECT * FROM ACCOUNT WHERE customer_id = ? AND account_type='savings'";
   // private final String GET_CURRENT_LIST = "SELECT * FROM ACCOUNT WHERE customer_id = ? AND account_type='savings'";
    private final String GET_INACTIVE_LIST = "SELECT * FROM ACCOUNT WHERE account_status='inactive'";
    private final String GET_ACCOUNT_BY_ID = "SELECT * FROM ACCOUNT WHERE account_id=?";
    private final String UPDATE_ACCOUNT_BALANCE = "UPDATE Account SET account_balance = ? WHERE account_id=?";
    private ResultSet resultset = null;

    @Override
    public boolean createAccount(String type , int customerId) {
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(CREATE_ONE_ACCOUNT);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setString(2, type);
            preparedStatement.setDouble(3, type.equals("savings") ? 4.5 : 0);
            preparedStatement.setDouble(4, 0);
            preparedStatement.setDouble(5, type.equals("savings") ? 1000 : 0);
            preparedStatement.setString(6, "inactive");
            preparedStatement.setDouble(7, type.equals("savings") ? 0 : 10000);;

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


    // @Override
    // public List<Savings> getSavings(int customerId) {
    //     throw new UnsupportedOperationException("Unimplemented method 'getCurrent'");
    //    try {
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
    //         if(connection!=null){
    //             System.out.println("Connected to db.");
    //         }
    //     } catch (ClassNotFoundException | SQLException e) {
    //         e.printStackTrace();
    //     }
    //     try {
    //         preparedStatement = connection.prepareStatement(GET_SAVINGS_LIST);
    //         resultset = preparedStatement.executeQuery();
    //         List<Savings> employees = new ArrayList<Savings>();
    //         while (resultset.next()){
    //             int id = resultset.getInt("account_id");
    //             String type = "savings";
    //             double rate = resultset.getDouble("account_rate");
    //             double bal = resultset.getDouble("account_balance");
    //             double min_bal = resultset.getDouble("account_minimum_balance");
    //             String status =  resultset.getString("");
    //             double with
    //             Employee employee = new Employee(id, fname, lname, salary);
    //             employees.add(employee);
    //         }
    //         return employees;
    //     } catch (SQLException e) {
    //         System.err.println(e.toString());
    //         return null;
    //     }
    //}


    // @Override
    // public List<Current> getCurrent(int customerId) {
    //     throw new UnsupportedOperationException("Unimplemented method 'getCurrent'");
    // }


    @Override
    public List<Account> getPendingAccounts() {
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
            preparedStatement = connection.prepareStatement(GET_INACTIVE_LIST);
            resultset = preparedStatement.executeQuery();
            List<Account> accounts = new ArrayList<Account>();
            while (resultset.next()){
                int id = resultset.getInt("account_id");
                //String type = "savings";
                //double rate = resultset.getDouble("account_rate");
                double bal = resultset.getDouble("account_balance");
                //double min_bal = resultset.getDouble("account_minimum_balance");
                String status =  resultset.getString("account_status");
                //double withdrawal_limit = resultset.getDouble("withdrawal_limit");
                Account account = new Account(id , "" , bal);
                account.setStatus(status);
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        }
    }


    @Override
    public int activateAccount(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(ACTIVATE_ACCOUNT);
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                System.out.println("Activated Account with acc number " + id);
              return 1;
            }else{
                return -1;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            System.out.println(e.getLocalizedMessage());
            return -1;
        }
    }


    @Override
    public Account geAccountByID(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_ID);
            preparedStatement.setInt(1, id);
            resultset = preparedStatement.executeQuery();
            Account acc = null;
            if(resultset.next()){
                int accId = resultset.getInt("account_id");
                //String type = resultset.getString("account_type");
                double balance = resultset.getDouble("account_balance");
                acc = new Account(accId, "", balance);
                System.out.println(acc.getBalance());
            }
            return acc;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }


    @Override
    public boolean updateAccBalance(Account account) {
        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
                if(connection!=null){
                    //System.out.println("Connected to db.");
                }
                preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_BALANCE);
                preparedStatement.setDouble(1, account.getBalance());
                preparedStatement.setInt(2, account.getAccountNumber());
                int count = preparedStatement.executeUpdate();
                if(count > 0){
                    //System.out.println("Activated Account with acc number " + id);
                  return true;
                }else{
                    return false;
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.toString());
                System.out.println(e.getLocalizedMessage());
                return false;
            }
    }

    
}
