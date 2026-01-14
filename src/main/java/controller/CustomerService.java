package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerInfoDTO;

public interface CustomerService {

    ObservableList<CustomerInfoDTO> getAllCustomers();
    void addCustomer();
    void updateCustomer();
    void deleteCustomer();
}
