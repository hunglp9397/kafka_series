package com.hunglp.springkafkasendussd.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    public static String generateRandomPhone() {
        Random rand = new Random();

//        // Generate the first two digits based on common prefixes
//        String[] commonPrefixes = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29"};
//        String prefix = commonPrefixes[rand.nextInt(commonPrefixes.length)];
//
//        // Generate the remaining 8 digits
//        StringBuilder phoneNumber = new StringBuilder("84" + prefix);
//        for (int i = 0; i < 8; i++) {
//            phoneNumber.append(rand.nextInt(10));
//        }
//
//        return (phoneNumber.toString());
         StringBuilder phoneNumber = new StringBuilder("849346426");
         for(int i = 0; i < 2; i++){
             phoneNumber.append(rand.nextInt(10));
         }

         return phoneNumber.toString();
    }

    public static Date generateRandomDateTime(Date startTime)  {
        Random rand = new Random();
        Calendar calendar = Calendar.getInstance();


        // Set a random year between 2000 and 2023
        int year = 2023;

        // Set a random month (0-11, where 0 is January and 11 is December)
        int month = 9;

        // Set a random day of the month (1-31)
        int day = 10;

        // Set a random hour (0-23)
        int hour = 11;

        // Set a random minute (0-59)
        int minute = 12;
//        if(startTime != null){
//            int low = startTime.getMinutes();
//            int high = 60;
//            minute = rand.nextInt(high-low) + low;
//        } else{
//            minute = rand.nextInt(60);
//        }

        // Set a random second (0-59)
        int second;
        if(startTime != null){
            int low = startTime.getSeconds();
            int high = 60;
            second = rand.nextInt(high-low) + low;
        } else{
            second = rand.nextInt(60);
        }

        calendar.set(year, month, day, hour, minute, second);

        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date startDate = generateRandomDateTime(null);
        System.out.println(startDate);
        System.out.println(generateRandomDateTime(startDate));
    }


}
