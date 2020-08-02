package com.mike.lanterna.test;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.bundle.LanternaThemes;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


public class TablaTest {
    public static void main(String[] args) throws IOException {

        // Setup terminal and screen layers
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        // Create panel to hold components
        final Panel contentPanel = new Panel();
        contentPanel.setLayoutManager(new GridLayout(2));

        contentPanel.addComponent(new Label("Forename"));
        final TextBox tbForename = new TextBox();
        contentPanel.addComponent(tbForename);

        contentPanel.addComponent(new Label("Surname"));
        contentPanel.addComponent(new TextBox());

        contentPanel.addComponent(new EmptySpace(new TerminalSize(0,0))); // Empty space underneath labels
        Button btPrueba = new Button("Prueba", new Runnable() {
            @Override
            public void run() {
                // Actions go here
                System.out.println(tbForename.getText());

            }
        });


        contentPanel.addComponent(btPrueba);


        contentPanel.addComponent(new Label("Read-only Combo Box (forced size)"));
        List<String> timezonesAsStrings = new ArrayList<String>();
        for(String id: TimeZone.getAvailableIDs()) {
            timezonesAsStrings.add(id);
        }
        ComboBox<String> readOnlyComboBox = new ComboBox<String>(timezonesAsStrings);
        readOnlyComboBox.setReadOnly(true);
        readOnlyComboBox.setPreferredSize(new TerminalSize(20, 1));
        contentPanel.addComponent(readOnlyComboBox);

        contentPanel.addComponent(new Label("Editable Combo Box (filled)"));
        contentPanel.addComponent(
                new ComboBox<String>("Item #1", "Item #2", "Item #3", "Item #4")
                        .setReadOnly(false)
                        .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(1)));


        final Table<String> tabla = new Table<String>("Nombre", "Apellido", "Num Cuenta");
        tabla.getTableModel().addRow("Jason", "Bourne", "111111");
        tabla.getTableModel().addRow("Denis", "Pastor", "222222");
        tabla.getTableModel().addRow("James", "Stocks", "333333");

        tabla.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = tabla.getTableModel().getRow(tabla.getSelectedRow());
                for(int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i));
                }
            }
        });

        contentPanel.addComponent(tabla);
        contentPanel.setTheme(LanternaThemes.getRegisteredTheme("conqueror"));
        System.out.println(LanternaThemes.getRegisteredThemes().toString());
        // Create window to hold the panel
        BasicWindow window = new BasicWindow();
        window.setComponent(contentPanel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

    }
}
