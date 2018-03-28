import java.util.*;
import java.io.IOException;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.EventQueue;

public class Proyecto2{
	
	public static void main(String[] args) throws IOException{
		Training read = new Training();
		Classifier classi = new Classifier();
		Hashtable<String,List<String>> classifier = new Hashtable<String,List<String>>();
		read.Read("corpus.txt");
		read.Read("corpus_2.txt");
		read.Reading(0.6);
		read.trainHam();
    	read.trainSpam();
    	classifier =  classi.classifying(read,read.getClas());
    	classi.generate(classifier);
		//System.out.println(read.getSpamM());
		//System.out.println(read.getSpam());
		//System.out.println(read.getHamM());
		//System.out.println(read.getHam());
		//System.out.println(read.proba());
		//System.out.println(read.probAbHam());
		//System.out.println(read.probAbSpam());
		//System.out.println(read.probSpam("secret"));
		//System.out.println(read.probHam("secret"));
		//System.out.println(read.divide("secret is new","ham"));
		//System.out.println(read.probability("Lol Oops sorry Have fun"));
		//System.out.println(read.getClas());
		//System.out.println(read.cross());
		//System.out.println(read.test());
		//System.out.println(read.probHam("Get",100));
    	
		
	}
	
	
	
}