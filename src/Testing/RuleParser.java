package Testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class RuleParser {
    
    public static void main(String[] args) throws Exception {
        String file1  = "/home/vedavyas/scratch";
        String file2  = "/home/vedavyas/scratch2";
        
        Set<String> mine = new HashSet<String>();
        BufferedReader  br = new BufferedReader(new FileReader(file2));
        
        String line = "";
        
        while ( (line=br.readLine()) != null) {
            mine.add(line);
        }
        
        Set<String> weka = new HashSet<String>();
        br = new BufferedReader(new FileReader(file1));
        
        while ( (line=br.readLine()) != null) {
            weka.add(line);
        }
        
        Set<String> diff1 = new HashSet<String>(weka);
        diff1.removeAll(mine);
        
        System.out.println("WEKA BUT NOT MINE:");
        diff1.forEach(string -> System.out.println(string));
        System.out.println("\n\n");
        
        Set<String> diff2 = new HashSet<String>(mine);
        diff2.removeAll(weka);
        System.out.println("MINE BUT NOT WEKA:");
        diff2.forEach(string -> System.out.println(string));
        System.out.println("\n\n");
    }
}
