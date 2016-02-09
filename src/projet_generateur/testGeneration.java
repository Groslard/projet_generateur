package projet_generateur;

public class testGeneration {

	public static void main(String[] args) {
		MjParser parser = new MjParser("C:\\Users\\Anthony\\git\\projet_generateur\\src\\entity.xml");
		
		MjPackage pkg = parser.getMetaInstance();
		
		
		GenerateurVisitor visitgenerer= new GenerateurVisitor(pkg);
		visitgenerer.visit(visitgenerer.pkg);
		System.out.println(pkg);
		
	}

}
