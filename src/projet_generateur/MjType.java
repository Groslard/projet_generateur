package projet_generateur;

import java.util.HashMap;

public abstract class MjType {
	protected String id;
	protected HashMap<String, String> imports;
	protected String defaultValue = null;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void addImportPath(String langage, String path){
		imports.put(langage, path);
	}
	
	public String getImportPathFromLangage(String langage){
		return imports.get(langage);
	}

	public HashMap<String, String> getImports() {
		return imports;
	}

	public void setImports(HashMap<String, String> imports) {
		this.imports = imports;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
