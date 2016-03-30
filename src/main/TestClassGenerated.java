package main;



import GrosPaquet.Balise;
import GrosPaquet.Coucou;

public class TestClassGenerated {

	public static void main(String[] args) {
	
		
		Balise uneBalise= new Balise();
		Coucou unCoucou = new Coucou();
		unCoucou.setNumero(5);
		System.out.println("Class coucou , nom:"+unCoucou.getNom()+" numero :"+unCoucou.getNumero());
		System.out.println(uneBalise.getMonString());
	}

}
