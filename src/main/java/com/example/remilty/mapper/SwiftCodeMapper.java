package com.example.remilty.mapper;

import com.example.remilty.dto.BranchDTO;
import com.example.remilty.dto.HeadquarterDTO;
import com.example.remilty.dto.SwiftCodeBranchDTO;
import com.example.remilty.dto.SwiftCodeDTO;
import com.example.remilty.model.SwiftCode;

import java.util.List;

public class SwiftCodeMapper {
    public static SwiftCode mapSwiftCodeDTOToSwiftCode(SwiftCodeDTO swiftCodeDTO) {
        SwiftCode swiftCode = new SwiftCode();
        swiftCode.setSwiftCode(swiftCodeDTO.getSwiftCode());
        swiftCode.setBankName(swiftCodeDTO.getBankName());
        swiftCode.setAddress(swiftCodeDTO.getAddress());
        swiftCode.setCountryISO2(swiftCodeDTO.getCountryISO2().toUpperCase());
        swiftCode.setCountryName(swiftCodeDTO.getCountryName().toUpperCase());
        swiftCode.setHeadquarter(swiftCodeDTO.isHeadquarter());
        return swiftCode;
    }

    public static BranchDTO mapSwiftCodeToBranchDTO(SwiftCode swiftCode) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setAddress(swiftCode.getAddress());
        branchDTO.setBankName(swiftCode.getBankName());
        branchDTO.setCountryISO2(swiftCode.getCountryISO2().toUpperCase());
        branchDTO.setHeadquarter(swiftCode.isHeadquarter());
        branchDTO.setSwiftCode(swiftCode.getSwiftCode());
        return branchDTO;
    }

    public static HeadquarterDTO mapSwiftCodeToHeadquartersDTO(SwiftCode swiftCode, List<BranchDTO> branches) {
        HeadquarterDTO headquarters = new HeadquarterDTO();
        headquarters.setAddress(swiftCode.getAddress());
        headquarters.setBankName(swiftCode.getBankName());
        headquarters.setCountryISO2(swiftCode.getCountryISO2().toUpperCase());
        headquarters.setCountryName(swiftCode.getCountryName().toUpperCase());
        headquarters.setHeadquarter(swiftCode.isHeadquarter());
        headquarters.setSwiftCode(swiftCode.getSwiftCode());
        headquarters.setBranches(branches);
        return headquarters;
    }

    public static SwiftCodeBranchDTO mapSwiftCodeToSwiftCodeBranchDTO(SwiftCode swiftCode) {
        SwiftCodeBranchDTO swiftCodeBranchDTO = new SwiftCodeBranchDTO();
        swiftCodeBranchDTO.setAddress(swiftCode.getAddress());
        swiftCodeBranchDTO.setBankName(swiftCode.getBankName());
        swiftCodeBranchDTO.setCountryISO2(swiftCode.getCountryISO2().toUpperCase());
        swiftCodeBranchDTO.setCountryName(swiftCode.getCountryName().toUpperCase());
        swiftCodeBranchDTO.setHeadquarter(swiftCode.isHeadquarter());
        swiftCodeBranchDTO.setSwiftCode(swiftCode.getSwiftCode());
        return swiftCodeBranchDTO;
    }

}
