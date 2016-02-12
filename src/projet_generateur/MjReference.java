package projet_generateur;

public class MjReference extends MjType {
	MjEntity entity;
	
	public MjReference(MjEntity entity) {
		super();
		this.entity = entity;
	}
	
	public MjReference(MjEntity entity, String defaultValue) {
		super();
		this.entity = entity;
		this.defaultValue = defaultValue;
	}
	
	@Override
	public String toString() {
		return "MjReference [entity=" + getId() + "]";
	}
	
	@Override
	public String getId(){
		return entity.name;
	}
	
	 public void accept(GenerateurVisitor visitor){
		 visitor.visit(this);
	 }
}
