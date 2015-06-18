package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class ClassLabelManager {
    public static void main(String[] args) throws Exception {
        String inputFile = "/home/vedavyas/project/data/heart/cleveland/nominalized/final/clev_all_nominalized";
        String outputFile = "/home/vedavyas/project/data/heart/cleveland/nominalized/final/clev_all_0_1";
        
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter writer = new FileWriter(outputFile);
        
        String line = "";
        
        while ( (line=br.readLine()) != null ) {
            StringBuffer str = new StringBuffer(line);
            char ch = line.charAt(line.length() - 1); 
            if (ch == '2' || ch == '3' || ch == '4') {
                str.setCharAt(line.length() - 1, '1');
            }
            writer.append(str + "\n");
        }
        writer.flush();
        writer.close();
        br.close();
    }
}
