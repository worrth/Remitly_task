package com.example.remilty.parser;


import au.com.bytecode.opencsv.CSVReader;
import com.example.remilty.model.SwiftCode;
import com.example.remilty.repository.SwiftCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SwiftCodeParser {

    private final SwiftCodeRepository swiftCodeRepository;

    public void parseSwiftCodes(String filePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            csvReader.readNext();
            List<String[]> rows = csvReader.readAll();
            for (String[] row : rows) {
                String code = row[1] ;
                String bankName = row[3];
                String address = row[4];
                String countryISO2 = row[0];
                String countryName = row[6];
                boolean isHeadquarter = code.endsWith("XXX");
                SwiftCode swiftCode = new SwiftCode(code, bankName, address, countryISO2, countryName, isHeadquarter);
                if(!swiftCodeRepository.existsById(code)) {
                    swiftCodeRepository.save(swiftCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
