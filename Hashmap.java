/*
Mike Ciaccio
CS 231
4/11/2022
 
Hashmap.java
*/
//Import arraylist and the comparator
import java.util.ArrayList;
import java.util.Comparator;


public class Hashmap<K,V> implements MapSet<K,V>{
    //Create fields for size, comparator, an array of BST maps, and an int for collisions.
    private int size;
    private Comparator<String> c;
    private BSTMap[] keysArray;
    private int collisions;
    // Hashmap constructor that starts with default size hash table
    public Hashmap(Comparator<K> incomp) {
        /*Set the size equal to zero, create a comparator, and hashmap with length 10*/
        size = 0;
        c = new AscendingString();
        keysArray = new BSTMap[10];
         //Create a new bstmap for each entry
        for(int i = 0; i < keysArray.length; i++){
            keysArray[i] = new BSTMap(c);
        }

    }

    // Hashmap constructor that starts with the suggecsted capacity hash table
    public Hashmap( Comparator<K> incomp, int capacity ) {
         /*Set the size to zero, and set the comparator and capacity to the values*/
        size = 0;
        c = new AscendingString();
        keysArray = new BSTMap[capacity];
         //Create a new bstmap for each entry
        for(int i = 0; i < keysArray.length; i++){
            keysArray[i] = new BSTMap(c);
        }

    }

    public V put( K new_key, V new_value ){
        /*Puts a set of key and value into the hashmap*/
        if(get(new_key) != null){
            collisions++;
        }
        //Create a key value pair
        KeyValuePair kV = new KeyValuePair(new_key, new_value);
        //Get ints for the hash, and the bucket index
        int hash = new_key.hashCode();
        int bucketIndex = Math.abs(hash % keysArray.length);
        //Get the bstmap from the hasmap
        BSTMap bst = keysArray[bucketIndex];
        //Insert the value into the hashmap
        V value = (V)bst.put(new_key,new_value);
        

        //Return the value
       return value;
    }
    
    // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ){
        /*Returns if the key is in the hashmap*/
        //Get ints for the hashmap and bucket index
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % keysArray.length);
        //Get the bstmap from that location
        BSTMap bst = keysArray[bucketIndex];
        //Ask the bst if it contains the key
        boolean contains = bst.containsKey((String)key);
        //Return the value
        return contains;
    }
    
    // Returns the value associated with the given key.
    // If that key is not in the map, then it returns null.
    public V get( K key ){
         /*REturns whether or not the key is in the hashmap*/
        //Get the hash and bucket index
        int hash = key.hashCode();
        int bucketIndex = Math.abs(hash % keysArray.length);
         //Get the bst
        BSTMap bst = keysArray[bucketIndex];
        //System.out.println(bst);
        //Call the bst's get method
        V value = (V)bst.get(key);
        

        
        //Return the value
        return value;
    }
    
    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet(){
        /*Returns an arraylist of keys in the set*/
        //Create an arraylist of keys
        ArrayList<K> keys = new ArrayList<K>();
        size = keys.size();
        //Go through each index of the hashmap, get the arraylist of there keys, and add them to the big list.
        for (int i = 0; i < keysArray.length; i++){
            ArrayList<K> subCount = keysArray[i].keySet();
            keys.addAll(subCount);

        }
        //Return this arraylist
        return keys;
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values(){
         /*Returns a arraylist of values */
        ArrayList<V> values = new ArrayList<V>();
        //Go through each index of the hashmap, get the values, and add it to the list
        for (int i = 0; i < keysArray.length; i++){
            ArrayList<V> subVals = keysArray[i].values();
            values.addAll(subVals);

        }
        //Return the list
        return values;
    }

    public int getCollisions(){
        /*Returns the number of collisions*/
        return collisions;
    }
    
    // return an ArrayList of pairs.
    public ArrayList<KeyValuePair<K,V>> entrySet(){
        /*Returns an arraylist of key value pairs*/
        //declare the arraylists
        ArrayList<KeyValuePair<K,V>> total = new ArrayList<KeyValuePair<K,V>>();
        ArrayList<V> values = values();
        ArrayList<K> keys = keySet();

        //Loop through the keys and values, and add them to the arraylist.
        for (int i = 0; i < values.size(); i++){
            KeyValuePair kV = new KeyValuePair(keys.get(i), values.get(i));
            total.add(kV);
            
        }
        return total;
    }

    // Returns the number of key-value pairs in the map.
    public int size(){
        /*Returns the number of key value paris in the map*/
        int size = 0;
        for(int i = 0; i < keysArray.length; i++){
            int x = keysArray[i].size();
            size += x;
        }
        return size;

    }
        
    // removes all mappings from this MapSet
    public void clear(){
        /*Clears the list by making a new array*/
        BSTMap[] keysArray = new BSTMap[size];
    }

    public static void main(String[] args){
        Hashmap h = new Hashmap(new AscendingString(), 1000);
        //System.out.println(h.size());
        h.put("hello", 1);
        h.put("0", 2);
        h.put("he", 3);
        h.put("1", 4);
        h.put("bubbles", 5);
        h.put("2", 6);
        h.put("urmomma", 7);
        System.out.println(h.entrySet());
    }
}