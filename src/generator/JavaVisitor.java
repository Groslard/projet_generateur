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

import modelMiniSpec.MsArray;
import modelMiniSpec.MsAttribute;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;
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
		File dir;
		// creation du package sous forme de dossier
		String packageDir =conf.getPackageReference(o.getName());
		if(packageDir!=null){
			 dir = new File("src/" + packageDir);
			 mdl.setName(packageDir);
		}else{
			 dir = new File("src/" + o.getName());
		}
		
		dir.mkdir();
		// parcours des entities du package
		for (MsEntity entitie : o.getEntities()) {
			importBlock = "";
			header = "";
			footer = "\n }";
			declarationBloc = "";
			methodBloc = "";
			entityImports = new HashSet<>();
			// initialisation des generatedName pour tous les type des attribut
			// de msentity

			entitie.accept(this);

			// on stocke la premiere class dans la has map
			listeclass.put(entitie.getName(), importBlock + header + declarationBloc + methodBloc + footer);
		}
	}

	@Override
	public void visit(MsEntity o) {
		// reinitialisation du set pour chaque entities
		this.importPath = new HashSet<String>();

		importBlock += "package " + mdl.getName() + "; \n\n";
		if (o.getParent() != null) {
			this.lastVisitedTypeName = "";
			o.getParent().accept(this);
			header += "public class " + o.getName() + " extends " + this.lastVisitedTypeName + " { \n\n";
			methodBloc += "\tpublic " + o.getName() + "(){\n\t super();\n\t} \n\n";
		} else {
			header += "public class " + o.getName() + " { \n\n";
			methodBloc += "\tpublic " + o.getName() + "(){} \n\n";
		}

		for (MsAttribute attrib : o.getAttributes()) {
			attrib.accept(this);
		}
		declarationBloc += "\n";

		entityImports.remove(null);
		for (String path : importPath)
			importBlock += "import " + path + ";\n";

		importBlock += "\n";
	}

	@Override
	public void visit(MsAttribute o) {
		this.lastVisitedTypeName = "";

		o.getType().accept(this);

		// faire un o.type.accept(self) et des visit pour mjtype en stockant la
		// decla
		declarationBloc += "\tprivate " + lastVisitedTypeName + " " + (o.getName()).toLowerCase() + "; \n";
		methodBloc += getSetter(o);
		methodBloc += getGetter(o);

	}

	@Override
	public void visit(MsList list) {

		// entityImports.add(list.getImportPath());
		lastVisitedTypeName += "ArrayList<";
		list.getType().accept(this);
		lastVisitedTypeName += ">";
		this.importPath.add(conf.getImportReference("list"));
	}

	@Override
	public void visit(MsReference ref) {

		lastVisitedTypeName += ref.getTypeName();
		String model=conf.getImportReference(ref.getTypeName());
		MsModel msModel=ref.getEntity().getModel();
		if ( conf.getImportReference(ref.getTypeName())!= null) {
			this.importPath.add(model);
		}else if (msModel!=null){
			//teste si la classe est a l'exterieur du package
			if(!msModel.getName().equals(mdl.getName())){
				this.importPath.add(msModel.getName()+"."+ref.getTypeName());
			}
			
		}

	}

	/** GETTERS/SETTER BUILDER **/
	private String getSetter(MsAttribute o) {
		String setter = "";
		setter += "\tpublic void set" + o.getName().substring(0, 1).toUpperCase() + o.getName().substring(1) + "("
				+ lastVisitedTypeName + " " + o.getName().toLowerCase() + "){\n\t\tthis." + o.getName().toLowerCase()
				+ "=" + o.getName().toLowerCase() + ";\n\t}\n\n";
		return setter;
	}

	private String getGetter(MsAttribute o) {
		String getter = "";
		getter += "\tpublic " + lastVisitedTypeName + " get" + o.getName().substring(0, 1).toUpperCase()
				+ o.getName().substring(1) + "(){\n\t\treturn " + o.getName().toLowerCase() + ";\n\t}\n\n";
		return getter;
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
				File file;
				String packageDir =conf.getPackageReference( mdl.getName());
				if(packageDir!=null){
					System.out.println("src/" + packageDir+ "/" + cle + ".java");
					file = new File("src/" + packageDir+ "/" + cle + ".java");
				}else{
					file = new File("src/" + mdl.getName() + "/" + cle + ".java");
				}
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

		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Files Generated");
	}


	public void visit(MsSet msSet) {
		// TODO Auto-generated method stub

	}

	public void visit(MsArray msArray) {
		// TODO Auto-generated method stub
		
	}

}
