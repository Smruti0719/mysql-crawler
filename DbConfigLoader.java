package com.crawler.config;

import java.io.InputStream;
import org.json.JSONObject;

public class DbConfigLoader {

    public static JSONObject loadConfig() throws Exception {
        InputStream is = DbConfigLoader.class.getClassLoader()
                                             .getResourceAsStream("config.json");

        if (is == null) {
            throw new RuntimeException("config.json file not found in resources folder");
        }

        String json = new String(is.readAllBytes());
        return new JSONObject(json);
    }
}

