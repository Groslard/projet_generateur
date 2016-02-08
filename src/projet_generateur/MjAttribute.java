package projet_generateur;

public class MjAttribute {
	String name;
	MjType type;
	
	public MjAttribute(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MjType getType() {
		return type;
	}

	public void setType(MjType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "\n\tMjAttribute [name=" + name + ", type=" + type + "]";
	}
	
}
