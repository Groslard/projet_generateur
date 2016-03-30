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

	public PrmModel getParameterModel(String clefRecherche) {

		return paramsModel.get(clefRecherche);
	}

	public PrmPrimitif getParameterPrimitif(String clefRecherche) {

		return paramsPrimitif.get(clefRecherche);
	}

	public ArrayList<String> getPrimitivesNames() {
		ArrayList<String> listeStringParam = new ArrayList<>();

		Set<String> clesPrim = paramsPrimitif.keySet();
		Iterator<String> itPrim = clesPrim.iterator();
		while (itPrim.hasNext()) {
			listeStringParam.add(itPrim.next());
		}

		return listeStringParam;
	}

	public ArrayList<String> getListeParameterAll() {
		ArrayList<String> listeStringParam = new ArrayList<>();

		Set<String> clesMod = paramsModel.keySet();
		Iterator<String> itModel = clesMod.iterator();
		while (itModel.hasNext()) {
			listeStringParam.add(itModel.next());
		}

		Set<String> clesPrim = paramsPrimitif.keySet();
		Iterator<String> itPrim = clesPrim.iterator();
		while (itPrim.hasNext()) {
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

	
	// finir  methode + l'implementer lors du java generateur +reset le set lors de chaque enttity
	public String getImportReference(String idRecherche) {
		String retour = null;
		if(paramsPrimitif.get(idRecherche)!=null){
			return paramsPrimitif.get(idRecherche).getPkg();
		}
		if(paramsModel.get(idRecherche)!=null){
			return paramsModel.get(idRecherche).getPkg()+"."+paramsModel.get(idRecherche).getName();
		}
		


		return retour;
	}
	
	public boolean getPrimitifparam(String idRecherche) {
		boolean retour=false;
	
		if(paramsPrimitif.get(idRecherche)!=null){
			return paramsPrimitif.get(idRecherche).isPrimitif();
		}
		
		if(paramsModel.get(paramsModel)!=null){
			return paramsModel.get(idRecherche).isPrimitif();
		}


		return retour;
	}
	
	
	
	public String getPackageReference(String modelRechercher) {
		String retour = null;
	
		if(paramsModel.get(modelRechercher)!=null){
			return paramsModel.get(modelRechercher).getPkg();
		}
		


		return retour;
	}

}
