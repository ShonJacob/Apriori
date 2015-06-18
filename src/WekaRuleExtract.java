import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;


public class WekaRuleExtract {

    public static void main(String[] args) throws Exception {
        extract();
    }
    
    public static void extract() throws Exception {
        /*BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/scratch"));
        FileWriter LHSWriter = new FileWriter("/home/vedavyas/rulesLHS");
        FileWriter RHSWriter = new FileWriter("/home/vedavyas/rulesRHS");
        */
        
        BufferedReader br = new BufferedReader(new FileReader("/home/vedavyas/project/data/heart/cleveland/final/rules/scratch"));
        FileWriter LHSWriter = new FileWriter("/home/vedavyas/project/data/heart/cleveland/final/rules/type_0_rulesLHS_0_1");
        FileWriter RHSWriter = new FileWriter("/home/vedavyas/project/data/heart/cleveland/final/rules/type_0_rulesRHS_0_1");
        
        String line = "";
        String SEARCH_KEY = "TYPE=0";
        
        while((line = br.readLine()) != null) {
            String[] rules = line.split("==>");
            
            if (rules[0].contains(SEARCH_KEY)) {
                System.out.println("Writing LHS rule...");
                LHSWriter.append(line.split("\\. ", 2)[1] + "\n\n");
            }
            
            if (rules[1].contains(SEARCH_KEY)) {
                System.out.println("Writing RHS rule...");
                RHSWriter.append(line.split("\\. ", 2)[1] + "\n\n");
            }
        }

        LHSWriter.flush();
        RHSWriter.flush();
        
        LHSWriter.close();
        RHSWriter.close();
        br.close();
        
        System.out.println("DONE!");
    }
}
