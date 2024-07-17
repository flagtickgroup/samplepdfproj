package com.app.flagtick.services.impl;

import com.app.flagtick.exception.PDFGenerationException;
import com.app.flagtick.services.PDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFServiceImpl implements PDFService {

    @Override
    public void generatePDF(String filename, String text) throws PDFGenerationException {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(text);
            contentStream.endText();
            contentStream.close();

            document.save(filename);
            document.close();

            System.out.println("PDF generated successfully.");
        } catch (IOException e) {
            throw new PDFGenerationException("Failed to generate PDF", e);
        }
    }
}
