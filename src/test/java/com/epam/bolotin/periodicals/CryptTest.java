package com.epam.bolotin.periodicals;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author: Viacheslav Bolotin
 * @date: 19.01.2023
 */

public class CryptTest {

    @Test
    public void testHashPassword() throws AppException {

        assertEquals(Crypt.hashPassword("123456SD"), "6883545352515049");
    }

    @Test
    public void testCheckPassword() throws AppException {

        String passwordCrypt = Crypt.hashPassword("123456SD");
        assertEquals(Crypt.checkPassword("123456SD", passwordCrypt), true);
    }

}
