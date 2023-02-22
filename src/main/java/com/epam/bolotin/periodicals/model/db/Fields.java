package com.epam.bolotin.periodicals.model.db;

public class Fields {

    //USER table field list
    public static final String USER_ID = "id";
    public static final String USER_NAME = "username";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CREATE_TIME = "create_time";
    public static final String USER_BLOCKED = "blocked";
    public static final String USER_ROLE_ID = "user_role_id";

    //TOPIC table field list
    public static final String TOPIC_ID = "id";
    public static final String TOPIC_NAME = "name";

    //PUBLICATION table field list
    public static final String PUBLICATION_ID = "id";
    public static final String PUBLICATION_TOPIC_ID = "topic_id";
    public static final String PUBLICATION_TITLE = "title";
    public static final String PUBLICATION_PRICE = "price";
    public static final String PUBLICATION_TEXT = "text";

    //ACCOUNT table field list
    public static final String ACCOUNT_ID = "id";
    public static final String ACCOUNT_USER_ID = "user_id";
    public static final String ACCOUNT_AMOUNT = "amount";

    //READER_PUBLICATION table field list
    public static final String READER_PUBLICATION_ID = "id";
    public static final String READER_PUBLICATION_PUBLICATION_ID = "publication_id";
    public static final String READER_PUBLICATION_USER_ID = "user_id";
    public static final String READER_PUBLICATION_PRICE = "price";
    public static final String READER_PUBLICATION_SUBSCRIBE_DATE = "subscribe_date";

    //PUBLICATION report field list
    public static final String REPORT_PUBLICATION_AMOUNT = "amount";
    public static final String REPORT_PUBLICATION_QUANTITY = "quantity";

}
