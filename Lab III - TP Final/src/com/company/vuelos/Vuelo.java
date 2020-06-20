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
import java.util.Date;

public class Vuelo {
    private Date fechaVuelo;
    private Avion avion;
    private Ciudad origen;
    private Ciudad destino;
    private Cliente cliente;
    private int cantPasajeros;
    private int costoVuelo;
    private double kmsRecorrido;

    public void pedirDatosVuelo(String[] parametros) throws IOException, ParseException {

        BufferedReader brTeclado = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("DNI: ");
        String strDni = brTeclado.readLine();
        System.out.print("Fecha (dd/mm/aaaa): ");
        String strFecha = brTeclado.readLine();
        System.out.print("Origen:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n ");
        int nOrigen = Integer.parseInt(brTeclado.readLine());
        System.out.print("Destino:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n ");
        int nDestino = Integer.parseInt(brTeclado.readLine());
        System.out.print("Cantidad de pasajeros: ");
        String strCantPasajeros = brTeclado.readLine();
        System.out.print("Tipo de avion:\n   1. Gold.\n   2. Silver.\n   3. Bronze.\n");
        int nTipoAvion = Integer.parseInt(brTeclado.readLine());

        SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = sdfg.parse(strFecha);

        Ciudad ciudades[] = Ciudad.values();
        TipoAvion tipos[] = TipoAvion.values();

        parametros[0] = strDni;
        parametros[1] = strFecha;
        parametros[2] = ciudades[nOrigen - 1].name();
        parametros[3] = ciudades[nDestino - 1].name();
        parametros[4] = strCantPasajeros;
        parametros[5] = tipos[nTipoAvion - 1].name();
    }

    public void validarDatosVuelo(String[] parametros) throws IOException, ParseException {
        /// Verificar que el dni exista en clientes
        Cliente cliente = Empresa.buscarCliente(Integer.parseInt(parametros[0]));
        if (cliente == null) {
            System.out.println("ERROR: Cliente no encontrado :(\n");
        } else {
            System.out.println("Hola, " + cliente.getNombre());
            /// Verificar que para esa fecha hay aviones de ese tipo disponibles


            /// Verificar que la fecha sea mayor que "hoy"
            /// Verificar que la fecha sea mayor que "hoy"
            /// falta hacer

            /// Verificar que el origen y el destino no sea el mismo
            if (parametros[2].equals(parametros[3])) {
                System.out.println("Me estas cargando " + cliente.getNombre() + "? Para que vas a pagar un vuelo para subir y bajar? Tanta plata tenes?");
            }

            /// Verificar que para esa fecha hay aviones de ese tipo disponibles
            ///Casteos necesarios para consultaAvionDisponible

            TipoAvion tipoAvion = TipoAvion.valueOf(parametros[5]);
            SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = sdfg.parse(parametros[1]);
            int cantPax = Integer.parseInt(parametros[4]);

            Avion avionOk = Empresa.consultaAvionDisponible(tipoAvion, fecha, cantPax);
            if (avionOk == null) {
                System.out.println("No hay aviones disponibles\n");
            } else {
                System.out.println("Te hemos asignado el avion nº: " + avionOk.getId() + "\n");
            }
        }


        /// Verificar que el origen y el destino no sea el mismo

        if (parametros[2].equals(parametros[3])) {
            System.out.println("Me estas cargando " + cliente.getNombre() + "? Para que vas a pagar un vuelo para subir y bajar? Tanta plata tenes?");
        }

        /// Verificar que la cantidad de pasajeros no supere el max

    }



    public double calcularDistancia (Ciudad origen, Ciudad destino) {
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

    /// Getters & Setters

    public Date getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(Date fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
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

    public int getCostoVuelo() {
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
}
