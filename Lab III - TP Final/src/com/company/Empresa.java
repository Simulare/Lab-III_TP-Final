package com.company;

import com.company.aviones.Avion;
import com.company.vuelos.Vuelo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public abstract class Empresa {

    protected static ArrayList<Cliente> clientes;
    protected ArrayList<Vuelo> vuelos; // Esto es para poder probar hasta hacer el tema de los vuelos
    protected ArrayList<Avion> aviones; // Esto tambien es solo para probar, depende lo que se decida para manejarlos

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

    public void agregarCliente (Cliente cliente){
        clientes.add(cliente);
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

}