package projet_generateur;

public class MjList extends MjType {
	// minimum variables in the list
	int min;
	
	// Maximum variables in the list (0 if no limits)
	int max;
	
	public MjList(String entityName, int min, int max) {
		super(entityName);
		this.min = min;
		this.max = max;
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
		return "MjList [entity=" + entityName + ", min=" + min + ", max=" + max + "]";
	}
}
