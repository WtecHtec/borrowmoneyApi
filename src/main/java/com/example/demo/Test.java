package com.example.demo;

import com.example.demo.emailUtil.EmailTool;
import com.example.demo.uitl.DateTool;
import com.example.demo.uitl.IdWorker;

public class Test {

    public static void main(String args[]) {
        IdWorker idWorker = new IdWorker(1,1);
        System.out.println(idWorker.nextId());
        System.out.println( DateTool.getDayAfter());
    }
}
