package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.CustomerInfoDTO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerService customerService = new CustomerController();
    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();
    Stage stage = new Stage();


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
        String cus_id = txtId.getText();
        String fname = txtFirstName.getText();
        String lname = txtLastName.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String city = txtCity.getText();
        String date = String.valueOf(txtDate.getValue());

        customerService.addCustomer(cus_id, fname, lname, email, mobile, city, date);
        loadAllCustomers();
        clearFields();
    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnReload(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String cus_id = txtId.getText();
        String fname = txtFirstName.getText();
        String lname = txtLastName.getText();
        String email = txtEmail.getText();
        String mobile = txtMobile.getText();
        String city = txtCity.getText();
        String date = String.valueOf(txtDate.getValue());

        customerService.updateCustomer(fname,lname,email,mobile,city,date,cus_id);
        loadAllCustomers();
        clearFields();
    }

    @FXML
    void btnBack(ActionEvent event) {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oldStage.close();
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCusId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("FName"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("LName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCty.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));

        tblCustomer.setItems(customerInfoDTOS);

        loadAllCustomers();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void loadAllCustomers() {
        customerInfoDTOS.clear();
        tblCustomer.setItems(customerService.getAllCustomers());
    }

    private void setSelectedValue(CustomerInfoDTO selectedValue) {
        if (selectedValue == null) {
            clearFields();
            return;
        }
        txtId.setText(selectedValue.getId());
        txtFirstName.setText(selectedValue.getFName());
        txtLastName.setText(selectedValue.getLName());
        txtEmail.setText(selectedValue.getEmail());
        txtCity.setText(selectedValue.getCity());
        txtDate.setValue(LocalDate.parse(selectedValue.getDate()));
        txtMobile.setText(selectedValue.getMobile());

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
