package com.epam.bolotin.periodicals.controller.util;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author: Viacheslav Bolotin
 * @date: 29.12.2022
 */
public class RequestUtils {
    // returns parameter value or 0 if there is no such parameter, or it's not a long number
    public static long getLongParameter(HttpServletRequest request, String parameterName) {
        try {
            return Long.parseLong(request.getParameter(parameterName));
        } catch(NumberFormatException nfe) {
            return 0;
        }
    }

    // returns parameter value or 0 if there is no such parameter, or it's not a long number
    public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String parameterName) {
        try {
            return new BigDecimal(request.getParameter(parameterName));
        } catch(NumberFormatException | NullPointerException e) {
            return new BigDecimal(0);
        }
    }

    // returns parameter value or 0 if there is no such parameter, or it's not a long number
    public static int getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch(NumberFormatException nfe) {
            return 0;
        }
    }

    // returns parameter value or empty string if there is no such parameter
    public static String getStringParameter(HttpServletRequest request, String parameterName) {
        if (request.getParameter(parameterName) == null) {
            return "";
        }else {
            return request.getParameter(parameterName);
        }
    }

    // returns parameter value or current date if there is no such parameter, or it's not a Timestamp
    public static LocalDate getDateTimeParameter(HttpServletRequest request, String parameterName) {
        try {
            return LocalDate.parse(request.getParameter(parameterName));
        } catch(NullPointerException e) {
            return LocalDate.now();
        }
    }

}
