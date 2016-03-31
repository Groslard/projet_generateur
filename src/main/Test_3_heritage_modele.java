package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import GrosPaquet.Balise;
import GrosPaquet.Camion;
import GrosPaquet.Coucou;
import GrosPaquet.Fils;
import GrosPaquet.Livre;
import GrosPaquet.Pere;
import GrosPaquet.Vehicule;

public class Test_3_heritage_modele {

	
	
	
	

	
	 @Test
	  public void testHeritageSimple() {

			/*************heritage simple d'une autre classe********************/
			// la class coucou herite de la classe balise
			Balise maBalise =new Balise();
			Coucou monCoucou = new Coucou();
			
			//test de l'heitage de l'heritage  de  balise
			
			assertEquals(monCoucou.getClass().getSuperclass().getName(), maBalise.getClass().getName());
			
			/*************heritage simple d'une classe java********************/
			Livre monLivre=new Livre();
			//la classe livre herite  de arralist<String>
			assertEquals(monLivre.getClass().getSuperclass().getName(), "java.util.ArrayList");
	  }
	 
	 @Test
	  public void testHeritageCircularite() {

		 /*************gestion de l'erreur de circularité circularite********************/
			
			// véhicule hérite de camion et camion herite de véhicule 
			// règle de resolution on supprime l'heritage  de la permiere classe rencontre lors du parsage
			//lors du parsage  on passe dans un premier temps dans l'attribut camion , il n'heritera donc plus  de vahicule
			
			Vehicule monVehicule = new Vehicule();
			Camion monCamion = new Camion();
			//controle que les camion n'herite plus de vehicule
			assertNotEquals(monCamion.getClass().getSuperclass().getName(), monVehicule.getClass().getName());
	  }
	 
	 @Test
	  public void testHeritageDefinitionMultiple() {

		 /*************gestion de l'erreur  de définition multiple d'attribut********************/
			// la classe Pere est fils on toutes les deux le meme attriuts nomDeFamille 
			// règle de résolution l'attribut en doublon  est supprimé de la classe fils
			Pere monPere= new Pere();
			Fils monFils= new Fils();
	  }
	 
}
