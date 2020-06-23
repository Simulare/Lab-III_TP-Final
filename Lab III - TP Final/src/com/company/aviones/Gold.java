package com.company.aviones;

public class Gold extends Avion {
    private static final boolean CATERING = true;
    private boolean poseeWifi;

    public Gold() {
    }

    public Gold( int id, double ltsCombustible, double costokm, int capacidad, double maxVelocidad, Motor propulsion, boolean poseeWifi, TipoAvion tipoAvion){
        super(id, ltsCombustible, costokm, capacidad, maxVelocidad, propulsion, tipoAvion);
        this.poseeWifi = poseeWifi;
    }

    public boolean isPoseeWifi() {
        return poseeWifi;
    }

    public void setPoseeWifi(boolean poseeWifi) {
        this.poseeWifi = poseeWifi;
    }

    @Override
    public String toString(){
        return "AVION GOLD " + super.toString();
    }
}
