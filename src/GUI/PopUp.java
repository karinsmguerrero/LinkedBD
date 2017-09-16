package GUI;

import data.files.FileManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {
    Stage window;
    FileManager flManager;

    public void createNewBD(String path) {
        window = new Stage();
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Crear nueva base de datos");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("Nombre de la nueva base de datos");
        TextField txtName = new TextField();
        flManager = new FileManager("", path);
        Button createButton = new Button("Crear");
        createButton.setOnAction(e -> {
            flManager.createFolder(txtName.getText());
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, txtName, createButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
