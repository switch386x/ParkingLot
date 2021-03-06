package parkinglot;

import java.math.BigDecimal;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

public class Vehicle {

    private final int id;
    private final int spacesNeeded;
    private final BigDecimal costFactor;
    private String vehicleType;

    public Vehicle(String vehicleType, int id, int spacesNeeded, BigDecimal costFactor) {
        this.vehicleType = vehicleType;
        this.id = id;
        this.spacesNeeded = spacesNeeded;
        this.costFactor = costFactor;
    }

    public int getSpacesNeeded() {
        return spacesNeeded;
    }

    public BigDecimal getCostFactor() {
        return costFactor;
    }

    @Override
    public String toString() {
        return String.format("Vehicle [vehicleType=%s, id=%d, spacesNeeded=%d, costFactor=%.2f]", vehicleType, id, spacesNeeded, costFactor);
    }
}
