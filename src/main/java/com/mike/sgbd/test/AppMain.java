package com.mike.sgbd.test;

import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args) {
        System.out.printf("Iniciando el sgbd");
        try {
            Gui app = new Gui();
            app.verPopUp("que pasa peña?!!!\nComo lo llevais??","Saludo", MessageDialogButton.Continue);
            app.verMenuPrincipal();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
