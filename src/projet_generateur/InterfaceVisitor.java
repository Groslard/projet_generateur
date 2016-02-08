package projet_generateur;

public interface InterfaceVisitor {
	

	void visit(MjPackage o);
    void visit(MjEntity o);
    void visit(MjAttribute o);

}
