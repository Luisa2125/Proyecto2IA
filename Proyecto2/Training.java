import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Training{
	List<String> hamMes = new ArrayList<String>();
	List<String> spamMes = new ArrayList<String>();
	Hashtable<String, Integer> ham = new Hashtable<String, Integer>();
	Hashtable<String, Integer> spam = new Hashtable<String, Integer>();
	double k = 0;
	List<String> clas = new ArrayList<String>();

	public void Reading(double value){
		this.k = value;
	}

	public void Read(String name)throws IOException{
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
    					
    				}else{
    					//System.out.println(ascii+" - "+character);
    				}
    				//System.out.println(line);


    			}try{
    				String[] typ = line.split("\t");
	    			if(typ[0].equals("ham")){
	    				this.hamMes.add(typ[1]);
	    			}else if (typ[0].equals("spam")) {
	    				
	    				this.spamMes.add(typ[1]);
	    			}else{
	    				clas.add(typ[0]);
	    			}
    			}
	    			
	    		catch(Exception e){
	    			System.out.println("Exception");
	    			clas.add(line);

	    		}
				
    			//System.out.println(hamMes);
    			//System.out.println(typ[0]+"-"+typ[1]);
    			
    			//System.out.println(line);
    			
    		//System.out.println(ham.size()+"-"+spam.size());
    		}
    		
    		
    	//
		} catch(Exception e){
			br.close();
			//System.out.println("holi holi");
			//System.out.println(this.ham.size()+"-"+spam.size());
		}
		//System.out.println(spamMes);
    	//System.out.println(">>"+spam);
		
    	

		//System.out.println("holi holi");
		//System.out.println(this.ham.size()+"-"+spam.size());

	}
	public List<String> getClas(){
		return clas;
	}
	public void trainHam(){
		//System.out.println(hamMes.size()*0.8);
		int limit = (int)((long)(hamMes.size()*0.8));
		//System.out.println(limit);
		for(int i = 0;i<limit;i++){
			String[] words = hamMes.get(i).split(" ");

			for(int j = 0;j<words.length;j++){
				String word= words[j].toLowerCase();
				//System.out.println(word);
				if(word.length() > 1 && word.charAt(0) != ((char)00)){
					if(ham.containsKey(word)){
						int count = ham.get(word);
						this.ham.put(word,count+1);
					}else{
						this.ham.put(word,1);
					}
									//System.out.println(ham.size()+"-"+spam.size());
				}
			}
			
		}
		//System.out.println(ham);
		
	}
	public void trainSpam(){
		//System.out.println(spamMes.size()*0.8);
		int limit = (int)((long)(spamMes.size()*0.8));
		//System.out.println(limit);
		for(int i = 0;i<limit;i++){
			//System.out.println(i);
			String[] words = spamMes.get(i).split(" ");
			//System.out.println(words);
			for(int j = 0;j<words.length;j++){
				String word= words[j].toLowerCase();
				if(word.length() > 1 && word.charAt(0) != ((char)00)){
					if(spam.containsKey(word)){
						int count = spam.get(word);
						//System.out.println(word);
						this.spam.put(word,count+1);
					}else{
						this.spam.put(word,1);
					}
									//System.out.println(ham.size()+"-"+spam.size());
				}
			}
			
		}
		//System.out.println(words[0]);
		
	}
	public Hashtable<String,Integer> getHam(){
		return ham;
	}
	public List<String> getHamM(){
		return hamMes;
	}
	public List<String> getSpamM(){
		return spamMes;
	}
	public Hashtable<String,Integer> getSpam(){
		return spam;
	}
	/**public int proba(){
		double spamP= 0;
		double hamP = 0;
		int k = 0.9;
		int limit1 = (int)((long)(spamMes.size()*0.8));
		int limit2 = (int)((long)(spamMes.size()*0.8 + spamMes.size()*0.1));
		System.out.println(spamMes.size()+"-"+limit1+"-"+limit2);
		for(int i = limit1;i<limit2;i++){
			String[] words = spamMes.get(i).split(" ");
			double probS = 0;
			double probH = 0;
			for(int j = 0;j<words.length;j++){
				probS += probSpam(words[i],k);
				probH += probHam(words[i],k);

			}

			spamP = (probS * probAbSpam(k))/((probS*probAbSpam())+(probH*probAbHam))
			hamP = (probH * probAbHam(k))/((probH*probAbHam())+(probS*probAbSpam));
			System.out.println(spamP+" - "+hamP);
		}
		return limit1;
	}**/
	public double probAbSpam(){
		double probabilidad = 0;
		//System.out.println((spamMes.size() + k)+" "+(spamMes.size()+hamMes.size())+(k*2));
		probabilidad = (spamMes.size() + k)/((spamMes.size()+hamMes.size())+(k*2));
		return probabilidad;
	}
	public double probAbHam(){
		double probabilidad = 0;
		probabilidad = (hamMes.size() + k)/((spamMes.size()+hamMes.size())+(k*2));
		return probabilidad;
	}
	public double probSpam(String palabra){
		double probabilidad = 0;
		double suma = 0;
		int i =0;
		int cant = 0;
		List<String> words = new ArrayList<String>(ham.keySet());
		//System.out.println(words);
		words.addAll(spam.keySet());
		//System.out.println(words);
		Set<String> hs = new HashSet<>();
		hs.addAll(words);
		words.clear();
		words.addAll(hs);
		//System.out.println(words);
		//System.out.println(spam);
		try{
			cant = spam.get(palabra);
		}
		catch(Exception e){
			cant = 0;
		}
		probabilidad = (cant + k)/(spamMes.size()+(k*words.size()));
		//System.out.println(cant+" - "+spamMes.size()+" - "+words.size());
		
		
		//System.out.println(spam.size());
		return probabilidad;
	}
	public double probHam(String palabra){
		double probabilidad = 0;
		double suma = 0;
		int i =0;
		int cant = 0;
		List<String> words = new ArrayList<String>(spam.keySet());
		//System.out.println(words);
		words.addAll(ham.keySet());
		//System.out.println(words);
		Set<String> hs = new HashSet<>();
		hs.addAll(words);
		words.clear();
		words.addAll(hs);
		//System.out.println(words);
		//System.out.println(spam);
		try{
			cant = ham.get(palabra);
		}
		catch(Exception e){
			cant = 0;
		}
		probabilidad = (cant + k)/(hamMes.size()+(k*words.size()));
		//System.out.println(cant+" - "+hamMes.size()+" - "+words.size());
		
		
		//System.out.println(spam.size());
		return probabilidad;
	}
	public double divide(String sentence,String type){
		double probabilidad = 1;
		String[] words = sentence.split(" ");
		//System.out.println(">"+words.length);
		for(int i = 0;i<words.length;i++){
			if(type.equals("spam")){
				//System.out.println("."+probSpam(words[i])+" , "+probabilidad);	
				//if(probabilidad * probSpam(words[i])>0){
					probabilidad = Math.exp(Math.log(probabilidad) + Math.log(probSpam(words[i])));
				//}

			}
			else if(type.equals("ham")){
				//System.out.println("."+probSpam(words[i])+" , "+probabilidad);		
				//if(probabilidad * probHam(words[i])>0){
					probabilidad = Math.exp(Math.log(probabilidad) + Math.log(probHam(words[i])));
				//}
			}
		}

		return probabilidad;
	
	}
	public String probability(String sentence){
		double probabilidadS = 1;
		double probabilidadH = 1;
		double hamP = probAbHam();
		double spamP = probAbSpam();
		//System.out.println(">"+divide(sentence,"spam"));
		//System.out.println(">>"+divide(sentence,"ham"));
		probabilidadS = (divide(sentence,"spam")*spamP)/((divide(sentence,"spam")*spamP)+(divide(sentence,"ham")*hamP));
		//System.out.println("--"+divide(sentence,"spam")+","+spamP+","+divide(sentence,"ham")+","+hamP);
		probabilidadH = (divide(sentence,"ham")*hamP)/((divide(sentence,"ham")*hamP)+(divide(sentence,"spam")*spamP));
		
		//System.out.println(probabilidadS+" - "+probabilidadH);
		if(probabilidadS>probabilidadH){
			return "spam";
		}else{
			return "ham";
		}
		

	}
	public double cross(){
		double performance = 0;
		double total = 0;
		int limit1 = (int)((long)(hamMes.size()*0.8));
		int limit2 = (int)((long)(hamMes.size()*0.8))+(int)((long)(hamMes.size()*0.1))+1;
		//System.out.println(limit1+" - "+limit2);
		int aciertos = 0;
		String sel = "";
		total+= limit2-limit1;
		for(int i = limit1;i<limit2;i++){
			sel = probability(hamMes.get(i));
			if (sel.equals("ham")){
				aciertos+=1;
			}
		}
		limit1 = (int)((long)(spamMes.size()*0.8));
		limit2 = (int)((long)(spamMes.size()*0.8))+(int)((long)(spamMes.size()*0.1))+1;
		for(int i = limit1;i<limit2;i++){
			sel = probability(spamMes.get(i));
			if (sel.equals("spam")){
				aciertos+=1;
			}
		}
		total+= limit2-limit1;
		//System.out.println("a"+aciertos+" - t"+total);
		performance = aciertos/total;
		return performance;

	}
	public double test(){
		double performance = 0;
		double total = 0;
		int limit1 = (int)((long)(hamMes.size()*0.8))+(int)((long)(hamMes.size()*0.1));
		int limit2 = limit1+(int)((long)(hamMes.size()*0.1));
		//System.out.println(limit1+" - "+limit2);
		int aciertos = 0;
		String sel = "";
		total+= limit2-limit1;
		for(int i = limit1;i<limit2;i++){
			sel = probability(hamMes.get(i));
			if (sel.equals("ham")){
				aciertos+=1;
			}
		}
		limit1 = (int)((long)(spamMes.size()*0.8))+(int)((long)(spamMes.size()*0.1));
		limit2 = limit1+(int)((long)(spamMes.size()*0.1));
		for(int i = limit1;i<limit2;i++){
			sel = probability(spamMes.get(i));
			if (sel.equals("spam")){
				aciertos+=1;
			}
		}
		total+= limit2-limit1;
		//System.out.println("a"+aciertos+" - t"+total);
		performance = aciertos/total;
		return performance;
	}

}