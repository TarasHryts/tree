package com.task.tree;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TreeApplication {
    static List<String> requiredFiles = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(TreeApplication.class, args);
        TreeApplication treeApplication = new TreeApplication();
        ExecutiveFile executiveFile = new ExecutiveFile(requiredFiles);
        Thread fileThread = new Thread(executiveFile);
        PrintingDataThread printingDataThread = new PrintingDataThread(requiredFiles);
        Thread printThread = new Thread(printingDataThread);
        printThread.start();
        fileThread.start();
    }
}
