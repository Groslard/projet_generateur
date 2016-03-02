package projet_generateur;

public class MjMapPrimitives {
	
	protected String id;
	protected String importPath=null;
	protected String defaultValue = null;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}


	public String getImportPath() {
		return importPath;
	}

	public void setImportPath(String importPath) {
		this.importPath = importPath;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	 public void accept(JavaVisitor visitor){
		 return;
	 }
	
}
