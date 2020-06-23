package com.company.vuelos;

import com.company.Cliente;
import com.company.Empresa;
import com.company.aviones.Avion;
import com.company.aviones.TipoAvion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Vuelo {
    private Date fechaVuelo;
    private int idAvion;
    private Ciudad origen;
    private Ciudad destino;
    private Cliente cliente;
    private int cantPasajeros;
    private double costoVuelo;
    private double kmsRecorrido;

    public Vuelo(){}

    public Vuelo(Date fechaVuelo, int idAvion, Ciudad origen, Ciudad destino, Cliente cliente, int cantPasajeros, double costoVuelo, double kmsRecorrido) {
        this.fechaVuelo = fechaVuelo;
        this.idAvion = idAvion;
        this.origen = origen;
        this.destino = destino;
        this.cliente = cliente;
        this.cantPasajeros = cantPasajeros;
        this.costoVuelo = costoVuelo;
        this.kmsRecorrido = kmsRecorrido;
    }

    public static void pedirDatosVuelo(String[] parametros) throws IOException, ParseException {

        BufferedReader brTeclado = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("DNI: ");
        String strDni = brTeclado.readLine();
        int rest = 0;
        do {

            System.out.print("Fecha (dd/mm/aaaa): ");
            String strFecha = brTeclado.readLine();

            SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");

            parametros[1] = strFecha;

            try {
                Date fecha = sdfg.parse(strFecha);
                java.util.Date Fecha = new Date();

                if (fecha.compareTo(Fecha) < 0) {
                    System.out.println("Fecha menor q la actual, kapo no seas boludo y compra algo para el futuro");
                } else {
                    rest = 1;
                }
            }catch (ParseException e){
                System.out.println("Error. La fecha debe ingresarse en el formato indicado.");
            }
        }while(rest == 0);

        System.out.print("Origen:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n ");
        int nOrigen = Integer.parseInt(brTeclado.readLine());
        System.out.print("Destino:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n ");
        int nDestino = Integer.parseInt(brTeclado.readLine());
        System.out.print("Cantidad de pasajeros: ");
        String strCantPasajeros = brTeclado.readLine();
        System.out.print("Tipo de avion:\n   1. Gold.\n   2. Silver.\n   3. Bronze.\n");
        int nTipoAvion = Integer.parseInt(brTeclado.readLine());



        Ciudad ciudades[] = Ciudad.values();
        TipoAvion tipos[] = TipoAvion.values();

        parametros[0] = strDni;

        parametros[2] = ciudades[nOrigen - 1].name();
        parametros[3] = ciudades[nDestino - 1].name();
        parametros[4] = strCantPasajeros;
        parametros[5] = tipos[nTipoAvion - 1].name();
    }


    public static double calcularDistancia (Ciudad origen, Ciudad destino) {
        double distancia = 0;

        if (origen == Ciudad.BUENOS_AIRES || destino == Ciudad.BUENOS_AIRES) {
            if (origen == Ciudad.CORDOBA || destino == Ciudad.CORDOBA) {
                distancia = 695; // Ruta Bs.as - Cordoba

            } else if (origen == Ciudad.SANTIAGO_DE_CHILE || destino == Ciudad.SANTIAGO_DE_CHILE) {
                distancia = 1400; // Ruta Bs.As - Santiago

            } else if (origen == Ciudad.MONTEVIDEO || destino == Ciudad.MONTEVIDEO) {
                distancia = 950; // Ruta Bs.As. - Montevideo
            }

        } else if (origen == Ciudad.CORDOBA || destino == Ciudad.CORDOBA) {
            if (origen == Ciudad.MONTEVIDEO || destino == Ciudad.MONTEVIDEO) {
                distancia = 1190; // Ruta Cordoba - Montevideo

            } else if (origen == Ciudad.SANTIAGO_DE_CHILE || destino == Ciudad.SANTIAGO_DE_CHILE) {
                distancia = 1050; // Ruta Cordoba - Santiago
            }

        } else if (origen == Ciudad.MONTEVIDEO || destino == Ciudad.MONTEVIDEO) {
            if (origen == Ciudad.SANTIAGO_DE_CHILE || destino == Ciudad.SANTIAGO_DE_CHILE) {
                distancia = 2100; // Ruta Montevideo - Santiago
            }
        }
        return distancia;
    }

    public static double calculaCostoVuelo(double cantKms,double costoKms, int cantPasajeros,TipoAvion tipoAvion){
        int tarifa;
        double costoTotal;
        if (tipoAvion.name().equals(TipoAvion.BRONZE)){
            tarifa=3000;
        }else{
            if (tipoAvion.name().equals(TipoAvion.SILVER)){
                tarifa=4000;
            }else{
                tarifa=6000;
            }
        }
        costoTotal=(cantKms*costoKms)+(cantPasajeros*3500)+tarifa;
        return costoTotal;
    }

    /// Getters & Setters

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    public double getCostoVuelo() {
        return costoVuelo;
    }

    public void setCostoVuelo(int costoVuelo) {
        this.costoVuelo = costoVuelo;
    }

    public double getKmsRecorrido() {
        return kmsRecorrido;
    }

    public void setKmsRecorrido(double kmsRecorrido) {
        this.kmsRecorrido = kmsRecorrido;
    }

    @Override
    public String toString(){
        return "Vuelo [ Fecha: " + fechaVuelo + " - ID Avión: " + idAvion + " - Origen: " + origen +
                " - Destino: " + " - DNI Cliente: " + cliente.getDni() +  " - cant. Asientos: " + cantPasajeros +
                " - Costo vuelo: " + costoVuelo + " - Kms. recorridos: " + kmsRecorrido + "]";
    }
}
