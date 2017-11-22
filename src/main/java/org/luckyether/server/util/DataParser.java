package org.luckyether.server.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author Igor Hnes on 11/21/17.
 */
public class DataParser {

    public static void main(String[] args) {
        System.out.println(DataParser.getCurrentDate());
    }

    /**
     * @return current date in London.
     */
    public static String getCurrentDate() {
        return DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm:ss Z")
                .format(LocalDateTime.now(ZoneId.of("Europe/London")));
    }
}
