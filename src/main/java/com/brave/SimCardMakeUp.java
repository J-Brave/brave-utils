package com.brave;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jbrave
 */
public class SimCardMakeUp {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("20220806132940.csv"));
        writer.write("ICCID,IMSI,MSISDN");
        writer.newLine();
        for(int i = 100; i < 50000; i++){
            writer.write("49860700020211229" + i);
            writer.write(",");
            writer.write("4600020121229" + i);
            writer.write(",");
            writer.write("46641229" + i);
            writer.newLine();
        }
        writer.flush();
    }

}
