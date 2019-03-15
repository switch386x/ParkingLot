package parkinglot;

import java.math.BigDecimal;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

public class Ticket {

    private final long startTime = System.currentTimeMillis(); // do konsturktora, plus slot  + metoda konczaca parkowanie zaiwerajaca wlasciwosc endtime na podstawie ktorej bedzie to liczone
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle, long startTime) {
        this.vehicle = vehicle;
    }

    public BigDecimal calculateParkingDuration() {
        return new BigDecimal(System.currentTimeMillis() - startTime);
    }

    public BigDecimal calculateCost(TicketController ticketController) {
        return ticketController.getTotalCost(calculateParkingDuration(), vehicle.getCostFactor());
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
