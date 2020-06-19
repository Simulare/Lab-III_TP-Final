package com.company;

import com.company.aviones.Avion;
import com.company.vuelos.Vuelo;

import java.util.ArrayList;

public abstract class Empresa {

    protected ArrayList<Cliente> clientes;
    protected ArrayList<Vuelo> vuelos; // Esto es para poder probar hasta hacer el tema de los vuelos
    protected ArrayList<Avion> aviones; // Esto tambien es solo para probar, depende lo que se decida para manejarlos

    public Empresa(){}

    public Empresa(ArrayList<Cliente> clientes, ArrayList<Vuelo> vuelos, ArrayList<Avion> aviones) {
        this.clientes = clientes;
        this.vuelos = vuelos;
        this.aviones = aviones;
    }

    public Cliente buscarCliente(int dni){
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

}