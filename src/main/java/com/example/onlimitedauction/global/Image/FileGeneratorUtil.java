package com.example.onlimitedauction.global.Image;

import java.util.UUID;


public class FileGeneratorUtil {

    private static final String URL_FORMAT = "%s/%s";
    private static final String BASE_URL = "/Users/gimjingwon/Documents/Auction/image";

    public static String generateFileName(String fileName) {
        String file = UUID.randomUUID().toString() + "_" + fileName;
        return generateFileUrl(file);
    }

    public static String generateFileUrl(String fileName) {
        return String.format(URL_FORMAT, BASE_URL, fileName);
    }
}
