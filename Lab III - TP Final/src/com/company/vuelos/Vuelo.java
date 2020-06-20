package com.company.vuelos;

import com.company.Cliente;
import com.company.Empresa;
import com.company.aviones.Avion;

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

        System.out.print("DNI: ");  String strDni = brTeclado.readLine();
        System.out.print("Fecha (dd/mm/aaaa): ");  String strFecha = brTeclado.readLine();
        System.out.print("Origen:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n "); int nOrigen = Integer.parseInt(brTeclado.readLine());
        System.out.print("Destino:\n   1. Buenos Aires.\n   2. Cordoba.\n   3. Santiago de Chile.\n   4. Montevideo.\n "); int nDestino = Integer.parseInt(brTeclado.readLine());
        System.out.print("Cantidad de pasajeros: "); String strCantPasajeros = brTeclado.readLine();
        System.out.print("Tipo de avion: "); String tipoAvion = brTeclado.readLine();

        SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = sdfg.parse(strFecha);

        Ciudad ciudades[] = Ciudad.values();


        parametros[0]= strDni;
        parametros[1]= strFecha;
        parametros[2]=ciudades[nOrigen-1].name();
        parametros[3]=ciudades[nDestino-1].name();
        parametros[4]=strCantPasajeros;
        parametros[5]=tipoAvion;
    }

    public void validarDatosVuelo(String[] parametros) throws IOException, ParseException{
        /// Verificar que el dni exista en clientes


        Cliente cliente = Empresa.buscarCliente(Integer.parseInt(parametros[0]));
        if (cliente==null){
            System.out.println("ERROR: Cliente no encontrado :(\n");
        }else{
            System.out.println("Hola, "+cliente.getNombre());
        }

        /// Verificar que para esa fecha hay aviones de ese tipo disponibles



        /// Verificar que la fecha sea mayor que "hoy"



        /// Verificar que el origen y el destino no sea el mismo

        if(parametros[2].equals(parametros[3])){
            System.out.println("Me estas cargando "+cliente.getNombre()+"? Para que vas a pagar un vuelo para subir y bajar? Tanta plata tenes?");
        }

        /// Verificar que la cantidad de pasajeros no supere el max


    }


}
