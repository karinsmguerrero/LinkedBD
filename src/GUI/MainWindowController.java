package GUI;

import data.files.DBList;
import data.files.TableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable{

    @FXML
    private MenuBar menuBar;

    @FXML
    private TreeView<String> treeMenu;

    @FXML
    private Menu searchMenu, newMenu, helpMenu;

    @FXML
    private MenuItem dbMenuItem, tableMenuItem, aboutMenuItem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTreeItems();
        System.out.println("loading tree...");
    }

    // loads some strings into the tree in the application UI.
    public void loadTreeItems() {
        TreeMenu tree = new TreeMenu();
        tree.addBranches(treeMenu);
       /* String[] rootItems = {"uno", "dos", "tres"};

        TreeItem<String> root = new TreeItem<String>();
        root.setExpanded(true);
        for (String itemString: rootItems) {
            root.getChildren().add(new TreeItem<String>(itemString));
        }

        treeMenu.setRoot(root);*/
    }

    private void createDB(String dbName){
        DBList dbList = new DBList();
        dbList.getDbList().addNodeToTheTail(new TableList(dbName, dbName));
    }

    public void createDBButton_clicked(String dbName){
        createDB(dbName);
    }

    private void switchToCreateDBPane(){

    }
}