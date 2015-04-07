package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Conversion {
    public static void main(String[] args) throws Exception {
        String inputFile = "/home/vedavyas/project/Data/diabetes_data";
        String outputFile = "/home/vedavyas/project/Data/diabetes_data_nominalized";
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        FileWriter writer = new FileWriter(outputFile);
        
        String line;
        
        while ( (line=br.readLine()) != null ) {
            String[] values = line.split(",");
            String output = "";
            
            int preg = Integer.parseInt(values[0]);
            if (preg == 0) {
                output += "no,";
            }
            else if (preg <= 4) {
                output += "normal,";
            }
            else if (preg > 4 && preg < 8) {
                output += "high,";
            }
            else {
                output += "very_high,";
            }
            
            float plas = Float.parseFloat(values[1]);
            if (plas <= 50) {
                output += "low,";
            }
            else if (plas > 50 && plas <= 120) {
                output += "normal,";
            }
            else if (plas > 120 && plas <= 160) {
                output += "high,";
            }
            else {
                output += "very_high,";
            }
            
            float pres = Float.parseFloat(values[2]);
            if (pres > 0 && pres <= 40) {
                output += "low,";
            }
            else if (pres > 40 && pres <= 80) {
                output +=  "normal,";
            }
            else {
                output += "high,";
            }
            
            
            float skin = Float.parseFloat(values[3]);
            if (skin > 0 && skin <= 15) {
                output += "normal,";
            }
            else if (skin > 15 && skin <= 50) {
                output += "high,";
            }
            else {
                output += "very_high,";
            }
            
            
            float insu = Float.parseFloat(values[4]);
            if (insu > 0 && insu <= 220) {
                output += "normal,";
            }
            else {
                output += "high,";
            }
            
            
            float mass = Float.parseFloat(values[5]);
            if (mass > 0 && mass <= 22) {
                output += "low,";
            }
            else if (mass > 20 && mass < 26) {
                output += "normal,";
            }
            else {
                output += "high,";
            }
            
            
            
            
        }
    }
}
