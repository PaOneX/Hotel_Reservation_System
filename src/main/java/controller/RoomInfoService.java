package controller;

import javafx.collections.ObservableList;
import model.dto.RoomInfoDTO;

public interface RoomInfoService {

    ObservableList<RoomInfoDTO> getAllRooms();

    void addRoomDetails(String roomId, String type, double pricePerNight, int maxGuests, boolean availability, String description, int floor);

    void updateRoomDetails(String cmbType, double txtPricePerNight, int cmbMaxGuests, boolean checkAvailability, String txtdescription, int cmbFloor, String txtRoomId);

    void deleteRoomDetails(String txtRoomId);


}
