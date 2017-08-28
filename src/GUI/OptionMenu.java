package GUI;

import javafx.scene.control.*;

public class OptionMenu {

    private MenuBar createMenuBarAux() {

        Menu newMenu = new Menu("Nuevo");
        MenuItem newBD = new MenuItem("Nueva base de datos");
        newBD.setOnAction(e -> System.out.println("Create a new bd..."));
        newMenu.getItems().add(newBD);

        MenuItem newTable = new MenuItem("Nueva tabla");
        newTable.setOnAction(e -> System.out.println("Create a new table..."));
        newMenu.getItems().add(newTable);

        newMenu.getItems().add(new SeparatorMenuItem());
        MenuItem newFK = new MenuItem("Nueva llave foranea");
        newTable.setOnAction(e -> System.out.println("Create a new FK..."));
        newMenu.getItems().add(newFK);

        newMenu.getItems().add(new SeparatorMenuItem());
        MenuItem newCommit = new MenuItem("Nuevo commit");
        newTable.setOnAction(e -> System.out.println("Create a new commit..."));
        newMenu.getItems().add(newCommit);

        //Edit menu
        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Cut"));
        editMenu.getItems().add(new MenuItem("Copy"));
        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(e -> System.out.println("Pasting some crap"));
        paste.setDisable(true);
        editMenu.getItems().add(paste);

        //Help menu
        Menu helpMenu = new Menu("Help");
        CheckMenuItem showLines = new CheckMenuItem("Show Line Numbers");
        showLines.setOnAction(e -> {
            if (showLines.isSelected())
                System.out.println("Program will now display line numbers");
            else
                System.out.println("Hiding line number");
        });
        CheckMenuItem autoSave = new CheckMenuItem("Enable Autosave");
        autoSave.setSelected(true);
        helpMenu.getItems().addAll(showLines, autoSave);

        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(newMenu, editMenu, helpMenu);
        return menuBar;
    }

    public MenuBar createMenuBar(){
        return createMenuBarAux();
    }

}
