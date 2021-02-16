package com.ecommerce.userservice.model;

import lombok.Data;

@Data
public class UserAddress {
    private String zipcode;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;
    private String complement;
}
