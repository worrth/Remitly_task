package com.example.remilty.controller;


import com.example.remilty.dto.BankDTO;
import com.example.remilty.dto.CountrySwiftCodeDTO;
import com.example.remilty.dto.SwiftCodeDTO;
import com.example.remilty.model.ResponseMessage;
import com.example.remilty.parser.SwiftCodeParser;
import com.example.remilty.service.SwiftCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/swift-codes")
@RequiredArgsConstructor
public class SwiftCodeController {

    private final SwiftCodeService swiftCodeService;


    @GetMapping("/{swift-code}")
    public ResponseEntity<BankDTO> getSwiftCode(@PathVariable("swift-code") String swiftCode) {
        return new ResponseEntity<>(swiftCodeService.getSwiftCode(swiftCode), HttpStatus.OK);
    }

    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<CountrySwiftCodeDTO> getSwiftCodesByCountry(@PathVariable("countryISO2code") String countryISO2){
        return new ResponseEntity<>(swiftCodeService.getCountrySwiftCodeDTO(countryISO2.toUpperCase()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addSwiftCode(@RequestBody SwiftCodeDTO swiftCodeDTO) {

        return new ResponseEntity<>(swiftCodeService.addSwiftCode(swiftCodeDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("{swift-code}")
    public ResponseEntity<ResponseMessage> removeSwiftCode(@PathVariable("swift-code") String swiftCode){
        return new ResponseEntity<>(swiftCodeService.removeSwiftCode(swiftCode),HttpStatus.OK);
    }
}
