package com.example.remilty.service;

import com.example.remilty.dto.BranchDTO;
import com.example.remilty.dto.CountrySwiftCodeDTO;
import com.example.remilty.dto.BankDTO;
import com.example.remilty.dto.SwiftCodeDTO;
import com.example.remilty.exceptions.exception.CountryNotFoundException;
import com.example.remilty.exceptions.exception.SwiftCodeNotFoundException;
import com.example.remilty.mapper.SwiftCodeMapper;
import com.example.remilty.model.ResponseMessage;
import com.example.remilty.model.SwiftCode;
import com.example.remilty.repository.SwiftCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SwiftCodeService {

    private final SwiftCodeRepository swiftCodeRepository;

    public boolean checkIfHeadquarters(String swiftCode){
        return swiftCode.endsWith("XXX");
    }


    public BankDTO getSwiftCode(String code) {
        SwiftCode swiftCode = swiftCodeRepository.getBySwiftCode(code)
                .orElseThrow(SwiftCodeNotFoundException::new);
        if(swiftCode.isHeadquarter()) {
            String branchCode = swiftCode.getSwiftCode().substring(0,8);
            List<SwiftCode> branches = swiftCodeRepository.getBranches(branchCode);
            List<BranchDTO> branchDTOS = branches.stream()
                    .map(SwiftCodeMapper::mapSwiftCodeToBranchDTO)
                    .toList();
            return SwiftCodeMapper.mapSwiftCodeToHeadquartersDTO(swiftCode, branchDTOS);
        }
        return SwiftCodeMapper.mapSwiftCodeToSwiftCodeBranchDTO(swiftCode);
    }

    public CountrySwiftCodeDTO getCountrySwiftCodeDTO(String countryISO2) {
        List<SwiftCode> swiftCodes = swiftCodeRepository.getByCountryISO2(countryISO2);
        if(swiftCodes.isEmpty()) throw new CountryNotFoundException();


        String countryName = swiftCodes.getFirst().getCountryName();

        List<BranchDTO> banks = swiftCodes.stream()
                .map(SwiftCodeMapper::mapSwiftCodeToBranchDTO)
                .toList();

        return new CountrySwiftCodeDTO(countryISO2,countryName,banks);
    }

    public ResponseMessage addSwiftCode(SwiftCodeDTO swiftDTO){
        SwiftCode swiftCode = SwiftCodeMapper.mapSwiftCodeDTOToSwiftCode(swiftDTO);
        if(checkIfHeadquarters(swiftCode.getSwiftCode()) && !swiftCode.isHeadquarter()) swiftCode.setHeadquarter(true);
        swiftCodeRepository.save(swiftCode);
        return new ResponseMessage("Swift Code Added",201);
    }


    public ResponseMessage removeSwiftCode(String code){
        SwiftCode swiftCode = swiftCodeRepository.getBySwiftCode(code)
                .orElseThrow(SwiftCodeNotFoundException::new);
        swiftCodeRepository.delete(swiftCode);
        return new ResponseMessage("Swift Code Removed",200);
    }
}
