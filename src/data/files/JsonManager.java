package data.files;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class JsonManager {
    private String jsonPath;
    private String documentName;
    private FileManager fileManager;

    public JsonManager(String jsonPath, String documentName){
        this.jsonPath = jsonPath;
        this.documentName = documentName;
        this.fileManager = new FileManager(jsonPath);
    }

    private void createJsonFile(String key, JSONArray value){
        fileManager.createFolder(this.documentName);
    }

    private void addJsonObjectAux(String key, String type, String FK, String required, String defaultValue){
        JSONObject fieldType = new JSONObject();
        fieldType.put("Type", type);
        JSONObject fieldFK = new JSONObject();
        fieldFK.put("FK", FK);
        JSONObject fieldIsRequired = new JSONObject();
        fieldIsRequired.put("Required", required);
        JSONObject fieldDefault = new JSONObject();
        fieldDefault.put("Default", defaultValue);
        JSONArray fieldsArray = new JSONArray();
        fieldsArray.add(fieldType);
        fieldsArray.add(fieldFK);
        fieldsArray.add(fieldIsRequired);
        fieldsArray.add(fieldDefault);

        JSONObject fieldName = new JSONObject();
        fieldName.put(key,fieldsArray);

        JSONArray fields = new JSONArray();
        fields.add(fieldName);
        FileManager fm = new FileManager(jsonPath + documentName);
        fm.writeToFile( documentName, jsonPath,fields.toJSONString());
    }

    public void addJsonObject(String key, String type, String FK, String required, String defaultValue){
        addJsonObjectAux(key, type, FK, required, defaultValue);
    }

    private void readJsonFileAux(String filePath) throws ParseException, IOException {
        /*
        String path = filePath + "\\" + documentName + ".json";
        FileManager fm = new FileManager(path);
        String text = fm.readFile(path);

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("C:\\Users\\karin\\Desktop\\prueba.json");

        JSONObject jsonRoot = (JSONObject) parser.parse(reader);

        JSONArray array = (JSONArray) jsonRoot.get("karina");
        Iterator<String> iterator = array.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
        String path = filePath + "\\" + documentName + ".json";
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("C:\\Users\\karin\\Desktop\\prueba.json"));

        JSONObject jsonObject = (JSONObject) obj;

        // Now we try to take the data from "presentationSlides" array
        JSONArray slideContent = (JSONArray) jsonObject.get("nombre");
        Iterator i = slideContent.iterator();

        while (i.hasNext()) {
            System.out.println(i.next());
            JSONObject slide = (JSONObject) i.next();
            String title = (String)slide.get("CodeNumber");
            // Here I try to take the title element from my slide but it doesn't work!
            System.out.println(title);
        }

    }

    public void readJsonFile() throws IOException, ParseException {
        readJsonFileAux(jsonPath);
    }

}
