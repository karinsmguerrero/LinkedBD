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
    /**
     * Codigo tomado de https://stackoverflow.com/questions/35045725/creating-a-custom-tree-with-javafx by fabian
     */
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
    }

    // loads some strings into the tree in the application UI.
    private  void loadTreeItems() {
        TreeMenu tree = new TreeMenu();
        tree.addBranches(treeMenu);
    }

    public TreeView<String> getTreeMenu(){
        return treeMenu;
    }


}
