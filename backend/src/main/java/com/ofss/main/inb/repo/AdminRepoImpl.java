package com.ofss.main.inb.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepoImpl implements AdminRepo {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultset = null;
    private final String GET_ONE_ADMIN = "SELECT * FROM Admin WHERE login_id = ?";
    private final String UPDATE_LOGIN_ATTEMPTS = "UPDATE Customer SET login_attempts=0 , customer_status='active' WHERE customer_id = ?";

    @Override
    public boolean login(String loginID, String pwd) {
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(GET_ONE_ADMIN);
            preparedStatement.setString(1, loginID);
            resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                String pass = resultset.getString("login_pass");
                if(pass.equals(pwd)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            return false;
        }
    }

    @Override
    public String unblockCustomer(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
            if(connection!=null){
                //System.out.println("Connected to db.");
            }
            preparedStatement = connection.prepareStatement(UPDATE_LOGIN_ATTEMPTS);
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
              return "Unblocked Customer";
            }else{
                return "Customer does not exist";
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            System.out.println(e.getLocalizedMessage());
            return "Error";
        }
    }
    
}
