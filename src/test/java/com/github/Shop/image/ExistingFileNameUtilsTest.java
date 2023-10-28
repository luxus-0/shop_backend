package com.github.Shop.image;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ExistingFileNameUtilsTest {

    @Test
    public void shouldNotRenameFile(@TempDir Path tempPath){
       String renameExistingFile = ExistingFileNameUtils.renameIfExists(tempPath, "test.png");
        assertEquals("test.png", renameExistingFile);
    }

    @Test
    public void shouldRenameExistingFile(@TempDir Path tempPath) throws IOException {
        Files.createFile(tempPath.resolve("test-1.png"));
        String renameExistingFile = ExistingFileNameUtils.renameIfExists(tempPath, "test-1.png");
        assertEquals("test-2.png", renameExistingFile);
    }

    @Test
    public void shouldRenameManyExistingFile(@TempDir Path tempPath) throws IOException {
        Files.createFile(tempPath.resolve("test.png"));
        Files.createFile(tempPath.resolve("test-1.png"));
        Files.createFile(tempPath.resolve("test-2.png"));
        Files.createFile(tempPath.resolve("test-3.png"));
        Files.createFile(tempPath.resolve("test-4.png"));

        String renameExistingFile = ExistingFileNameUtils.renameIfExists(tempPath, "test.png");
        assertEquals("test-5.png", renameExistingFile);
    }

}