package data.files;


import configuration.Setting;
import data.structures.generics.CircularDoubleList;
import data.structures.generics.DoubleList;
import javafx.scene.control.Tab;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileManager {
    private String filePath;
    private String fileTable;
    private String fileDB;

    public FileManager(){
        this("", "");
    }

    /**
     *
     * @param fileTable: nombre de la tabla
     * @param fileDB: nombre de la base de datos
     */
    public FileManager(String fileTable, String fileDB){
        this.filePath = Setting.getMainFolderPath() + "\\" + fileDB + "\\" + fileTable;
        this.fileDB = fileDB;
        this.fileTable = fileTable;
    }

    private void printAllFiles(){
        File folder = new File(filePath);
        String files;
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++){
            if (listOfFiles[i].isFile()){
                files = listOfFiles[i].getName();
                System.out.println(files);
            }
        }
    }

    private CircularDoubleList<FieldList> fileToListAux(){
        CircularDoubleList<FieldList> lnkListFiles = new CircularDoubleList<>();
        System.out.println("file To list aux: " + this.filePath);
        File[] files = getListOfFilesFilesAux(this.filePath);
        if(files != null) {
            for (File file : files) {
                String tableName = file.getName();
                int pos = tableName.lastIndexOf("."); //Busca el último . de la cadena
                if (pos > 0) { //Si pos es -1 el caracter no existe
                    tableName = tableName.substring(0, pos); //Corta la cadena
                }
                FieldList fldList = new FieldList(tableName, tableName, fileDB);
                lnkListFiles.insertNodeToTail(fldList);
            }
        }
        return lnkListFiles;
    }

    public CircularDoubleList<FieldList> fileToList(){
        return fileToListAux();
    }

    private DoubleList<TableList> folderToListAux(){
        DoubleList<TableList> lnkListFolder = new DoubleList<>();
        File[] folders = getListOfFoldersAux();
        for(File folder: folders){
            lnkListFolder.addNodeToTheTail(new TableList(folder.getName(), folder.getName()));
        }
        return lnkListFolder;
    }

    public DoubleList<TableList> folderToList(){
        return folderToListAux();
    }

    private File[] getListOfFilesFilesAux(String folderPath){
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public File[] getListOfFiles(String folderPath){
        return getListOfFilesFilesAux(folderPath);
    }

    private File[] getListOfFoldersAux(){
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles(File::isDirectory);
        return listOfFiles;
    }

    public File[] getListOfFolders(){
        return getListOfFoldersAux();
    }

    private void createFolderAux(String folderName){
        String newFile = "\\" + folderName;
        File file = new File(newFile);
        file.mkdir();
    }

    public void createFolder(String name){
        createFolderAux(name);
    }

    /**
     *
     * @param tableName: nombre del archivo
     * @param nameBD: nombre de la BD a la que pertenece el archivo
     * @param text: texto para escribir en el archivo
     */
    private void writeToFileAux(String tableName, String nameBD, String text){
        System.out.println("write to file: " + this.filePath + tableName + ".json");
        String fileRoute = this.filePath + "\\" + tableName + ".json";
        //crea el archivo si no existe
        File file = new File(this.filePath + "\\" + nameBD);
        FileWriter flWriter = null;
        BufferedWriter bfWriter = null;
        try {
            if(!file.exists())
            {
                createFolderAux(nameBD);
            }
            System.out.println("writing to: " + fileRoute);
            flWriter = new FileWriter(fileRoute, false);

            bfWriter = new BufferedWriter(flWriter);
            bfWriter.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bfWriter != null)
                    bfWriter.close();

                if (flWriter != null)
                    flWriter.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void writeToExistentFileAux(String fileRoute, String text){
        FileWriter flWriter = null;
        BufferedWriter bfWriter = null;
        try {
            flWriter = new FileWriter(fileRoute, false);

            bfWriter = new BufferedWriter(flWriter);
            bfWriter.write(text);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bfWriter != null)
                    bfWriter.close();

                if (flWriter != null)
                    flWriter.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeToFile(String fileName, String filePath, String json){
        writeToFileAux(fileName, filePath, json);
    }

    public void writeToExistentFile(String path, String text){
        writeToExistentFileAux(path, text);
    }

    public String readFile(String path) throws IOException {
        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(path);
            buffer = new BufferedReader(reader);
            return buffer.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
