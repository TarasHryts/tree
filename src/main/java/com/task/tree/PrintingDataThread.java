package com.task.tree;

import java.util.ArrayList;
import java.util.List;

public class PrintingDataThread implements Runnable {
    List<String> requiredFiles = new ArrayList<>();
    Integer foundFiles = 0;

    public PrintingDataThread(List<String> requiredFiles) {
    }

    @Override
    public void run() {
        if (requiredFiles.size() > foundFiles) {
            synchronized (this) {
                for (int i = foundFiles; i < requiredFiles.size(); i++) {
                    System.out.println(requiredFiles.get(i));
                }
                foundFiles = requiredFiles.size();
            }
        }
    }
}
