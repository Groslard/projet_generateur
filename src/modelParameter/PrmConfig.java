package modelParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PrmConfig {

	String languageType;

	HashMap<String, PrmParameter> params;

	public PrmConfig() {
		super();
		params = new HashMap<String, PrmParameter>();
	}

	public void addparameters(PrmParameter paremetre) {
		params.put(paremetre.getName(), paremetre);
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public PrmParameter getParameter(String clefRecherche) {

		return params.get(clefRecherche);
	}

	public ArrayList<String> getListeParameter() {
		ArrayList<String>listeStringParam= new ArrayList<>();

		Set<String> cles = params.keySet();
		Iterator<String> it = cles.iterator();
		while (it.hasNext()){
			listeStringParam.add(it.next());
		}
		
		return listeStringParam;
	}

	public HashMap<String, PrmParameter> getParams() {
		return params;
	}

	public void setParams(HashMap<String, PrmParameter> params) {
		this.params = params;
	}

}
