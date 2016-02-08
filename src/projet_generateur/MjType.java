package projet_generateur;

public class MjType {
	String entityName;
	
	// Si bornes mix et max st egaux 0 et 1 -> alor simple variable, si non collection
	// Borne min
	int min = 0;
	// Borne max
	int max = 1;
	
	@Override
	public String toString() {
		return "MjType [entity=" + entityName + "]";
	}
	
	
	public MjType(String entityName){
		this.entityName = entityName;		
	}
	
	public String getEntity() {
		return entityName;
	}

	public void setEntity(String entityName) {
		this.entityName = entityName;
	}
	
	
}
