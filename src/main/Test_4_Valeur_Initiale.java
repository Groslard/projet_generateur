package main;

import static org.junit.Assert.*;

import org.junit.Test;

import GrosPaquet.Balise;
import GrosPaquet.Coucou;
import GrosPaquet.Mot;
import GrosPaquet.Phrase;

public class Test_4_Valeur_Initiale {


	
	@Test
	  public void testValeurprimitive() {
		Phrase maphrase= new Phrase();
		Mot monMot= new Mot();
		
		//cas dans lequel oninitialise une valeur primitive avec une valeur par defaut 
		// le type est marque comme primitif dans le fichier import.xml
		// la valeur par defaut est saisie dans le fichier d'entity.xml (ici: Test_4_Valeur_Initiale)
		
		//1er cas l'attribut de type primitif a une valeur par default
		assertEquals("salut", maphrase.getNumero());
		//2eme cas l'attribut de type primitif  na pas de valeur par defaut
		assertEquals(null, monMot.getMonString());
	  }
	
	@Test
		
	  public void testValeurConstructeur() {
		Phrase maphrase= new Phrase();
		Mot monMot= new Mot();
		// test d'initialisation via construceur test que les variable  ne sont pas null
		//cas 1 pour une class du modele
		assertNotEquals(null, maphrase.getMaMot());
		
		// cas 2 pour une class de la librairie de base java
		assertNotEquals(null, monMot.getNoms());
		
		
	  }
	
	
	@Test
	  public void testValeurFonction() {
		//test d'initialisation via une fonction
		// cas 1 la fontion testf retourne null
		Mot monMot= new Mot();
		assertEquals(monMot.getPrenoms(), monMot.testf());
			
	  }
	
}
