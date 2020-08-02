package com.mike.lanterna.test;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class ActionListTest {
    public static void main(String[] args) throws IOException {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        final Screen screen = new TerminalScreen(terminal);
        screen.startScreen();


        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);


        //Definicion de lista de acciones
        TerminalSize size = new TerminalSize(35, 10);
        ActionListBox actionListBox = new ActionListBox(size);

        actionListBox.addItem("Crear Usuario", new Runnable() {
            @Override
            public void run() {
                System.out.println("Metodo crear usuario");
                screen.clear();
                try {
                    screen.refresh();
                    TablaTest a = new TablaTest();
                    a.main(null);//chapuza
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        actionListBox.addItem("Consultar Usuario", new Runnable() {
            @Override
            public void run() {
                System.out.println("Metodo Consultar Usuario");

            }
        });

        actionListBox.addItem("Reintegro en cuenta", new Runnable() {
            @Override
            public void run() {
                System.out.println("Metodo Reintegro");

            }
        });

        actionListBox.addItem("Ingreso en cuenta", new Runnable() {
            @Override
            public void run() {
                System.out.println("Metodo Ingreso");

            }
        });

        actionListBox.addItem("Ver historico de cuenta", new Runnable() {
            @Override
            public void run() {
                // Code to run when action activated
                System.out.println("Metodo Historico");

            }
        });

        actionListBox.addItem("Ver todos los usuarios y cuentas", new Runnable() {
            @Override
            public void run() {
                System.out.println("Metodo Ver Todos");

            }
        });

        actionListBox.addItem("Salir del SGBD", new Runnable() {
            @Override
            public void run() {
                new MessageDialogBuilder()
                        .setTitle("Mensaje")
                        .setText("Have a nice day!")
                        .addButton(MessageDialogButton.Continue)
                        .build()
                        .showDialog(textGUI);
                System.exit(0);
            }
        });

        Panel mainPanel = new Panel();
        mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
        window.setComponent(mainPanel.withBorder(Borders.singleLine("SGBD Maze Bank")));

        mainPanel.addComponent(actionListBox);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

    }
}
