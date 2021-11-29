package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.github.cage.YCage;

public class CageDemo {

    private static void generate(Cage cage, int num, String namePrefix,
                                 String namePostfix, String text) throws IOException {
        for (int fi = 0; fi < num; fi++) {
            try (OutputStream os = new FileOutputStream(namePrefix + fi + namePostfix, false)) {
                cage.draw(text != null ? text : cage.getTokenGenerator().next(), os);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        generate(new GCage(), 10, "cg1", ".jpg", "colding");
        generate(new YCage(), 10, "cy1", ".gif", "eT6wLAH");
        generate(new GCage(), 100, "cg2", ".jpeg", null);
        generate(new YCage(), 100, "cy2", ".png", null);
    }

}
