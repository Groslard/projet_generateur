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
import modelMiniSpec.MsCollection;
import modelMiniSpec.MsEntity;
import modelMiniSpec.MsList;
import modelMiniSpec.MsModel;
import modelMiniSpec.MsReference;
import modelMiniSpec.MsSet;
import modelMiniSpec.MsType;
import modelParameter.PrmConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaVisitor.
 */
public class JavaVisitor extends LangageVisitor {

	/** The type name visitor. */
	JavaTypeNameVisitor typeNameVisitor;

	/** The current attribute. */
	MsAttribute currentAttribute;

	/**
	 * CONSTRUCTOR *.
	 *
	 * @param mdl
	 *            the mdl
	 * @param conf
	 *            the conf
	 */
	public JavaVisitor(MsModel mdl, PrmConfig conf) {
		super();
		this.conf = conf;
		this.mdl = mdl;
		this.typeNameVisitor = new JavaTypeNameVisitor(conf);
	}


	/* (non-Javadoc)
	 * @see generator.Visitor#visit(modelMiniSpec.MsModel)
	 */
	@Override
	public void visit(MsModel o) {
		File dir;
		// creation du package sous forme de dossier
		String packageDir = conf.getPackageReference(o.getName());
		if (packageDir != null) {
			dir = new File("src/" + packageDir);
			mdl.setName(packageDir);
		} else {
			dir = new File("src/" + o.getName());
		}

		dir.mkdir();

		/** CREATION DU REPOSITORY **/
		repoImportBlock = "package " + mdl.getName() + "; \n\n";
		repoImportBlock += "import generator.AbstractRepository;\n";
		repoImportBlock += "import java.util.ArrayList;\n";
		repoImportBlock += "import java.io.IOException;\n\n";
		repoHeader = "public class " + mdl.getName()
				+ "Repository extends AbstractRepository {\n";
		repoMaterializeBlock = "\n\n\n\t@Override\n\tpublic void materialize(String path) throws IOException{\n";
		repoMaterializeBlock += "\t\t" + mdl.getName() + "Repository temp = ("
				+ mdl.getName() + "Repository) decodeFromFile(path);\n";
		repoFooterBlock = "\n}";
		repoDeclarationBlock = "";
		repoMethodBlock = "";

		// parcours des entities du package
		for (MsEntity entitie : o.getEntities()) {
			importBlock = "";
			header = "";
			footer = "\n }";
			declarationBloc = "";
			constructeurBLoc = "";
			methodBloc = "";
			collectionMethod = "";
			unImplementMethod = "";
			// initialisation des generatedName pour tous les type des attribut
			// de msentity

			entitie.accept(this);
			constructeurBLoc += "\n\t} \n\n";
			// on stocke la premiere class dans la has map
			listeclass.put(entitie.getName(), importBlock + header
					+ constructeurBLoc + declarationBloc + methodBloc
					+ collectionMethod + unImplementMethod + footer);
		}
		repoMaterializeBlock += "\n\t}";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsEntity)
	 */
	@Override
	public void visit(MsEntity o) {
		// reinitialisation du set pour chaque entities
		this.importPath = new HashSet<String>();

		importBlock += "package " + mdl.getName() + "; \n\n";
		if (o.getParent() != null) {
			o.getParent().accept(this);
			o.getParent().accept(typeNameVisitor);
			header += "public class " + o.getName() + " extends "
					+ typeNameVisitor.getResult() + " { \n\n";
			constructeurBLoc += "\tpublic " + o.getName() + "(){\n\t super();";
		} else {
			header += "public class " + o.getName() + " { \n\n";
			constructeurBLoc += "\tpublic " + o.getName() + "(){";
		}

		for (MsAttribute attrib : o.getAttributes()) {
			attrib.accept(this);
		}
		declarationBloc += "\n";

		for (String path : importPath)
			importBlock += "import " + path + ";\n";

		importBlock += "\n";

		/** REPOSITORY BUILDING **/
		String varName = Character.toLowerCase(o.getName().charAt(0))
				+ o.getName().substring(1);
		repoDeclarationBlock += "\tpublic ArrayList<" + o.getName() + "> "
				+ varName + "s = new ArrayList<" + o.getName() + ">();\n";

		repoMethodBlock += "\n\tpublic void addInstance(" + o.getName() + " "
				+ varName + "){\n";
		repoMethodBlock += "\t\t" + varName + "s.add(" + varName + ");\n\t}\n";

		repoMethodBlock += "\n\tpublic ArrayList<" + o.getName() + "> get"
				+ o.getName() + "s(){\n";
		repoMethodBlock += "\t\t return " + varName + "s;\n\t}\n";

		repoMaterializeBlock += "\t\tthis." + varName + "s = temp." + varName
				+ "s;\n";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsAttribute)
	 */
	@Override
	public void visit(MsAttribute o) {

		currentAttribute = o;
		o.getType().accept(this);
		o.getType().accept(typeNameVisitor);
		String typeVisitResult = typeNameVisitor.getResult();
		String typeConstructor = typeNameVisitor.getConstructor();
		declarationBloc += "\tprivate " + typeVisitResult + " "+ (o.getName()).toLowerCase() + "; \n";
		
		//cas  attribut primitif possedant une valeur par defaut 
		if (conf.isPrimitif(o.getType().getTypeName())
				&& o.getInitialValue() != null) {
			constructeurBLoc += "\n \t this." + o.getName().toLowerCase() + "="
					+ o.getInitialValue() + ";\n";
		} //cas l'attribut est initialiser par une fonction
		else if (o.getMethod() != null && !o.getMethod().isEmpty()) {
			// faire la construction de la methode si elle n'existe pas
			constructeurBLoc += "\n \t this." + o.getName().toLowerCase() + "="
					+ o.getMethod() + "();\n";
			unImplementMethod += "\t public " + typeConstructor
					+ " " + o.getMethod() + "(){\n\t return null;\n}";
			
		}//cas l'attribut est initialiser par son contrcteur
		else if (!conf.isPrimitif(o.getType().getTypeName())
				&& o.isConstructor()) {
			constructeurBLoc += "\n \t this." + o.getName().toLowerCase()
					+ "= new " + typeConstructor + ";\n";

		}

		// On passe le type de facon à typer l'appel de method pour bien
		// aiguiller la construction des méthodes (pour savoir si collection ou
		// reference)
		// methodBloc += buildMethods(o, o.getType());
		methodBloc += getSetter(o);
		methodBloc += getGetter(o);
		currentAttribute = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsReference)
	 */
	@Override
	public void visit(MsReference ref) {

		String model = conf.getImportReference(ref.getTypeName());
		MsModel msModel = ref.getEntity().getModel();
		if (conf.getImportReference(ref.getTypeName()) != null) {
			this.importPath.add(model);
		} else if (msModel != null) {
			// teste si la classe est a l'exterieur du package
			if (!msModel.getName().equals(mdl.getName())) {
				this.importPath
						.add(msModel.getName() + "." + ref.getTypeName());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsList)
	 */
	@Override
	public void visit(MsList list) {

		if (!(list.getType() instanceof MsCollection)) {
			list.getType().accept(this);
		}

		this.importPath.add(conf.getImportReference("list"));
		if (currentAttribute != null) {
			collectionMethod += getAddToList(list);
			collectionMethod += getRemoveFromList(list);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsSet)
	 */
	public void visit(MsSet msSet) {

		if (!(msSet.getType() instanceof MsCollection)) {
			msSet.getType().accept(this);
		}

		this.importPath.add(conf.getImportReference("set"));
		if (currentAttribute != null) {
			collectionMethod += getAddToList(msSet);
			collectionMethod += getRemoveFromList(msSet);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see generator.Visitor#visit(modelMiniSpec.MsArray)
	 */
	public void visit(MsArray msArray) {
		msArray.getType().accept(this);
		if (currentAttribute != null) {
			collectionMethod += getAddToArray(msArray);
		}
	}


	/**
	 * Gets the adds to list function.
	 *
	 * @param o the o
	 * @return the adds to list function
	 */
	private String getAddToList(MsCollection o) {
		// ajouter gestion des listes enchaine
		o.getType().accept(typeNameVisitor);
		String chaineNom = typeNameVisitor.getResult();
		String add = "";
		add += "\tpublic void add"
				+ currentAttribute.getName().substring(0, 1).toUpperCase()
				+ currentAttribute.getName().substring(1) + "(" + chaineNom
				+ " " + o.getType().getTypeName().toLowerCase() + "){\n\t\t";
		if (o.getMax() > 0)
			add += "if(" + currentAttribute.getName().toLowerCase()
					+ ".size()<" + o.getMax() + ")\n";
		add += "\tthis." + currentAttribute.getName().toLowerCase() + ".add("
				+ o.getType().getTypeName().toLowerCase() + ");\n\t}\n\n";
		return add;
	}

	/**
	 * Gets the removes from list function.
	 *
	 * @param o
	 *            the o
	 * @return the removes from list function
	 */
	private String getRemoveFromList(MsCollection o) {
		String remove = "";
		o.getType().accept(typeNameVisitor);
		String chaineNom = typeNameVisitor.getResult();
		remove += "\tpublic void  remove"
				+ currentAttribute.getName().substring(0, 1).toUpperCase()
				+ currentAttribute.getName().substring(1) + "(" + chaineNom
				+ " " + o.getType().getTypeName().toLowerCase() + "){";
		if (o.getMin() > 0)
			remove += "\n\t\tif(" + currentAttribute.getName().toLowerCase()
					+ ".size()>" + o.getMin() + ")";
		remove += "\n\t\t this." + currentAttribute.getName().toLowerCase()
				+ ".remove(" + o.getType().getTypeName().toLowerCase()
				+ ");\n\t}\n\n";
		return remove;
	}



	/**
	 * Gets the adds to array function.
	 *
	 * @param o the o
	 * @return the adds to array function
	 */
	private String getAddToArray(MsArray o) {
		// ajouter gestion des listes enchaine
		String add = "";
		o.getType().accept(typeNameVisitor);
		String chaineNom = typeNameVisitor.getResult();
		add += "\tpublic void add"
				+ currentAttribute.getName().substring(0, 1).toUpperCase()
				+ currentAttribute.getName().substring(1) + "(int index, "
				+ chaineNom + " val){\n\t\tif(index<"+o.getMax()+")";

		add += "\n\t\tthis." + currentAttribute.getName().toLowerCase()
				+ "[index]=val;\n\t}\n\n";
		return add;
	}


	/**
	 * Gets the setter function.
	 *
	 * @param o the o
	 * @return the setter function
	 */
	private String getSetter(MsAttribute o) {
		String setter = "";
		o.getType().accept(typeNameVisitor);
		setter += "\tpublic void set"
				+ o.getName().substring(0, 1).toUpperCase()
				+ o.getName().substring(1) + "(" + typeNameVisitor.getResult()
				+ " " + o.getName().toLowerCase() + "){\n\t\tthis."
				+ o.getName().toLowerCase() + "=" + o.getName().toLowerCase()
				+ ";\n\t}\n\n";
		return setter;
	}

	/**
	 * Gets the getter function.
	 *
	 * @param o
	 *            the o
	 * @return the getter function
	 */
	private String getGetter(MsAttribute o) {
		String getter = "";
		o.getType().accept(typeNameVisitor);
		getter += "\tpublic " + typeNameVisitor.getResult() + " get"
				+ o.getName().substring(0, 1).toUpperCase()
				+ o.getName().substring(1) + "(){\n\t\treturn "
				+ o.getName().toLowerCase() + ";\n\t}\n\n";
		return getter;
	}


	/**
	 * Generate final code.
	 */
	public void generate() {
		/** Build source code **/
		this.mdl.accept(this);
		Logger.getLogger(this.getClass().getName()).log(Level.INFO,
				"Source Code Built");

		/** Files building **/
		for (Entry<String, String> entry : this.listeclass.entrySet()) {
			String cle = entry.getKey();
			String valeur = entry.getValue();
			Writer writer = null;

			try {
				File file;
				String packageDir = conf.getPackageReference(mdl.getName());
				if (packageDir != null) {
					file = new File("src/" + packageDir + "/" + cle + ".java");
				} else {
					file = new File("src/" + mdl.getName() + "/" + cle
							+ ".java");
				}
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

		/** REPO BUILDING **/
		Writer writer = null;
		File file;
		try {
			String content = repoImportBlock + repoHeader
					+ repoDeclarationBlock + repoMethodBlock
					+ repoMaterializeBlock + repoFooterBlock;
			String packageDir = conf.getPackageReference(mdl.getName());
			if (packageDir != null) {
				file = new File("src/" + packageDir + "/" + mdl.getName()
						+ "Repository.java");
			} else {
				file = new File("src/" + mdl.getName() + "/" + mdl.getName()
						+ "Repository.java");
			}
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "utf-8"));
			writer.write(content);
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"Files Generated");
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
