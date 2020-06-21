package com.company.aviones;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonTypeName("Gold")

public class Gold extends Avion {

    private boolean poseeWifi;

    public Gold() {
    }

    @JsonCreator
    public Gold(@JsonProperty("id")int id, @JsonProperty("ltsCombustible")double ltsCombustible, @JsonProperty("costoKm") double costokm, @JsonProperty("capacidad")int capacidad, @JsonProperty("maxVelocidad")double maxVelocidad, @JsonProperty("propulsion") Motor propulsion, @JsonProperty("poseeWifi")boolean poseeWifi, @JsonProperty("tipoAvion") TipoAvion tipoAvion){
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
