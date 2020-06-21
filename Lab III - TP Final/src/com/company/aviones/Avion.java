package com.company.aviones;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonSubTypes({
        @JsonSubTypes.Type(value = Gold.class, name = "Gold"),
        @JsonSubTypes.Type(value = Silver.class, name = "Silver"),
        @JsonSubTypes.Type(value = Bronze.class, name = "Bronze"),
})

public abstract class Avion {
    protected int id;
    protected double ltsComustible;
    protected double costoKm;
    protected int capacidad;
    protected double maxVelocidad;
    protected Motor propulsion;
    protected TipoAvion tipoAvion;

    public Avion(){}

    @JsonCreator
    public Avion(@JsonProperty("id")int id, @JsonProperty("ltsCombustible") double ltsComustible, @JsonProperty("costoKm") double costoKm, @JsonProperty("capacidad") int capacidad, @JsonProperty ("maxVelocidad") double maxVelocidad, @JsonProperty("propulsion") Motor propulsion, @JsonProperty("tipoAvion") TipoAvion tipoAvion) {
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

    @Override
    public String toString(){
        return "[ id: " + id + "- Lts. Combustible: " + " -Costo Km: " + costoKm +
                " -Capacidad: " + capacidad + "max. Velocidad: " + maxVelocidad +
                " -Tipo de propulsión: " + propulsion + "]";
    }
}
