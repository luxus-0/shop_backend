package com.github.Shop.image;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

class UploadedFilesNameUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);

        Slugify slug = Slugify.builder()
                .customReplacement("a", "ą")
                .customReplacement("c", "ć")
                .customReplacement("e", "ę")
                .customReplacement("l", "ł")
                .customReplacement("o", "ó")
                .customReplacement("s", "ś")
                .customReplacement("z", "ź")
                .customReplacement("z", "ż")
                .customReplacement("_", "-")
                .build();
        String changedName = slug.slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }
}
