package com.ecommerce.orderservice.controller.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {
    private String zipcode;
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private int number;
    private String complement;
}
