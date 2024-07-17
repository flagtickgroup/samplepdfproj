package com.app.flagtick;

import com.app.flagtick.deserializer.EmployeeDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class EmployeeService {
    public Map<String, Object> getEmployeeData(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Map.class, new EmployeeDeserializer());
        mapper.registerModule(module);

        return mapper.readValue(result, Map.class);
    }
}
