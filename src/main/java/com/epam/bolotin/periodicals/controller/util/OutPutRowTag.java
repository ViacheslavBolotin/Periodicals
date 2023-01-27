package com.epam.bolotin.periodicals.controller.util;

import com.epam.bolotin.periodicals.model.db.entity.Topic;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

public class OutPutRowTag extends SimpleTagSupport {

    private Topic message;

    public void setMessage(Topic msg) {
        this.message = msg;
    }

    @Override
    public void doTag() throws JspException, IOException {

        StringWriter sw = new StringWriter();

        if (message != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            out.println("<td> "+message.getId() + " </td>"+ "<td> "+message.getName() + " </td>" );
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }

    }

}
