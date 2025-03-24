package com.example.remilty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

/**
 *     "address": string,
 *     "bankName": string,
 *     "countryISO2": string,
 *     "countryName": string,
 *     "isHeadquarter": bool,
 *     "swiftCode": string
 *     “branches”: []
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonPropertyOrder({"address", "bankName", "countryISO2", "countryName", "isHeadquarter", "swiftCode", "branches"})
public class HeadquarterDTO implements BankDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    @JsonProperty("isHeadquarter")
    private boolean isHeadquarter;
    private String swiftCode;
    private List<BranchDTO> branches;
}
