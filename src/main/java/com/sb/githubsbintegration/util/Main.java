package com.sb.githubsbintegration.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMin = LocalDateTime.now().minusHours(3).minusMinutes(30);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String formattedDateTime = now.format(formatter);
        String formattedDateTimeMinus = nowMin.format(formatter);

        System.out.println(formattedDateTime);
        System.out.println(formattedDateTimeMinus);
    }
}