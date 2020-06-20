package com.company;

import com.company.aviones.Avion;
import com.company.aviones.TipoAvion;
import com.company.vuelos.Vuelo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public abstract class Empresa {

    protected static ArrayList<Cliente> clientes;
    protected static ArrayList<Vuelo> vuelos; // Esto es para poder probar hasta hacer el tema de los vuelos
    protected static ArrayList<Avion> aviones; // Esto tambien es solo para probar, depende lo que se decida para manejarlos

    public Empresa(){}

    public Empresa(ArrayList<Cliente> clientes, ArrayList<Vuelo> vuelos, ArrayList<Avion> aviones) {
        this.clientes = clientes;
        this.vuelos = vuelos;
        this.aviones = aviones;

    }

    public static Cliente buscarCliente(int dni){
        for (Cliente cliente : clientes){
            if (cliente.getDni() == dni){
                return cliente;
            }
        }
        return null;
    }

    public void eliminarCliente(Cliente cliente){
        clientes.remove(cliente);
    }

    public void listarClientes(){
        for (Cliente cliente : clientes){
            System.out.println(cliente.toString());
        }
    }

    public static void comprarVuelo(){
        Vuelo v = new Vuelo();
        Date fecha = null;
        int dni=0;
        try {
            String[] parametros= new String[6];
            v.pedirDatosVuelo(parametros);
            /*
            System.out.println(parametros[0]);
            System.out.println(parametros[1]);
            System.out.println(parametros[2]);
            System.out.println(parametros[3]);
            System.out.println(parametros[4]);
            System.out.println(parametros[5]);
            */
            v.validarDatosVuelo(parametros);


        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Avion consultaAvionDisponible(TipoAvion tipoAvion, Date fechaVuelo, int cantPasajeros){
        for(Avion avion : aviones){
            if(avion.getTipoAvion().equals(tipoAvion)){
                if (avion.getCapacidad() >= cantPasajeros){
                    boolean todoOk=consultaVueloDisponible(avion.getId(),fechaVuelo);
                    if(todoOk==true){
                        return avion;
                    }
                }
            }
        }
        return null;
    }

    public static boolean consultaVueloDisponible(int idAvion ,Date fechaVuelo){
        for(Vuelo vuelo: vuelos){
            if(vuelo.getAvion().getId()==idAvion && vuelo.getFechaVuelo().equals(fechaVuelo)){
                return false;
            }
        }
        return true;
    }





}