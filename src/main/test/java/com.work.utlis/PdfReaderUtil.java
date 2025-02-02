import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PdfReaderUtil {

    public static List<String> extractTextFromPdf(String filePath) throws IOException {
        List<String> extractedLines = new ArrayList<>();
        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripper textStripper = new PDFTextStripper() {
                @Override
                protected void writePage() throws IOException {
                    List<TextPosition> textPositions = new ArrayList<>();
                    for (List<TextPosition> textLine : charactersByArticle) {
                        textPositions.addAll(textLine);
                    }

                    // Sort by Y (top-to-bottom) and then by X (left-to-right)
                    textPositions.sort(Comparator
                            .comparing(TextPosition::getYDirAdj) // Top to bottom (lower Y comes first)
                            .thenComparing(TextPosition::getXDirAdj)); // Left to right

                    // Build sorted lines
                    StringBuilder currentLine = new StringBuilder();
                    double lastY = -1;

                    for (TextPosition text : textPositions) {
                        if (lastY != -1 && Math.abs(text.getYDirAdj() - lastY) > 5) {
                            extractedLines.add(currentLine.toString().trim());
                            currentLine.setLength(0);
                        }
                        currentLine.append(text.getUnicode());
                        lastY = text.getYDirAdj();
                    }

                    if (currentLine.length() > 0) {
                        extractedLines.add(currentLine.toString().trim());
                    }
                }
            };

            textStripper.setSortByPosition(true);
            textStripper.getText(document);
        }

        return extractedLines;
    }
}
