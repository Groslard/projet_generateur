package projet_generateur;

public class MjReference extends MjType {
	String entityName;
	
	@Override
	public String toString() {
		return "MjReference [entity=" + entityName + "]";
	}
	
	
	public MjReference(String entityName){
		this.entityName = entityName;		
	}
	
	public String getEntity() {
		return entityName;
	}

	public void setEntity(String entityName) {
		this.entityName = entityName;
	}
	
	
}
