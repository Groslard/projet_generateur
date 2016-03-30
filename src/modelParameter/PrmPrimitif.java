package modelParameter;

public class PrmPrimitif extends PrmParameter {
	String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isPrimitif() {
		return primitif;
	}

	public void setPrimitif(boolean primitif) {
		this.primitif = primitif;
	}

}
