package sample;

import data.structures.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    HBox layoutTop;
    VBox layoutLeft;
    BorderPane layout;
    TreeView<String> treeMenu;

    Scene welcomeScene;

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
        TreeItem<String> treeRoot = new TreeItem<>();
        treeRoot.setExpanded(true);
        treeMenu = new TreeView<String>(treeRoot);
        layoutLeft.getChildren().addAll(treeMenu);

        //here region
        //region


        /*TreeItem<String> root, bucky, megan;

        //Root
        root = new TreeItem<>();
        root.setExpanded(true);

        //Bucky
        bucky = makeBranch("Bucky", root);
        makeBranch("thenewboston", bucky);
        makeBranch("YouTube", bucky);
        makeBranch("Chicken", bucky);

        //Megan
        megan = makeBranch("Megan", root);
        makeBranch("Glitter", megan);
        makeBranch("Makeup", megan);

        //Create the tree and hide the main Root
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    if (newValue != null)
                        System.out.println(newValue.getValue());
                });

        //Layout
        StackPane layout = new StackPane();
layout.getChildren().add(tree);*/

        //endregion

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

    public static void main(String[] args) {
        //launch(args);
        

    }
}
