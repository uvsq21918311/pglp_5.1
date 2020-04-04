package fr.uvsq.pglp_5_1;

import fr.uvsq.pglp_5_1.AbstractDAOFactory.DAOType;

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
		Personnel dupond = new PersonnelBuilder("Dupond", "Dupont", "Employé")
				   			.build();
		Personnel dupont = new PersonnelBuilder("Dupont", "Dupond", "Employé")
							.build();
		PersonnelGroupe pg = new PersonnelGroupe();
		PersonnelGroupe spg = new PersonnelGroupe();
		pg.addPersonnel(spg);
		pg.addPersonnel(dupont);
		spg.addPersonnel(dupond);
		// Ajout DAO
		pgDAO.create(pg);
		pgDAO.create(spg);
		personnelDAO.create(dupond); // update
		personnelDAO.create(dupont); // update
		System.out.println("\t" + pgDAO.read(spg.getId()));
		System.out.println("\t" + personnelDAO.read("Dupont"));
	}
	
	public static void main(String[] args) {
		
		ENVIRONNEMENT.run(args, DAOType.Serial);
		//ENVIRONNEMENT.run(args, DAOType.JDBC);
	}
	
}
