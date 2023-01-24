package com.epam.bolotin.periodicals.controller.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: Viacheslav Bolotin
 * @date: 29.12.2022
 */
public class RequestUtils {
    // returns parameter value or 0 if there is no such parameter or it's not a long number
    public static long getLongParameter(HttpServletRequest request, String parameterName) {
        try {
            return Long.parseLong(request.getParameter(parameterName));
        } catch(NumberFormatException nfe) {
            return 0;
        }
    }

    // returns parameter value or null if there is no such parameter or it's not a long number
    public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String parameterName) {
        try {
            return new BigDecimal(request.getParameter(parameterName));
        } catch(NumberFormatException | NullPointerException e) {
            return null;
        }
    }

    // returns parameter value or 0 if there is no such parameter or it's not a long number
    public static int getIntParameter(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch(NumberFormatException nfe) {
            return 0;
        }
    }

    // returns parameter value or null if there is no such parameter
    public static String getStringParameter(HttpServletRequest request, String parameterName) {
        if (request.getParameter(parameterName) == null) {
            return "";
        }else {
            return request.getParameter(parameterName);
        }
    }

}
