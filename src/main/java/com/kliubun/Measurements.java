package com.kliubun;

public class Measurements {

    public static void main(String[] args) {
        final long bytes =
              (("{ \"content\" : \"test_message_test_message\", \"title\" : "
                    + "\"some_title_title_title_title_title_title_12\" }").repeat(10)).getBytes().length;
        System.out.println("Size of message: " + bytes);
    }
}
