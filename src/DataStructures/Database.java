package DataStructures;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import Algorithms.Apriori.Item;

/**
 * 
 * @author Vedavyas
 * A class which parses ARFF files with nominalized values ONLY
 *
 */
public class Database {
    /**
     * 
     * @author Vedavyas
     * An internal class which represents an instance
     * 
     */
    public class Row {
        private static final String ATTRIBUTE_DELIMITER = ",";
        public List<Instance> instances;
        
        public Row(String line) throws Exception {
            instances = new ArrayList<Instance>();
            createRow(line);
        }
        
        public boolean contains(Item item) {
            return this.instances.containsAll(item.instances);
        }
        
        private void createRow(String line) throws Exception {
            String[] lineValues = line.split(ATTRIBUTE_DELIMITER);
            if (lineValues.length != attributes.size()) {
                throw new Exception("Invalid number of instances in row "+line);
            }
            for (int i=0; i<lineValues.length; i++) {
                if (attributes.get(i).canHaveValue(lineValues[i])) {
                    instances.add(new Instance(attributes.get(i), lineValues[i]));
                }
                else {
                    throw new Exception("Invalid value '"+lineValues[i]+"' for attribute '"
                            +attributes.get(i)+"'");
                }
            }
        }
        
        @Override
        public String toString() {
            String out = "";
            for (int i=0; i<this.instances.size(); i++) {
                out = out + this.instances.get(i);
                if (i != this.instances.size() - 1) {
                    out = out + ", ";
                }
            }
            
            return out;
        }
    }
    
    private final String KEY_RELATION = "@relation";
    private final String KEY_ATTRIBUTE = "@attribute";
    private final String KEY_DATA = "@data";
    
    public String name;
    public List<Attribute> attributes;
    public List<Row> rows;
    public Attribute classLabel;
    
    public Database(String file) throws Exception {
        this.attributes = new ArrayList<Attribute>();
        this.rows = new ArrayList<Row>();
        readFile(file);
        
        this.classLabel = this.attributes.get(this.attributes.size() - 1);
    }
    
    public int rowSize() {
        return this.attributes.size();
    }
    
    public int size() {
        return this.rows.size();
    }
    
    public int getItemCount(Item item) {
        int count = 0;        
        for (Row row: this.rows) {
            if (row.contains(item)) {
                count++;
            }
        }
        
        return count;
    }
    
    private void readFile(String file) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) !=  null) {
            
            if (line.contains(KEY_DATA)) {
                readData(br);
            }
            
            if (line.contains(KEY_RELATION)) {
                this.name = line.split(" ")[1];
                continue;
            }
            
            if (line.contains(KEY_ATTRIBUTE)) {
                String[] attrLine = line.split(" ");
                Attribute attribute = new Attribute(attrLine[1]);
                
                StringBuffer attrValuesBuffer = new StringBuffer(attrLine[2]);
                attrValuesBuffer.deleteCharAt(0);
                attrValuesBuffer.deleteCharAt(attrValuesBuffer.length() - 1);
                
                String[] attrValues = attrValuesBuffer.toString().split(",");
                for (String value: attrValues) {
                    attribute.addValue(value);
                }
                
                this.attributes.add(attribute);
                continue;
            }
        }
        br.close();
    }
    
    private void readData(BufferedReader br) throws Exception {
        String line;
        
        while ((line=br.readLine()) != null) {
            this.rows.add(new Row(line));
        }
    }
    
    @Override
    public String toString() {
        String out = "";
        for (Row instance: this.rows) {
            out =  out + instance + "\n";
        }
        return out;
    }
}
