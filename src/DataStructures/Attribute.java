package DataStructures;
import java.util.HashSet;
import java.util.Set;

public class Attribute {
    public String name;
    Set<String> validValues;
    
    public Attribute(String name) {
        this.name = name;
        validValues = new HashSet<String>();
    }
    
    public void addValue(String validValue) {
        validValues.add(validValue);
    }
    
    public boolean canHaveValue(String value) {
        return validValues.contains(value);
    }
    
    @Override
    public String toString() {
        return name + ": " + validValues.toString();
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Attribute) {
            Attribute that = (Attribute) object;
            return this.name.equals(that.name);
        }
        else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
        
    }
}
