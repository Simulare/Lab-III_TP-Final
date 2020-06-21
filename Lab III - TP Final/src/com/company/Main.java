package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {  //Esto despues lo acomodo
            Menu.existenciaFiles();
        } catch (IOException e) {
        }
        Menu menu = new Menu();
        menu.iniciarMenu();
        //Menu.listAvionesToJSONFile();

    }
}
