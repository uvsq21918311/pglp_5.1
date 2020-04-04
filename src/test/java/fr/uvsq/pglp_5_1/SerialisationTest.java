package fr.uvsq.pglp_5_1;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fr.uvsq.pglp_5_1.AbstractDAOFactory.DAOType;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class SerialisationTest {

	private Personnel p;
	private PersonnelGroupe pg;
	
	@Before()
	public void setUp() {
		p = new Personnel.PersonnelBuilder("Jean", "Jacques", "Enseignant")
				.dateNaissance(LocalDate.parse("1970-01-01", DateTimeFormatter.ISO_DATE))
				.addNumeroTelephone(new NumeroTelephone("Portable", "0666666666"))
				.addNumeroTelephone(new NumeroTelephone("Fax", "0123456786")).build();
		
		pg = new PersonnelGroupe();
		PersonnelGroupe qg = new PersonnelGroupe();
		pg.addPersonnel(qg);
		pg.addPersonnel(p);
	}
	
	@Test()
	public void testSerializationPersonnel() {
		Personnel q = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("serialisation.txt"));
			ois = new ObjectInputStream(new FileInputStream("serialisation.txt"));
			
			try {
				oos.writeObject(p);
				q = (Personnel) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} finally {
				oos.close();
				ois.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(p, q);
	}
	
	@Test()
	public void testSerializationPersonnelGroupe() {
		PersonnelGroupe qg = null;
		ObjectOutputStream oos;
		ObjectInputStream ois;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("serialisation.txt"));
			ois = new ObjectInputStream(new FileInputStream("serialisation.txt"));
			
			try {
				oos.writeObject(pg);
				qg = (PersonnelGroupe) ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} finally {
				oos.close();
				ois.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(pg, qg);
	}
	
	@Test()
	public void testPersonnelCRUD() {
		DAO<Personnel> pc = AbstractDAOFactory
				.getFactory(DAOType.Serial)
				.getPersonnelDAO();
		pc.delete(p); // Supprime le fichier s'il existe déjà
		pc.create(p);
		p.addNumeroTelephone(new NumeroTelephone("Maison", "0123456789"));
		pc.update(p);
		assertEquals(p, pc.read(p.getNom()));
	}
	
	@Test()
	public void testPersonnelGroupeCRUD() {
		DAO<PersonnelGroupe> pgc = DAOFactory
				.getFactory(DAOType.Serial)
				.getPersonnelGroupeDAO();
		pgc.delete(pg); // Supprime le fichier s'il existe déjà
		pgc.create(pg);
		pg.addPersonnel(p);
		pgc.update(pg);
		assertEquals(pg, pgc.read(pg.getId()));
	}

}
