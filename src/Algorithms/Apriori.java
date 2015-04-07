package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

import DataStructures.Attribute;
import DataStructures.Database;
import DataStructures.Database.Row;
import DataStructures.Instance;

import com.google.common.collect.Sets;

/**
 * 
 * @author Vedavyas Bhat
 * A class which implements a naive Apriori algorithm
 * 
 */

public class Apriori {
    
    public class Rule {
        Item antecedent, consequent;
        float support, confidence, lift;
        
        public Rule(Item antecedent, Item consequent) {
            this.antecedent = antecedent;
            this.consequent = consequent;
            
            Item union = new Item();
            union.addItem(consequent);
            union.addItem(antecedent);
            float unionCount = database.getItemCount(union);
            
            this.support = (float) unionCount / (float) database.size();
            this.confidence = Math.round(((float) unionCount / 
                                          (float) database.getItemCount(antecedent)) 
                                          * 100);
            
            // lift(X->Y) = sup(XUY) / n   /
            //              sup(X)/n * sup(Y)/n
            float antecedentProb = ((float) database.getItemCount(antecedent)) / database.size();
            float consequentProb = ((float) database.getItemCount(consequent)) / database.size();
            
            this.lift = this.support / (antecedentProb * consequentProb); 
        }
        
        @Override
        public boolean equals(Object object) {
            if (object instanceof Rule) {
                Rule rule = (Rule) object;
                
                return this.antecedent.equals(rule.antecedent) && 
                       this.consequent.equals(rule.consequent);
            }
            else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return this.antecedent.hashCode() * this.consequent.hashCode();
        };
        
        @Override
        public String toString() {
            String result = "";
            result += this.antecedent.instances + " ==> " + this.consequent.instances;
            result += " [supp=" + this.support + ", conf=" + this.confidence + ", lift=" + this.lift + "]";
            
            return result;
        }
    }
    
    public class Item {
        public Set<Instance> instances;
        public int count;
        
        public Item() {
            instances = new HashSet<Instance>();
            count = 1;
        }
        
        public Item(Set<Instance> instances) {
            this.instances = instances;
        }
        
        public void incrementCount() {
            this.count++;
        }
        
        public void addInstance(Instance instance) {
            for (Instance inst: this.instances) {
                if (inst.attribute.equals(instance.attribute)) {
                    return;
                }
            }
            this.instances.add(instance);
        }
        
        public void addItem(Item item) {
            for (Instance instance: item.instances) {
                addInstance(instance);
            }
        }
        
        public void removeItem(Item item) {
            this.instances.removeAll(item.instances);
        }
        
        public Set<Attribute> getAttributes() {
            Set<Attribute> attributes = new HashSet<>(); 
            this.instances.forEach(instance -> attributes.add(instance.attribute));
            
            return attributes;
        }       
        
        @Override
        public boolean equals(Object object) {
            if (object instanceof Item) {
                Item item = (Item) object;
                
                boolean answer = true;
                boolean foundInstance = false;
                
                for (Instance myInstance: this.instances) {
                    foundInstance = false;
                    for (Instance itemInstance: item.instances) {
                        if (myInstance.equals(itemInstance)) {
                            foundInstance = true;
                            break;
                        }
                    }
                    if (!foundInstance) {
                        answer = false;
                        break;
                    }
                }
                return answer;
            }
            else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return this.instances.hashCode();
        };
        
        @Override
        public String toString() {
            return this.instances + "(" + this.count + ")";
        }
    }

    Database database;
    Set<Item> candidateItemSet;
    Set<Item> frequentItemSet;
    Set<Rule> rules;
    int itemsetNumber;
    float minSupport;
    int minSupportCount;
    float minConfidence;
    boolean DEBUG;
    Set<Instance> consequentInstances;
    
    int MAX_RULES = 100;
    
    public Apriori(Database database, float support, float confidence, boolean DEBUG) throws Exception {
        this.database = database;
        this.minSupport  =  support;
        this.minSupportCount = Math.round(this.minSupport * database.size());
        this.candidateItemSet = new HashSet<>();
        this.frequentItemSet = new HashSet<>();
        this.rules = new HashSet<>();
        this.minConfidence = confidence;
        this.itemsetNumber = 0;
        this.DEBUG = DEBUG;
        this.consequentInstances =  new HashSet<>();
        this.consequentInstances.add(new Instance(this.database.classLabel, "0"));
        this.consequentInstances.add(new Instance(this.database.classLabel, "1"));
        this.consequentInstances.add(new Instance(this.database.classLabel, "2"));
        this.consequentInstances.add(new Instance(this.database.classLabel, "3"));
        this.consequentInstances.add(new Instance(this.database.classLabel, "4"));
    }
    
    /**
     *==============================================================================================
     *                              FREQUENT ITEMSET GENERATION
     *==============================================================================================                               
     */
    
