package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.RoomInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomInfoFormController implements Initializable {

    RoomInfoService roomInfoService = new RoomController();

    ObservableList<RoomInfoDTO>roomInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Integer> cmbFloor;

    @FXML
    private ComboBox<Integer> cmbMaxGuests;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFloor;

    @FXML
    private TableColumn<?, ?> colMaxGuests;

    @FXML
    private TableColumn<?, ?> colPricePerNight;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private RadioButton radioAvailable;

    @FXML
    private RadioButton radioUnavailabile;

    @FXML
    private TableView<RoomInfoDTO> tblRoomInfo;

    @FXML
    private TextField txtPricePerNight;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtdescription;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPricePerNight.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        colMaxGuests.setCellValueFactory(new PropertyValueFactory<>("maxGuests"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colFloor.setCellValueFactory(new PropertyValueFactory<>("floor"));

        tblRoomInfo.setItems(roomInfoDTOS);

        loadAllRooms();

        //combo box data load
        ObservableList<String> types = FXCollections.observableArrayList("Single","Double","Suite","Deluxe");
        cmbType.setItems(types);

        ObservableList<Integer> maxGuests = FXCollections.observableArrayList(1,2,3,4,5);
        cmbMaxGuests.setItems(maxGuests);

        ObservableList<Integer> floors = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
        cmbFloor.setItems(floors);

        //radio button action control
        ToggleGroup toggleGroup = new ToggleGroup();
        radioAvailable.setToggleGroup(toggleGroup);
        radioUnavailabile.setToggleGroup(toggleGroup);

        //set selected row data to the fields
        tblRoomInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String roomId = txtRoomId.getText();
        String type = cmbType.getValue();
        double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        int maxGuests = cmbMaxGuests.getValue();
        boolean availability = checkAvailability();
        String description = txtdescription.getText();
        int floor = cmbFloor.getValue();

        roomInfoService.addRoomDetails(roomId, type, pricePerNight, maxGuests, availability, description, floor);
        loadAllRooms();
        clearFields();


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {


        roomInfoService.deleteRoomDetails(txtRoomId.getText());

        loadAllRooms();
        clearFields();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadAllRooms();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String roomId = txtRoomId.getText();
        String type = cmbType.getValue();
        double pricePerNight = Double.parseDouble(txtPricePerNight.getText());
        int maxGuests = cmbMaxGuests.getValue();
        boolean availability = checkAvailability();
        String description = txtdescription.getText();
        int floor = cmbFloor.getValue();

        roomInfoService.updateRoomDetails(type, pricePerNight, maxGuests, availability, description,floor,roomId);
        loadAllRooms();
        clearFields();

    }

    //load all data to table
    private void loadAllRooms(){
        roomInfoDTOS.clear();
        tblRoomInfo.setItems(roomInfoService.getAllRooms());



    }
    //check availability
    private boolean checkAvailability(){
        if(radioAvailable.isSelected()){
            return true;
        }else{
            return false;
        }
    }
    //clear all fields
    private void clearFields(){
        txtRoomId.clear();
        cmbType.setValue(null);
        txtPricePerNight.clear();
        cmbMaxGuests.setValue(null);
        radioAvailable.setSelected(false);
        radioUnavailabile.setSelected(false);
        txtdescription.clear();
        cmbFloor.setValue(null);
    }
    //set selected row data to the fields
    private void setSelectedValue(RoomInfoDTO selectedValue) {
        if(selectedValue == null){
            clearFields();
            return;
        }
        txtRoomId.setText(selectedValue.getRoomId());
        cmbType.setValue(selectedValue.getType());
        txtPricePerNight.setText(String.valueOf(selectedValue.getPricePerNight()));
        cmbMaxGuests.setValue(selectedValue.getMaxGuests());
        if (selectedValue.isAvailability()){
            radioAvailable.setSelected(true);
        }else{
            radioUnavailabile.setSelected(true);
        }
        txtdescription.setText(selectedValue.getDescription());
        cmbFloor.setValue(selectedValue.getFloor());
    }
}
