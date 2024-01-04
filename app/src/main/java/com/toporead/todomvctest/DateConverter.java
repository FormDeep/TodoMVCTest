package com.toporead.todomvctest;

import androidx.room.TypeConverters;
import java.util.Date;

public class DateConverter {

    @TypeConverters
    public static Date toDate(Long dateLong){
        return dateLong == null ? null :new Date(dateLong);
    }
    public  static long fromDate(Date date){
        return date==null?null:date.getTime();
    }
}
