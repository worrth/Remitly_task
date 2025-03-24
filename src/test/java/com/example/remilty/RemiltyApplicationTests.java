package com.example.remilty;

import com.example.remilty.dto.BankDTO;
import com.example.remilty.dto.CountrySwiftCodeDTO;

import com.example.remilty.dto.SwiftCodeDTO;
import com.example.remilty.exceptions.exception.CountryNotFoundException;
import com.example.remilty.exceptions.exception.SwiftCodeNotFoundException;
import com.example.remilty.mapper.SwiftCodeMapper;
import com.example.remilty.model.ResponseMessage;
import com.example.remilty.model.SwiftCode;
import com.example.remilty.repository.SwiftCodeRepository;
import com.example.remilty.service.SwiftCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RemiltyApplicationTests {

    @Mock
    private SwiftCodeRepository swiftCodeRepository;

    @InjectMocks
    private SwiftCodeService swiftCodeService;

    private SwiftCode headquarterSwiftCode;
    private SwiftCode branchSwiftCode;
    private SwiftCodeDTO swiftCodeDTO;

    @BeforeEach
    void setUp() {
        // Headquarter Swift Code
        headquarterSwiftCode = new SwiftCode();
        headquarterSwiftCode.setSwiftCode("CBOPPLP1XXX");
        headquarterSwiftCode.setHeadquarter(true);
        headquarterSwiftCode.setCountryISO2("PL");
        headquarterSwiftCode.setCountryName("Poland");

        // Branch Swift Code
        branchSwiftCode = new SwiftCode();
        branchSwiftCode.setSwiftCode("CBOPPLP1ABC");
        branchSwiftCode.setHeadquarter(false);
        branchSwiftCode.setCountryISO2("PL");
        branchSwiftCode.setCountryName("Poland");

        // Swift Code DTO
        swiftCodeDTO = new SwiftCodeDTO();
        swiftCodeDTO.setSwiftCode("CBOPPLP1XXX");
        swiftCodeDTO.setHeadquarter(true);
        swiftCodeDTO.setCountryISO2("PL");
        swiftCodeDTO.setCountryName("Poland");
    }


    @Test
    void shouldReturnHeadquarters() {
        when(swiftCodeRepository.getBySwiftCode("CBOPPLP1XXX")).thenReturn(Optional.of(headquarterSwiftCode));


        BankDTO result = swiftCodeService.getSwiftCode("CBOPPLP1XXX");
        assertNotNull(result);
        assertEquals("CBOPPLP1XXX", result.getSwiftCode());
        assertEquals("PL", result.getCountryISO2());
        assertTrue(result.isHeadquarter());
        verify(swiftCodeRepository).getBranches("CBOPPLP1");
    }

    @Test
    void shouldReturnBranch() {
        when(swiftCodeRepository.getBySwiftCode("CBOPPLP1ABC")).thenReturn(Optional.of(branchSwiftCode));

        BankDTO result = swiftCodeService.getSwiftCode("CBOPPLP1ABC");

        assertNotNull(result);
        assertEquals("CBOPPLP1ABC", result.getSwiftCode());
        assertFalse(result.isHeadquarter());
    }

    @Test
    void shouldThrowExceptionWhenSwiftCodeNotFound() {
        when(swiftCodeRepository.getBySwiftCode("INVALID")).thenReturn(Optional.empty());

        assertThrows(SwiftCodeNotFoundException.class, () -> swiftCodeService.getSwiftCode("INVALID"));
    }

    @Test
    void shouldReturnCountrySwiftCodes() {
        when(swiftCodeRepository.getByCountryISO2("PL")).thenReturn(List.of(headquarterSwiftCode, branchSwiftCode));

        CountrySwiftCodeDTO result = swiftCodeService.getCountrySwiftCodeDTO("PL");

        assertNotNull(result);
        assertEquals("Poland", result.getCountryName());
        assertEquals(2, result.getSwiftCodes().size());
    }

    @Test
    void shouldThrowExceptionWhenCountryNotFound() {
        when(swiftCodeRepository.getByCountryISO2("XX")).thenReturn(List.of());

        assertThrows(CountryNotFoundException.class, () -> swiftCodeService.getCountrySwiftCodeDTO("XX"));
    }

    @Test
    void shouldAddSwiftCode() {
        SwiftCode swiftCode = SwiftCodeMapper.mapSwiftCodeDTOToSwiftCode(swiftCodeDTO);
        when(swiftCodeRepository.save(any(SwiftCode.class))).thenReturn(swiftCode);

        ResponseMessage response = swiftCodeService.addSwiftCode(swiftCodeDTO);

        assertEquals("Swift Code Added", response.getMessage());
        assertEquals(201, response.getStatusCode());
        verify(swiftCodeRepository).save(any(SwiftCode.class));
    }

    @Test
    void shouldRemoveSwiftCode() {
        when(swiftCodeRepository.getBySwiftCode("CBOPPLP1XXX")).thenReturn(Optional.of(headquarterSwiftCode));
        doNothing().when(swiftCodeRepository).delete(headquarterSwiftCode);

        ResponseMessage response = swiftCodeService.removeSwiftCode("CBOPPLP1XXX");

        assertEquals("Swift Code Removed", response.getMessage());
        assertEquals(200, response.getStatusCode());
        verify(swiftCodeRepository).delete(headquarterSwiftCode);
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonExistingSwiftCode() {
        when(swiftCodeRepository.getBySwiftCode("INVALID")).thenReturn(Optional.empty());

        assertThrows(SwiftCodeNotFoundException.class, () -> swiftCodeService.removeSwiftCode("INVALID"));
    }
}
