package modelMiniSpec;

import generator.JavaVisitor;

public class MsList extends MsType implements UnresolveObject {
	MsType type;
	// minimum variables in the list
	int min;
	// Maximum variables in the list
	int max;

	public MsList(String id, MsType type) {
		super();
		this.id = id;
		this.type = type;
	}

	public MsList(String id, MsType type, int min, int max) {
		super();
		this.id = id;
		this.type = type;
		this.min = min;
		this.max = max;
	}

	public MsType getType() {
		return type;
	}

	public void setType(MsType type) {
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

	@Override
	public MsType getUnresolvedType() {
		return type;
	}

	@Override
	public void setResolvedType(MsType resolvedType) {
		this.type = resolvedType;
	}
}
