package com.company.aviones;

public class Gold extends Avion {

    private boolean poseeWifi;

    public Gold() {
    }

    public Gold(double ltsComustible, double costoKm, int capacidad, double maxVelocidad, Motor propulsion) {
        super(ltsComustible, costoKm, capacidad, maxVelocidad, propulsion);
    }
}
