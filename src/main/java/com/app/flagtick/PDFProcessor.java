package com.app.flagtick;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldTree;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PDFProcessor {
    private static final String FT_TEXT_FIELD = "Tx";

    public void processPDF(Map<String, String> templateTaxes, Map<String, Object> employeeData) {
        HashMap<String, String> transformedEmployeeData = new HashMap<>();
        for (Map.Entry<String, Object> entry : employeeData.entrySet()) {
            String originalKey = entry.getKey();
            String newKey = originalKey + "[0]";
            transformedEmployeeData.put(newKey, String.valueOf(entry.getValue()));
        }
        employeeData.clear();
        employeeData.putAll(transformedEmployeeData);

        String template = templateTaxes.get("2023");
        System.out.println("Template: " + template);

        try {
            URL res = getClass().getClassLoader().getResource(template);
            if (res == null) {
                System.out.println("Resource not found: " + template);
                return;
            }
            File file = Paths.get(res.toURI()).toFile();
            PDDocument document = Loader.loadPDF(file);
            try {
                PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
                if (acroForm != null) {
                    PDFieldTree fieldTree = acroForm.getFieldTree();
                    for (PDField field : fieldTree) {
                        String fullyQualifiedName = field.getFullyQualifiedName();
                        String partialName = field.getPartialName();
                        String fieldType = field.getFieldType();
                        System.out.println("Field name: " + fullyQualifiedName);
                        System.out.println("Field Partial Name: " + partialName);
                        System.out.println("Field type: " + fieldType);
                        System.out.println("Field class: " + field.getClass().getSimpleName());

                        if (fieldType != null && fieldType.equals(FT_TEXT_FIELD) && employeeData.containsKey(partialName)) {
                            field.setValue((String) employeeData.get(partialName));
                        }
                    }
                    acroForm.flatten();
                } else {
                    System.out.println("The PDF does not contain any form fields.");
                }
                String filename = UUID.randomUUID() + ".pdf";

                // Define the target folder relative to your project root
                String targetFolder = "pdfs";
                File folder = new File(targetFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                File savedFile = new File(folder, filename);
                document.save(savedFile.getAbsolutePath());
            } finally {
                document.close();
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
