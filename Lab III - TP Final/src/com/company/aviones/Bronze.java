package com.company.aviones;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonTypeName("Bronze")

public class Bronze extends Avion {

    public Bronze() { }

    @JsonCreator
    public Bronze(@JsonProperty("id")int id, @JsonProperty("ltsCombustible")double ltsCombustible, @JsonProperty("costoKm") double costokm, @JsonProperty("capacidad")int capacidad, @JsonProperty("maxVelocidad")double maxVelocidad, @JsonProperty("propulsion") Motor propulsion, @JsonProperty("tipoAvion") TipoAvion tipoAvion){
        super(id, ltsCombustible, costokm, capacidad, maxVelocidad, propulsion, tipoAvion);
    }

    @Override
    public String toString(){
        return "AVION BRONZE " + super.toString();
    }
}
