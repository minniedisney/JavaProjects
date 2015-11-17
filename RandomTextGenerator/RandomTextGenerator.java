// Name: Yaqiao Deng
// USC loginid: yaqiaode
// CS 455 PA4
// Fall 2014

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 * This class generates the next word using hashmap and store the words in an arraylist.
 * Map the prefix to a string arraylist successor, which contain all the words following the key prefix.
 */
public class RandomTextGenerator {

	private HashMap<Prefix,ArrayList<String>> prefixmap;
	private ArrayList<String> newText;
	
	public RandomTextGenerator(HashMap<Prefix,ArrayList<String>> prefixmap) {
	    this.prefixmap=prefixmap;    
	    newText=new ArrayList<String>();
	}

/**
 * Use the hasText method to generate the random text from hashmap
 * search in the successor string arraylist and randomly choose a word as the next word
 * @param debug: debug mode
 * @param num: number of the words in the output arraylist.
 * @return the randomly generated string arraylist
 */
	
	public void hasText(boolean debug, int num){
		
		String nextword="";
		ArrayList<String> successor=new ArrayList<String>();
		Set<Prefix> prefixSet = prefixmap.keySet();  
		ArrayList<Prefix> prefixArr = new ArrayList<Prefix>(prefixSet); //randomly chosen the first prefix from hashmap key set
		Random rand=new Random();
		Prefix currentPre=prefixArr.get(rand.nextInt(prefixSet.size()));
		newText.add(currentPre.toString()); //add the first prefix to the generated text
		if(debug){
			System.out.println("DEBUG: chose a new initial prefix: " + currentPre.toString());
			System.out.println("DEBUG: prefix: " +currentPre.toString());
		}
		successor=prefixmap.get(currentPre);
		for(int i=0; i<num;i++){
			if(successor.isEmpty()){ 
				currentPre=prefixArr.get(rand.nextInt(prefixSet.size())); //randomly choose a prefix again if it reaches the end of file
				newText.add(currentPre.toString()); 
				if(debug){
					System.out.println("DEBUG: successors: <END OF FILE>"); 
					System.out.println("DEBUG: chose a new initial prefix:" + currentPre.toString());
					System.out.println("DEBUG: prefix: " + currentPre.toString());
				}
			}
			
			else{
			nextword=successor.get(rand.nextInt(successor.size()));
			newText.add(nextword); //add the generated string from successor to new text
			currentPre=currentPre.shiftIn(nextword);
			successor=prefixmap.get(currentPre); //update the successor
			if(debug){
                 String sucStr="";
                     for(int j=0;j<successor.size();j++){
	                       sucStr += successor.get(j) + " ";
                  }
				System.out.println("DEBUG: successors: " + sucStr);
				System.out.println("DEBUG: word generated: " + nextword);
				System.out.println("DEBUG: prefix: " + currentPre.toString());
			}	
		}
		}

	}

/**
 * get the generated text
 * @return String arraylist newText
 */
	public ArrayList<String> getText() {

		return newText;
	} 
			
		
	}
	

