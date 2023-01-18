package com.epam.bolotin.periodicals.controller.util;

/**
 * @author: Viacheslav Bolotin
 * @date: 29.12.2022
 */
public class PaginationSort {
    public static final int MAX_ITEM_ON_PAGE = 5;
    public static final String WITHOUT_SORT = "ws";
    public static final String NAME_STRAIGHT = "nms";
    public static final String NAME_FORWARD = "nmf";
    public static final String BALANCE_STRAIGHT = "bs";
    public static final String BALANCE_FORWARD = "bf";

    private long topicFilter;
    private String titleFilter;

    private long userFilter;

    private int currentPage;
    private int numberOfPages;
    private long numberOfItems;
    private int prevPage;
    private int nextPage;
    private String sortType;
    private long startFrom;

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public long getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(long numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public long getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(long startFrom) {
        this.startFrom = startFrom;
    }

    public long getTopicFilter() {
        return topicFilter;
    }

    public void setTopicFilter(long topicFilter) {
        this.topicFilter = topicFilter;
    }

    public String getTitleFilter() {
        return titleFilter;
    }

    public void setTitleFilter(String titleFilter) {
        this.titleFilter = titleFilter;
    }

    public long getUserFilter() {
        return userFilter;
    }

    public void setUserFilter(long userFilter) {
        this.userFilter = userFilter;
    }

    @Override
    public String toString() {
        return "PaginationSort{" +
                "currentPage=" + currentPage +
                ", numberOfPages=" + numberOfPages +
                ", numberOfItems=" + numberOfItems +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", sortType='" + sortType + '\'' +
                ", titleFilter='" + titleFilter + '\'' +
                ", topicFilter='" + topicFilter + '\'' +
                ", userFilter='" + userFilter + '\'' +
                ", startFrom=" + startFrom +
                '}';
    }

    public void calc() {
        // Pagination
        numberOfPages = (int) numberOfItems / MAX_ITEM_ON_PAGE;
        if (numberOfItems % MAX_ITEM_ON_PAGE != 0) numberOfPages++;
        if (currentPage <= 1) {
            currentPage = 1;
        } else if (currentPage > numberOfPages) {
            currentPage = numberOfPages;
        }
        prevPage = currentPage - 1;
        if (prevPage == 0) prevPage = 1;
        nextPage = currentPage + 1;
        if (prevPage > numberOfPages) prevPage = numberOfPages;
        startFrom = (long) (currentPage - 1) * MAX_ITEM_ON_PAGE;

        //Sorting
        if (sortType == null) {
            setSortType(WITHOUT_SORT);
        }
    }
}
