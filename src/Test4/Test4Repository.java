package Test4; 

import generator.AbstractRepository;
import java.util.ArrayList;
import java.io.IOException;

public class Test4Repository extends AbstractRepository {
	public ArrayList<Balon> balons = new ArrayList<Balon>();
	public ArrayList<Jeux> jeuxs = new ArrayList<Jeux>();

	public void addInstance(Balon balon){
		balons.add(balon);
	}

	public ArrayList<Balon> getBalons(){
		 return balons;
	}

	public void addInstance(Jeux jeux){
		jeuxs.add(jeux);
	}

	public ArrayList<Jeux> getJeuxs(){
		 return jeuxs;
	}



	@Override
	public void materialize(String path) throws IOException{
		Test4Repository temp = (Test4Repository) decodeFromFile(path);
		this.balons = temp.balons;
		this.jeuxs = temp.jeuxs;

	}
}