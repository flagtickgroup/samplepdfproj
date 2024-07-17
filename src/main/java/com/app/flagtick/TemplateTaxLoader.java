package com.app.flagtick;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TemplateTaxLoader {
    public Map<String, String> loadTemplateTaxes(Properties prop) {
        Map<String, String> templateTaxes = new HashMap<>();
        String templates = prop.getProperty("templates");
        if (templates != null && !templates.isEmpty()) {
            for (String kv : templates.split(",")) {
                String[] a = kv.split(":");
                if (a.length == 2) {
                    templateTaxes.put(a[0], a[1]);
                }
            }
        }
        return templateTaxes;
    }
}
