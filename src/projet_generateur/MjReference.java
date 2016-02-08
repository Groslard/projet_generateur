package projet_generateur;

public class MjReference extends MjType {
	@Override
	public String toString() {
		return "MjReference [entity=" + entity.name + "]";
	}

	MjEntity entity;
	
	public MjReference(MjEntity entity){
		this.entity = entity;		
	}
	
	public MjEntity getEntity() {
		return entity;
	}

	public void setEntity(MjEntity entity) {
		this.entity = entity;
	}
	
	
}
