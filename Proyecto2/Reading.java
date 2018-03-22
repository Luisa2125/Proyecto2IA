import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Reading{
	List<String> doc = new ArrayList<String>();
	Hashtable<String, Integer> ham = new Hashtable<String, Integer>();
	Hashtable<String, Integer> spam = new Hashtable<String, Integer>();

	public List<String> Read(String name)throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("./"+name));
		
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			//System.out.println(br.readLine());
			
			while (line != null) {
				//System.out.println(">>"+line.isEmpty());
    			//sb.append(line);
    			//sb.append(System.lineSeparator());
    			line = br.readLine();
    			//System.out.println(line);
    			for(int i=0; i<line.length();i++){
    				char character = line.charAt(i);
    				int ascii = (int)character;
    				if (!(ascii>64 && ascii<91) && !(ascii == 32) && !(ascii == 9) && !(ascii > 96 && ascii<123)){
    					//System.out.println(ascii);
    					line = line.replace(character,((char)00));
    					//System.out.println(line);
    					doc.add(line);
    				}else{
    					//System.out.println(ascii+" - "+character);
    				}
    				//System.out.println(line);


    			}
    			String[] typ = line.split("\t");
    			
    			//System.out.println(typ[0]+"-"+typ[1]);
    			String[] words = typ[1].split(" ");
    			//System.out.println(words[0]);
    			for(int i = 0;i<words.length;i++){
    				String word= words[i].toLowerCase();
    				
    				if(word.length() > 1 && word.charAt(0) != ((char)00)){

	    				//System.out.println(word+" - "+word.length());
	    				if(typ[0].equals("ham")){
	    					//System.out.println(word+" - "+word.length());
	    					if(ham.containsKey(word)){
	    						int count = ham.get(word);
	    						this.ham.put(word,count+1);
	    					}else{
	    						this.ham.put(word,1);
	    						
	    					}
	    					
	    				}else{
	    					if(spam.containsKey(word)){
	    						int count = spam.get(word);
	    						this.spam.put(word,count+1);
	    					}else{
	    						this.spam.put(word,1);
	    						
	    					}
	    				}
	    				//System.out.println(ham.size()+"-"+spam.size());
	    			}
    			}
    			//System.out.println(line);
    			
    		//System.out.println(ham.size()+"-"+spam.size());
    		}

    	//
		} catch(Exception e){
			br.close();
			//System.out.println("holi holi");
			//System.out.println(this.ham.size()+"-"+spam.size());
		}
		//System.out.println("holi holi");
		//System.out.println(this.ham.size()+"-"+spam.size());
		return doc;
	}
	public Hashtable<String,Integer> getHam(){
		return ham;
	}
	public Hashtable<String,Integer> getSpam(){
		return spam;
	}
}