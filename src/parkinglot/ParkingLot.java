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
    private final TicketController ticketController = new TicketController(); // czemu stala? czemu tutaj?  - PROBLEM

    public ParkingLot(int numberOfSlots) {
        Random random = new Random();
        for (int i = 0; i < numberOfSlots; i++) {
            allParkingSlots.add(new ParkingSlot(1 + random.nextInt(3)));         //podzielenie implementacji na tworzenie poszczegolnych miejsc w osobnych oddelegowanych metodach 
        }
        freeParkingSlots.addAll(allParkingSlots);
    }
    // metoda sprawdzajaca can i park my vehicle 

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSlot targetSlot = freeParkingSlots.stream().filter(p -> p.accepts(vehicle)).findFirst();    // checked unchecked exception   metoda acceptCar() i metoda checkIsFree()   - PROBLEM 2 
        targetSlot.addVehicle(vehicle);
        if (!targetSlot.isFree()) {
            freeParkingSlots.remove(targetSlot);
        }
        parkingVehicles.put(vehicle, targetSlot);
        return new Ticket(vehicle);
    }

    public void unparkVehicle(Ticket ticket) {
        ParkingSlot targetSlot = parkingVehicles.remove(ticket.getVehicle());  // get wyekstrahowany do zmiennej 
        targetSlot.remove(ticket.getVehicle());
        freeParkingSlots.add(targetSlot);
        income += ticket.calculateCost(TICKET_CONTROLLER);   // oddelegowanie odpowiedzialnosci obslugi z biletu do kontrolera a nie na odwrot tak jak jest tutaj
    }

    @Override
    public String toString() {
        return String.format("ParkingLot [income=%.2f, freeParkingSlots=%d, parkingVehicles=%d]", income, freeParkingSlots.size(), parkingVehicles.size());  //java formatter (znaczniki)
    }
}
