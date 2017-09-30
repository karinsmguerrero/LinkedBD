package GUI;

import data.files.Commit;
import data.files.DBList;
import data.files.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuBarController {

    Commit commitClass;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu searchMenu, newMenu, helpMenu, editionMenu;

    @FXML
    private MenuItem dbMenuItem, tableMenuItem, aboutMenuItem, commitChangesMenuItem;

    @FXML
    private Menu commitMenu;


    @FXML
    private void switchToCreteDB() {
        try {
            AnchorPane newDBWindow = FXMLLoader.load(getClass().getResource("NewBDWindow.fxml"));

            BorderPane border = MainWindow.getRoot();
            border.setCenter(newDBWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToCreateTable(){
        try {
            AnchorPane newDBWindow = FXMLLoader.load(getClass().getResource("NewTableWindow.fxml"));

            BorderPane border = MainWindow.getRoot();
            border.setCenter(newDBWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToEditionPage(){
        try {
            AnchorPane editionWindow = FXMLLoader.load(getClass().getResource("EditionWindow.fxml"));

            BorderPane border = MainWindow.getRoot();
            border.setCenter(editionWindow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void commit_clicked(){
        System.out.println("Commit...");
        DBList dbList = new DBList();
        commitClass = new Commit();
        commitClass.commitList(dbList.getDbList());
    }
}