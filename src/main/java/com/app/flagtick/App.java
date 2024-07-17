package com.app.flagtick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            PropertiesLoader propertiesLoader = new PropertiesLoader();
            Properties prop = propertiesLoader.loadProperties("application.properties");

            TemplateTaxLoader templateTaxLoader = new TemplateTaxLoader();
            Map<String, String> templateTaxes = templateTaxLoader.loadTemplateTaxes(prop);

            EmployeeService employeeService = new EmployeeService();
            Map<String, Object> employeeData = employeeService.getEmployeeData("https://dummy.restapiexample.com/api/v1/employee/1");

            PDFProcessor pdfProcessor = new PDFProcessor();
            pdfProcessor.processPDF(templateTaxes, employeeData);

            LOGGER.info("Templates loaded successfully: {}", templateTaxes);
        } catch (IOException e) {
            LOGGER.error("Error loading application.properties", e);
        }
    }
}

