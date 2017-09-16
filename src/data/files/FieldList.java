package data.files;

import data.structures.generics.SimpleList;

public class FieldList {
    private String fileName;
    private SimpleList<RowMaker> objectList;
    private String fileTable, fileDB;

    /**
     * Esta clase compone los nodos de la lista circular, y contiene como una de sus datos una lista simple
     * de tipo RowMaker, con los campos de una tabla
     * @param fileName: nombre del archivo JSON
     * @param fileTable: nombre de la tabla
     * @param fileDB: nombre de la base de datos
     * @see RowMaker
     * @see JsonManager#JsonToList() ;
     */
    public FieldList(String fileName, String fileTable, String fileDB){
        this.fileName = fileName;
        this.fileTable = fileTable;
        this.fileDB = fileDB;
    }

    public String getFileTable() {
        return fileTable;
    }

    public String getFileDB(){
        return this.fileDB;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SimpleList<RowMaker> getObjectList() {
        return objectList;
    }

    /**
     * Obtiene una lista simple y la guarda como en objectList
     * @see JsonManager#JsonToList() ;
     */
    private void setObjectList() {
        JsonManager jsMng = new JsonManager(fileDB, fileTable);
        objectList = jsMng.JsonToList();
    }
}
