import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
public class Classifier{
	List<String> spam = new ArrayList<String>();
	List<String> ham = new ArrayList<String>();
	public Hashtable<String,List<String>> classifying(Training read,List<String> messages){
		Hashtable<String,List<String>> classifier = new Hashtable<String,List<String>>();
		String type = "";
		for(int i = 0;i<messages.size();i++){
			type = read.probability(messages.get(i));
			if(type.equals("spam")){
				spam.add(messages.get(i));
			}else if(type.equals("ham")){
				ham.add(messages.get(i));
			}
		}
		classifier.put("spam",spam);
		classifier.put("ham",ham);
		return classifier;

	}
	public void generate(Hashtable<String,List<String>> classifier)throws IOException{
		String str = "Hello";
    	BufferedWriter writer = new BufferedWriter(new FileWriter("sorted.txt"));
    	writer.write("spam\n");
    	System.out.println("spam\n");
		for(int i = 0;i<spam.size();i++){
			System.out.println(spam.get(i)+"\n");
			writer.write(spam.get(i)+"\n");
		}
		writer.write("ham\n");
		System.out.println("ham\n");
		for(int i = 0;i<ham.size();i++){
			System.out.println(ham.get(i)+"\n");
			writer.write(ham.get(i)+"\n");
		}
     
    	writer.close();

		
	}
}