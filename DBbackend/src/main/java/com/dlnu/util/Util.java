package com.dlnu.util;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Util {
    public static int getIntervalDays(String date1Str, String date2Str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fDate = sdf.parse(date1Str);
        Date oDate = sdf.parse(date2Str);
        if (null == fDate || null == oDate) {

            return -1;

        }
        long intervalMilli = oDate.getTime() - fDate.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000))+1;

    }
}
