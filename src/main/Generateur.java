package main;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;
import generator.JavaVisitor;
import parser.MiniSpecParser;
import parser.ParamParser;

// TODO: Auto-generated Javadoc
/**
 * The Class Generateur.
 */
public class Generateur {
	/** The xml directory. */
	static String xmlDirectory = "C:\\Users\\anthony\\git\\projet_generateur\\src\\xmlExamples\\";
	
	/** The minispec xml. */
	static String minispecXML = "Test_4_valeur_initiale.xml";
	
	/** The config xml. */


	static String configXML = "imports.xml";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		/** On commence par parser le fichier de config **/
		ParamParser paramParser= new ParamParser(xmlDirectory + configXML);
		PrmConfig prmConfig =paramParser.getMetaInstance();
		
		/** Puis on parse le xml de minispec **/
		MiniSpecParser parser = new MiniSpecParser(xmlDirectory + minispecXML );
		parser.setPrimitives(prmConfig.getPrimitivesNames());
		MsModel pkg = parser.getMetaInstance();

		/** Une fois parsé, on génère les classe java correspondantes **/
		JavaVisitor visitgenerer= new JavaVisitor(pkg,prmConfig);
		visitgenerer.generate();
	}

}
