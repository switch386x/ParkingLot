package parkinglot;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

public class Ticket {

    private final long startTime = System.currentTimeMillis();
    private final Vehicle vehicle;

    public Ticket(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long calculateParkingDuration() {
        return System.currentTimeMillis() - startTime;
    }

    public double calculateCost(TicketController ticketController) {
        return ticketController.getTotalCost(calculateParkingDuration(), vehicle.getCostFactor());
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
