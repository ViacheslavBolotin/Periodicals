package com.epam.bolotin.periodicals.util;

import com.epam.bolotin.periodicals.model.Validator;
import org.junit.jupiter.api.Test;

import static com.epam.bolotin.periodicals.model.Validator.validateEMail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 10.01.2023
 */
public class ValidatorTest {

    @Test
    void validateLoginSuccess() {
        assertEquals(null, Validator.validateLogin("12345678901234567890",  20));
    }

    @Test
    void validateNamesFailNull() {
        assertNotEquals(null, validateEMail(null));
    }

    @Test
    void validateEMailSuccess() {
        assertEquals(null, validateEMail("123@gmail.com"));
    }
    @Test
    void validateEMailFailREGEX() {
        assertNotEquals(null, validateEMail("123.gmail.com"));
    }
    @Test
    void validateEMailFailNull() {
        assertNotEquals(null, validateEMail(null));
    }

    @Test
    void validateNamesFailREGEX() {
        assertNotEquals(null, validateEMail("+3804411122333"));
    }

    @Test
    void validateSentencesSuccess() {
        assertEquals(null, Validator.validateSentences("12345678901234567890", "password", 45));
    }

    @Test
    void validateSentencesFailLenght() {
        assertNotEquals(null, Validator.validateSentences("12345678901234567890", "password", 15));
    }

    @Test
    void validateLoginFailData() {
        assertNotEquals(null, Validator.validateLogin("1234567890123456*78A",  20));
    }


    @Test
    void validateDateSuccess() {
        assertNotEquals(null,Validator.validateDate("2022-06-27 15:31:14"));
    }

    @Test
    void validateDateFailData() {
        assertNotEquals(null,Validator.validateDate("2022-06-127 15:31:14"));
    }

    @Test
    void validateAmmountSuccess() {
        assertEquals(null,Validator.validateAmount("105.93"));
    }

    @Test
    void validateAmmountFailData() {
        assertNotEquals(null,Validator.validateAmount("105,93"));
    }

    @Test
    void validateIntNumberSuccess() {
        assertEquals(null,Validator.validateIntNumber("10593"));
    }

    @Test
    void validateIntNumberFailData() {
        assertNotEquals(null,Validator.validateIntNumber("10A93"));
    }

}
