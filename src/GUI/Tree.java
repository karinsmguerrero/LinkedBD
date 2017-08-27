package GUI;

import data.files.management.FileManager;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;

public class Tree {

    private FileManager files;
    private File[] listOfFolders;

    private TreeView<String> treeMenu;
    private TreeItem<String> treeRoot;

    public Tree(String filesPath){
        files = new FileManager(filesPath);
        listOfFolders = files.getListOfFolders();
    }

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
        treeMenu.getSelectionModel()
                .selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
                    //if (newValue != null)
                    //System.out.println(newValue.getValue());
                });

        //Añadir las ramas desde un archivo
        addBranch(treeRoot);
        return treeMenu;
    }


    private void addBranch(TreeItem parent){
        for(File item: listOfFolders) {
            String itemName = item.getName();
            int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
            if (pos > 0) { //Si pos es -1 el caracter no existe
                itemName = itemName.substring(0, pos); //Corta la cadena
            }
            TreeItem<String> branch = createBranch(itemName, parent);

            FileManager subFolder = new FileManager(item.getPath());
            File[] subFolderList = subFolder.getListOfFolders();

            addSubBranch(branch, subFolderList);

        }
    }

    private void addSubBranch(TreeItem parent, File[] List) {
        for (File subItem : List) {
            String itemName = subItem.getName();
            int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
            if (pos > 0) { //Si pos es -1 el caracter no existe
                itemName = itemName.substring(0, pos); //Corta la cadena
            }
            TreeItem<String> branch = createBranch(itemName, parent);

            FileManager subFolder = new FileManager(subItem.getPath());
            File[] subFolderDocs = subFolder.getListOfFiles(subItem.getPath());
            for (File document : subFolderDocs) {
                addLeave(branch, document);
            }
        }

    }

    private void addLeave(TreeItem parent, File item) {
        String itemName = item.getName();
        int pos = itemName.lastIndexOf("."); //Busca el último . de la cadena
        if (pos > 0) { //Si pos es -1 el caracter no existe
            itemName = itemName.substring(0, pos); //Corta la cadena
        }
        createBranch(itemName, parent);
    }

    //Create branches
    private TreeItem<String> createBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
