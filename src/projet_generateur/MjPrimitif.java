package projet_generateur;

public class MjPrimitif extends MjType {
	
	public MjPrimitif(String id) {
		super();
		this.id = id;
	}
	
	public MjPrimitif(String id, String defaultValue) {
		super();
		this.id = id;
		this.defaultValue = defaultValue;
	}

	@Override
	public String toString() {
		return "MjPrimitif [id=" + id + "]";
	}
}
