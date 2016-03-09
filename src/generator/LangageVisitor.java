package generator;

import java.util.HashMap;
import java.util.Set;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;

public abstract class LangageVisitor extends Visitor {
	
	/** Different part of export file **/
	String importBlock;
	String header;
	String declarationBloc;
	String methodBloc;
	String footer;
	String collectionMethod;

	/** List of already generated class with source code **/
	HashMap<String, String> listeclass = new HashMap<>();
	
	/**list of import path**/
	Set<String> importPath;
	
	/** Model to generate **/
	MsModel mdl;
	
	/** user configuration parsed **/
	PrmConfig conf;
}
