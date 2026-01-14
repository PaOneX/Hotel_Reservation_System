package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoDTO {
    private String id;
    private String FName;
    private String LName;
    private String email;
    private String city;
    private Date date;
    private String mobile;

    public CustomerInfoDTO(String customerId, String firstName, String lastName, String email, String phone, String address, String city, String registeredDate) {
    }
}

