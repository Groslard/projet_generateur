package GrosPaquet;

public class Balise {
	private Arraylist<Arraylist<String>> liste_a;
	private String monstring;

	public Balise() {
	}

	public void setliste_a(Arraylist<Arraylist<String>> liste_a) {
		this.liste_a = liste_a;
	}

	public Arraylist<Arraylist<String>> getliste_a() {
		return liste_a;
	}

	public void setmonstring(String monstring) {
		this.monstring = monstring;
	}

	public String getmonstring() {
		return monstring;
	}

}