package com.bookify.enitity;

import jakarta.persistence.Embeddable;

@Embeddable
public class ShippingAddress {
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode; // Postal code attribute

    private String country;

}
