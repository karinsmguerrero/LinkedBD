package configuration;

public class Setting {
    private static String mainFolderPath = "C:\\Users\\karin\\Documents\\LinkedBD";

    public static String getMainFolderPath() {
        return mainFolderPath;
    }

    public void setMainFolderPath(String mainFolderPath) {
        this.mainFolderPath = mainFolderPath;
    }
}
