package com.app.flagtick.services;

import com.app.flagtick.exception.PDFGenerationException;

public interface PDFService {
    void generatePDF(String filename, String text) throws PDFGenerationException;
}
