package com.brave;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jbrave
 */
public class SimCardMakeUp {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("333.csv"));
        writer.write("ICCID,IMSI,MSISDN");
        writer.newLine();
        for(int i = 200; i < 210; i++){
            writer.write("29860700020211229" + i);
            writer.write(",");
            writer.write("2600020121229" + i);
            writer.write(",");
            writer.write("26641229" + i);
            writer.newLine();
        }
        writer.flush();
    }

}
