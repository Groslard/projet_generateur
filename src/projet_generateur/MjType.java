package projet_generateur;


public abstract class MjType {
	protected String id;
	protected String defaultValue = null;
	
	// Valeur mise a jour par le visiteur qui renseigne le nom r�el � �crire lors de la g�n�ration
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
	
}
