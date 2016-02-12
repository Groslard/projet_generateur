package projet_generateur;

public interface InterfaceVisitor {
	

	void visit(MjPackage o);
    void visit(MjEntity o);
    void visit(MjAttribute o);
	void visit(MjList list);
	void visit(MjReference ref);
	void visit(MjPrimitif prim);

}
