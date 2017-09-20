package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class TreeMenuController implements Initializable{

    @FXML
    private TreeView<String> treeMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTreeItems();
        System.out.println("loading tree...");
    }

    // loads some strings into the tree in the application UI.
    public void loadTreeItems() {
        TreeMenu tree = new TreeMenu();
        System.out.println(treeMenu);
        tree.addBranches(treeMenu);
    }
}
