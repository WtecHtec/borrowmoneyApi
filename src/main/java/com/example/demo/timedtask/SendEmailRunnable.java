package com.example.demo.timedtask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendEmailRunnable implements Runnable {
    private int runnableId ;
    public SendEmailRunnable(int runnableId) {
        this.runnableId =  runnableId;
    }
    @Override
    public void run() {
        log.info(" 定时任务 " + this.runnableId);
    }
}
