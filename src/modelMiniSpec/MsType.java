package modelMiniSpec;

import generator.JavaVisitor;


public abstract class MsType {
	protected String id;
	protected String defaultValue = null;
	
	// Valeur mise a jour par le visiteur qui renseigne le nom réel à écrire lors de la génération
	protected String generatedName;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	 public void accept(JavaVisitor visitor){
		 return;
	 }

	public String getGeneratedName() {
		return generatedName;
	}

	public void setGeneratedName(String generatedName) {
		this.generatedName = generatedName;
	}
	
}
