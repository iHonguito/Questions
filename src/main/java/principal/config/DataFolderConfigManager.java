package principal.config;

import principal.QuestionsPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class DataFolderConfigManager {
    protected QuestionsPlugin plugin;
    protected String folderName;
    protected List<CustomConfig> configs;

    public DataFolderConfigManager(QuestionsPlugin plugin, String folderName){
        this.plugin = plugin;
        this.folderName = folderName;
        this.configs = new ArrayList<>();
        configure();
    }

    public void configure(){
        createFolder();
        reloadConfigs();
    }

    public void reloadConfigs(){
        this.configs = new ArrayList<>();
        registerConfigFiles();
        loadConfigs();
    }

    public void createFolder(){
        File folder;
        try {
            folder = new File(plugin.getDataFolder() + File.separator + folderName);
            if (!folder.exists()){
                folder.mkdir();
            }
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    public void saveConfigFiles(){
        for (CustomConfig configFile : configs){
            configFile.saveConfig();
        }
    }

    public void registerConfigFiles(){
        String path = plugin.getDataFolder() + File.separator + folderName;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles){
            if (file.isFile()){
                registerConfigFile(file.getName());
            }
        }
    }

    public CustomConfig getConfigFile(String pathName){
        for (CustomConfig configFile : configs){
            if (configFile.getPath().equals(pathName)){
                return configFile;
            }
        }
        return null;
    }

    public CustomConfig registerConfigFile(String path) {
        CustomConfig config = new CustomConfig(path, folderName, plugin, true);
        config.registerConfig();
        configs.add(config);
        return config;
    }

    public List<CustomConfig> getConfigs(){
        return configs;
    }

    public abstract void loadConfigs();
    public abstract void saveConfigs();
}
