package main;

import modelMiniSpec.MsModel;
import generator.JavaVisitor;
import parser.MiniSpecParser;

public class testGeneration {

	public static void main(String[] args) {
		MiniSpecParser parser = new MiniSpecParser("C:\\Users\\anthony\\git\\projet_generateur\\src\\entity.xml");
		MsModel pkg = parser.getMetaInstance();
		
		JavaVisitor visitgenerer= new JavaVisitor(pkg);
		visitgenerer.generate();
	}

}
