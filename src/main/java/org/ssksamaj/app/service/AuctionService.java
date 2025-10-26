package org.ssksamaj.app.service;

import java.sql.Date;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.ssksamaj.app.dto.AuctionDTO;
import org.ssksamaj.app.dto.ChitDTO;

@Service
public class AuctionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionService.class);

    private final ChitService chitService;

    public AuctionService(ChitService chitService) {
        this.chitService = chitService;
    }
    
    public AuctionDTO conductAuction(final Integer chitId, Integer auctionNumber) {
        ChitDTO chitDTO = chitService.getChitDetails(chitId);
        AuctionDTO auctionDTO = AuctionDTO.builder()
            .isClosed(false)
            .chitDTO(chitDTO)
            .auctionNumber(auctionNumber)
            .basicAuctionAmount(evaluateBasicAuctionAmount(chitDTO, auctionNumber))
            .auctionDate(Date.valueOf(LocalDate.now()))
            .build();
            evaluateAuction(auctionDTO, auctionDTO.getBasicAuctionAmount());
            return auctionDTO;
    }

    public void closeAuction(AuctionDTO auctionDTO, Double finalAuctionAmount) {
        auctionDTO.setFinalAuctionAmount(finalAuctionAmount);
        auctionDTO.setClosed(true);
        evaluateAuction(auctionDTO, finalAuctionAmount);
    }

    private Double evaluateBasicAuctionAmount(ChitDTO chitDTO, Integer auctionNumber) {
        Double principleAmount = chitDTO.getPrincipleAmount();
        Integer memberCount = chitDTO.getMemberCount();
        double sskCommission = (chitDTO.getSskCommissionRate() /100) * principleAmount;
        double memberCommission = ((chitDTO.getMemberComissionRate() / 100) * principleAmount) * ((memberCount + 1) - auctionNumber) ;
        return sskCommission + memberCommission;
    }

    private void evaluateAuction(final AuctionDTO auctionDTO, Double auctionAmount) {
        
        ChitDTO chitDTO = auctionDTO.getChitDTO();
        double sskCommission = (chitDTO.getSskCommissionRate() /100) * chitDTO.getPrincipleAmount();
        double netMemberComission = (auctionAmount - sskCommission) / chitDTO.getMemberCount();
        double installmentPayable = chitDTO.getMonthlyPayment() - netMemberComission;
        auctionDTO.setSskCommission(sskCommission);
        auctionDTO.setNetMemberComission(netMemberComission);
        auctionDTO.setInstallmentAmount(installmentPayable);
    }
}
