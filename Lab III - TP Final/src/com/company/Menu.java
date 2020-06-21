package com.company;

import com.company.aviones.*;
import com.company.vuelos.Vuelo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends Empresa{

    private static final String CLIENTES_FILE = "clientes.json";
    private static final String AVIONES_FILE = "aviones.json";

    public static Scanner scanner = new Scanner(System.in);
    static ObjectMapper mapper = new ObjectMapper();
    private static File fileClientes = new File(CLIENTES_FILE);
    private static File fileAviones = new File(AVIONES_FILE);

    public Menu(){
        super(descargarClientesJSON(), new ArrayList<>(), descargarAvionesJSON());
        //Esto se va a modificar para cargar desde el archivo los vuelos y aviones.
    }

    public static void existenciaFiles() throws IOException {
        try {
            if (!fileClientes.exists()){
                fileClientes.createNewFile();
            }
            if (!fileAviones.exists()){
                fileAviones.createNewFile();
            }
        }catch (IOException e){}
    }

    public void iniciarMenu(){
        menuPrincipal();
    }

    public void menuPrincipal (){
        int respuesta = 0;
        do {
            System.out.println(" -------------------------------------------------------");
            System.out.println("            SISTEMA DE PASAJES \"AEROTAXI\"");
            System.out.println(" -------------------------------------------------------");
            System.out.println("              <<<<< MENU PRINCIPAL >>>>>\n");
            System.out.println("0. Salir del sistema.\n" +
                    "1. Comprar vuelos.\n" +
                    "2. Menu de listados.\n" +
                    "3. Administrar aviones.\n\n" +
                    "Elija una opción: ");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;
                case 1:
                    /*
                    int dni = menuIdentificarCliente();
                    if (dni != -1){

                        Cliente clienteActual = buscarCliente(dni);
                        if (clienteActual == null){
                            clienteActual = registrarCliente(dni);
                        }
                        // Funciones para comprar los vuelos

                    }
                     */
                    comprarVuelo();
                    break;
                case 2:
                    menuListados();
                    break;
                case 3:
                    menuAdministarAviones();
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        }while (respuesta != 0);

    }

    public int menuIdentificarCliente(){
        int respuesta = 0;
        int dni;

            do {
                System.out.println(" -------------------------------------------------------");
                System.out.println("         <<<<< IDENTIFICACIÓN DEL USUARIO >>>>>");
                System.out.println(" -------------------------------------------------------\n");
                System.out.println("Ingrese el DNI del cliente:");
                dni = checkInt(scanner.nextLine());
                if (dni == -1){
                    System.out.println("              +++ DNI no válido +++");
                }
            } while (dni == -1);
            System.out.println(" -------------------------------------------------------");
            System.out.println("         <<<<< IDENTIFICACIÓN DEL USUARIO >>>>>");
            System.out.println(" -------------------------------------------------------\n");
            System.out.println("Confirma que se ha ingresado correctamente el DNI Nº " + dni + "? (Presione '1' para confirmar, o cualquier tecla para volver):");;
            respuesta = checkInt(scanner.nextLine());

            if (respuesta != 1){
                System.out.println("No confirmó el DNI. Volverá al menú principal.");
                dni = -1;
            }

        return dni;
    }


    public void menuListados(){
        int respuesta = 0;
        do{
            System.out.println(" -------------------------------------------------------");
            System.out.println("               <<<<< MENU LISTADOS >>>>>");
            System.out.println(" -------------------------------------------------------\n");
            System.out.println("0. Volver.\n" +
                    "1. Listado de vuelos.\n" +
                    "2. Listados de clientes.\n\n" +
                    "Elija una opción: ");
            respuesta = checkInt(scanner.nextLine());

            switch (respuesta){
                case 0:
                    break;
                case 1:
                    ///Menu para listar vuelos
                    break;
                case 2:
                    listadosClientes();
                    break;
                default:
                    System.out.println("              +++ Opción inválida. +++");
                    break;
            }

        }while (respuesta != 0);
    }

    public void listadosClientes(){
        int respuesta = 0;
        do {

            System.out.println(" -------------------------------------------------------");
            System.out.println("          <<<<< MENU LISTADOS DE CLIENTES >>>>>");
            System.out.println(" -------------------------------------------------------\n");
            System.out.println("0. Volver.\n" +
                    "1. Clientes y datos personales.\n" +
                    "2. Clientes y su mejor categoría de avión utilizada.\n" +
                    "3. Clientes y su gasto total.\n\n" +
                    "Elija una opción:");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;

                case 1:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("        <<<<< CLIENTES Y DATOS PERSONALES >>>>>");
                    System.out.println(" -------------------------------------------------------\n");
                    listarClientes();
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;

                case 2:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("  <<<<< CLIENTES Y SU MEJOR CATEGORIA DE AVIÓN >>>>>");
                    System.out.println(" -------------------------------------------------------\n");

                    ///Listado de mejor categoría

                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("         <<<<< CLIENTES Y SU GASTO TOTAL >>>>>");
                    System.out.println(" -------------------------------------------------------\n");

                    ///Listado de GASTO TOTAL

                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;

                default:
                    System.out.println("              +++ Opción inválida. +++");
                    break;
            }
        }while (respuesta != 0);
    }

    public Cliente registrarCliente(int dni){

        System.out.println(" -------------------------------------------------------");
        System.out.println("            <<<<< REGISTRAR CLIENTE >>>>>");
        System.out.println(" -------------------------------------------------------\n");
        System.out.println("Su DNI " + dni + " no está registrado. Para registrar complete sus datos:\n");
        Cliente nuevoCliente = new Cliente();
        agregarCliente(nuevoCliente);
        clientes.add(nuevoCliente);
        System.out.println("            +++ Registrado con éxito! +++");
        return nuevoCliente;
    }

    public void menuAdministarAviones(){
        int respuesta;
        do {
            System.out.println(" -------------------------------------------------------");
            System.out.println("            <<<<< ADMINISTRAR AVIONES >>>>>");
            System.out.println(" -------------------------------------------------------\n");

            System.out.println("0. Volver\n" +
                    "1. Añadir avión.\n" +
                    "2. Listar aviones.\n\n" +
                    "Elegir opción:");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;
                case 1:
                    Avion avion1 = new Gold(1, 234.2, 4564.3, 6, 2223, Motor.HELICE, true, TipoAvion.GOLD);
                    Avion avion2 = new Silver(2, 252.1, 2264.3, 7, 133, Motor.PISTONES, TipoAvion.SILVER);
                    Avion avion3 = new Bronze(3, 3423.6, 4564.3, 6, 2223, Motor.REACCION, TipoAvion.BRONZE);
                    Avion avion4 = new Gold(4, 2234, 775, 2, 243, Motor.PISTONES, true, TipoAvion.GOLD);
                    aviones.add(avion1);
                    aviones.add(avion2);
                    aviones.add(avion3);
                    aviones.add(avion4);

                    break;
                case 2:
                    listarAviones();
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        }while (respuesta != 0);
    }

    public static void listClientesToJSONFile (){    // Guarda el ArrayList en el archivo
        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(fileClientes, clientes);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al guardar los clientes en el archivo.");
        }
    }

    private static ArrayList<Cliente> descargarClientesJSON(){  //Devuelve un ArrayList con el archivo de clientes
        try {
            return mapper.readValue(fileClientes, new TypeReference<ArrayList<Cliente>>(){});
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los clientes desde el archivo.");
        }
        return new ArrayList<Cliente>();
    }

    public static void listAvionesToJSONFile(){  //Guarda el arrayList de aviones en el archivo.

        try {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(fileAviones, aviones);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al guardar los aviones en el archivo.");
        }
    }

    private static ArrayList<Avion> descargarAvionesJSON(){  //Devuelve un ArrayList con el archivo de aviones

        try {
            return mapper.readValue(fileAviones, ListaAviones.class);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los aviones desde el archivo.");
        }
        return new ArrayList<Avion>();
    }

    public static int checkInt (String entrada){ //Para evitar que se rompa el programa si se ingresa un string por teclado esperando int.
        int salida;
        try {
            salida = Integer.parseInt(entrada);
        }catch (NumberFormatException e){
            salida = -1;
        }
        return salida;
    }

    public static void pausarConsola(){     // Pausa hasta presionar ENTER
        try{
            System.in.read();
        } catch(Exception e){}
    }

}
