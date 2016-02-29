package projet_generateur;

public class MjList extends MjType {
	MjType type;
	// minimum variables in the list
	int min;
	// Maximum variables in the list (0 if no limits)
	int max;

	public MjList(String id, MjType type) {
		super();
		this.id = id;
		this.type = type;
	}

	public MjList(String id, MjType type, int min, int max) {
		super();
		this.id = id;
		this.type = type;
		this.min = min;
		this.max = max;
	}

	public MjType getType() {
		return type;
	}

	public void setType(MjType type) {
		this.type = type;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "MjList [type=" + type + ", min=" + min + ", max=" + max + "]";
	}
	
	 public void accept(JavaVisitor visitor){
		 visitor.visit(this);
	 }
}
