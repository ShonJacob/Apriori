package Testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class Verifier {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/project/data/heart/cleveland/clev"));
        FileWriter LHSWriter = new FileWriter("/home/vedavyas/rulesLHS");
        FileWriter RHSWriter = new FileWriter("/home/vedavyas/rulesRHS");
        
        String line = "";
        
        
        while((line = br.readLine()) != null) {
            String[] values = line.split(",");
            
            for (int i=0; i<values.length; i++) {
                String val = values[i];
                if (i >= 58 && i <= 67 ) {
                    if (!val.contains("-9")) {
                        System.out.print(val+", ");
                    }
                }
            }
            System.out.println();
        }

        LHSWriter.flush();
        RHSWriter.flush();
        
        LHSWriter.close();
        RHSWriter.close();
        br.close();
        
        System.out.println("DONE!");
    }
}
