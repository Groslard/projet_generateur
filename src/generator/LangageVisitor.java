package generator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelParameter.PrmConfig;

public abstract class LangageVisitor {
	
	/** Different part of export file **/
	String importBlock;
	String header;
	String declarationBloc;
	String methodBloc;
	String footer;
	
	String lastVisitedTypeName;

	/** List of already generated class with source code **/
	HashMap<String, String> listeclass = new HashMap<>();
	
	/** List of imports needed for current building class **/
	HashSet<String> entityImports;
	
	/** Model to generate **/
	MsModel mdl;
	
	/** user configuration parsed **/
	PrmConfig conf;
	
	public abstract void visit(MsModel o);
	public abstract void visit(MsEntity o);
	public abstract void visit(MsAttribute o);
	public abstract void visit(MsList list);
	public abstract void visit(MsReference ref);
	public abstract Set<String> getImport();
}
