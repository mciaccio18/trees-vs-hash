/*
Mike Ciaccio
CS231
4/4/2022
KeyValuePair.java

*/


public class KeyValuePair<Key,Value>{
    //Create values for key and value
    private Key key;
    private Value value;

    public KeyValuePair(Key k, Value v){
        /*Constructor for keyValuePair, assigns the values taken in to the correct spots.*/
        key = k;
        value = v;
    }

    public Key getKey(){
        /*This method returns the key*/
        return key;
    }

    public Value getValue(){
        /*This method returns the value*/
        //System.out.println("the current value is" + value);
        return value;
    }

    public void setValue(Value v){
        /*This method takes in a value and assigns it to the value*/
        //System.out.println("Setting the new value to "+ v);
        this.value = v;
        //System.out.println(this.value);
    }

    public String toString(){
        /*This method returns a string representation of the key and value*/
        return ""+ key+": "+value;
    }

    public static void main(String[] args){
        KeyValuePair k = new KeyValuePair("bubbles", 22);
        System.out.println(k);
        k.setValue(54);
        System.out.println(k.getValue());
        System.out.println(k.getKey());
    }
}