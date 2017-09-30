package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;

public class WindowUtility {

    public void reloadTree(){
        reloadTreeAux();
    }

    private void reloadTreeAux(){
        URL treeMenuUrl = getClass().getResource("treeMenu.fxml");
        TreeView<String> treeMenu = null;
        try {
            treeMenu = FXMLLoader.load(treeMenuUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BorderPane border = MainWindow.getRoot();
        border.setLeft(treeMenu);
    }
}
