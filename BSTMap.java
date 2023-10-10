/*
Mike Ciaccio

Template for the BSTMap classes
Fall 2020
CS 231 Project 6
*/
import java.util.ArrayList;
import java.util.Comparator;

public class BSTMap<K, V> implements MapSet<K, V> {
    // fields here
    private TNode mainNode;
    private int size;
    private Comparator<K> c;
    


	

	// constructor: takes in a Comparator object
	public BSTMap( Comparator<K> comp ) {
		mainNode = null;
                c = comp;
                size = 0;
	}

	// adds or updates a key-value pair
	// If there is already a pair with new_key in the map, then update
	// the pair's value to new_value.
	// If there is not already a pair with new_key, then
	// add pair with new_key and new_value.
	// returns the old value or null if no old value existed
	public V put( K key, V value ) {
                /*puts the value into the bst*/
				// check for and handle the special case
                TNode node = new TNode(key,value);
                
                if (mainNode == null){
                        size++;
                        mainNode = node;
                        //System.out.println("A new mainNode has been set "+ mainNode);
                        
                }else{
                        //System.out.println("Putting the node elsewhere");
                        node.put(key,value, c);
                }
                
				// call the root node's put method

				// stub code
				return value;
        }

    // gets the value at the specified key or null
    public V get( K key ) {
            /*Get the value from the bst*/
            // check for and handle the special case
            
            // call the root node's get method
            TNode node = new TNode(key, null);
            //System.out.println(node);
            V x = node.get(key,c);



            // stub code
            return x;
    }

     // Returns true if the map contains a key-value pair with the given key
    public boolean containsKey( K key ){
            /*Returns wether or not the bst has the key*/
            //ArrayList<K> keys = keySet();
            //System.out.println("The set of keys is "+ keys);

            if (get(key) == null){
                    return false;
            }
            return true;
    }
   
    // Returns an ArrayList of all the keys in the map. There is no
    // defined order for the keys.
    public ArrayList<K> keySet(){
            /*Returns an arraylsit of keys*/
            ArrayList<K> newAr = new ArrayList<K>();
            ArrayList<K> keys = treeTravel(mainNode, newAr);
            //System.out.println(keys);
            return keys;
            
    }

    // Returns an ArrayList of all the values in the map. These should
    // be in the same order as the keySet.
    public ArrayList<V> values(){
            /*returns an arraylist of values*/
            ArrayList<K> keys = keySet();
            ArrayList<V> values = new ArrayList<V>();
            //System.out.println("The keys are " +keys);
            for (int i = 0; i < keys.size(); i++){
                    K key = keys.get(i);
                    V value = get(key);
                    //System.out.println("The return value of " + key+ " is " + value);
                    //System.out.println("The value is " + value);
                    values.add(value);
            }
            return values;
    }

    

   

    

    

    private ArrayList<K> treeTravel(TNode node, ArrayList<K> values) {
            /*Helper method for traversing the tree*/
        if (node != null) {
            values.add(node.pair.getKey());
            treeTravel(node.left, values);
            //System.out.println("Adding" + node.pair.getKey());
            treeTravel(node.right,values);
            
        }
        return values;
    }
    
    // return an ArrayList of pairs.
    public ArrayList<KeyValuePair<K,V>> entrySet(){
            /*Returns an arraylist of keyvalue pairs*/
            ArrayList<KeyValuePair<K,V>> pairs = new ArrayList<KeyValuePair<K,V>>();
            ArrayList<K> keys = keySet();
            System.out.println("Got the keys");
            ArrayList<V> values = values();
            System.out.println("Got the values");
            //System.out.println(values);
            for (int i = 0; i < keys.size(); i++){
                KeyValuePair<K,V> kV = new KeyValuePair<K,V>(keys.get(i),values.get(i));
                //System.out.println("The key value pair is " + kV);
                pairs.add(kV);
            }
            return pairs;
    }

    // Returns the number of key-value pairs in the map.
    public int size(){
            /*Returns size*/
            return size;
    }
        
    // removes all mappings from this MapSet
    public void clear(){
            /*Clears*/
            mainNode = null;
    }

    // Write stubs (functions with no code) for the remaining
    // functions in the MapSet interface


	// entrySet notes: For the sake of the word-counting project, the
    // pairs should be added to the list by a pre-order traversal.



    // You can make this a nested class
    private class TNode {
        TNode left;
        TNode right;
        KeyValuePair<K,V> pair;
            // need fields for the left and right children
            // need a KeyValuePair to hold the data at this node

