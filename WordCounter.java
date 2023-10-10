/*
Mike Ciaccio
4/7/2022
CS 231
Project 7
*/

//Import things to read the files.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.*;

public class WordCounter{
    //Create feilds for bstmap and word counter
    private BSTMap<String, Integer> bst;
    private int wordCount;

    public WordCounter(BSTMap<String,Integer> map){
        //Initialise the map and count
        bst = map;
        wordCount = 0;
    }

    public void analyze(String filename){

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
        int amountToLoop = words.length;
        
        
       
      
        for (int i = 0; i < amountToLoop; i++) {
            String word = words[i].trim().toLowerCase();
           //If the word contains a number, diregard it 
            boolean containsNumber = word.matches(".*[0-9].*");
            
            //System.out.println(word);
        // Might want to check for a word of length 0 and not process it
            if(containsNumber){
                //If the word contains a number, diregard it 
                break;
            }
            if (word.length() == 0){
                //Skip words with no length
                continue;
            }else{
                //System.out.println(word);
                wordCount++;
                //System.out.println(bst.keySet());
                //System.out.println(word);
                //If the tree contains the word
                if(bst.containsKey(word)){
                    //System.out.println(word + " is already in the thingie, ");
                    Integer value = bst.get(word);
                    //System.out.println(value);
                    value++;
                    //System.out.println(value);
                    //Increment and put the value back in
                    bst.put(word, value);
                }else{
                    //Put the value into the tree.
                    bst.put(word, 1);
                    //System.out.println("Putting in a new entry");
                }
            }
        // Write code to update the map
        }
        //Read the next line
        line = b1.readLine();
      }
      //close the file
      b1.close();
      
          // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
          // print the String (line)
          // print the size of the String array (you can use .length)
          // assign to line the result of calling the readLine method of your BufferedReader object.
      // call the close method of the BufferedReader
      // return true
    }
    catch(FileNotFoundException ex) {
      System.out.println("Board.read():: unable to open file " + filename );
    }
    catch(IOException ex) {
      System.out.println("Board.read():: error reading file " + filename);
    }

    
    }

    public int getTotalWordCount(){
        /*return the total word count*/
        return wordCount;
    }

    public int getUniqueWordCount(){
        /*Return the total unique word count*/
        return bst.size();
    }

    public int getCount(String word){
        /*Return the counter of a specific word*/
        int counter = bst.get(word);
        return counter;
        }

    public double getFrequency(String word){
        /*Get the frequency of a word*/
        int amount = getCount(word);
        double freq = (double)amount/getTotalWordCount();
        return freq;
    }

    public void writeWordCountFile(String newFileName){
        /*Write the BST to a file*/
        //Create a string and put all things in the BST in the string
        String str = "";
        str += "Total Word Count: ";
        str += getTotalWordCount() + "\n";
        ArrayList<KeyValuePair<String,Integer>> outcomes = bst.entrySet();
        System.out.println("The entry set has been gotten");
        for(int i = 0; i < outcomes.size(); i++){
            str += outcomes.get(i).getKey();
            str += " "+ outcomes.get(i).getValue();
            str += "\n";
        }
       try{
           //Write the string into the file/
        FileWriter w1 = new FileWriter(newFileName, true);
        w1.write(str);
        w1.close();
       }
       catch(FileNotFoundException ex) {
       System.out.println("Board.read():: unable to open file " + newFileName );
       }
       catch(IOException ex) {
       System.out.println("Board.read():: error reading file " + newFileName);
     }
    }

    public void readWordCountFile(String filename){
        /*this method creates a BST from a file*/
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
            while(line != null){
                words = line.split(" ");
                //System.out.println(words);
                String key = words[0];
                Integer value = Integer.valueOf(words[1]);
                //Put the values into the bst
                bst.put(key,value);
                line = b1.readLine();
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        }
    }

    public static void main(String[] args){
        BSTMap<String, Integer> bst = new BSTMap<String, Integer>( new AscendingString() );
        WordCounter counter = new WordCounter(bst);
        String entryFile = args[0];
        String exitFile = args[1];

        long x = System.currentTimeMillis();
        System.out.println("Doing this");
        counter.analyze(entryFile);
        System.out.println("The entry file has been analyzed");
        counter.writeWordCountFile(exitFile);
        System.out.println("The word file has been written");
        counter.readWordCountFile(exitFile);
        System.out.println(counter.getUniqueWordCount());
        long y = System.currentTimeMillis();
        System.out.println("Total Time " + (y-x));
        System.out.println(counter.getTotalWordCount());
        //counter.analyze("countTest.txt");
        //System.out.println(counter.getTotalWordCount());
        //System.out.println(counter.getUniqueWordCount());
        //System.out.println(counter.getCount("the"));
        //System.out.println(counter.getFrequency("the"));a
        //counter.readWordCountFile("countTest.txt");
        //System.out.println(bst.entrySet());
        //System.out.println(counter.getTotalWordCount());
    }



}