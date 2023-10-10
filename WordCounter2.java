/*
Mike Ciaccio
CS 231
4/11/2022
 
Hashmap.java
*/

//Impprt io and ArrayList
import java.io.*;
import java.util.ArrayList;

public class WordCounter2{
    //Create fields for dataStructure, word count and collisions
    MapSet dataStructure;
    int wordCount;
    int collisions;
    

    public WordCounter2(String data_structure){
        /*This constructor decides what data structure to make based on the inputted string, and then creating it, setting word count to 0*/
        //If string is bst, make a bst
        if (data_structure.compareTo("bst") == 0){
            System.out.println("BST");
            dataStructure = new BSTMap<String, Integer>( new AscendingString() );
        }else{
            //make a hashmap
            dataStructure = new Hashmap(new AscendingString(), 1000000);
            collisions = 0;
        }
        //Word count = 0;
        wordCount = 0;
    }

    public ArrayList<String> readWords(String filename){
        /*Takes in a string filename and returns an arraylist of strings of all the words in the document*/
        //Create an arraylist
        ArrayList<String> words1 = new ArrayList<String>();
    try {
      // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
      FileReader f1 = new FileReader(filename);
      // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
      BufferedReader b1 = new BufferedReader(f1);
      

      // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
      String line = b1.readLine();
      //System.out.println(line);
      // start a while loop that loops while line isn't null
      int k = 0;
      //Go through each line
      while(line != null){
        k++;
        String[] words = line.split("[^a-zA-Z0-9']");
        //System.out.println(words.length);
        
        
        
       
      //Loop through the list of words in each line
        for (int i = 0; i < words.length; i++) {
            //Trim them to lower case
            String word = words[i].trim().toLowerCase();
           //If the word contains a number, diregard it 
            
            if (word.length() == 0){
                //Skip words with no length
                continue;
            }else{
                //Add them to the arraylist
                words1.add(word);
            }
        // Write code to update the map
        }
        /*if (k % 10000 == 0){
            System.out.println(k);
        }*/
        //Read the next line
        line = b1.readLine();
      }
      //close the file
      b1.close();
      
       
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }
    //Return the list of words
    return words1;
    }


    public double buildMap(ArrayList<String> words){
        /*This function takes in an arraylist of strings and make the appropriate data structure, returns how long it takes to build*/
        //Get the start time
        double x = System.nanoTime();
        wordCount = words.size();
        //Loop through all the words in the file, adding them to the data structure
        for(int i = 0; i < words.size(); i++){
            String word = words.get(i);
            if(dataStructure.get(word) != null){
                //If it is already in the data structure, increment the value and put it back in
                Integer amount = Integer.valueOf(String.valueOf(dataStructure.get(word)));
                if(amount > 1){
                    collisions++;
                }
                amount++;
                dataStructure.put(word, amount);
                //System.out.println("Duplicate item");
            }else{
                //Put the new value in 
                dataStructure.put(word, 1);
            }
        }
        //Get the time at the end
        double y = System.nanoTime();
        //Return how long it takes.
        return y-x;
    }

    public void clearMap(){
        /*Clears the data structure, and num of collisions*/
        dataStructure.clear();
        collisions = 0;
    }

    public int totalWordCount(){
        /*Returns the total word count*/
        return wordCount;
    }

    public int getUniqueWordCount(){
        /*Returns the unique word count*/
        return dataStructure.size();
    }

    public int getCount(String word){
        /*Returns the count of the word given*/
        if (dataStructure.get(word) != null){
            //Call the get method on the word in the data structure*/
            Integer amount = Integer.valueOf(String.valueOf(dataStructure.get(word)));
            return amount;
        }else{
            return 0;
        }
    }

    public double getFrequency(String word){
        /*Returns the frequency of the given word.*/
       if (dataStructure.get(word) != null){
           //Call the get method on the word in the data structure
            Integer amount = Integer.valueOf(String.valueOf(dataStructure.get(word)));
            //Return this amount divided by the amount of total words
            return amount/(double)wordCount;
        }else{
            return 0;
        }
        
    }
   

    public boolean writeWordCountFile(String filename){
        /*Takes in a file name, and writes a word file based on what comes in*/
        //Get a string and add the header
        String str = "";
        str += "Total Word Count: ";
        str += wordCount + "\n";
        ArrayList<KeyValuePair<String,Integer>> outcomes = dataStructure.entrySet();
        //Loop through the arraylist, and add them to the string
        for(int i = 0; i < outcomes.size(); i++){
            str += outcomes.get(i).getKey();
            str += " "+ outcomes.get(i).getValue();
            str += "\n";
        }
         try{
           //Write the string into the file/
        FileWriter w1 = new FileWriter(filename, true);
        w1.write(str);
        w1.close();
       }
       catch(FileNotFoundException ex) {
       System.out.println("Board.read():: unable to open file " + filename );
       }
       catch(IOException ex) {
       System.out.println("Board.read():: error reading file " + filename);
     }
     return true;

    }
    public boolean readWordCountFile(String filename){
        /*Takes in a file to read and creates a data structure of the values*/
        try{
            FileReader f1 = new FileReader(filename);
            // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
            BufferedReader b1 = new BufferedReader(f1);
      

            // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
            String line = b1.readLine();
            String[] words = line.split(" ");
            int index = words.length-1;
            int numOfWords = Integer.valueOf(words[index]);
            wordCount = numOfWords;
            line = b1.readLine();

            //Loop through all the lines, get the word, and add it to the data structure.
            while(line != null){
                words = line.split(" ");
                //System.out.println(words);
                String key = words[0];
                Integer value = Integer.valueOf(words[1]);
                //Put the values into the bst
                dataStructure.put(key,value);
                line = b1.readLine();
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
        return true;
    }

    public String toString(){
        /*Returns a string representation of the data structure*/
        String str = "The total word count is " + totalWordCount() + "\nThe unique word count is " + getUniqueWordCount();
        return str;
    }

    public static void main(String[] args){
        WordCounter2 counter = new WordCounter2("bst1");
        ArrayList<String> words = counter.readWords("reddit_comments_2009.txt");
        for(int i = 0; i < 2; i++){
            counter.clearMap();
            System.out.println(counter.buildMap(words));
            System.out.println(" " +counter);
            System.out.println();
        }
        //System.out.println("the depth of the tree is "+ counter.getDepth());
        
    }
}