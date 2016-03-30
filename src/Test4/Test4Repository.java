package Test4; 

import generator.AbstractRepository;
import java.util.ArrayList;
import java.io.IOException;

public class Test4Repository extends AbstractRepository {
	public ArrayList<Mot> mots = new ArrayList<Mot>();
	public ArrayList<Phrase> phrases = new ArrayList<Phrase>();

	public void addInstance(Mot mot){
		mots.add(mot);
	}

	public ArrayList<Mot> getMots(){
		 return mots;
	}

	public void addInstance(Phrase phrase){
		phrases.add(phrase);
	}

	public ArrayList<Phrase> getPhrases(){
		 return phrases;
	}



	@Override
	public void materialize(String path) throws IOException{
		Test4Repository temp = (Test4Repository) decodeFromFile(path);
		this.mots = temp.mots;
		this.phrases = temp.phrases;

	}
}