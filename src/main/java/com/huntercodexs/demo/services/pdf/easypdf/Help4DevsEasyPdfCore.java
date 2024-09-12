package com.huntercodexs.demo.services.pdf.easypdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.ColorsToEasyPdf.color;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.FontNameToEasyPdf.fontName;
import static com.huntercodexs.demo.services.pdf.easypdf.Help4DevsEasyPdfElements.FontSizeToEasyPdf.fontSize;

/**
 * @author huntercodexs (powered by jereelton-devel)
 * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
 * @implNote This class use as "pdfbox 2.0.0" from org.apache.pdfbox base process to PDF files handler
 * */
@Slf4j
@Service
public abstract class Help4DevsEasyPdfCore extends Help4DevsEasyPdfElements {

    private static void contentStreamBeginText(
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.beginText();
            contentStream.setNonStrokingColor(color(pageSettings.getFontColor()));
            contentStream.setLeading(pageSettings.getLineHeight());
            contentStream.newLineAtOffset(pageSettings.getOffsetX(), pageSettings.getOffsetY());
            contentStream.setFont(fontName(pageSettings.getFontName()), fontSize(pageSettings.getFontSize()));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void contentStreamAddText(String text, PDPageContentStream contentStream) {
        try {
            contentStream.showText(text);
            contentStream.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void contentStreamEndText(PDPageContentStream contentStream) {
        try {
            contentStream.endText();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

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

    protected static PDPageContentStream contentStreamInit(
            PDPage page,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        try {

            if (contentStream == null) {
                return new PDPageContentStream(document, page);
            }

            return contentStream;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static void contentStreamText(
            String line,
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStreamBeginText(pageSettings, contentStream);
            contentStreamAddText(line, contentStream);
            contentStreamEndText(contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static void contentStreamText(
            String[] lines,
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStreamBeginText(pageSettings, contentStream);

            for (String line : lines) {
                contentStreamAddText(line, contentStream);
            }

            contentStreamEndText(contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static void contentStreamText(
            List<String> lines,
            EasyPdfPage pageSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStreamBeginText(pageSettings, contentStream);

            for (String line : lines) {
                contentStreamAddText(line, contentStream);
            }

            contentStreamEndText(contentStream);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static PDPageContentStream contentStreamFillRect(
            EasyPdfContainer rectSettings,
            PDPageContentStream contentStream
    ) {
        try {

            contentStream.setNonStrokingColor(color(rectSettings.getBackColor()));
            contentStream.addRect(
                    rectSettings.getOffsetX(),
                    rectSettings.getOffsetY(),
                    rectSettings.getWidth(),
                    rectSettings.getHeight());
            contentStream.fill();
            contentStream.setNonStrokingColor(0,0,0);
            return contentStream;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static PDPageContentStream contentStreamBorderRect(
            EasyPdfContainer rectSettings,
            PDPageContentStream contentStream
    ) {
        try {

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

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static PDPageContentStream contentStreamEmptyRect(PDPageContentStream contentStream) {
        try {

            //TODO: CHECK AND FIX THIS BUG
            /*contentStream.addRect(
                    rectSettings.getOffsetX(),
                    rectSettings.getOffsetY(),
                    rectSettings.getWidth(),
                    rectSettings.getHeight());
            contentStream.setNonStrokingColor(0,0,0);*/
            return contentStream;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static PDPageContentStream contentStreamImage(
            String imageFilePath,
            int width,
            int height,
            int offsetX,
            int offsetY,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        if (imageFilePath == null) {
            System.out.println("Missing Image File Path in the contentStreamImage call");
            return contentStream;
        }

        try {
            PDImageXObject image = PDImageXObject.createFromFile(imageFilePath, document);
            contentStream.drawImage(image, offsetX, offsetY, width, height);
            return contentStream;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }

    protected static PDPageContentStream contentStreamBufferedImage(
            int dpi,
            int width,
            int height,
            float offsetX,
            float offsetY,
            BufferedImage bufferedImage,
            PDDocument document,
            PDPageContentStream contentStream
    ) {
        if (bufferedImage == null) {
            System.out.println("Missing Image File Path in the contentStreamBufferedImage call");
            return contentStream;
        }

        try {
            PDImageXObject image = JPEGFactory.createFromImage(document, bufferedImage, 1, dpi);
            contentStream.drawImage(image, offsetX, offsetY, width, height);
            return contentStream;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage());
        }
    }
}
