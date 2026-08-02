package com.project.studentManagement.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String type;
    private String street;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}
