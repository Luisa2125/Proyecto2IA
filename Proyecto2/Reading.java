public class Classifier{
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


    			}
    			String[] typ = line.split("\t");
    			if(typ[0].equals("ham")){
    				this.hamMes.add(typ[1]);
    			}else if (typ[0].equals("spam")) {
    				
    				this.spamMes.add(typ[1]);
    			}
				
    			//System.out.println(hamMes);
    			//System.out.println(typ[0]+"-"+typ[1]);
    			
    			//System.out.println(line);
    			
    		//System.out.println(ham.size()+"-"+spam.size());
    		}
}