package data.files;


import configuration.Setting;
import data.structures.generics.CircularDoubleList;
import data.structures.generics.DoubleList;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileManager {
    private String filePath;
    private Charset utf8 = StandardCharsets.UTF_8;

    public FileManager(){
        this("");
    }

    /**
     *
     * @param filePath: ruta dentro de la carpeta principal, BD\\tabla
     */
    public FileManager(String filePath){
        this.filePath = Setting.getMainFolderPath() + "\\" + filePath;
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

    private CircularDoubleList<File> fileToListAux(){
        CircularDoubleList<File> lnkListFiles = new CircularDoubleList<>();
        File[] files = getListOfFilesFilesAux(this.filePath);
        for(File file: files){
            lnkListFiles.insertNodeToTail(file);
        }
        return lnkListFiles;
    }

    public CircularDoubleList<File> fileToList(){
        return fileToListAux();
    }

    private DoubleList<File> folderToListAux(){
        DoubleList<File> lnkListFolder = new DoubleList<>();
        File[] folders = getListOfFoldersAux();
        for(File folder: folders){
            lnkListFolder.addNodeToTheTail(folder);
        }
        return lnkListFolder;
    }

    public DoubleList<File> folderToList(){
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
