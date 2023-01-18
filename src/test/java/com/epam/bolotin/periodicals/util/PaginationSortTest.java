package com.epam.bolotin.periodicals.util;

import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Viacheslav Bolotin
 * @date: 10.01.2023
 */
class PaginationSortTest {

    PaginationSort ps;

    @BeforeEach
    public void setUp() {
        ps = new PaginationSort();
    }

    @Test
    void testPaginationCalc() {
        ps.setSortType(null);
        ps.setCurrentPage(2);
        ps.setNumberOfItems(15);
        ps.calc();
        assertEquals(3,ps.getNumberOfPages());
        assertEquals(1,ps.getPrevPage());
        assertEquals(3,ps.getNextPage());
        assertEquals(5,ps.getStartFrom());
    }
}