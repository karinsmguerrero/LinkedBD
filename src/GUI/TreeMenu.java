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

    public void addBranches(TreeView<String> menu){
        addBranchesAux(menu);
    }

    private void addBranchesAux(TreeView<String> menu){
        menu.setShowRoot(false);
        TreeItem root = new TreeItem();
        root.setExpanded(true);
        addBranch(root);
        menu.setRoot(root);
    }

    private void addBranch(TreeItem<String> parent){
        DoubleNode<TableList> temp = DBList.getDbList().getHead();

        for (int i = 0; i < DBList.getDbList().getSize(); i++){
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

    private void addLeave(TreeItem<String> parent, String item) {
        int pos = item.lastIndexOf("."); //Busca el último . de la cadena
        if (pos > 0) { //Si pos es -1 el caracter no existe
            item = item.substring(0, pos); //Corta la cadena
        }
        createBranch(item, parent, "table");
    }

    /**
     * Código tomado del tutorial JavaFx Java GUI tutorial - 16 - treeview por thenewboston
     * link: https://www.youtube.com/watch?v=SvmSNbXQSnQ&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=16
     *
     * @param title
     * @param parent
     * @param context
     * @return
     */
    private TreeItem<String> createBranch(String title, TreeItem<String> parent, String context) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;

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
