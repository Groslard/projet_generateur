package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import GrosPaquet.Balon;
import GrosPaquet.Jeux;

public class Test_2_Association {

	 @Test
	  public void testAssociationSimple() {
		 // test association entre deux classe générées a partir du model 
		 Balon monBalon= new Balon();
		 Jeux monJeux = new Jeux();
		 monJeux.setBalon(new Balon());
		 // on verifie que le type de l'attribut balon de la classe jeux est bien du type de  la classe Balon
		 assertEquals(monBalon.getClass().getName(),monJeux.getBalon().getClass().getName());
	 }
	 
	 @Test
	  public void testAssociationMultiple() {
		 Balon monBalon= new Balon();
		 Jeux monJeux = new Jeux();
		 monJeux.setMesBalons(new ArrayList<Balon>());
		 monJeux.setMesStrings(new ArrayList<String>());
		 //cas1 liste de balons
		 //on verifie que le type de l'array list est bien balon
		// assertEquals(monBalon.getClass().getName(),monJeux.getMesBalons().getClass().getName());
		 
		 
		 //cas 2 on test les bornes borne min=1 borne max =3
		 monJeux.addMesStrings("test");
		 monJeux.addMesStrings("test");
		 monJeux.addMesStrings("test");
		 monJeux.addMesStrings("test");
		 // on ajoute 4 balons la tailles n'aura pas depasse 3
		 assertEquals(3,monJeux.getMesStrings().size());
		 //onsupprime 4 oject de la liste la taille resteegale a 1
		 monJeux.removeMesStrings("test");
		 monJeux.removeMesStrings("test");
		 monJeux.removeMesStrings("test");
		 monJeux.removeMesStrings("test");
		 assertEquals(1,monJeux.getMesStrings().size());
	 }

	
	
}
