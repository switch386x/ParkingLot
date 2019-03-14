package parkinglot;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

/*
 * Copyright (c) 2019. Schenker AG
 * All rights reserved.
 */

/**
 * 
 * 7.4 Parking Lot 
 * Zaproponuj strukture reprezentujaca model parkingu dla samochodow. Obsluz koncept miejsca parkingowego z
 * uwzglednieniem oplacania postoju. Parking ma miejsca parkingowe (ParkingSlot), Sloty maja rozne rozmiary. Slot moze pomiescic
 * maksymalnie dwa pojazdy. Jesli jest zajety, pojazd nie ma mozliwosci zajecia go. Wysokosc oplaty za parkowanie jest zalezna od
 * rozmiaru pojazdu oraz czasu trwania postoju. Pojazdy maja rozne rozmiary determinujace mozliwosci parkowania
 * 
 * @author PILASKOW
 *
 */
public class ParkingLot {

    private final Collection<ParkingSlot> freeParkingSlots = new HashSet<>();
    private final Collection<ParkingSlot> allParkingSlots = new HashSet<>();
    private final Map<Vehicle, ParkingSlot> parkingVehicles = new HashMap<>();
    private double income = 0.0;
    private static final TicketController TICKET_CONTROLLER = new TicketController();

    public ParkingLot(int numberOfSlots) {
        Random random = new Random();
        for (int i = 0; i < numberOfSlots; i++) {
            allParkingSlots.add(new ParkingSlot(1 + random.nextInt(3)));
        }
        freeParkingSlots.addAll(allParkingSlots);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot targetSlot = freeParkingSlots.stream().filter(p -> p.accepts(vehicle)).findFirst().orElseThrow(() -> new RuntimeException("No free slot for " + vehicle));
        targetSlot.addVehicle(vehicle);
        if (!targetSlot.isFree()) {
            freeParkingSlots.remove(targetSlot);
        }
        parkingVehicles.put(vehicle, targetSlot);
        return new Ticket(vehicle);
    }

    public void unparkVehicle(Ticket ticket) {
        ParkingSlot targetSlot = parkingVehicles.remove(ticket.getVehicle());
        targetSlot.remove(ticket.getVehicle());
        freeParkingSlots.add(targetSlot);
        income += ticket.calculateCost(TICKET_CONTROLLER);
    }

    @Override
    public String toString() {
        return String.format("ParkingLot [income=%.2f, freeParkingSlots=%d, parkingVehicles=%d]", income, freeParkingSlots.size(), parkingVehicles.size());  //java formatter (znaczniki)
    }
}