    /**
     * A function which adds an item to a set if it doesn't contain it.  If it is contained,
     * increments the item's count.
     * 
     * @param set Set to add items to
     * @param item The item to add
     */
    private void addItem(Set<Item> set, Item item) {
        boolean itemExists = false;
        for (Item i : set) {
            if (i.equals(item)) {
                i.incrementCount();
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            set.add(item);
        }
    }
    
    /**
     * A function which creates the 1-itemset form the database.
     */
    private void createOneItemset() {
        for (Row row: database.rows) {
            for (int i=0; i<database.rowSize(); i++) {
                Item item = new Item();
                item.addInstance(new Instance(row.instances.get(i)));
                addItem(candidateItemSet, item);
            }
        }
        
        this.itemsetNumber++;
    }
    
    /**
     * Function which generates the next level itemset
     * An nth level itemset has items of size n
     */
    private void generateNextLevelItemset() {
        List<Item> itemset = new ArrayList<>(this.candidateItemSet);
        this.candidateItemSet = new HashSet<Apriori.Item>();
        
        for (int i=0; i<itemset.size(); i++) {
            for (int j=i+1; j<itemset.size(); j++) {
                Item item = new Item();
                item.addItem(itemset.get(i));
                item.addItem(itemset.get(j));
                addItem(candidateItemSet, item);
            }
        }
        
        this.itemsetNumber++;
        this.candidateItemSet.removeIf(item -> item.instances.size() != this.itemsetNumber);
    }
    
    /**
     * A function which calculates the support for every item in the candidateItemSet
     */
    private void calculateSupport() {
        for (Item item: this.candidateItemSet) {
            item.count = this.database.getItemCount(item);
        }
    }
    
    /**
     * A function which prunes the candidateItemSet and adds all it's elements to frequentItemSet
     */
    private void prune() {
        this.candidateItemSet.removeIf(item -> item.count < this.minSupportCount);
        this.frequentItemSet.addAll(this.candidateItemSet);
        System.out.println("FREQUENT ITEMSET IS NOW: ");
        this.frequentItemSet.forEach(item -> log(item));
        log("\n\n\n");
    }
    
    /**
     * Function to filter frequentItemSet
     * Filtration:
     * 1. Remove all instances with size 1, because no rules can come out of them
     */
    private void filterFrequentItemset() {
        this.frequentItemSet.removeIf(item -> item.instances.size() == 1);
    }
    
    
    /**
     * =============================================================================================
     *                                      RULE GENERATION
     * =============================================================================================                                     
     */
    
    /**
     * A function which generates all rules. Criteria for rule generation:
     * 1. Minimum support
     * 2. Minimum confidence
     * @param it The item to generate rules for
     */
    private void generateAllRules() {
        for (Item frequentItem: this.frequentItemSet) {
            List<Item> subsets = new ArrayList<>();
            Sets.powerSet(frequentItem.instances).forEach(set -> subsets.add(new Item(set)));
            subsets.removeIf(it -> it.instances.size() == 0);
            subsets.remove(frequentItem);
            subsets.forEach(it -> it.count =  this.database.getItemCount(it));
            
            for (Item subset: subsets) {
                Item consequent = new Item();
                consequent.addItem(frequentItem);
                consequent.removeItem(subset);
                
                Rule rule = new Rule(subset, consequent);
                if (satisfactory(rule)) {
                    this.rules.add(rule);
                }
            }
            
        }
    }
    
    private boolean satisfactory(Rule rule) {
        boolean supportMet = rule.support >= this.minSupport;
        boolean confidenceMet = rule.confidence >= this.minConfidence;
        boolean classInConsequent = false;
        
        for (Instance inst: this.consequentInstances) {
            if (rule.consequent.instances.contains(inst)) {
                classInConsequent = true;
                break;
            }
        }
                
        return supportMet && confidenceMet && classInConsequent;
    }
    
    /**
     * Function which filters the ruleset. Criteria:
     * 1. Consequent of the rule must contain the class label
     *
    private void filterRules() throws Exception {
        Instance in = new Instance(this.database.classLabel, "1");
        for (Rule rule: this.rules) {
            if (rule.consequent.instances.contains(in)) {
                System.out.println(rule);
            }
        }
    }
    */
    
    /**
     * Utility function to log messages to the console
     * @param message Message to be logged
     */
    private void log(Object message) {
        if (DEBUG) {
            System.out.println(message);
        }
    }
    
    private void printAllRules() {
        List<Rule> list = new ArrayList<>(this.rules);
        Collections.sort(list, new Comparator<Rule>() {

            @Override
            public int compare(Rule o1, Rule o2) {
                if (o1.confidence > o2.confidence) {
                    return -1;
                }
                else if (o1.confidence < o2.confidence) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            
        });
        
        list.forEach(rule -> log(rule));
    }
    
    /**
     * The main function which runs the algorithm 
     */
    public void runApriori() throws Exception {
        log("CREATING ONE ITEMSET....");
        createOneItemset();        
        prune();
        log("DONE!\n\n");
        
        log("GENERATING FREQUENT ITEMSETS...");
        while (this.candidateItemSet.size() != 0) {
            generateNextLevelItemset();
            calculateSupport();
            prune();
            //alternatePrune();
        }
        
        filterFrequentItemset();
        log("GENERATED "+this.frequentItemSet.size()+" FREQUENT ITEMS!\n\n");
        //this.frequentItemSet.forEach(itemset -> log(itemset));
        
        log("GENERATING RULES...");
        generateAllRules();
        printAllRules();
        log("\nGENERATED "+this.rules.size()+" RULES!");
    }
}
