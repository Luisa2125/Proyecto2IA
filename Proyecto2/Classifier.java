/**
 * Created by luisa on 7/04/18.
 */
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;
public class Classifier{
    List<String> spam = new ArrayList<String>();
    List<String> ham = new ArrayList<String>();
    //dividir las frases en sus respectivos arrayss
    public Hashtable<String,List<String>> classifying(Training read,List<String> messages){
        Hashtable<String,List<String>> classifier = new Hashtable<String,List<String>>();
        spam.clear();
        ham.clear();
        String type = "";
        //por cada mensaje en la lista
        for(int i = 0;i<messages.size();i++){
            //vemos a a cual pertenece
            type = read.probability(messages.get(i));
            //lo guardo en su respectivo array
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
    //genero el txt con los mensajes clasificados en spam y ham
    public void generate(Hashtable<String,List<String>> classifier)throws IOException{
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter("sorted.txt"));
        writer.write("spam\n");
        System.out.println("spam\n");
        //System.out.println(">>|"+spam);
        for(int i = 0;i<spam.size();i++){
            System.out.println(spam.get(i)+"\n");
            writer.write(spam.get(i)+"\n");
        }
        writer.write("ham\n");
        System.out.println("ham\n");
        //System.out.println(">>|"+ham);
        for(int i = 0;i<ham.size();i++){
            System.out.println(ham.get(i)+"\n");
            writer.write(ham.get(i)+"\n");
        }

        writer.close();


    }
}