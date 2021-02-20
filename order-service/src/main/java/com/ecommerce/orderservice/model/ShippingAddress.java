package com.ecommerce.orderservice.model;

import lombok.Data;

@Data
public class ShippingAddress {
    private String zipcode;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;
    private String complement;
}
