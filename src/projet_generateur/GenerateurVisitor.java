package projet_generateur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerateurVisitor implements InterfaceVisitor {
	/** Different part of export file **/
	String header;
	String declarationBloc;
	String methodBloc;
	String footer;

	/** current editing part **/
	String editingBlock;

	/** List of already generated class with source code **/
	HashMap<String, String> listeclass = new HashMap<>();
	
	/** Package to generate **/
	MjPackage pkg;

	/** CONSTRUCTOR **/
	public GenerateurVisitor(MjPackage pkg) {
		super();
		this.pkg = pkg;
	}

	/** VISIT METHODS **/
	@Override
	public void visit(MjPackage o) {

		// creation du package sous forme de dossier
		File dir = new File(o.name);
		dir.mkdir();
		// parcours des entities du package
		for (MjEntity entitie : o.entities) {
			header = "";
			footer = "\n }";
			declarationBloc = "";
			methodBloc = "";
			entitie.accept(this);

			// on stocke la premiere class dans la has map
			listeclass.put(entitie.name, header + declarationBloc + methodBloc
					+ footer);
		}
	}

	@Override
	public void visit(MjEntity o) {
		// creation

		header += "package " + pkg.name + "; \n\n";
		header += "public class " + o.name + " { \n\n";
		methodBloc += "\tpublic " + o.name + "(){} \n\n";
		for (MjAttribute attrib : o.attributes) {
			attrib.accept(this);
		}
		declarationBloc += "\n";
	}

	@Override
	public void visit(MjAttribute o) {
		declarationBloc += "\tprivate " + getDeclaration(o.type) + " "
				+ (o.name).toLowerCase() + "; \n";
		methodBloc += getSetter(o);
		methodBloc += getGetter(o);

	}
	
	
	/** GETTERS/SETTER BUILDER **/
	private String getSetter(MjAttribute o) {
		String setter = "";
		setter += "\tpublic void set" + o.name.substring(0, 1).toUpperCase()
				+ o.name.substring(1) + "(" + getDeclaration(o.type) + " "
				+ o.name.toLowerCase() + "){\n\t\tthis." + o.name.toLowerCase()
				+ "=" + o.name.toLowerCase() + ";\n\t}\n\n";

		return setter;
	}

	private String getGetter(MjAttribute o) {
		
		String getter = "";
		getter += "\tpublic " + getDeclaration(o.type) + " get"
				+ o.name.substring(0, 1).toUpperCase() + o.name.substring(1)
				+ "(){\n\t\treturn " + o.name.toLowerCase() + ";\n\t}\n\n";
		return getter;
	}
	
	private String getDeclaration(MjType type){
		String result = "";
		if (type instanceof MjList)
			result = getDeclaration((MjList)type);
		if (type instanceof MjReference)
			result = getDeclaration((MjReference)type);
		if (type instanceof MjPrimitif)
			result = getDeclaration((MjPrimitif)type);
		return result;
	}
	
	private String getDeclaration(MjList list){
		return "Arraylist<" + getDeclaration(list.type) + ">";
	}
	
	private String getDeclaration(MjReference ref){
		return ref.id;
	}
	
	private String getDeclaration(MjPrimitif prim){
		return prim.id;
	}
	
	private String getImport(MjType type){
		if(type.getImportPathFromLangage("Java") != null)
			return "import "+type.getImportPathFromLangage("Java")+";";
		return "";
	}
	
	private String getInitialisation(MjAttribute attribut){
		return attribut.name + " = " + attribut.type.getDefaultValue();
	}
	
	
	/** GENERATION METHOD **/
	public void generate() {
		/** Build source code **/
		this.pkg.accept(this);
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Source Code Built");
		
		/** Files building **/
		for (Entry<String, String> entry : this.listeclass.entrySet()) {
			String cle = entry.getKey();
			String valeur = entry.getValue();
			Writer writer = null;

			try {
				File file = new File(pkg.name + "/" + cle + ".java");
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(file), "utf-8"));
				writer.write(valeur);
			} catch (IOException ex) {
				// report
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
					/* ignore */}
			}
		}
		
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Files Generated");
	}

}
