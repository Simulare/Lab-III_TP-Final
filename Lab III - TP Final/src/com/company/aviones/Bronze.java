package com.company.aviones;

public class Bronze extends Avion {

    public Bronze() { }


    public Bronze( int id, double ltsCombustible, double costokm, int capacidad, double maxVelocidad, Motor propulsion, TipoAvion tipoAvion){
        super(id, ltsCombustible, costokm, capacidad, maxVelocidad, propulsion, tipoAvion);
    }

    @Override
    public String toString(){
        return "AVION BRONZE " + super.toString();
    }
}
