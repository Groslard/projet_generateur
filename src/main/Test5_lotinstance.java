package main;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import concession.Garage;
import concession.Moteur;
import concession.Voiture;
import concession.concessionRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class Test5_lotinstance.
 */
public class Test5_lotinstance {
	
	/** The garages. */
	public static ArrayList<Garage> garages = new ArrayList<Garage>();
	
	/** The voitures. */
	public static ArrayList<Voiture> voitures = new ArrayList<Voiture>();
	
	/** The moteurs. */
	public static ArrayList<Moteur> moteurs = new ArrayList<Moteur>();
	
	/** The repo. */
	public static concessionRepository repo;
	
	/**
	 * Inits the moteurs.
	 */
	@BeforeClass
	public static void initMoteurs(){
		Moteur m1 = new Moteur();
		m1.setModel("General Moteur");
		m1.setPuissance(111);
		moteurs.add(m1);
				
		Voiture v1 = new Voiture();
		v1.setModel("147");
		v1.setMoteur(m1);
		v1.setMarque("Alfa Romeo");
		v1.setAnnee(1991);
		
		Voiture v2 = new Voiture();
		v2.setModel("159");
		v2.setMoteur(m1);
		v2.setMarque("Alfa Romeo");
		v2.setAnnee(1998);
		
		voitures.add(v1);
		voitures.add(v2);
		
		Garage g1 = new Garage();
		g1.addClients("Cariou");
		g1.addClients("Cosquer");
		g1.addVehicules(v1);
		g1.addVehicules(v2);
		garages.add(g1);
	}
	
	/**
	 * Inits the repo.
	 */
	@Before
	public void initRepo(){
		repo = new concessionRepository();
	}
	
	/**
	 * Test serialisation.
	 */
	@Test
	public void testSerialisation() {
		for (Garage garage : garages) {
			repo.addInstance(garage);
		}
		for (Voiture voiture : voitures) {
			repo.addInstance(voiture);
		}
		for (Moteur moteur : moteurs) {
			repo.addInstance(moteur);
		}
		
		try {
			repo.serialize("./testSer.xml");
		} catch (FileNotFoundException e) {
			fail("Echec de la Serialisation");
		}
		
		File f = new File("./testSer.xml");
		assertTrue(f.exists());
	}
	
	/**
	 * Test materialisation.
	 */
	@Test
	public void testMaterialisation(){
		try {
			repo.materialize("./testSer.xml");
		} catch (IOException e) {
			fail("Echec de la matérialisation");
		}
		
		assertEquals(garages.size(), repo.garages.size());
		assertEquals(garages.get(0).getClients(), repo.garages.get(0).getClients());
		assertEquals(garages.get(0).getVehicules().get(0).getModel(), repo.garages.get(0).getVehicules().get(1).getModel());
		assertEquals(moteurs.get(0).getPuissance(), repo.moteurs.get(0).getPuissance());
	}
	
	/**
	 * Removes the xml file.
	 */
	@AfterClass
    public static void removeXmlFile() {
		File xml = new File("./testSer.xml");
		xml.delete();
    }

}
