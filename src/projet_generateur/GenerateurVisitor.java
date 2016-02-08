package projet_generateur;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map.Entry;

public class GenerateurVisitor implements InterfaceVisitor {
	String header;
	String declarationBloc;
	String methodBloc;
	String footer;

	String editingBlock;

	HashMap<String, String> listeclass = new HashMap<>();
	MjPackage pkg;

	public GenerateurVisitor(MjPackage pkg) {
		super();
	
		this.pkg = pkg;

	}



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
			listeclass.put(entitie.name, header + declarationBloc + methodBloc + footer);
			// reset des attributs du visiteur
			
		}

		exportclass(listeclass);
		
	}
public void exportclass(HashMap<String, String> lesclass){
	
	for(Entry<String, String> entry : lesclass.entrySet()) {
	    String cle = entry.getKey();
	    String valeur = entry.getValue();
	    Writer writer = null;

		try {
			File file = new File(pkg.name+"/"+cle+".java");
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
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
	}
	
	
	@Override
	public void visit(MjEntity o) {
		// creation

		header += "package " + pkg.name + "; \n";
		header += "public class " + o.name + " { \n";
		methodBloc += "public " + o.name + "(){} \n";
		for (MjAttribute attrib : o.attributes) {
			attrib.accept(this);
		}
	}

	@Override
	public void visit(MjAttribute o) {
		
		declarationBloc += "private "+o.type.entityName+" "+(o.name).toLowerCase() + "; \n";
		methodBloc+=buildSetter(o);
		methodBloc += buildGetter(o);
	
	}

	private String buildSetter(MjAttribute o) {
		String setter="";
		setter+="public void set" + o.name.toLowerCase() + "("+o.type.entityName+" "+o.name.toLowerCase()+"){\nthis." + o.name.toLowerCase()+"="+o.name.toLowerCase()+";\n }\n";
		
		return setter;
	}

	
	
	
	private String buildGetter(MjAttribute o) {
		String getter="";
		getter+="public "+o.type.entityName+ " get" + o.name.toLowerCase() + "(){\nreturn " + o.name.toLowerCase()+";\n }\n";
		return getter;
	}

}
