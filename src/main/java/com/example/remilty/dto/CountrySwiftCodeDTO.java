package com.example.remilty.dto;

import lombok.*;

import java.util.List;


/**
 *     "countryISO2": string,
 *     "countryName": string,
 *     "swiftCodes": []
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CountrySwiftCodeDTO {
    private String countryISO2;
    private String countryName;
    private List<BranchDTO> swiftCodes;
}
