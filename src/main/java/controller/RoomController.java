package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.RoomInfoDTO;

import java.sql.*;

public class RoomController implements RoomInfoService{

    @Override
    public ObservableList<RoomInfoDTO> getAllRooms() {

        ObservableList<RoomInfoDTO> roomInfoDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rooms");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoomInfoDTO roomInfoDTO = new RoomInfoDTO(
                        resultSet.getString("room_id"),
                        resultSet.getString("type"),
                        resultSet.getDouble("price_per_night"),
                        resultSet.getInt("max_guests"),
                        resultSet.getBoolean("availability"),
                        resultSet.getString("description"),
                        resultSet.getInt("floor")

                );
                roomInfoDTOS.add(roomInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roomInfoDTOS;
    }
@Override
    public void addRoomDetails(String roomId, String type, double pricePerNight, int maxGuests, boolean availability, String description, int floor) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO rooms VALUES (?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, roomId);
            preparedStatement.setObject(2, type);
            preparedStatement.setObject(3, pricePerNight);
            preparedStatement.setObject(4, maxGuests);
            preparedStatement.setObject(5, availability);
            preparedStatement.setObject(6, description);
            preparedStatement.setObject(7, floor);

            preparedStatement.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public void updateRoomDetails(String cmbType,  double txtPricePerNight, int cmbMaxGuests,boolean checkAvailability, String txtdescription, int cmbFloor, String txtRoomId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE rooms SET type = ?, price_per_night = ?, max_guests = ?, availability = ?, description = ?, floor = ? WHERE room_id = ?");

            preparedStatement.setObject(1, cmbType);
            preparedStatement.setObject(2, txtPricePerNight);
            preparedStatement.setObject(3, cmbMaxGuests);
            preparedStatement.setObject(4, checkAvailability);
            preparedStatement.setObject(5, txtdescription);
            preparedStatement.setObject(6, cmbFloor);
            preparedStatement.setObject(7, txtRoomId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
@Override
    public void deleteRoomDetails(String txtRoomId) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM rooms WHERE room_id = ? ");

            preparedStatement.setObject(1,txtRoomId);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

