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
		File dir = new File("src/"+o.name);
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
		if(o.getParents()!=null){
			header += "public class " + o.name + " extends  "+o.getParents().getName() +" { \n\n";
			methodBloc += "\tpublic " + o.name + "(){\n\t super();\n\t} \n\n";
		}else{
			header += "public class " + o.name + " { \n\n";
			methodBloc += "\tpublic " + o.name + "(){} \n\n";
		}
		
	
		
		for (MjAttribute attrib : o.attributes) {
			attrib.accept(this);
		}
		declarationBloc += "\n";
	}

	@Override
	public void visit(MjAttribute o) {
		declarationBloc += "\tprivate " + o.type.getTypeName() + " "
				+ (o.name).toLowerCase() + "; \n";
		methodBloc += buildSetter(o);
		methodBloc += buildGetter(o);

	}
	
	
	/** GETTERS/SETTER BUILDER **/
	private String buildSetter(MjAttribute o) {
		String setter = "";
		setter += "\tpublic void set" + o.name.substring(0, 1).toUpperCase()
				+ o.name.substring(1) + "(" + o.type.getTypeName() + " "
				+ o.name.toLowerCase() + "){\n\t\tthis." + o.name.toLowerCase()
				+ "=" + o.name.toLowerCase() + ";\n\t}\n\n";

		return setter;
	}

	private String buildGetter(MjAttribute o) {
		String getter = "";
		getter += "\tpublic " + o.type.getTypeName() + " get"
				+ o.name.substring(0, 1).toUpperCase() + o.name.substring(1)
				+ "(){\n\t\treturn " + o.name.toLowerCase() + ";\n\t}\n\n";
		return getter;
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
				File file = new File("src/"+pkg.name + "/" + cle + ".java");
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
