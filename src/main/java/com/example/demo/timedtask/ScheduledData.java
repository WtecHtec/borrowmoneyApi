package com.example.demo.timedtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public  class ScheduledData {

    private  static Map<String,ScheduledFuture<?>>  futures = new HashMap<>();
    private static List<String> strings =  new ArrayList<>();

    public static Map<String, ScheduledFuture<?>> getFutures() {
        return futures;
    }

    public static void setFutures(Map<String, ScheduledFuture<?>> futures) {
        ScheduledData.futures = futures;
    }

    public static List<String> getStrings() {
        return strings;
    }

    public static void setStrings(List<String> strings) {
        ScheduledData.strings = strings;
    }
}
