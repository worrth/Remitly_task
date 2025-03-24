package com.example.remilty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SwiftCodeDTO {
    private String swiftCode;
    private String bankName;
    private String address;
    private String countryISO2;
    private String countryName;
    @JsonProperty("isHeadquarter")
    private boolean isHeadquarter;
}