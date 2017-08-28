package GUI;

import data.files.FileManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;

public class MainWindow extends Application {
    HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;

    Scene welcomeScene;

    FileManager files = new FileManager("C:\\Users\\karin\\Desktop\\PruebaBD");
    File[] listOfForders = files.getListOfFolders();

    @Override
    public void start(Stage primaryStage) throws Exception{



        layoutLeft = new VBox();
        Tree folderTree = new Tree("C:\\Users\\karin\\Desktop\\PruebaBD");
        TreeView<String> treeMenu = folderTree.createTree();
        layoutLeft.getChildren().addAll(treeMenu);

        layout = new BorderPane();
        layout.setLeft(layoutLeft);
        Table table = new Table();
        layout.setCenter(table.addTable());
        OptionMenu menu = new OptionMenu();
        layout.setTop(menu.createMenuBar());

        welcomeScene = new Scene(layout);
        primaryStage.setTitle("Linked BD");
        primaryStage.setScene(welcomeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public void openWindow(){
        System.out.println("opened");
    }

    public static void main(String[] args) {
        launch(args);
    }



}
