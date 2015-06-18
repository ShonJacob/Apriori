package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Conversion {
    public static void main(String[] args) throws Exception {
        String inputFile = "/home/vedavyas/project/data/diabetes/diabetes.arff";
        String outputFile = "/home/vedavyas/project/data/diabetes/diabetes_nominalized.arff";
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
            if (plas <= 70) {
                output += "low,";
            }
            else if (plas > 70 && plas <= 120) {
                output += "normal,";
            }
            else if (plas > 120 && plas <= 160) {
                output += "high,";
            }
            else {
                output += "very_high,";
            }
            
            float pres = Float.parseFloat(values[2]);
            if (pres > 0 && pres <=50) {
                output += "low,";
            }
            else if (pres > 50 && pres <= 80) {
                output +=  "normal,";
            }
            else {
                output += "high,";
            }
            
            
            float skin = Float.parseFloat(values[3]);
            if (skin > 0 && skin <= 15) {
                output += "low,";
            }
            else if (skin > 15 && skin < 23) {
                output += "normal,";
            }
            else if (skin > 23 && skin <= 30) {
                output += "high,";
            }
            else if (skin > 30 && skin < 45) {
                output += "very_high,";
            }
            else {
                output += "extremely_high,";
            }
            
            
            float insu = Float.parseFloat(values[4]);
            if (insu > 0 && insu <= 220) {
                output += "normal,";
            }
            else {
                output += "high,";
            }
            
            
            float mass = Float.parseFloat(values[5]);
            if (mass > 0 && mass <= 21) {
                output += "low,";
            }
            else if (mass > 21 && mass < 27) {
                output += "normal,";
            }
            else if (mass > 27 && mass < 35) {
                output += "high,";
            }
            else {
                output += "very_high,";
            }
            
            float pedi = Float.parseFloat(values[6]);
            if (pedi <= 0.4) {
                output += "LT_0.4,";
            }
            else if (pedi > 0.4 && pedi <= 0.8) {
                output += "0.4_0.8,";
            }
            else if (pedi > 0.8 && pedi <= 1.5) {
                output += "0.8_1.5,";
            }
            else if (pedi > 1.5 && pedi <= 1.9) {
                output += "1.5_1.9,";
            }
            else if (pedi > 1.9 && pedi <= 2.5) {
                output += "1.9_2.5,";
            }
            
            float age = Float.parseFloat(values[7]);
            if (age > 21 && age <= 30) {
                output += "young,";
            }
            else if (age > 30 && age < 40) {
                output += "middle_age,";
            }
            else if (age > 40 && age < 55) {
                output += "old,";
            }
            else {
                output += "very_old,";
            }
            
            output += values[8] + "\n";
            
            writer.write(output);
        }
        
        writer.flush();
        
    }
}
