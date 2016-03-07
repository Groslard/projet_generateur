package main;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;
import generator.JavaVisitor;
import parser.MiniSpecParser;
import parser.ParamParser;

public class testGeneration {

	public static void main(String[] args) {
		ParamParser paramParser= new ParamParser("C:\\Users\\krabbos\\git\\projet_generateur\\src\\xmlExamples\\imports.xml");
		PrmConfig prmConfig =paramParser.getMetaInstance();
		
		MiniSpecParser parser = new MiniSpecParser("C:\\Users\\krabbos\\git\\projet_generateur\\src\\xmlExamples\\entity.xml");
		parser.setPrimitives(prmConfig.getPrimitivesNames());
		MsModel pkg = parser.getMetaInstance();

		JavaVisitor visitgenerer= new JavaVisitor(pkg,prmConfig);
		visitgenerer.generate();
	}

}
