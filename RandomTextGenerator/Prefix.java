// Name: Yaqiao Deng
// USC loginid: yaqiaode
// CS 455 PA4
// Fall 2014


import java.util.ArrayList;

/**
 * A sequence of words using as a basis to choose the next word to generate
 * Store the words in an arraylist
 */
public class Prefix {
	
	private ArrayList<String> prefix=new ArrayList<String>();
	
   
    public Prefix(ArrayList<String> temp)  {
	for (int i=0;i<temp.size();i++){
		prefix.add(temp.get(i));
	}
    }

/**
 * ShiftIn method: shift the next generated word into prefix
 * @param next 
 * @return: a new prefix which contains the new word and drops the first word of the prefix
 */
   public Prefix shiftIn(String next){
	  ArrayList<String> temp=new ArrayList<String>();
	  for(int i=1;i<prefix.size();i++){
		  temp.add(prefix.get(i));	  
	  }
	  temp.add(next);
	   Prefix newprefix = new Prefix(temp);
	return newprefix;	
    }

/**
 * Convert the prefix to a string   
 */
    public String toString()
    {
	String output = "";
		int i=0;
	while(i < prefix.size()-1){
		output = output + prefix.get(i) + " ";
		i++;
	    }
	output=output+prefix.get(i);
	return output;
    }
    
/**
 * override equals and hashcode   
 */
    public boolean equals(Object o){  
		if(this.hashCode() == o.hashCode()){
			return true;
		}
		else{
			return false;
		}	
	}
		

    public int hashCode(){	
		return prefix.hashCode();
	}
	
}