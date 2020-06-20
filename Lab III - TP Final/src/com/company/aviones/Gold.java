package com.company.aviones;

public class Gold extends Avion {

    private boolean poseeWifi;

    public Gold() {
    }

    public Gold(int id, double ltsComustible, double costoKm, int capacidad, double maxVelocidad, Motor propulsion) {
        super(id, ltsComustible, costoKm, capacidad, maxVelocidad, propulsion);
    }
}
