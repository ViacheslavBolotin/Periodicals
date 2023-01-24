package com.epam.bolotin.periodicals.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    static final String INVALID_SYMBOLS = "\" has invalid symbols";
    private static final String EMPTY = "\" cannot be empty";
    private static final String FIELD = "field \"";
    private static final String LONG = "\" is too long";
    private static final String HAS_WRONG_LENGHT = "\" has wrong lenght";
    public static final String REGEX_MAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String REGEX_NAME = "[А-ґА-яà-ÿA-Za-z\\d]+";
    public static final String REGEX_TITLE = "[А-ґА-яà-ÿA-Za-z0-9\\d\\s\\w]*";
    public static final String REGEX_DATE = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-" +
            "(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))";

    public static final String REGEX_LOGIN = "^[A-Za-z0-9]+([A-Za-z0-9]*|[._-]?[A-Za-z0-9]+)*$";
    public static final String REGEX_AMMOUNT = "(([1-9][0-9]*)|0)?(\\.[0-9]*)?";
    public static final String REGEX_INT_NUMBER = "[0-9]+";

    /**
     *
     * @param email e-mail
     * @return error message or null if validation was successful
     */

    public static String validateEMail(String email) {
        if (email == null) {
            return FIELD + "e-mail" + EMPTY;
        } else if (email.length() > 100) {
            return FIELD + "e-mail" + LONG;
        }
        Pattern pattern = Pattern.compile(REGEX_MAIL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) return null;
        return FIELD + "e-mail" + INVALID_SYMBOLS;
    }

    /**
     *
     * @param field value of the input field
     * @param fieldName name of the input field
     * @param length maximum length of the variable in database
     * @return error message or null if validation was successful
     */
    public static String validateNames(String field, String fieldName, int length) {

        if (field.length() == 0) {
            return FIELD + fieldName + EMPTY;
        } else if (field.length() > length) {
            return FIELD + fieldName + LONG;
        }

        Pattern p = Pattern.compile(REGEX_NAME);
        Matcher m = p.matcher(field);

        if (!m.find()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }
        if (m.group().length() != field.length()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }

        return null;
    }

    /**
     *
     * @param field value of the input field
     * @param fieldName name of the input field
     * @param length maximum length of the variable in database
     * @return error message or null if validation was successful
     */
    public static String validateTitle(String field, String fieldName, int length) {

        if (field.length() == 0) {
            return FIELD + fieldName + EMPTY;
        } else if (field.length() > length) {
            return FIELD + fieldName + LONG;
        }

        Pattern p = Pattern.compile(REGEX_TITLE);
        Matcher m = p.matcher(field);

        if (!m.find()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }
        if (m.group().length() != field.length()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }
        p = Pattern.compile("[ ]+");
        m = p.matcher(field);

        if (m.find() && m.group().length() == field.length()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }
        return null;
    }

    /**
     * Validator for 'test name' 'question name' 'answer name' input fields
     * @param field value of the input field
     * @param fieldName name of the input field
     * @param length maximum length of the variable in database
     * @return error message or null if validation was successful
     */

    public static String validateSentences(String field, String fieldName, int length) {

        if (field.length() == 0) {
            return FIELD + fieldName + EMPTY;
        } else if (field.length() > length) {
            return FIELD + fieldName + LONG;
        }

        Pattern p = Pattern.compile("[À-ßà-ÿA-Za-z\\d\\s\\W]+");
        Matcher m = p.matcher(field);

        if (!m.find()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }
        if (m.group().length() != field.length()) {
            return FIELD + fieldName + INVALID_SYMBOLS;
        }

        p = Pattern.compile("[ ]+");
        m = p.matcher(field);

        if (m.find() && m.group().length() == field.length()) {

            return FIELD + fieldName + INVALID_SYMBOLS;

        }

        return null;
    }

    /**
     * validator for input fields that must contain only latin symbols or digits
     * @param field value of the field
     * @param length maximum length of the variable in database
     * @return error message or null if validation was successful
     */
    public static String validateLogin(String field,
                                       int length) {

        if (field.length() == 0) {
            return FIELD + field + EMPTY;
        } else if (field.length() > length) {
            return FIELD + field + LONG;
        }

        Pattern p = Pattern.compile(REGEX_LOGIN);
        Matcher m = p.matcher(field);

        if (m.matches()) return null;
        return FIELD + field + INVALID_SYMBOLS;

    }

    /**
     * validator for input fields that must contain only date
     * @param field value of the field
     * @return error message or null if validation was successful
     */
    public static String validateDate(String field) {

        Pattern p = Pattern.compile(REGEX_DATE);
        Matcher m = p.matcher(field);
        if (m.matches()) return null;
        return FIELD + field + INVALID_SYMBOLS;

    }

    /**
     * validator for input fields that must contain only ammount
     * @param field value of the field
     * @return error message or null if validation was successful
     */
    public static String validateAmount(String field) {

        Pattern p = Pattern.compile(REGEX_AMMOUNT);
        Matcher m = p.matcher(field);
        if (m.matches()) return null;
        return FIELD + field + INVALID_SYMBOLS;

    }

    /**
     * validator for input fields that must contain only int number
     * @param field value of the field
     * @return error message or null if validation was successful
     */
    public static String validateIntNumber(String field) {

        int length = 9;
        if (field.length() == 0) {
            return FIELD + field + EMPTY;
        } else if (field.length() > length) {
            return FIELD + field + LONG;
        }
        Pattern p = Pattern.compile(REGEX_INT_NUMBER);
        Matcher m = p.matcher(field);
        if (m.matches()) return null;
        return FIELD + field + INVALID_SYMBOLS;
    }

}
