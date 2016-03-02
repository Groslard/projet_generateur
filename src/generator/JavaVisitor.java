package generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsPrimitif;
import modelMiniSpec.MsReference;
import modelParameter.PrmConfig;

public class JavaVisitor extends LangageVisitor {
	

	/** CONSTRUCTOR **/
	public JavaVisitor(MsModel mdl, PrmConfig conf) {
		super();
		this.conf = conf;
		this.mdl = mdl;
	}

	/** VISIT METHODS **/
	@Override
	public void visit(MsModel o) {

		// creation du package sous forme de dossier
		File dir = new File("src/"+o.name);
		dir.mkdir();
		// parcours des entities du package
		for (MsEntity entitie : o.entities) {
			importBlock = "";
			header = "";
			footer = "\n }";
			declarationBloc = "";
			methodBloc = "";
			entityImports = new HashSet<>();
			entitie.accept(this);

			// on stocke la premiere class dans la has map
			listeclass.put(entitie.name, importBlock+header + declarationBloc + methodBloc
					+ footer);
		}
	}

	@Override
	public void visit(MsEntity o) {
		importBlock += "package " + mdl.name + "; \n\n";
		if(o.getParent()!=null){
			this.lastVisitedTypeName = "";
			o.getParent().accept(this);
			header += "public class " + o.name + " extends "+ this.lastVisitedTypeName +" { \n\n";
			methodBloc += "\tpublic " + o.name + "(){\n\t super();\n\t} \n\n";
		}else{
			header += "public class " + o.name + " { \n\n";
			methodBloc += "\tpublic " + o.name + "(){} \n\n";
		}
		
		for (MsAttribute attrib : o.attributes) {
			attrib.accept(this);
		}
		declarationBloc += "\n";
		
		entityImports.remove(null);
		for(String path : entityImports)
			importBlock += "import "+ path+";\n";
		
		importBlock += "\n";
	}

	@Override
	public void visit(MsAttribute o) {
		this.lastVisitedTypeName = "";
		
		o.type.accept(this);
		
		// faire un o.type.accept(self) et des visit pour mjtype en stockant la decla
		declarationBloc += "\tprivate " + lastVisitedTypeName + " "
				+ (o.name).toLowerCase() + "; \n";
		methodBloc += getSetter(o);
		methodBloc += getGetter(o);

	}
	
	@Override
	public void visit(MsList list){
		entityImports.add(list.getImportPath());
		lastVisitedTypeName += "ArrayList<";
		list.type.accept(this);
		lastVisitedTypeName += ">";
	}
	
	@Override
	public void visit(MsReference ref){
		entityImports.add(ref.getImportPath());
		lastVisitedTypeName += ref.getId();
	}
	
	@Override
	public void visit(MsPrimitif prim){
		entityImports.add(prim.getImportPath());
		lastVisitedTypeName += prim.getId();
	}
	
	
	/** GETTERS/SETTER BUILDER **/
	private String getSetter(MsAttribute o) {
		String setter = "";
		setter += "\tpublic void set" + o.name.substring(0, 1).toUpperCase()
				+ o.name.substring(1) + "(" + lastVisitedTypeName + " "
				+ o.name.toLowerCase() + "){\n\t\tthis." + o.name.toLowerCase()
				+ "=" + o.name.toLowerCase() + ";\n\t}\n\n";
		return setter;
	}

	private String getGetter(MsAttribute o) {
		String getter = "";
		getter += "\tpublic " + lastVisitedTypeName + " get"
				+ o.name.substring(0, 1).toUpperCase() + o.name.substring(1)
				+ "(){\n\t\treturn " + o.name.toLowerCase() + ";\n\t}\n\n";
		return getter;
	}
	
	private String getInitialisation(MsAttribute attribut){
		return attribut.name + " = " + attribut.type.getDefaultValue();
	}
	
	
	/** GENERATION METHOD **/
	public void generate() {
		/** Build source code **/
		this.mdl.accept(this);
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Source Code Built");
		
		/** Files building **/
		for (Entry<String, String> entry : this.listeclass.entrySet()) {
			String cle = entry.getKey();
			String valeur = entry.getValue();
			Writer writer = null;

			try {
				File file = new File("src/"+mdl.name + "/" + cle + ".java");
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