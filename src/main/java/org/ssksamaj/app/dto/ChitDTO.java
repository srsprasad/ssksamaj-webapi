package org.ssksamaj.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChitDTO {
    
    private Integer number;
    private Double principleAmount;
    private Double monthlyPayment;
    private Integer durationInMonths;
    private Double sskCommissionRate;
    private Double memberComissionRate;
    private Integer memberCount;
}
