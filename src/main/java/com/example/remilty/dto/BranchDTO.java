package com.example.remilty.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * "address": string,
 * "bankName": string,
 * "countryISO2": string,
 * "isHeadquarter": bool,
 * "swiftCode": string
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    @JsonProperty("isHeadquarter")
    private boolean isHeadquarter;
    private String swiftCode;
}
