package parkinglot;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

public class TicketController {

    public double getTotalCost(long parkingDuration, double costFactor) {
        return parkingDuration * costFactor;
    }
}
