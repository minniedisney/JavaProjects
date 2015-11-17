// Name: Yaqiao Deng
// USC loginid: yaqiaode
// CS 455 PA4
// Fall 2014

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * contains main method 
 * handles error, processes args, inputs the source text, outputs the generated text
 */
public class GenText {
	
	static int originalVer=4; //original version contains 4 args
	static int debugVer=5; //debug version contains 5 args
	static int prefixLength ; 
	static int numWords ;
	static String sourceFile ;
	static String outFile ;
	static boolean debug = false;
	static ArrayList<String> text=new ArrayList<String>();
	
/**
 * main method
 * process the args and error handling	
 * 
 */
	public static void main(String args[]){
		try{			
			 if (args.length == originalVer) 
				{
				    prefixLength = Integer.parseInt(args[0]);
				    numWords = Integer.parseInt(args[1]);
				    sourceFile = args[2];
				    outFile = args[3];
				}
			    else if (args.length == debugVer && args[0].equals("-d")) 
				{
				    debug = true;
				    prefixLength = Integer.parseInt(args[1]);
				    numWords = Integer.parseInt(args[2]);
				    sourceFile = args[3];
				    outFile = args[4];
				}
			    else 
				{
				    System.out.println("ERROR: missing command-line arguments");
				    helpMsg();
				    
				}
			 if(numWords<0){
				 System.out.println("ERROR: numWords is less than 0");
				 helpMsg();
			 }
			 if(prefixLength<1){
				 System.out.println("ERROR: prefixLength is less than 1");
				 helpMsg();
			 }
			 
				 HashMap<Prefix,ArrayList<String>> makePrefixMap = readTextFile(sourceFile, prefixLength);
				 RandomTextGenerator textGene = new RandomTextGenerator(makePrefixMap);
				 outputText(textGene, outFile);
			 if(prefixLength>=text.size()){
				 System.out.println("ERROR: prefixLength is no less than number of words in sourceFile");
			 }
		}
		catch(FileNotFoundException e){
			System.out.println("ERROR: input file does not exist");
		} 
		catch (IOException e) {
			System.out.println("ERROR: can't write to output file");
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("ERROR: prefixLength is no less than number of words in sourceFile");
		}
		catch (NumberFormatException exp) 
	    {
		    System.out.println("ERROR: prefixLength or numWords are not integers");
		    helpMsg();
	    }
		
	}

/**
 * Convert the source text to a hashmap, which makes the search more efficient.
 * The key of the hashmap is every prefix in the source text. 
 * The value of the hashmap is a string arraylist which contains the words right behind the prefix.
 * @param fileName: the file name of the source text
 * @param prefixLength: length of the prefix
 * @return hashmap which is generated from the source text 
 * @throws IOException
 */
	
	public static HashMap<Prefix,ArrayList<String>> readTextFile(String fileName, int prefixLength) throws IOException{
		HashMap<Prefix,ArrayList<String>> PrefixMap=new HashMap<Prefix,ArrayList<String>>();
		Scanner file = new Scanner(new File(fileName));
		while(file.hasNext()){
	    	text.add(file.next());
	    }
		ArrayList<String> suffix = new ArrayList<String>();  
		ArrayList<String> first = new ArrayList<String>();  
		for(int i = 0 ; i < prefixLength ; i++){     
			first.add(text.get(i));
		}
		Prefix initialPre=new Prefix(first);
		suffix.add(text.get(prefixLength));    
		PrefixMap.put(initialPre, suffix);     
		for(int i = prefixLength ; i < text.size()-1 ; i++){   
			initialPre = initialPre.shiftIn(text.get(i));    
			if(PrefixMap.containsKey(initialPre)){   
				suffix=PrefixMap.get(initialPre);
			}
			else{ 
				suffix = new ArrayList<String>();   
			}
			suffix.add(text.get(i+1));     
			PrefixMap.put(initialPre, suffix);			
		}
		PrefixMap.put(initialPre.shiftIn(text.get(text.size()-1)), new ArrayList<String>()); 
		return PrefixMap;
	}
	
/**
 * Generate the random text
 * Store the generated words in an arraylist and output it into a formatted style.
 * @param textGene 
 * @param outFile: name of the output file
 * @throws IOException 
 */
	public static void outputText(RandomTextGenerator textGene, String outFile) throws IOException{
		textGene.hasText(debug, numWords);
		ArrayList<String> output = new ArrayList<String>(textGene.getText()); 
		toFormatted(output);
	}

	/**
	 * Format the output string arraylist to less than 80 words per line
	 * @param outArr
	 * @throws IOException
	 */
	public static void toFormatted(ArrayList<String> outArr) throws IOException{
		Writer out = new FileWriter(new File(outFile));
		int num = 0;   
		int count = 0;
		int i=0;
		while(i<outArr.size()-1){
			if(i == outArr.size()-1){
				out.write(outArr.get(i));
			}
			else{
				count = num + outArr.get(i).length() + 1 + outArr.get(i+1).length();
				if(count<=80){
					num = num + outArr.get(i).length() + 1;
					out.write(outArr.get(i) + " ");
				}
				else{
					out.write(outArr.get(i));
					out.append("\r\n");  
					num = 0; 
				}
			}
			i++;
		}
		out.close();  
	}

/**
 * Help message which helps run the program
 */
	public static void helpMsg()
    {
		System.out.println("");
		System.out.println("<HELP>");	
	    System.out.println("command-line argument: [-d] prefixLength numWords sourceFile outFile");
	    System.out.println("-d: turn on debugging and print out results");
	    System.out.println("prefixLength: the length of the randomly chosen prefix");
	    System.out.println("numWords: the number of the words in the output file");
	    System.out.println("sourceFile: the path to the source text");
	    System.out.println("outFile: the path to the output text");
    }

	
	
	
	
}


	


