package com.epam.bolotin.periodicals.controller.util;

import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class OutPutRowTag extends SimpleTagSupport {

    private RecordReportPublicationDto recordReport;

    public void setRecordReport(RecordReportPublicationDto inputRecordReport) {
        this.recordReport = inputRecordReport;
    }

    @Override
    public void doTag() throws JspException, IOException {

        if (recordReport != null) {
            /* Use recordReport from attribute */
            JspWriter out = getJspContext().getOut();
            out.println("<td> " + recordReport.getTopicName() + " </td>" +
                        "<td> " + recordReport.getTitle() + " </td>"+
                        "<td> " + recordReport.getQuantity() + " </td>"+
                        "<td> " + recordReport.getAmount() + " </td>");
        }
    }
}
