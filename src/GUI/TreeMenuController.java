package GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TreeMenuController implements Initializable{

    @FXML
    private TreeView<String> treeMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTreeItems();
        treeMenu.setEditable(true);
        treeMenu.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            private final MyContextMenu contextMenu = new MyContextMenu();
            private final StringConverter converter = new DefaultStringConverter();

            @Override
            public TreeCell<String> call(TreeView<String> param) {
                return new CustomTreeCell(contextMenu, converter);
            }

        });

        System.out.println("loading tree...");
    }

    // loads some strings into the tree in the application UI.
    public void loadTreeItems() {
        TreeMenu tree = new TreeMenu();
        System.out.println(treeMenu);
        tree.addBranches(treeMenu);
    }


}
