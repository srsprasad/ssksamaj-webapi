package org.ssksamaj.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssksamaj.app.dto.AuctionDTO;
import org.ssksamaj.app.dto.ChitDTO;
import org.ssksamaj.app.service.AuctionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/auction/{chitId}")
public class AuctionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionController.class);

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/start/{auctionNumber}")
    public ResponseEntity<AuctionDTO> startAuction(@PathVariable Integer chitId, @PathVariable Integer auctionNumber) {
        ChitDTO chitDTO = getChitDTO();
        AuctionDTO auctionDTO = auctionService.conductAuction(chitDTO, auctionNumber);
        return ResponseEntity.ok(auctionDTO);
    }

    @GetMapping("/close/{auctionNumber}/{finalAuctionAmount}")
    public ResponseEntity<AuctionDTO> closeAuction(@PathVariable Integer chitId, @PathVariable Integer auctionNumber, @PathVariable Double finalAuctionAmount) {
        ChitDTO chitDTO = getChitDTO();
        AuctionDTO auctionDTO = auctionService.conductAuction(chitDTO, auctionNumber);
        auctionDTO.setFinalAuctionAmount(finalAuctionAmount);
        auctionService.closeAuction(auctionDTO, finalAuctionAmount);
        return ResponseEntity.ok(auctionDTO);
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
