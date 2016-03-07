package main;


import modelParameter.PrmConfig;
import modelParameter.PrmParameter;
import parser.ParamParser;

public class TestParserParam {
	public static void main(String[] args) {
		ParamParser parser = new ParamParser(
				"C:\\Users\\krabbos\\git\\projet_generateur\\src\\xmlExamples\\imports.xml");
		PrmConfig pkg = parser.getMetaInstance();
		
		// test de la liste de PrmParameter
		for (String nomEntities : pkg.getPrimitivesNames()) {
			System.out.println(nomEntities);
		}
		
		
		//test du get
		
		PrmParameter paramTest=pkg.getParameterPrimitif("set");
		System.out.println("le path d'import du set est : "+paramTest.getPkg());
	}

}
