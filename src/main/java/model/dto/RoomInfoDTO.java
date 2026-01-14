package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomInfoDTO {

    private String roomId;

    private String type;

    private double pricePerNight;

    private int maxGuests;

    private boolean availability;

    private String description;

    private int floor;


}
