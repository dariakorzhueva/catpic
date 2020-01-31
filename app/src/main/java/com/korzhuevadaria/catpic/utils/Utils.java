package com.korzhuevadaria.catpic.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String convertUnixTimeToString(String unixTimeString){
        int unixSeconds  = Integer.parseInt(unixTimeString);

        Date date = new java.util.Date(unixSeconds  * 1000L);

        SimpleDateFormat sdf = new java.text.SimpleDateFormat("E dd MMM yyyy\nHH:mm");

        String formattedDate = sdf.format(date);

        return formattedDate;
    }
}
