package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerInfoDTO;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerService customerService = new CustomerController();
    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCty;

    @FXML
    private TableColumn<?, ?> colCusId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFname;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colLname;

    @FXML
    private TableView<CustomerInfoDTO> tblCustomer;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtMobile;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnReload(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("cusId"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("FName"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("LName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCty.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        tblCustomer.setItems(customerInfoDTOS);

        loadAllRooms();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {;
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void loadAllRooms(){
        customerInfoDTOS.clear();
        tblCustomer.setItems(customerService.getAllCustomers());
    }

    private void setSelectedValue(CustomerInfoDTO selectedValue) {
        if(selectedValue == null){
            clearFields();
            return;
        }
        colCusId.setText(selectedValue.getId());
        colFname.setText(selectedValue.getFName());
        colLname.setText(selectedValue.getLName());
        colEmail.setText(selectedValue.getEmail());
        colCty.setText(selectedValue.getCity());
        colDate.setText(String.valueOf(selectedValue.getDate()));
        colMobile.setText(selectedValue.getMobile());

    }

    private void clearFields() {
        txtId.clear();
        txtCity.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtDate.cancelEdit();
    }
}
