package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Replace {
    public static void main(String[] args) throws Exception {
        oneTwoThreeToOne();
        removeZeroInstances();
    }
    
    public static void oneTwoThreeToOne() throws Exception {
        String inFile = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff", 
               outFile = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized_0_1.arff";
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        
        String line = "";
        StringBuilder output = new StringBuilder();
        while ( (line = br.readLine()) != null) {
            output.append(line);
            if (output.charAt(output.length() - 1) == '2' ||
                output.charAt(output.length() - 1) == '3' ||
                output.charAt(output.length() - 1) == '4') {
                
                output.setCharAt(output.length() - 1, '1');
            }
            output.append('\n');
        }
        
        FileWriter writer = new FileWriter(outFile);
        writer.write(output.toString());
        writer.flush();
        
        writer.close();
        br.close();
        
        System.out.println("DONE");
    }
    
    public static void removeZeroInstances() throws Exception {
        String inFile = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff", 
                outFile = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized_1_2_3_4.arff";
         BufferedReader br = new BufferedReader(new FileReader(inFile));
         
         String line = "";
         StringBuilder output = new StringBuilder();
         while ( (line = br.readLine()) != null) {
             if (line.length() !=  0 && line.charAt(line.length() - 1) != '0') {
                 output.append(line+"\n");
             }
         }
         
         FileWriter writer = new FileWriter(outFile);
         writer.write(output.toString());
         writer.flush();
         
         writer.close();
         br.close();
         
         System.out.println("DONE");
        
    }
}
