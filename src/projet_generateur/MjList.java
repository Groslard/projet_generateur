package projet_generateur;

public class MjList extends MjType {
	MjType type;
	// minimum variables in the list
	int min;
	// Maximum variables in the list (0 if no limits)
	int max;
	
	public MjList(MjType type, int min, int max) {
		super();
		this.type = type;
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
		return "MjList [entity=" + type + ", min=" + min + ", max=" + max + "]";
	}
}
