package com.brave.patterns.decorator.step2;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jbrave
 */
public class InputTest {
    public static void main(String[] args) throws IOException {
        int c;
        try {
            ClassPathResource classPathResource = new ClassPathResource("test.txt");
            InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(classPathResource.getFile())));
            while ((c = in.read()) >= 0) {
                System.out.print((char) c);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
