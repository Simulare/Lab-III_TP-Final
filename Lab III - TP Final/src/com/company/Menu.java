package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu extends Empresa{

    private static final String CLIENTES_FILE = "clientes.json";

    public static Scanner scanner = new Scanner(System.in);
    static ObjectMapper mapper = new ObjectMapper();
    private static File fileClientes = new File(CLIENTES_FILE);

    public Menu(){
        super(descargarClientesJSON(), new ArrayList<>(), new ArrayList<>());
        //Esto se va a modificar para cargar desde el archivo los vuelos y aviones.
    }

    public void iniciarMenu(){
        int respuesta;
        int dni;

        do {
            do {
                do {
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("Bienvenido! Ingrese su DNI para identificarse:");
                    dni = checkInt(scanner.nextLine());
                    if (dni == -1){
                        System.out.println("DNI no válido.");
                    }
                } while (dni == -1);
                System.out.println(" -------------------------------------------------------");
                System.out.println("Confirma que su DNI es " + dni + "? (Presione '1' para confirmar):");;
                respuesta = checkInt(scanner.nextLine());

                if (respuesta != 1){
                    System.out.println("No confirmó su DNI, intente de nuevo.");
                }

            }while (respuesta != 1);

            Cliente clienteActual = buscarCliente(dni);

            if (clienteActual == null){
                clienteActual = registrarCliente(dni);
            }

        }while (respuesta != 0);
    }

    public void menuListados(){
        int respuesta = 0;
        do{
            System.out.println(" -------------------------------------------------------");
            System.out.println("<<<<< MENU LISTADOS >>>>> (0 para volver)\n");
            System.out.println("1. Listado de vuelos.\n" +
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
                    System.out.println("Opción inválida.");
                    break;
            }

        }while (respuesta != 0);
    }

    public void listadosClientes(){
        int respuesta = 0;
        do {

            System.out.println(" -------------------------------------------------------");
            System.out.println("<<<<< MENU LISTADOS DE CLIENTES >>>>> (0 para volver)\n");
            System.out.println("1. Clientes y datos personales.\n" +
                    "2. Clientes y su mejor categoría de avión utilizada.\n" +
                    "3. Clientes y su gasto total.\n\n" +
                    "Elija una opción:");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;
                case 1:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("<<<<< CLIENTES Y DATOS PERSONALES >>>>>\n");
                    listarClientes();
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("<<<<< CLIENTES Y SU MEJOR CATEGORIA DE AVIÓN >>>>>\n");
                    ///Listado de mejor categoría
                    break;
                case 3:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("<<<<< CLIENTES Y SU GASTO TOTAL >>>>>\n");
                    ///Listado de GASTO TOTAL
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }while (respuesta != 0);
    }

    public Cliente registrarCliente(int dni){

        System.out.println("Su DNI " + dni + " no está registrado. Para registrar complete sus datos: ");
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.crearNuevo(dni);
        //clientes.add(nuevoCliente);       Comentado para no guardar las pruebas en el archivos
        System.out.println("Registrado con éxito!");
        return nuevoCliente;
    }

    public void listClientesToJSONFile (){    // Guarda el ArrayList en el archivo
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            mapper.setDateFormat(dateFormat);
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(fileClientes, clientes);
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al guardar los clientes en el archivo.");
        }
    }

    private static ArrayList<Cliente> descargarClientesJSON(){  //Devuelve un ArrayList con el archivo de clientes
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            mapper.setDateFormat(dateFormat);
            return mapper.readValue(fileClientes, new TypeReference<ArrayList<Cliente>>(){});
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los clientes desde el archivo.");
        }
        return new ArrayList<Cliente>();
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
