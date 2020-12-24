package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

public class Icu4jDemo {

    public static void openFileWithEncoding(String fileName) throws IOException {
        System.out.println("===Getting encoding of " + fileName);
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        CharsetDetector detector = new CharsetDetector();
        detector.setText(data);
        CharsetMatch match = detector.detect();
        String encoding = match.getName();
        System.out.println("The Content in " + encoding);
        CharsetMatch[] matches = detector.detectAll();
        System.out.println("All possibilities:");
        for (CharsetMatch m : matches) {
            System.out.println("  CharsetName:" + m.getName() + " Confidence:" + m.getConfidence());
        }
    }

    public static void main(String[] args) throws IOException {
        String basePath = Icu4jDemo.class.getResource("../../").getPath().substring(1);
        openFileWithEncoding(basePath + "utf8_test.txt");
        openFileWithEncoding(basePath + "gbk_test.txt");
    }

}
