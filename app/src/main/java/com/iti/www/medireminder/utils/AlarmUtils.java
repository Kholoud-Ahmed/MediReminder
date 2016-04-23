package com.iti.www.medireminder.utils;

/**
 * Created by Shall on 18/4/2016.
 */
public class AlarmUtils {
    public static int getTimeMarker(int hour){
        if(hour<=12){
            return 0;
        }else{
            return 1;
        }
    }
}
