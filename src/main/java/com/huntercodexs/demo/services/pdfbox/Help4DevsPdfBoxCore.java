package com.huntercodexs.demo.services.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.ColorsToPdfBox.color;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontNameToPdfBox.fontName;
import static com.huntercodexs.demo.services.pdfbox.Help4DevsPdfBoxElements.FontSizeToPdfBox.fontSize;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public abstract class Help4DevsPdfBoxCore extends Help4DevsPdfBoxElements {

    protected static int assertBarcodeFormOffsetX(String input, int currentX, int adjustX) {

        int agencyLen = input.length();
        int paddingSize = 32-agencyLen;

        StringBuilder agencyPadding = new StringBuilder();

        for (int n = 0; n < paddingSize; n++) {
            agencyPadding.append("0");
        }

        float dynamicOffsetX = (float) ((agencyPadding.length()) * 4.5);

        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-') {
                count++;
            }
            if (input.charAt(i) == '/') {
                count++;
            }
            if (input.charAt(i) == '.') {
                count++;
            }
            if (input.charAt(i) == ',') {
                count++;
            }
            if (input.charAt(i) == ' ') {
                count++;
            }
        }
        int assertOffsetX = count * 2;

        if (assertOffsetX == 0) {
            assertOffsetX = -1;
        }

        if (input.matches("^.*([A-Z]+).*$")) {
            return ((int) (dynamicOffsetX+assertOffsetX) - 2) + currentX + adjustX;
        }

        return (int) (dynamicOffsetX+assertOffsetX) + currentX + adjustX;
    }

    /*TODO: REFACTOR THIS METHOD TO GET MORE ORGANIZATION SEPARATING THE STREAM TEXT, IMAGE, RECTANGLE E ETC...*/
    protected static PDPageContentStream contentStream(
            String option,
            PDPage page,
            PDDocument document,
            PdfBoxPage pageSettings,
            PdfBoxContainer rectSettings,
            PDPageContentStream contentStream
    ) {
        try {

            if (contentStream == null) {
                contentStream = new PDPageContentStream(document, page);
            }

            switch (option) {

                case "new":
                    return contentStream;

                case "text":
                    contentStream.beginText();
                    contentStream.setNonStrokingColor(color(pageSettings.getFontColor()));
                    contentStream.setLeading(pageSettings.getLineHeight());
                    contentStream.newLineAtOffset(pageSettings.getOffsetX(), pageSettings.getOffsetY());
                    contentStream.setFont(fontName(pageSettings.getFontName()), fontSize(pageSettings.getFontSize()));
                    return contentStream;

                case "rec-empty":
                    //TODO: CHECK AND FIX THIS BUG
                    /*contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.setNonStrokingColor(0,0,0);*/
                    return contentStream;

                case "rec-fill":
                    contentStream.setNonStrokingColor(color(rectSettings.getBackColor()));
                    contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.fill();
                    contentStream.setNonStrokingColor(0,0,0);
                    return contentStream;

                case "rec-border":
                    contentStream.setLineWidth(rectSettings.getBorderWidth());
                    contentStream.setStrokingColor(color(rectSettings.getBorderColor()));
                    contentStream.addRect(
                            rectSettings.getOffsetX(),
                            rectSettings.getOffsetY(),
                            rectSettings.getWidth(),
                            rectSettings.getHeight());
                    contentStream.closeAndStroke();
                    contentStream.setStrokingColor(0,0,0);
                    return contentStream;

            }

            return contentStream;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
