package DataStructures;



public class Instance {
    public Attribute attribute;
    public String value;
    
    public Instance(Attribute attribute, String value) throws Exception {
        this.attribute = attribute;
        
        if (this.attribute.canHaveValue(value)) {
            this.value = value;
        }
        else {
            throw new Exception("Invalid value '"+value+"' for attribute '"
                    +attribute.name+"'");
        }
    }
    
    public Instance(Instance instance) {
        this.attribute = instance.attribute;
        this.value = instance.value;
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Instance) {
            Instance instance = (Instance) object;
            return this.attribute.equals(instance.attribute)
                    && this.value.equals(instance.value);
        }
        else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return  this.attribute.hashCode() * this.value.hashCode();
    };
    
    @Override
    public String toString() {
        return this.attribute.name + "=" + this.value;
    }
}
