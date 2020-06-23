package com.company.aviones;


public class Silver extends Avion {
    private static final boolean CATERING = true;
    public Silver(){}

    public Silver( int id, double ltsCombustible, double costokm, int capacidad, double maxVelocidad, Motor propulsion, TipoAvion tipoAvion){
        super(id, ltsCombustible, costokm, capacidad, maxVelocidad, propulsion, tipoAvion);
    }

    @Override
    public String toString(){
        return "AVION SILVER " + super.toString();
    }
}
