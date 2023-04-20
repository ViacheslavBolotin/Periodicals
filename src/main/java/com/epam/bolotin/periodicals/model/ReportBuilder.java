package com.epam.bolotin.periodicals.model;

import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Viacheslav Bolotin
 * @date: 01.03.2023
 */
public class ReportBuilder {

    public boolean reportPublicationPdf(HttpServletResponse response, List<RecordReportPublicationDto> reportPublications){

        String tempTopicName = "";
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        HashMap<String, Double> topicMap = (HashMap<String, Double>) reportPublications.
                stream().
                collect(Collectors.groupingBy(RecordReportPublicationDto::getTopicName,
                        Collectors.summingDouble(x -> x.getAmount().doubleValue())));

        topicMap = (HashMap<String, Double>) topicMap.entrySet().stream().
                sorted(Map.Entry.<String, Double>comparingByValue().reversed()).
                collect(Collectors.toMap(x-> x.getKey(), x-> x.getValue()));

        try {

            PdfWriter.getInstance(document, baos);
            document.open();
            BaseFont arial = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);
            Paragraph title = new Paragraph("Звіт по найбільш прибуткові періодичні видання", new Font(arial, 16));
            title.setAlignment(1);
            Chapter chapter = new Chapter(title, 1);
            chapter.setNumberDepth(0);
            chapter.add(new Paragraph("\n", new Font(arial, 12)));

            PdfPTable table = new PdfPTable(3);
            writeTitle(table, arial);

            tempTopicName = "";
            for (RecordReportPublicationDto item: reportPublications) {

                if (!tempTopicName.equals(item.getTopicName())) {

                    tempTopicName = item.getTopicName();
                    writeGroup(table, arial, tempTopicName, topicMap.get(tempTopicName));
                }

                writeRow(table, arial, item);
            }

            chapter.add(table);
            chapter.add(new Paragraph("\n", new Font(arial, 12)));
            chapter.add(new Paragraph("© "+ LocalDate.now() +" Periodicals", new Font(arial, 6)));
            document.add(chapter);
            document.close();

            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the content length
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                baos.writeTo(os);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void writeTitle(PdfPTable table, BaseFont arial) {

        Font font = new Font(arial, 14,1, BaseColor.GRAY);

        Paragraph title1 = new Paragraph("Тема / назва публікації", font);
        PdfPCell cell = new PdfPCell(title1);
        cell.setHorizontalAlignment(1);
        table.addCell(cell);

        Paragraph title2 = new Paragraph("Кількість публікацій", font);
        cell.setPhrase(title2);
        table.addCell(cell);

        Paragraph title3 = new Paragraph("Загальний прибуток", font);
        cell.setPhrase(title3);
        table.addCell(cell);
    }

    private void writeGroup(PdfPTable table, BaseFont arial, String topicName, Double amount) {

        Font font = new Font(arial, 14,1, BaseColor.BLUE);

        Paragraph col1 = new Paragraph(topicName, font);
        PdfPCell cell = new PdfPCell(col1);
        cell.setPhrase(col1);
        cell.setHorizontalAlignment(0);
        cell.setColspan(2);
        table.addCell(cell);

        Paragraph col2 = new Paragraph(amount.toString(), font);
        cell.setPhrase(col2);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);
    }

    private void writeRow(PdfPTable table, BaseFont arial, RecordReportPublicationDto item) {

        Paragraph col1 = new Paragraph(item.getTitle(), new Font(arial, 12));
        PdfPCell cell = new PdfPCell(col1);
        cell.setPhrase(col1);
        cell.setHorizontalAlignment(0);
        cell.setColspan(1);
        table.addCell(cell);

        Paragraph col2 = new Paragraph(item.getQuantity().toString());
        cell.setPhrase(col2);
        cell.setHorizontalAlignment(2);
        table.addCell(cell);

        Paragraph col3 = new Paragraph(item.getAmount().toString());
        cell.setPhrase(col3);
        table.addCell(cell);
    }
}