            // constructor, given a key and a value
            public TNode( K k, V v ) {
                    // initialize all of the TNode fields
                    left = null;
                    right = null;
                    pair = new KeyValuePair<K,V>(k,v);
            }

            // Takes in a key, a value, and a comparator and inserts
            // the TNode in the subtree rooted at this node 

			// Returns the value associated with the key in the subtree
			// rooted at this node or null if the key does not already exist
            public V put( K key, V value, Comparator<K> comp ) {
                    /*puts the value into the bst*/
                    // implement the binary search tree put
                    // insert only if the key doesn't already exist
                    TNode newNode = new TNode(key,value);
                    TNode focusNode = mainNode;
                    TNode parent;
                    while(true){
                            //System.out.println();
                            //System.out.println("The main node is" + mainNode);
                            //System.out.println("The focus node is " +focusNode);
                            parent = focusNode;
                            
                            //System.out.println("Comparing " + key + " with " + parent.pair.getKey());
                            //System.out.println(ogValue);
                            //System.out.println(key);
                            int outcome = comp.compare(key,parent.pair.getKey());
                            //System.out.println(outcome);
                            if(outcome < 0){
                                    focusNode = focusNode.left;
                                    //System.out.println(key + " has been put to the left of " +parent.pair.getKey() );

                                    if(focusNode == null){
                                            parent.left = newNode;
                                            size++;
                                            return value;
                                    }
                                }else if(outcome == 0){
                                        //System.out.println("This value was already seen");
                                        //System.out.println("Setting the value to " + value);
                                        //System.out.println("The current value of the pair is " + pair);
                                        focusNode.pair.setValue(value);
                                        //System.out.println("The new value of the pair is " + pair);
                                        return value;
                                        }else{
                                                //System.out.println(key + " has been put to the right of " +parent.pair.getKey() );
                                                focusNode = focusNode.right;
                                                if(focusNode == null){
                                                parent.right = newNode;
                                                size++;
                                                return value;
                                                }
                                    }
                            }
                        
                    }
                    
                    




            // Takes in a key and a comparator
            // Returns the value associated with the key or null
            public V get( K key, Comparator<K> comp ) {
                    /*Get the value from the bst*/
                    TNode focusNode = mainNode;
                    TNode parent;
                    while(true){
                            //System.out.println();
                            //System.out.println("The main node is" + mainNode);
                            //System.out.println("The focus node is " +focusNode);
                            parent = focusNode;
                            if (parent == null){
                                    return null;
                            }
                            //System.out.println("Comparing " + key + " with " + parent.pair.getKey());
                            //System.out.println(ogValue);
                            //System.out.println(key);

                            int outcome = comp.compare(key,parent.pair.getKey());
                            //System.out.println(outcome);
                            if(outcome < 0){
                                    focusNode = focusNode.left;
                                    //mainNode = mainNode.left;
                                    //System.out.println(key + " has been put to the left of " +parent.pair.getKey() );

                                    if(focusNode == null){
                                            
                                            return null;
                                    }
                                }else if(outcome == 0){
                                        //System.out.println("Found the node");
                                        //System.out.println(mainNode.pair.getValue());
                                        return focusNode.pair.getValue();
                                        
                                        }else{
                                                //System.out.println(key + " has been put to the right of " +parent.pair.getKey() );
                                                focusNode = focusNode.right;
                                                //mainNode = mainNode.right;
                                                if(focusNode == null){
                                                
                                                return null;
                                                }
                                    }
                            }
                        
                    

                    // stub code
                  
            }

            public String toString(){
                    return "" +pair;
            }

            // Any additional methods you want to add below, for
            // example, for building ArrayLists
            
            
    }// end of TNode class

    // test function
    public static void main( String[] argv ) {
            
            
               

            // create a BSTMap
            BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );
            bst.put( "cock", 11 );
            bst.put( "apple", 20 );
            bst.put( "banana", 10 );
            bst.put( "tiger", 6 );
            bst.put( "tiger", 10);
            System.out.println(bst.get("tiger"));

            //System.out.println( "egg" + bst.get( "egg" ) );
            //System.out.println( "corn" + bst.get( "corn" ) );
            //System.out.println( "apple" + bst.get( "apple" ) );
            //System.out.println( "banana" + bst.get( "banana" ) );
            //System.out.println(bst.containsKey("egg"));
            //System.out.println(bst.containsKey("apple"));
            //System.out.println(bst.containsKey("corn"));

            System.out.println(bst.keySet());
            System.out.println(bst.values());
            System.out.println(bst.entrySet());

            // put more test code here

            
    }

}