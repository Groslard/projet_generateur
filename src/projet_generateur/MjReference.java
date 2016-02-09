package projet_generateur;

public class MjReference extends MjType {
	String name="reference";
	String entityName;

	
	public MjReference() {
		super();
		typename="reference";
	}

	
	
	
	public  String getTypeName(){
		return entityName;
	}
	
	
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public MjReference(String entityName) {
		super();
		this.entityName = entityName;
	}
	

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	@Override
	public String toString() {
		return "MjType [entity=" + entityName + "]";
	}
}
