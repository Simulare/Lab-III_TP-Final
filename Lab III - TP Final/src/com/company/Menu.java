package com.company;

import com.company.aviones.*;
import com.company.vuelos.Vuelo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu extends Empresa{

    private static final String CLIENTES_FILE = "clientes.json";
    private static final String BRONZE_FILE = "avionesBronze.json";
    private static final String SILVER_FILE = "avionesSilver.json";
    private static final String GOLD_FILE = "avionesGold.json";


    public static Scanner scanner = new Scanner(System.in);
    static ObjectMapper mapper = new ObjectMapper();
    private static File fileClientes = new File(CLIENTES_FILE);
    private static File fileBronze = new File(BRONZE_FILE);
    private static File fileSilver = new File(SILVER_FILE);
    private static File fileGold = new File(GOLD_FILE);


    public Menu(){
        super(new ArrayList<>(), new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        clientes = descargarClientesJSON();
        descargarAvionesJSON();
        vuelos=descargarVuelosJSON();

        //Esto se va a modificar para cargar desde el archivo los vuelos y aviones.
    }

    public static void existenciaFiles() {
        try {
            if (!fileClientes.exists()){
                fileClientes.createNewFile();
            }
            if (!fileGold.exists()){
                fileGold.createNewFile();
            }
            if (!fileSilver.exists()){
                fileSilver.createNewFile();
            }
            if (!fileBronze.exists()){
                fileBronze.createNewFile();
            }
        }catch (IOException e){}
    }

    public void iniciarMenu(){

        existenciaFiles();
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
                    "3. Administrar aviones.\n" +
                    "4. Registrar cliente.\n" +
                    "5. Eliminar cliente.\n" +
                    "6. Cancelar vuelo.\n\n" +
                    "Elija una opción: ");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;
                case 1:
                    comprarVuelo();
                    break;
                case 2:
                    menuListados();
                    break;
                case 3:
                    menuAdministarAviones();
                    break;
                case 4:
                    registrarCliente();
                    break;
                case 5:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("Ingrese el DNI del cliente a eliminar:");
                    int dni = checkInt(scanner.nextLine());
                    if (dni != -1){
                        Cliente cliente = buscarCliente(dni);
                        if (cliente == null){

                            System.out.println("ERROR. No hay un cliente con el DNI ingresado.");
                        }else{

                            System.out.println("Confirma que quiere eliminar el cliente " +cliente.getNombre() + " " + cliente.getApellido() + "?"
                                    + " Presione 's' para confirmar.");
                            String entrada = scanner.nextLine();
                            if (entrada.equalsIgnoreCase("s")){
                                eliminarCliente(cliente);
                                listClientesToJSONFile();
                                System.out.println("Cliente eliminado correctamente.");
                            }else {
                                System.out.println("El cliente no se ha eliminado.");
                            }
                        }
                    }else {
                        System.out.println("ERROR. El DNI ingresado no es válido.");
                    }
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;

                case 6:

                    //Cancelar vuelo
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        }while (respuesta != 0);

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
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("             <<<<< LISTADO DE VUELOS >>>>>");
                    System.out.println(" -------------------------------------------------------\n");
                    listarVuelos();
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
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
                    listarClientesMejorAvion();
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;

                case 3:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("         <<<<< CLIENTES Y SU GASTO TOTAL >>>>>");
                    System.out.println(" -------------------------------------------------------\n");
                    listarClientesGastos();
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

    public void registrarCliente(){

        System.out.println(" -------------------------------------------------------");
        System.out.println("            <<<<< REGISTRAR CLIENTE >>>>>");
        System.out.println(" -------------------------------------------------------\n");
        System.out.println("Ingrese el DNI del cliente:");
        int dni = checkInt(scanner.nextLine());
        if (dni != -1){
            Cliente cliente = buscarCliente(dni);
            if (cliente != null){
                System.out.println("Ya existe un cliente registrado con el DNI ingresado. Volverá al menú principal.");
                System.out.println("\n... Presione ENTER para continuar ...");
                pausarConsola();
                scanner.nextLine();
            }else{
                cliente = new Cliente();
                cliente.crearNuevo(dni);
                agregarCliente(cliente);
                listClientesToJSONFile();
                System.out.println("            +++ Registrado con éxito! +++");
                System.out.println("\n... Presione ENTER para continuar ...");
                pausarConsola();
                scanner.nextLine();
            }
        }
    }

    public void menuAdministarAviones(){
        int respuesta;
        do {
            System.out.println(" -------------------------------------------------------");
            System.out.println("            <<<<< ADMINISTRAR AVIONES >>>>>");
            System.out.println(" -------------------------------------------------------\n");

            System.out.println("0. Volver\n" +
                    "1. Añadir avión.\n" +
                    "2. Eliminar avión.\n" +
                    "3. Listar aviones.\n\n" +
                    "Elegir opción:");

            respuesta = checkInt(scanner.nextLine());
            switch (respuesta){
                case 0:
                    break;
                case 1:
                    cargarAvion();
                    break;
                case 2:
                    System.out.println("Ingrese el id del avión a borrar:");
                    int id = checkInt(scanner.nextLine());
                    Avion avion = buscarAvion(id);
                    if (avion == null){
                        System.out.println("No se encontró avión con el id ingresado.");
                    }else{
                        switch (avion.getTipoAvion()){
                            case GOLD ->{
                                avionesGold.remove(avion);
                                break;
                            }
                            case BRONZE ->{
                                avionesBronze.remove(avion);
                                break;
                            }
                            case SILVER -> {
                                avionesSilver.remove(avion);
                                break;
                            }
                        }
                        System.out.println("Avión eliminado exitosamente!");
                    }
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println(" -------------------------------------------------------");
                    System.out.println("             <<<<< LISTADO DE AVIONES >>>>>");
                    System.out.println(" -------------------------------------------------------\n");
                    listarAviones();
                    System.out.println("\n... Presione ENTER para continuar ...");
                    pausarConsola();
                    scanner.nextLine();
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            mapper.setDateFormat(dateFormat);
            return mapper.readValue(fileClientes, new TypeReference<ArrayList<Cliente>>(){});
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los clientes desde el archivo.");
        }
        return new ArrayList<Cliente>();
    }

    private static Map<Date, ArrayList<Vuelo>> descargarVuelosJSON(){  //Devuelve un Map con el archivo de vuelos
        try {
         //   avionesBronze = mapper.readValue(fileBronze, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Bronze.class));
            ObjectMapper mapperVuelos =new ObjectMapper();
            SimpleDateFormat sdfg = new SimpleDateFormat("dd/MM/yyyy");
            mapperVuelos.setDateFormat(sdfg);
            //HashMap<Date, ArrayList<Vuelo>> vuelos = mapperVuelos.readValue(fileVuelos, new TypeReference<HashMap<Date, mapperVuelos.getTypeFactory()ArrayList<Vuelo>>>(){});
            TypeReference<HashMap<Date, ArrayList<Vuelo>>> typeRef=new TypeReference<HashMap<Date, ArrayList<Vuelo>>>(){};
            HashMap<Date, ArrayList<Vuelo>> vuelos = mapperVuelos.readValue(fileVuelos,typeRef);

            return vuelos;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los vuelos desde el archivo.");
        }
        return new HashMap<Date, ArrayList<Vuelo>>();
    }

    public static void listAvionesToJSONFile(){  //Guarda el arrayList de aviones en el archivo.

        try {
            mapper = new ObjectMapper();
            mapper.writeValue(fileBronze, avionesBronze);
            mapper.writeValue(fileSilver, avionesSilver);
            mapper.writeValue(fileGold, avionesGold);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al guardar los aviones en el archivo.");
        }
    }

    private static void descargarAvionesJSON(){  //Devuelve un ArrayList con el archivo de aviones

        try {
            mapper = new ObjectMapper();
            avionesBronze = mapper.readValue(fileBronze, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Bronze.class));
            avionesSilver = mapper.readValue(fileSilver, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Silver.class));
            avionesGold = mapper.readValue(fileGold, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Gold.class));

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar los aviones desde el archivo.");
        }
    }

    private void cargarAvion(){

        boolean flag = true;

        System.out.println("Tipo de avión:\n1.Gold. \n2. Silver. \n3. Bronze.");
        int opTipoAvion = checkInt(scanner.nextLine());
        System.out.println("Id:");
        int id = checkInt(scanner.nextLine());
        System.out.println("Capacidad de combustible en lts.:");
        double ltsCombustible = checkDouble(scanner.nextLine());
        System.out.println("Costo del km:");
        double costoKm = checkDouble(scanner.nextLine());
        System.out.println("Capacidad:");
        int capacidad = checkInt(scanner.nextLine());
        System.out.println("Max. Velocidad:");
        double maxVelocidad = checkDouble(scanner.nextLine());
        System.out.println("Propulsión: \n1. Reacción. \n2. Hélice. \n3. Pistones.");
        int opPropulsion = checkInt(scanner.nextLine());

        TipoAvion tipoAvion = null;
        Motor propulsion = null;
        boolean poseewifi = false;

        switch (opPropulsion){
            case 1:
                propulsion = Motor.REACCION;
                break;
            case 2:
                propulsion = Motor.HELICE;
                break;
            case 3:
                propulsion = Motor.PISTONES;
                break;
            default:
                flag = false;
        }

        //Verifica que el dato ingresado corresponda con el tipo de dato se espera, si es -1 no es el tipo de dato correcto.
        if (opTipoAvion == -1 || id == -1 || ltsCombustible == -1 || costoKm == -1 || capacidad == -1 || maxVelocidad == -1 || opPropulsion == -1){
            flag = false;
        }

        if (flag)
        switch (opTipoAvion){
            case 1:
                tipoAvion = TipoAvion.GOLD;
                System.out.println("Posee Wifi: \n1. Si. \n2. No.");
                int op = scanner.nextInt();
                if (op == 1){
                    poseewifi = true;
                }
                Gold gold = new Gold(id, ltsCombustible, costoKm, capacidad, maxVelocidad, propulsion, poseewifi, tipoAvion);
                agregarAvion(gold);
                break;
            case 2:
                tipoAvion = TipoAvion.SILVER;
                Silver silver = new Silver(id, ltsCombustible, costoKm, capacidad, maxVelocidad, propulsion, tipoAvion);
                agregarAvion(silver);
                break;
            case 3:
                tipoAvion = TipoAvion.BRONZE;
                Bronze bronze = new Bronze(id, ltsCombustible, costoKm, capacidad, maxVelocidad, propulsion, tipoAvion);
                agregarAvion(bronze);
                break;
            default:
                break;
        }
        if (!flag){

            System.out.println( "Error en los datos cargados, no son válidos. No se guardó el avión.");;
        }else{
            System.out.println("           +++ Avión guardado con éxito! +++");
            listAvionesToJSONFile();
        }
        System.out.println("\n... Presione ENTER para continuar ...");
        pausarConsola();
        scanner.nextLine();
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

    public static double checkDouble (String entrada){ //Para evitar que se rompa el programa si se ingresa un string por teclado esperando double.
        double salida;
        try {
            salida = Double.parseDouble(entrada);
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
