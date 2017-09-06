package GUI;

import data.files.ColumnCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Table {

    TableView<ColumnCreator> table;
    TextField nameInput, typeInput, fkInput, defaultInput;
    ChoiceBox requiredChoice;

    public HBox addFooter(){
        return addFooterAux();
    }

    public VBox addTable(){
        return addTableAux();
    }

    private VBox addTableAux() {

        //Name column
        TableColumn<ColumnCreator, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("columnName"));

        TableColumn<ColumnCreator, Double> typeColumn = new TableColumn<>("Tipo de dato");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("columnType"));

        TableColumn<ColumnCreator, String> fkColumn = new TableColumn<>("Tipo especial");
        fkColumn.setMinWidth(100);
        fkColumn.setCellValueFactory(new PropertyValueFactory<>("columnFK"));

        TableColumn<ColumnCreator, String> requiredColumn = new TableColumn<>("Es requerido");
        requiredColumn.setMinWidth(100);
        requiredColumn.setCellValueFactory(new PropertyValueFactory<>("columnRequired"));

        TableColumn<ColumnCreator, String> defaultColumn = new TableColumn<>("Valor por defecto");
        defaultColumn.setMinWidth(100);
        defaultColumn.setCellValueFactory(new PropertyValueFactory<>("columnDefault"));

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, typeColumn, fkColumn, requiredColumn, defaultColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, addFooter());
        VBox.setVgrow(table, Priority.ALWAYS);
        return vBox;
    }

    private HBox addFooterAux(){
        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Nombre");
        nameInput.setMinWidth(100);

        typeInput = new TextField();
        typeInput.setPromptText("Tipo de dato");

        fkInput = new TextField();
        fkInput.setPromptText("Tipo especial");

        requiredChoice = new ChoiceBox<String>();
        requiredChoice.getItems().addAll("true", "false");
        requiredChoice.setValue("false");

        defaultInput = new TextField();
        defaultInput.setPromptText("Valor por defecto");

        //Button
        Button addButton = new Button("Añadir");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Eliminar");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, typeInput, fkInput, requiredChoice, defaultInput, addButton, deleteButton);
        return hBox;
    }
    //Add button clicked
    private void addButtonClicked(){
        ColumnCreator column = new ColumnCreator();
        column.setColumnName(nameInput.getText());
        column.setColumnType(typeInput.getText());
        column.setColumnFK(fkInput.getText());
        String req = (String)requiredChoice.getValue();
        if (req == "true") {
            column.setColumnRequired(true);
        }else {
            column.setColumnRequired(false);
        }
        column.setColumnDefault(defaultInput.getText());
        table.getItems().add(column);
        nameInput.clear();
        typeInput.clear();
        fkInput.clear();
        requiredChoice.getSelectionModel().clearSelection();
        defaultInput.clear();
    }

    //Delete button clicked
    private void deleteButtonClicked(){
        ObservableList<ColumnCreator> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    //Get all of the products
    private ObservableList<ColumnCreator> getProduct(){
        ObservableList<ColumnCreator> products = FXCollections.observableArrayList();
        products.add(new ColumnCreator("Código", "Number", "None", true, "None"));
        products.add(new ColumnCreator("Nombre", "String", "None", true, "None"));
        products.add(new ColumnCreator("Profesor", "String", "Cédula", false, "No asignado"));
        return products;
    }

}
