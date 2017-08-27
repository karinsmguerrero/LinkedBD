package data.files.management;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileManager {
    private String filePath;
    private Charset utf8 = StandardCharsets.UTF_8;

    public FileManager(String filePath){
        this.filePath = filePath;
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

    private void createFolderAux(String folderName, String newfolderPath){
        String newFile = newfolderPath + folderName;
        File file = new File(newFile);
        file.mkdir();
    }

    public void createFolder(String name){
        createFolderAux(File.pathSeparator + name, filePath);
    }

    private void writeToFileAux(String fileName, String filePath, String text){
        String fileRoute = filePath + "\\" + fileName + ".json";
        File file = new File(filePath + "\\" + fileName);
        FileWriter flWriter = null;
        BufferedWriter bfWriter = null;
        try {
            if(!file.exists())
            {
                String folderName = filePath.substring(filePath.lastIndexOf("\\"));
                createFolderAux(folderName, filePath.substring(0, filePath.lastIndexOf("\\")));
            }
            flWriter = new FileWriter(fileRoute, true);

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
