package org.ssksamaj.app.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionDTO {

    private Integer id;
    private Integer auctionNumber;
    private ChitDTO chitDTO;
    private Double basicAuctionAmount;
    private Double finalAuctionAmount;
    private Double sskCommission;
    private Double netMemberComission;
    private Double installmentAmount;
    private boolean isClosed;
    private Date auctionDate;
}
