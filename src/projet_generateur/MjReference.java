package projet_generateur;

public class MjReference extends MjType {
	
	public MjReference(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "MjType [entity=" + id + "]";
	}
	
	public String getTypeName() {
		return id;
	}
}
