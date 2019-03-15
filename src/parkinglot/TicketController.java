package parkinglot;

import java.math.BigDecimal;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

public class TicketController {

    public BigDecimal getTotalCost(BigDecimal costFactor, BigDecimal parkingDuration) {  //BigDecimal   + logika zmiany kosztu w zaleznosci od dlugosci parkowania 
        return costFactor.multiply(parkingDuration);
    }
    
}
