package generator;

import java.util.HashMap;
import java.util.Set;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;

/**
 * The Class LangageVisitor.
 */
public abstract class LangageVisitor extends Visitor {

	/** Different part of export file *. */
	String importBlock;

	/** The header. */
	String header;

	/** The declaration bloc. */
	String declarationBloc;

	/** The method bloc. */
	String methodBloc;

	/** The footer. */
	String footer;

	/** The collection method. */
	String collectionMethod;

	/** The constructeur b loc. */
	String constructeurBLoc;

	/** The un implement method. */
	String unImplementMethod;

	/** Different part of repository file *. */
	String repoImportBlock;

	/** The repo header. */
	String repoHeader;

	/** The repo declaration block. */
	String repoDeclarationBlock;

	/** The repo method block. */
	String repoMethodBlock;

	/** The repo materialize block. */
	String repoMaterializeBlock;

	/** The repo footer block. */
	String repoFooterBlock;

	/** List of already generated class with source code *. */
	HashMap<String, String> listeclass = new HashMap<>();

	/** list of import path*. */
	Set<String> importPath;

	/** Model to generate *. */
	MsModel mdl;

	/** user configuration parsed *. */
	PrmConfig conf;
}
