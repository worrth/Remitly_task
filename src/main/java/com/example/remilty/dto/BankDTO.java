package com.example.remilty.dto;

import lombok.Getter;

import java.util.List;


public interface BankDTO {
    String getSwiftCode();
    String getBankName();
    String getAddress();
    String getCountryISO2();
    String getCountryName();
    boolean isHeadquarter();
}
