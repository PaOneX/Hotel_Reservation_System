package model.dto;

import lombok.*;

@Setter
@Getter
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
