package sample;

import data.files.management.FileManager;
import data.files.management.JsonManager;
import data.structures.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;
    TreeView<String> treeMenu;
    TreeItem<String> treeRoot;

    Scene welcomeScene;

    FileManager files = new FileManager("C:\\Users\\karin\\Desktop\\PruebaBD");
    File[] listOfForders = files.getListOfFolders();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Stage window = primaryStage;
        //Aparentemente obtiene un xml con el diseño
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        layoutTop = new HBox();
        Button btnNew = new Button("Nuevo");
        btnNew.setOnAction(e -> openWindow());
        Button btnSearch = new Button("Buscar");
        Button btnAbout = new Button("Acerca de");
        layoutTop.getChildren().addAll(btnNew, btnSearch, btnAbout);

        layoutLeft = new VBox();
        //Crear la raiz del arbol
        treeRoot = new TreeItem<>();
        treeRoot.setExpanded(true);
        //Crear el arbol y esconder la raiz
        treeMenu = new TreeView<String>(treeRoot);
        treeMenu.setShowRoot(false);
        treeMenu.getSelectionModel()
                .selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    //if (newValue != null)
                        //System.out.println(newValue.getValue());
                });

        //Añadir las ramas desde un archivo
        addBranch(treeRoot);

        layoutLeft.getChildren().addAll(treeMenu);

        layout = new BorderPane();
        layout.setTop(layoutTop);
        layout.setLeft(layoutLeft);

        welcomeScene = new Scene(layout, 400,500);
        primaryStage.setTitle("Linked BD");
        primaryStage.setScene(welcomeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void openWindow(){
        System.out.println("opended");
    }

    public void addSubBranch(TreeItem parent, File item) {
        String itemName = item.getName();
        int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
        if (pos > 0) { //Si pos es -1 el caracter no existe
            itemName = itemName.substring(0, pos); //Corta la cadena
        }
        createBranch(itemName, parent);
    }

    public void addBranch(TreeItem parent){
        for(File item: listOfForders) {
            String itemName = item.getName();
            int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
            if (pos > 0) { //Si pos es -1 el caracter no existe
                itemName = itemName.substring(0, pos); //Corta la cadena
            }
            TreeItem<String> branch = createBranch(itemName, parent);

            FileManager subFolder = new FileManager(item.getPath());
            File[] subFolderItems = subFolder.getListOfFiles(item.getPath());
            for (File subItem : subFolderItems) {
                addSubBranch(branch, subItem);
            }
        }
    }

    //Create branches
    public TreeItem<String> createBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    public static void main(String[] args) {
        //launch(args);
        //FileManager fm = new FileManager("C:\\Users\\karin\\Desktop\\PruebaBD");
        //fm.createFolder("UNA");
        JsonManager js = new JsonManager("C:\\Users\\karin\\Desktop\\PruebaBD\\UNA", "Cursos");
        //addJsonObjectAux(String key, String type, String FK, boolean required, String defaultValue);
        js.addJsonObject("Código", "Number", "None", true, "None");
    }
}
