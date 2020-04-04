package fr.uvsq.pglp_5_1;

import fr.uvsq.pglp_5_1.AbstractDAOFactory.DAOType;
import fr.uvsq.pglp_5_1.Personnel.PersonnelBuilder;;
public enum Serialization {
	ENVIRONNEMENT;
	
	private void run(String[] args, DAOType dt) {
		DAO<Personnel> personnelDAO = AbstractDAOFactory
				.getFactory(dt) // Factory
				.getPersonnelDAO();
		DAO<PersonnelGroupe> pgDAO = AbstractDAOFactory
				.getFactory(dt)
				.getPersonnelGroupeDAO();
		// Creation personnel
		Personnel ELKADI = new PersonnelBuilder("EL KADI", "Kamal", "Employé")
				   			.build();
		Personnel Kamal = new PersonnelBuilder("EL KADI", "Kamal", "Employé")
							.build();
		PersonnelGroupe pg = new PersonnelGroupe();
		PersonnelGroupe spg = new PersonnelGroupe();
		pg.addPersonnel(spg);
		pg.addPersonnel(Kamal);
		spg.addPersonnel(ELKADI);
		// Ajout DAO
		pgDAO.create(pg);
		pgDAO.create(spg);
		personnelDAO.create(ELKADI); // update
		personnelDAO.create(Kamal); // update
		System.out.println("\t" + pgDAO.read(spg.getId()));
		System.out.println("\t" + personnelDAO.read("Kamal"));
	}
	
	public static void main(String[] args) {
		
		ENVIRONNEMENT.run(args, DAOType.Serial);
		//ENVIRONNEMENT.run(args, DAOType.JDBC);
	}
	
}
