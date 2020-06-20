package com.company.aviones;

public abstract class Avion {
    protected int id;
    protected double ltsComustible;
    protected double costoKm;
    protected int capacidad;
    protected double maxVelocidad;
    protected Motor propulsion;
    protected TipoAvion tipoAvion;

    public Avion(){}

    public Avion(int id, double ltsComustible, double costoKm, int capacidad, double maxVelocidad, Motor propulsion, TipoAvion tipoAvion) {
        this.id = id;
        this.ltsComustible = ltsComustible;
        this.costoKm = costoKm;
        this.capacidad = capacidad;
        this.maxVelocidad = maxVelocidad;
        this.propulsion = propulsion;
        this.tipoAvion = tipoAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLtsComustible() {
        return ltsComustible;
    }

    public void setLtsComustible(double ltsComustible) {
        this.ltsComustible = ltsComustible;
    }

    public double getCostoKm() {
        return costoKm;
    }

    public void setCostoKm(double costoKm) {
        this.costoKm = costoKm;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getMaxVelocidad() {
        return maxVelocidad;
    }

    public void setMaxVelocidad(double maxVelocidad) {
        this.maxVelocidad = maxVelocidad;
    }

    public Motor getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(Motor propulsion) {
        this.propulsion = propulsion;
    }

    public TipoAvion getTipoAvion() {
        return tipoAvion;
    }

    public void setTipoAvion(TipoAvion tipoAvion) {
        this.tipoAvion = tipoAvion;
    }
}
