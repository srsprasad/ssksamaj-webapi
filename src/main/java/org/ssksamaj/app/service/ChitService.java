package org.ssksamaj.app.service;

import org.springframework.stereotype.Service;
import org.ssksamaj.app.dto.ChitDTO;

@Service
public class ChitService {
    
    public ChitDTO getChitDetails(Integer chitId) {
        // Implementation to fetch chit details
        return getChitDTO();
    }
    private ChitDTO getChitDTO() {
        return ChitDTO.builder()
                .number(1)
                .principleAmount(100000.0)
                .monthlyPayment(5000.0)
                .durationInMonths(20)
                .sskCommissionRate(2.5)
                .memberComissionRate(0.5)
                .memberCount(20)
                .build();
    }
    
}
