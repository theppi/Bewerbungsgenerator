package de.theppi.generator.parser;

import de.theppi.generator.data.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class JsonParser {
    
    public static final Config parseConfig() throws IOException {
        Config config = new Config();
        
        JSONObject obj = new JSONObject(getConfigFile()) {
            public String getString(String key) {
                return has(key) ? super.getString(key) : null;
            }
        };
        
        config.setBaseFolder(obj.getString("baseFolder"));
        config.setCoverLetter(obj.getString("coverLetter"));
        config.setCvFile(obj.getString("cv"));
        config.setLic(obj.getString("lic"));
        
        return config;
    }
    
    private static String getConfigFile() throws IOException {
        File configFile = new File("configuration.json");
        FileReader fr = new FileReader(configFile);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line = null;
        
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
