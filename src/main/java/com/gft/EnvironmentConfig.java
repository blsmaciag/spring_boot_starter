package com.gft;

import java.util.HashMap;
import java.util.Map;

public enum EnvironmentConfig {

    DEV("starter_dev.properties"), UAT("starter_uat.properties");

    private String propertyFile;

    EnvironmentConfig(String propertyFile) {
        this.propertyFile = propertyFile;
    }

    private static Map<String, String> envToConfig = prepareEnvToConfigMap();

    private static Map<String, String> prepareEnvToConfigMap() {
        Map<String, String> propertyFilesMapping = new HashMap<>();
        propertyFilesMapping.put(EnvironmentConfig.DEV.name(), EnvironmentConfig.DEV.getPropertyFile());
        propertyFilesMapping.put(EnvironmentConfig.UAT.name(), EnvironmentConfig.UAT.getPropertyFile());
        return propertyFilesMapping;
    }

    public String getPropertyFile() {
        return propertyFile;
    }

    public static String getPropertyFile(String env) throws Exception {
        String upperCaseEnv = env.toUpperCase();
        if (envToConfig.containsKey(upperCaseEnv)) {
            return envToConfig.get(upperCaseEnv);
        } else {
            throw new Exception("Environment " + env + " in invalid. Correct values are: " + EnvironmentConfig.DEV.name() + ", " + EnvironmentConfig.UAT.name());
        }
    }
}

