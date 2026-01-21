package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

import java.util.Date;

public interface CustomerService {

    ObservableList<CustomerInfoDTO> getAllCustomers();
    void addCustomer(String id, String firstName, String lastName, String email, String mobile, String address, String cty, String date);
    void updateCustomer();
    void deleteCustomer();
}
