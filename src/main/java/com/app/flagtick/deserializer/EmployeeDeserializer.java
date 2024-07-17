package com.app.flagtick.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDeserializer extends JsonDeserializer<Map<String, Object>> {

    @Override
    public Map<String, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        Map<String, Object> employeeData = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> response = mapper.readValue(jsonParser, Map.class);
        Map<String, Object> data = (Map<String, Object>) response.get("data");

        employeeData.put("employee_name", data.get("employee_name"));
        employeeData.put("employee_salary", data.get("employee_salary"));
        employeeData.put("employee_age", data.get("employee_age"));

        return employeeData;
    }
}
