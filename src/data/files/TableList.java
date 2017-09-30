package data.files;

import data.structures.generics.CircularDoubleList;

public class TableList {

    private String fileName;
    private CircularDoubleList<FieldList> fileList;
    private String fileDB;

    public TableList(String fileName, String fileDB){
        this.fileName = fileName;
        this.fileDB = fileDB;
        setObjectList();
    }

    public TableList(){

    }

    public String getFileDB(){
        return this.fileDB;
    }

    public void setFileDB(String fileDB){
        this.fileDB = fileDB;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CircularDoubleList<FieldList> getFileList() {
        return fileList;
    }

    /**
     * Crea un lista circular de tipo FieldList, que contiene una lista simple de tipo RowMaker
     * @see RowMaker
     * @see FieldList
     */
    private void setObjectList() {
        //public FileManager(String fileTable, String fileDB)
        FileManager flMng = new FileManager("", fileDB);
        fileList = flMng.fileToList();
    }

    public void setObjectList(CircularDoubleList<FieldList> fileList){
        this.fileList = fileList;
    }
}
