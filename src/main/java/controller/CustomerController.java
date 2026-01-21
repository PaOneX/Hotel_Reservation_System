package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerController implements CustomerService {
    @Override
    public ObservableList<CustomerInfoDTO> getAllCustomers() {
        ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customers");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(
                        resultSet.getString("customer_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("city"),
                        resultSet.getString("registered_date"));

                customerInfoDTOS.add(customerInfoDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerInfoDTOS;
    }

    @Override
    public void addCustomer(String id, String firstName, String lastName, String email, String mobile, String cty, String date) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers VALUES (?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2,firstName);
            preparedStatement.setObject(3, lastName);
            preparedStatement.setObject(4, email);
            preparedStatement.setObject(5, mobile);
            preparedStatement.setObject(6, cty);
            preparedStatement.setObject(7, date);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String firstName, String lastName, String email, String mobile, String city, String date,String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customers SET first_name= ? , last_name = ?, email = ? , phone = ?, city = ? ,registered_date = ? WHERE customer_id = ?");

            preparedStatement.setObject(1,firstName);
            preparedStatement.setObject(2, lastName);
            preparedStatement.setObject(3, email);
            preparedStatement.setObject(4, mobile);
            preparedStatement.setObject(5, city);
            preparedStatement.setObject(6, date);
            preparedStatement.setObject(7, id);
            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {

    }



}
