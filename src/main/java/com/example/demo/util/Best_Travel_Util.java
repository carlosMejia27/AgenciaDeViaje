package com.example.demo.util;

import java.time.LocalDateTime;
import java.util.Random;

public class Best_Travel_Util {

    private static final Random randon=new Random();

    public static LocalDateTime getRandomSoon(){
        var randomHours=randon.nextInt(5-2)+2;
        var now =LocalDateTime.now();
        return now.minusHours(randomHours);
    }

    public static LocalDateTime getRandomLater(){
        var randomHours=randon.nextInt(12-6)+6;
        var now =LocalDateTime.now();
        return now.minusHours(randomHours);
    }
}
