package modelParameter;

import java.util.ArrayList;

import modelMiniSpec.MsEntity;

public class PrmConfig {

	ArrayList<PrmParameter> parameters;
	String languageType;
	
	public PrmConfig() {
		super();
		parameters = new ArrayList<PrmParameter>();
	}

	
	
	
	public void addparameters(PrmParameter paremetre) {
		parameters.add(paremetre);
	}
	
	
	
	public ArrayList<PrmParameter> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<PrmParameter> parameters) {
		this.parameters = parameters;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
	
}
