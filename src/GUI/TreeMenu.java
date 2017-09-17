package GUI;

//import data.files.FileManager;
import data.files.DBList;
import data.files.FieldList;
import data.files.TableList;
import data.structures.generics.DoubleNode;
import data.structures.generics.Node;
import javafx.scene.control.*;

import java.io.File;

public class TreeMenu {

    //private FileManager files;
    private File[] listOfFolders;

    private DBList dbList = new DBList();

    private TreeView<String> treeMenu;
    private TreeItem<String> treeRoot;


    public TreeView<String> createTree(){
        return createTreeAux();
    }

    private TreeView<String> createTreeAux(){
        //Crear la raiz del arbol
        treeRoot = new TreeItem<>();
        treeRoot.setExpanded(true);
        //Crear el arbol y esconder la raiz
        treeMenu = new TreeView<String>(treeRoot);
        treeMenu.setShowRoot(false);

        //Añadir las ramas desde un archivo
        addBranch(treeRoot);

        //Codigo de tutorial de oracle
        ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Crear nueva base de datos");
        item1.setOnAction(e -> System.out.println("Created"));
        PopUp popUpWindow = new PopUp();
        String path;

        MenuItem item2 = new MenuItem("Eliminar base de datos");
        item2.setOnAction(e -> System.out.println("Deleted"));
        contextMenu.getItems().addAll(item1, item2);

        treeMenu.setContextMenu(contextMenu);

        return treeMenu;
    }

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
            System.out.println(temp.getValue().getFileName());
            String dbName = temp.getValue().getFileName();
            TreeItem<String> branch = createBranch(dbName, parent);

            DoubleNode<FieldList> leave = temp.getValue().getFileList().getHead();

            for(int z = 0; z < temp.getValue().getFileList().getSize(); z++) {
                System.out.println(leave.getValue().getFileName());
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
        createBranch(item, parent);
    }

    //Create branches
    private TreeItem<String> createBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
