package main;

import org.omg.Messaging.SyncScopeHelper;

import modelMiniSpec.MsModel;
import modelParameter.PrmConfig;
import parser.MiniSpecParser;
import parser.ParamParser;

public class TestParserParam {
	public static void main(String[] args) {
		ParamParser parser = new ParamParser(
				"C:\\Users\\krabbos\\git\\projet_generateur\\src\\xmlExamples\\imports.xml");
		PrmConfig pkg = parser.getMetaInstance();
		for (String nomEntities : pkg.getentities()) {
			System.out.println(nomEntities);
		}
		
	}

}
