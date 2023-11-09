package com.github.shop.image;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UploadedFilesNameUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "hello Lukasz.png, hello-lukasz.png",
            "ąęśćżźńłó.png, aesczznlo.png",
            "Product 1.png, product-1.png",
            "lukasz_1.png, lukasz-1.png"
    })
    void shouldSlugifyFileName(String actualFileName, String expectedFileName){
        String slugify = UploadedFilesNameUtils.slugifyFileName(actualFileName);

        assertEquals(slugify, expectedFileName);
    }

}