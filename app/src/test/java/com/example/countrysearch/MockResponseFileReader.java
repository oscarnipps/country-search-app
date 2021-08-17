package com.example.countrysearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/*
 * class that reads the contents of the json file with the specified path
 */
public class MockResponseFileReader {


    public static String getContent(String path) {
        String content;

        File file = new File(path);

        System.out.println(file.getAbsolutePath());

        try {
            InputStream inputStream = new FileInputStream(file);

            byte[] bytes = new byte[inputStream.available()];

            inputStream.read(bytes);

            content = new String(bytes);

            //System.out.println(content);

            return content;

        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }
    }
}
