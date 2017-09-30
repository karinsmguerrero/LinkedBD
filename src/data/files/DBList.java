package data.files;

import data.structures.generics.DoubleList;
import data.structures.generics.DoubleNode;
import data.structures.generics.Node;

public class DBList {
    private static DoubleList<TableList> dbList;

    public DBList(){
        setDbList();
    }

    public static DoubleList<TableList> getDbList() {
        return dbList;
    }

    /**
     * Crea un lista doble de tipo TableList, que contiene una lista circular de tipo FieldList
     * @see TableList
     * @see FieldList
     */
    private void setDbList() {
        FileManager flMng = new FileManager();
        dbList = flMng.folderToList();
    }


    public static void printAll(){
        DoubleNode<TableList> temp = dbList.getHead();
        for(int i = 0; i < dbList.getSize(); i++){
            System.out.println("Base de datos: " + temp.getValue().getFileDB() + "\n");
            System.out.println("pureba: " + temp.getValue().getFileName()+"\n");
            if (temp.getValue().getFileList().getHead() != null) {
                DoubleNode<FieldList> tempTable = temp.getValue().getFileList().getHead();
                for (int x = 0; x < temp.getValue().getFileList().getSize(); x++) {
                    System.out.println("Tabla: " + tempTable.getValue().getFileName() + "\n");
                    //Node<RowMaker> tempFields = tempTable.getValue().getObjectList().getHead();
                    System.out.println(new JsonManager(temp.getValue().getFileDB(), tempTable.getValue().getFileName()).ListToJsonString(tempTable.getValue().getObjectList(), tempTable.getValue().getFileName()) + "\n");

                    tempTable = tempTable.getNext();
                }
            }
            temp = temp.getNext();
        }
        System.out.println("-------------------------------");
    }
}
