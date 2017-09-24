package GUI;

//import data.files.FileManager;
import data.files.DBList;
import data.files.FieldList;
import data.files.TableList;
import data.structures.generics.DoubleNode;
import data.structures.generics.Node;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.io.File;

public class TreeMenu {

    private DBList dbList = new DBList();

    public void addBranches(TreeView menu){
        menu.setShowRoot(false);
        TreeItem root = new TreeItem();
        root.setExpanded(true);
        addBranch(root);
        menu.setRoot(root);
    }

    private void addBranch(TreeItem parent){
        DoubleNode<TableList> temp = dbList.getDbList().getHead();

        for (int i = 0; i < dbList.getDbList().getSize(); i++){
            String dbName = temp.getValue().getFileName();
            TreeItem<String> branch = createBranch(dbName, parent, "DB");

            DoubleNode<FieldList> leave = temp.getValue().getFileList().getHead();

            for(int z = 0; z < temp.getValue().getFileList().getSize(); z++) {
                addLeave(branch, leave.getValue().getFileName());
                leave = leave.getNext();
            }
            temp = temp.getNext();
        }

    }

    private void addLeave(TreeItem parent, String item) {
        int pos = item.lastIndexOf("."); //Busca el último . de la cadena
        if (pos > 0) { //Si pos es -1 el caracter no existe
            item = item.substring(0, pos); //Corta la cadena
        }
        createBranch(item, parent, "table");
    }

    //Create branches
    private TreeItem<String> createBranch(String title, TreeItem<String> parent, String context) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;

        /*Mostrar un listado con todos los objetos en memoria. En este caso se deben considerar las llaves
foráneas también.
● Agregar un nuevo objeto JSON al documento: cuando se agrega un nuevo objeto, se debe validar que
el objeto que se trata de insertar cumpla con la estructura del documento definido previamente. El
objeto se agrega en memoria.
● Eliminar todos los objetos JSON del documento. Elimina los documentos en memoria.
● Eliminar un objeto al buscarlo por llave. Elimina un objeto en memoria.
● Buscar objetos por cualquier atributo en memoria.
● Actualizar uno o más objetos. Se especifica los atributos por actualizar. Se especifica también una
condición de búsqueda que indique cuáles objetos actualizar. La actualización se realiza en los
objetos en memoria.*/

/*        if(context == "table") {
            Menu tableMenu = new Menu();
            MenuItem addObjectMenuItem = new MenuItem("Añadir campo");
            MenuItem deleteObjectMenuItem = new MenuItem("Eliminar campo");
            MenuItem deleteAllMenuItem = new MenuItem("Eliminar todos los campos");
            MenuItem updateMenuItem = new MenuItem("Actualizar tabla");
            tableMenu.getItems().addAll(addObjectMenuItem, deleteObjectMenuItem, deleteAllMenuItem, updateMenuItem);

            tableMenu.setOnAction(e -> {
                TreeItem newEmployee = new TreeItem<>("New Tab");
                getTreeItem().getChildren().add(newEmployee);
            });

            contextMenuProperty().bind(
                    Bindings.when(Bindings.equal(itemProperty(), "TABS"))
                            .then(addMenu)
                            .otherwise((ContextMenu) null));
        }

    }*/
    }
}
