package com.github.Shop.image;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;

class ExistingFileNameUtils {
    public static String renameIfExists(Path path, String fileName) {
       if(Files.exists(path.resolve(fileName))){
          return renameAndCheckFile(path, fileName);
       }
       return fileName;
    }

    private static String renameAndCheckFile(Path path, String fileName) {
        String newName = renameFileName(fileName);
        if(Files.exists(path.resolve(newName))) {
            newName = renameAndCheckFile(path, newName);
        }
        return newName;
    }

    private static String renameFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        String[] split = name.split("-(?=[\\d+]+$)");
        int counter =  split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;
        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }
}
