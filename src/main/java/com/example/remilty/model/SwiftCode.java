package com.example.remilty.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "swift-code")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SwiftCode {


    @Id
    private String swiftCode;
    private String bankName;
    private String address;
    private String countryISO2;
    private String countryName;
    private boolean isHeadquarter;
}
