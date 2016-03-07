package modelParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PrmConfig {

	String languageType;

	HashMap<String, PrmModel> paramsModel;
	HashMap<String, PrmPrimitif> paramsPrimitif;

	public PrmConfig() {
		super();
		paramsModel = new HashMap<String, PrmModel>();
		paramsPrimitif = new HashMap<String, PrmPrimitif>();
	}

	public void addparametersModel(PrmModel paremetreModel) {
		paramsModel.put(paremetreModel.getName(), paremetreModel);
	}

	public void addparametersPrimitif(PrmPrimitif paremetrePrimitif) {
		paramsPrimitif.put(paremetrePrimitif.getName(), paremetrePrimitif);
	}
	
	
	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public PrmParameter getParameterModel(String clefRecherche) {

		return paramsModel.get(clefRecherche);
	}
	
	public PrmParameter getParameterPrimitif(String clefRecherche) {

		return paramsPrimitif.get(clefRecherche);
	}
	
	
	public ArrayList<String> getPrimitivesNames() {
		ArrayList<String>listeStringParam= new ArrayList<>();
		
		Set<String> clesPrim = paramsPrimitif.keySet();
		Iterator<String> itPrim = clesPrim.iterator();
		while (itPrim.hasNext()){
			listeStringParam.add(itPrim.next());
		}
		
		return listeStringParam;
	}

	

	public ArrayList<String> getListeParameterAll() {
		ArrayList<String>listeStringParam= new ArrayList<>();

		Set<String> clesMod = paramsModel.keySet();
		Iterator<String> itModel = clesMod.iterator();
		while (itModel.hasNext()){
			listeStringParam.add(itModel.next());
		}
		
		
		Set<String> clesPrim = paramsPrimitif.keySet();
		Iterator<String> itPrim = clesPrim.iterator();
		while (itPrim.hasNext()){
			listeStringParam.add(itPrim.next());
		}
		
		
		return listeStringParam;
	}

	public HashMap<String, PrmModel> getParamsModel() {
		return paramsModel;
	}

	public void setParamsModel(HashMap<String, PrmModel> paramsModel) {
		this.paramsModel = paramsModel;
	}

	public HashMap<String, PrmPrimitif> getParamsPrimitif() {
		return paramsPrimitif;
	}

	public void setParamsPrimitif(HashMap<String, PrmPrimitif> paramsPrimitif) {
		this.paramsPrimitif = paramsPrimitif;
	}

	

}
