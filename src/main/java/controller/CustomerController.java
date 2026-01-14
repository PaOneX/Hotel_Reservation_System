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
                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(resultSet.getString("customer_id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("phone"), resultSet.getString("address"), resultSet.getString("city"), resultSet.getString("registered_date"));

                customerInfoDTOS.add(customerInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerInfoDTOS;
    }

    @Override
    public void addCustomer() {

    }

    @Override
    public void updateCustomer() {

    }

    @Override
    public void deleteCustomer() {

    }
}
