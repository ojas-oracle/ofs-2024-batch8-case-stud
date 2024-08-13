// package com.ofss.main.inb.repo;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// import org.springframework.stereotype.Repository;

// import com.ofss.main.inb.domain.Customer;

// @Repository
// public class CustomerRepoImpl implements CustomerRepo {

//     // private Connection connection = null;
//     // private PreparedStatement preparedStatement = null;
//     // private ResultSet resultset = null;
//     // private final String GET_ONE_CUSTOMER = "SELECT * FROM Customer WHERE customer_login_id = ?";
//     // private final String INSERT_ONE_CUSTOMER = "INSERT INTO Customer (customer_name , customer_email , customer_phone , customer_address , customer_state , customer_country , customer_password , customer_login_id) VALUES (? , ? , ? , ? , ? , ? , ? , ?)";
//     // private final String UPDATE_LOGIN_ATTEMPTS = "UPDATE Customer SET login_attempts=? , customer_status=? WHERE customer_login_id = ?";

//     @Override
//     public int register(Customer c) {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
//             if(connection!=null){
//                 //System.out.println("Connected to db.");
//             }
//             preparedStatement = connection.prepareStatement(INSERT_ONE_CUSTOMER , Statement.RETURN_GENERATED_KEYS);
//             preparedStatement.setString(1, c.getName());
//             preparedStatement.setString(2, c.getEmail());
//             preparedStatement.setString(3, c.getPhone());
//             preparedStatement.setString(4, c.getAddress());
//             preparedStatement.setString(5, c.getState());
//             preparedStatement.setString(6, c.getCountry());
//             preparedStatement.setString(7, c.getPassword());
//             preparedStatement.setString(8, c.getLogin_id());

//             int res = preparedStatement.executeUpdate();
//             if(res>=1){
//                  ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                 if (generatedKeys.next()) {
//                     return generatedKeys.getInt(1);
//                 }
//                 else {
//                     return -1;
//                 }
//             }
//             return -1;
            
//         } catch (ClassNotFoundException | SQLException e) {
//             System.out.println(e.toString());
//             return -1;
//         }
        
//     }

//     @Override
//     public int login(String loginID, String pwd) {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inb", "root", "root");
//             if(connection!=null){
//                 //System.out.println("Connected to db.");
//             }
//             preparedStatement = connection.prepareStatement(GET_ONE_CUSTOMER);
//             preparedStatement.setString(1, loginID);
//             resultset = preparedStatement.executeQuery();
//             //String result = null;
//             int attempts;
//             String status;
//             if(resultset.next()){
//                 String pass = resultset.getString("customer_password");
//                 attempts = resultset.getInt("login_attempts");
//                 status = resultset.getString("customer_status");
//                 if(pass.equals(pwd)){
//                     if(status.equals("inactive")){
//                         System.out.println("Account Locked. Please ask admin to unlock");
//                         return -1;
//                     }
//                     System.out.println("Login Successful !");
//                     return resultset.getInt("customer_id");
//                 }else{
//                     attempts+=1;
//                     if(attempts==3){
//                         status = "inactive";
//                     }
//                     preparedStatement = connection.prepareStatement(UPDATE_LOGIN_ATTEMPTS);
//                     preparedStatement.setInt(1, attempts);
//                     preparedStatement.setString(2, status);
//                     preparedStatement.setString(3, loginID);
//                     preparedStatement.execute();
//                     System.out.println( "Password Incorrect" );
//                     return -1;
//                 }
//             }else{
//                 //result = "Customer does not exist.";
//                 return -1;
//             }
//         } catch (ClassNotFoundException | SQLException e) {
//             return -1;
//         }
//     }
    
// }
