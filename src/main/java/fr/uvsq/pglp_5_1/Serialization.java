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
		Personnel Kamal = new PersonnelBuilder("EL KADI", "Kamal", "Employé")
				   			.build();
		Personnel Henry = new PersonnelBuilder("David", "Henry", "Employé")
							.build();
		PersonnelGroupe pg = new PersonnelGroupe();
		PersonnelGroupe spg = new PersonnelGroupe();
		pg.addPersonnel(spg);
		pg.addPersonnel(Henry);
		spg.addPersonnel(Kamal);
		// Ajout DAO
		pgDAO.create(pg);
		pgDAO.create(spg);
		personnelDAO.create(Kamal); // update
		personnelDAO.create(Henry); // update
		System.out.println("\t" + pgDAO.read(spg.getId()));
		System.out.println("\t" + personnelDAO.read("Henry"));
	}
	
	public static void main(String[] args) {
		
		ENVIRONNEMENT.run(args, DAOType.Serial);
		//ENVIRONNEMENT.run(args, DAOType.JDBC);
	}
	
}
