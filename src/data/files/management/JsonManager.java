package data.files.management;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    private void addJsonObjectAux(String key, String type, String FK, boolean required, String defaultValue){
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
        FileManager fm = new FileManager(jsonPath + documentName);
        fm.writeToFile( documentName, jsonPath,fieldName.toJSONString());
    }

    public void addJsonObject(String key, String type, String FK, boolean required, String defaultValue){
        addJsonObjectAux(key, type, FK, required, defaultValue);
    }
}
