package modelParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * The Class PrmConfig.
 */
public class PrmConfig {

	/** The language type. */
	String languageType;

	/** The params model mapped by their name. */
	HashMap<String, PrmModel> paramsModel;

	/** The params primitif mapped by their name. */
	HashMap<String, PrmPrimitif> paramsPrimitif;

	/**
	 * Instantiates a new prm config.
	 */
	public PrmConfig() {
		super();
		paramsModel = new HashMap<String, PrmModel>();
		paramsPrimitif = new HashMap<String, PrmPrimitif>();
	}

	/**
	 * Adds the parameter.
	 *
	 * @param paremetreModel the paremetre model
	 */
	public void addParameter(PrmModel paremetreModel) {
		paramsModel.put(paremetreModel.getName(), paremetreModel);
	}

	/**
	 * Adds the parameter.
	 *
	 * @param paremetrePrimitif the paremetre primitif
	 */
	public void addParameter(PrmPrimitif paremetrePrimitif) {
		paramsPrimitif.put(paremetrePrimitif.getName(), paremetrePrimitif);
	}

	/**
	 * Gets the language type.
	 *
	 * @return the language type
	 */
	public String getLanguageType() {
		return languageType;
	}

	/**
	 * Sets the language type.
	 *
	 * @param languageType
	 *            the new language type
	 */
	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	/**
	 * Gets the parameter model.
	 *
	 * @param clefRecherche
	 *            the clef recherche
	 * @return the parameter model
	 */
	public PrmModel getParameterModel(String clefRecherche) {

		return paramsModel.get(clefRecherche);
	}

	/**
	 * Gets the parameter primitif.
	 *
	 * @param clefRecherche
	 *            the clef recherche
	 * @return the parameter primitif
	 */
	public PrmPrimitif getParameterPrimitif(String clefRecherche) {

		return paramsPrimitif.get(clefRecherche);
	}

	/**
	 * Gets the primitives names.
	 *
	 * @return the primitives names
	 */
	public ArrayList<String> getPrimitivesNames() {
		ArrayList<String> listeStringParam = new ArrayList<>();

		Set<String> clesPrim = paramsPrimitif.keySet();
		Iterator<String> itPrim = clesPrim.iterator();
		while (itPrim.hasNext()) {
			listeStringParam.add(itPrim.next());
		}

		return listeStringParam;
	}

	/**
	 * Gets the parameters names.
	 *
	 * @return the parameters names
	 */
	public ArrayList<String> getParametersNames() {
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

	/**
	 * Gets the params model.
	 *
	 * @return the params model
	 */
	public HashMap<String, PrmModel> getParamsModel() {
		return paramsModel;
	}

	/**
	 * Sets the params model.
	 *
	 * @param paramsModel
	 *            the params model
	 */
	public void setParamsModel(HashMap<String, PrmModel> paramsModel) {
		this.paramsModel = paramsModel;
	}

	/**
	 * Gets the params primitif.
	 *
	 * @return the params primitif
	 */
	public HashMap<String, PrmPrimitif> getParamsPrimitif() {
		return paramsPrimitif;
	}

	/**
	 * Sets the params primitif.
	 *
	 * @param paramsPrimitif
	 *            the params primitif
	 */
	public void setParamsPrimitif(HashMap<String, PrmPrimitif> paramsPrimitif) {
		this.paramsPrimitif = paramsPrimitif;
	}

	/**
	 * Gets the import reference.
	 *
	 * @param idRecherche
	 *            the id recherche
	 * @return the import reference
	 */
	public String getImportReference(String idRecherche) {
		String retour = null;
		if (paramsPrimitif.get(idRecherche) != null) {
			return paramsPrimitif.get(idRecherche).getPkg();
		}
		if (paramsModel.get(idRecherche) != null) {
			return paramsModel.get(idRecherche).getPkg() + "."
					+ paramsModel.get(idRecherche).getName();
		}

		return retour;
	}

	/**
	 * Gets the package reference.
	 *
	 * @param searchModel
	 *            the search model
	 * @return the package reference
	 */
	public String getPackageReference(String searchModel) {
		String retour = null;

		if (paramsModel.get(searchModel) != null) {
			return paramsModel.get(searchModel).getPkg();
		}

		return retour;
	}

	/**
	 * Param models contains.
	 *
	 * @param clefRecherche
	 *            the clef recherche
	 * @return true, if successful
	 */
	public boolean paramModelsContains(String clefRecherche) {

		return paramsModel.containsKey(clefRecherche);
	}

	/**
	 * Param primitf contains.
	 *
	 * @param clefRecherche
	 *            the clef recherche
	 * @return true, if successful
	 */
	public boolean paramPrimitfContains(String clefRecherche) {

		return paramsPrimitif.containsKey(clefRecherche);
	}

	/**
	 * Checks if is primitif.
	 *
	 * @param idRecherche
	 *            the id recherche
	 * @return true, if is primitif
	 */
	public boolean isPrimitif(String idRecherche) {
		boolean retour = false;

		if (paramPrimitfContains(idRecherche)) {
			return getParameterPrimitif(idRecherche).isPrimitif();
		}

		return retour;
	}

}
