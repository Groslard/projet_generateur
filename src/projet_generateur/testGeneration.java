package projet_generateur;

public class testGeneration {

	public static void main(String[] args) {
		MjParser parser = new MjParser("C:\\Users\\anthony\\git\\projet_generateur\\src\\entity.xml");
		MjModel pkg = parser.getMetaInstance();
		
		JavaVisitor visitgenerer= new JavaVisitor(pkg);
		visitgenerer.generate();
	}

}
