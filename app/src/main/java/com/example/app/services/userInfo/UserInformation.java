package com.example.app.services.userInfo;

/**
 * Created by Claudiu on 29.08.2017.
 */

public class UserInformation {
    private String companyName;
    private String contactName;
    private String phoneNumber;
    private String description;
    private String servicies;
    private String country;

    public UserInformation(){

    }
    public UserInformation(String country, String services,String companyName, String contactName, String phoneNumber, String description){
        //this.mailContact = mailContact;
        this.country = country;
        this.servicies = services;
        this.companyName = companyName;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public String getServicies() {
        return servicies;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }
}

