package projet_generateur;

import java.util.HashMap;
import java.util.HashSet;

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
	
	/** Package to generate **/
	MjPackage pkg;
	
	public abstract void visit(MjPackage o);
	public abstract void visit(MjEntity o);
	public abstract void visit(MjAttribute o);
	public abstract void visit(MjList list);
	public abstract void visit(MjReference ref);
	public abstract void visit(MjPrimitif prim);

}
