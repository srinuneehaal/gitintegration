package com.sb.githubsbintegration.util;

import java.time.Instant;

public class TimeUTCUtil {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());

        System.out.println(Instant.now().toString());
        System.out.println(Instant.now().minusSeconds(60));
        System.out.println(Instant.now().minusSeconds(360));

    }
}
