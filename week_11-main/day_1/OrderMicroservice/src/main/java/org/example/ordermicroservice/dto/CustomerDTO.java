package org.example.ordermicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String name;
    private String email;
    private Integer lastFourDigitsOfCard;
    private Date dob;
}
