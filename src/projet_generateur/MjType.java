package projet_generateur;

public abstract class MjType {
	protected String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public abstract String getTypeName();
}
