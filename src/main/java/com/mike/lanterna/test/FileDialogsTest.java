package com.mike.lanterna.test;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.io.IOException;

public class FileDialogsTest  {
    public static void main(String[] args) throws IOException {
        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();


        // Create window to hold the panel
        BasicWindow window = new BasicWindow();

        // Create gui and start gui
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

        // Create panel to hold components
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));

        panel.addComponent(new Button("Test", new Runnable() {
            @Override
            public void run() {
                File input = new FileDialogBuilder()
                        .setTitle("Open File")
                        .setDescription("Choose a file")
                        .setActionLabel("Open")
                        .build()
                        .showDialog(textGUI);
                System.out.println("Archivo seleccionado: " + input.toString());
            }
        }));

        window.setComponent(panel);

        textGUI.addWindowAndWait(window);
    }
}
