package generator;

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;

public abstract class Visitor {
	
	public abstract void visit(MsModel o);
	public abstract void visit(MsEntity o);
	public abstract void visit(MsAttribute o);
	public abstract void visit(MsList list);
	public abstract void visit(MsArray array);
	public abstract void visit(MsSet set);
	public abstract void visit(MsReference ref);
}
