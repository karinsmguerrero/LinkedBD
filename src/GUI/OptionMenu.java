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
        newFK.setOnAction(e -> System.out.println("Create a new FK..."));
        newMenu.getItems().add(newFK);

        newMenu.getItems().add(new SeparatorMenuItem());

        MenuItem newCommit = new MenuItem("Nuevo commit");
        newCommit.setOnAction(e -> System.out.println("Create a new commit..."));
        newMenu.getItems().add(newCommit);

        //Edit menu
        Menu searchMenu = new Menu("_Buscar");
        searchMenu.getItems().add(new SeparatorMenuItem());

        MenuItem newSearch = new MenuItem("Nueva búsqueda");
        newSearch.setOnAction(e -> System.out.println("Create a new search..."));
        searchMenu.getItems().add(newSearch);



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
        menuBar.getMenus().addAll(newMenu, searchMenu, helpMenu);
        return menuBar;
    }

    public MenuBar createMenuBar(){
        return createMenuBarAux();
    }

}
