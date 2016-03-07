package modelMiniSpec;

import generator.JavaVisitor;

public abstract class MsCollection extends MsType implements UnresolveObject {
	MsType type;
	// minimum variables in the list
	int min;
	// Maximum variables in the list
	int max;

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
	public MsType getUnresolvedType() {
		return type;
	}

	@Override
	public void setResolvedType(MsType resolvedType) {
		this.type = resolvedType;
	}
}
