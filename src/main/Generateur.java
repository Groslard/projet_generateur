package main;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;
import generator.JavaVisitor;
import parser.MiniSpecParser;
import parser.ParamParser;

public class Generateur {

	static String xmlDirectory = "C:\\Users\\anthony\\git\\projet_generateur\\src\\xmlExamples\\";
	
	static String minispecXML = "entity.xml";
	static String configXML = "imports.xml";
	
	public static void main(String[] args) throws Exception {
		/** On commence par parser le fichier de config **/
		ParamParser paramParser= new ParamParser(xmlDirectory + configXML);
		PrmConfig prmConfig =paramParser.getMetaInstance();
		
		/** Puis on parse le xml de minispec **/
		MiniSpecParser parser = new MiniSpecParser(xmlDirectory + minispecXML );
		parser.setPrimitives(prmConfig.getPrimitivesNames());
		MsModel pkg = parser.getMetaInstance();

		/** Une fois pars�, on g�n�re les classe java correspondantes **/
		JavaVisitor visitgenerer= new JavaVisitor(pkg,prmConfig);
		visitgenerer.generate();
	}

}
