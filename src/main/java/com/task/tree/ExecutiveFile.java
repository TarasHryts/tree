package com.task.tree;

import static java.io.File.separator;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecutiveFile implements Runnable {
    List<String> requiredFiles = new ArrayList<>();

    public ExecutiveFile(List<String> requiredFiles) {
        this.requiredFiles = requiredFiles;
    }

    public List<String> directoryListInPath(String mainPath, Integer depth, String mask) {
        List<String> pathList = Arrays.asList(mainPath);
        Integer count = 0;
        while (!pathList.isEmpty() && (depth >= count)) {
            List<String> foldersInCurrentDirectory = new ArrayList<>();
            for (String path : pathList) {
                File directoryPath = new File(path);
                requiredFiles.addAll(findRequiredFilesInDirectory(directoryPath, path, mask));
                foldersInCurrentDirectory.addAll(findFoldersInDirectory(directoryPath, path));
            }
            count++;
            pathList = foldersInCurrentDirectory;
        }
        return requiredFiles;
    }

    private List<String> findFoldersInDirectory(File directoryPath, String path) {
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        File[] fileNameArray = directoryPath.listFiles(filter);
        List<String> foldersInDirectory = new ArrayList<>();
        for (File nameOfFile : fileNameArray) {
            foldersInDirectory.add(path + separator + nameOfFile.getName());
        }
        return foldersInDirectory;
    }

    private List<String> findRequiredFilesInDirectory(File directoryPath, String path, String mask) {
        FileFilter nameFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(mask);
            }
        };
        List<String> result = new ArrayList<>();
        File[] requiredFilesArray = directoryPath.listFiles(nameFilter);
        for (File nameOfFile : requiredFilesArray) {
            result.add(path + separator + nameOfFile.getName());
        }
        return result;
    }

    @Override
    public void run() {
        ExecutiveFile executiveFile = new ExecutiveFile(requiredFiles);
        executiveFile.directoryListInPath(Constants.MAIN_PATH, Constants.DEPTH, Constants.MASK);
    }
}
