package projet_generateur;

public class MjList extends MjType {
	String name = "list";
	MjType type;
	// minimum variables in the list
	int min;
	// Maximum variables in the list (0 if no limits)
	int max;
	
	
	
	public MjList() {
		super();
		typename="list";
	}
	public MjList(MjType type, int min, int max) {
		super();
		typename="list";
		this.type = type;
		this.min = min;
		this.max = max;
	}
	
	
	
	
	
	public  String getTypeName(){
		
		return "Arraylist<"+type.getTypeName()+">";
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
		return "MjList [entity=" + type + ", min=" + min + ", max=" + max + "]";
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
}
