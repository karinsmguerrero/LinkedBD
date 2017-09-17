package sample;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Note that we load the panes with the FXMLLoader
 * on every use. This allows us to manipulate the
 * CSS between loads and have it take affect.
 *
 * Also, the panes should not save state internally.
 * Reloading the FXML forces better programming
 * design, because it is impossible to get lazy
 * and expect the panes to save their own state.
 */
public class MyMenusControl {

    @FXML // fx:id="displayOne"
    private MenuItem displayOne; // Value injected by FXMLLoader

    @FXML // fx:id="displayTwo"
    private MenuItem displayTwo; // Value injected by FXMLLoader

    // the FXML annotation tells the loader to inject this variable before invoking initialize.
    @FXML
    private TreeView<String> locationTreeView;

    /**
     * Event handler for MenuItem one
     */
    @FXML
    void switchToOne(ActionEvent event) {

        try {

            URL paneOneUrl = getClass().getResource("PaneOne.fxml");
            AnchorPane paneOne = FXMLLoader.load( paneOneUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(paneOne);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Event handler for MenuItem two
     */
    @FXML
    void switchToTwo(ActionEvent event) {

        try {

            URL paneTwoUrl = getClass().getResource("PaneTwo.fxml");
            AnchorPane paneTwo = FXMLLoader.load( paneTwoUrl );

            BorderPane border = Main.getRoot();
            border.setCenter(paneTwo);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // loads some strings into the tree in the application UI.
    public void loadTreeItems() {
        TreeItem<String> root = new TreeItem<String>("Root Node");
        root.setExpanded(true);
        String[] rootItems = {"Uno", "Dos", "Tres"};
        for (String itemString: rootItems) {
            root.getChildren().add(new TreeItem<String>(itemString));
        }

        locationTreeView.setRoot(root);
    }

}