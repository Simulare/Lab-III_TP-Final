package com.company;

import com.company.aviones.*;
import com.company.vuelos.Ciudad;
import com.company.vuelos.Vuelo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static com.company.vuelos.Vuelo.*;

public abstract class Empresa {

    protected static ArrayList<Cliente> clientes;
    protected static Map<Date, ArrayList<Vuelo>> vuelos; // Esto es para poder probar hasta hacer el tema de los vuelos
    protected static ArrayList<Bronze> avionesBronze; // Esto tambien es solo para probar, depende lo que se decida para manejarlos
    protected static ArrayList<Silver> avionesSilver;
    protected static ArrayList<Gold> avionesGold;

    protected static final String VUELOS_FILE = "vuelos.json";
    protected static File fileVuelos = new File(VUELOS_FILE);

    public Empresa(){}

    public Empresa(ArrayList<Cliente> clientes, Map<Date, ArrayList<Vuelo>> vuelos, ArrayList<Bronze> avionesBronze, ArrayList<Gold> avionesGold, ArrayList<Silver> avionesSilver){
        this.clientes = clientes;
        this.vuelos = vuelos;
        this.avionesBronze = avionesBronze;
        this.avionesSilver = avionesSilver;
        this.avionesGold = avionesGold;
    }

    public void agregarAvion(Avion nuevo){
        if (nuevo instanceof Gold){
            avionesGold.add((Gold) nuevo);

        }else if (nuevo instanceof Silver){
            avionesSilver.add((Silver)nuevo);

        }else if (nuevo instanceof Bronze){
            avionesBronze.add((Bronze)nuevo);
        }
    }

    public Avion buscarAvion(int id){
        Avion avion = null;

        for(Bronze bronze: avionesBronze){
            if (bronze.getId() == id){
                avion = bronze;
                break;
            }
        }
         if (avion == null){
             for(Silver silver: avionesSilver){
                 if (silver.getId() == id){
                     avion = silver;
                     break;
                 }
             }
             if (avion == null){
                 for(Gold gold: avionesGold){
                     if (gold.getId() == id){
                         avion = gold;
                         break;
                     }
                 }
             }
         }
         return avion;
    }

    public void listarAviones(){
        for(Bronze bronze: avionesBronze){
            System.out.println(bronze.getId() +"-"+ bronze.toString());
        }
        for(Silver silver: avionesSilver){
            System.out.println(silver.getId() +"-"+ silver.toString());
        }
        for(Gold gold: avionesGold){
            System.out.println(gold.getId() +"-"+ gold.toString());
        }
    }

    public void eliminarAvion(Avion avion){
        if (avion instanceof Gold){
            avionesGold.remove(avion);

        }else if (avion instanceof Silver){
            avionesSilver.remove(avion);

        }else if (avion instanceof Bronze){
            avionesBronze.remove(avion);
        }
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

        Date fecha = null;
        int dni=0;

        for(ArrayList<Vuelo> aVuelos : vuelos.values()){
            for(Vuelo v:aVuelos){
                System.out.println(v.getFechaVuelo()+"-"+v.getAvion().getId()+"-"+v.getOrigen()+"-"+v.getDestino()+"-"+v.getCliente().getNombre()+"-"+v.getCostoVuelo());
            }
        }



        try {
            String[] parametros= new String[6];
            pedirDatosVuelo(parametros);

            System.out.println(parametros[0]);//dni
            System.out.println(parametros[1]);//fecha
            System.out.println(parametros[2]);//origen
            System.out.println(parametros[3]);//destino
            System.out.println(parametros[4]);//cantPasajeros
            System.out.println(parametros[5]);//tipoAvion

            Avion avionOk = validarDatosVuelo(parametros);

            if (avionOk!=null){

                Cliente cli=buscarCliente(Integer.parseInt(parametros[0]));
                SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");
                Date dateVuelo = sdfg.parse(parametros[1]);
                Ciudad origen= Ciudad.valueOf(parametros[2]);
                Ciudad destino= Ciudad.valueOf(parametros[3]);
                int cantPasajeros=Integer.parseInt(parametros[4]);
                TipoAvion tipoAvion=TipoAvion.valueOf(parametros[5]);

                double kmsRecorrido = calcularDistancia(origen,destino);
                double costoVuelo = calculaCostoVuelo(kmsRecorrido,avionOk.getCostoKm(),cantPasajeros,tipoAvion);

                Vuelo v = new Vuelo(dateVuelo,avionOk,origen,destino,cli,cantPasajeros,costoVuelo,kmsRecorrido);
                ArrayList<Vuelo> arrVuelos=new ArrayList<Vuelo>();
                if(vuelos.containsKey(dateVuelo)) {
                    arrVuelos = vuelos.get(dateVuelo);
                }
                arrVuelos.add(v);
                vuelos.put(dateVuelo,arrVuelos);

                System.out.println(v.getFechaVuelo()+"-"+v.getAvion().getId()+"-"+v.getOrigen()+"-"+v.getDestino()+"-"+v.getCliente().getNombre()+"-"+v.getCostoVuelo());
                guardarVuelosToJson();
            }
        }catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static Avion consultaAvionDisponible(TipoAvion tipoAvion, Date fechaVuelo, int cantPasajeros){

        switch (tipoAvion){
            case GOLD ->{
                for (Gold gold : avionesGold){
                    if (gold.getCapacidad() >= cantPasajeros){
                        boolean todoOk=consultaVueloDisponible(gold.getId(),fechaVuelo);
                        if(todoOk==true){
                            return gold;
                        }
                    }
                }
                break;
            }
            case SILVER -> {
                for (Silver silver : avionesSilver){
                    if (silver.getCapacidad() >= cantPasajeros){
                        boolean todoOk=consultaVueloDisponible(silver.getId(),fechaVuelo);
                        if(todoOk==true){
                            return silver;
                        }
                    }
                }
                break;
            }

            case BRONZE -> {
                for (Bronze bronze : avionesBronze){
                    if (bronze.getCapacidad() >= cantPasajeros){
                        boolean todoOk=consultaVueloDisponible(bronze.getId(),fechaVuelo);
                        if(todoOk==true){
                            return bronze;
                        }
                    }
                }
                break;
            }
        }
        return null;
    }

    public static boolean consultaVueloDisponible(int idAvion ,Date fechaVuelo){
        if(vuelos.containsKey(fechaVuelo)){
            for(Vuelo vuelo: vuelos.get(fechaVuelo)){
                if(vuelo.getAvion().getId()==idAvion){
                    return false;
                }
            }
        }
        return true;
    }

    public static void guardarVuelosToJson(){
        ObjectMapper mapperVuelos = new ObjectMapper();
        try {
            mapperVuelos.writeValue(fileVuelos,vuelos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}