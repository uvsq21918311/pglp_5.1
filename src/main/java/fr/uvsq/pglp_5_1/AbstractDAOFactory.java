package fr.uvsq.pglp_5_1;

public abstract class AbstractDAOFactory {
	public enum DAOType {JDBC, Serial};
	
	public abstract DAO<Personnel> getPersonnelDAO();
	
	public abstract DAO<PersonnelGroupe> getPersonnelGroupeDAO();
	
	public static AbstractDAOFactory getFactory(DAOType type) {
		
		if (type == DAOType.Serial) return new DAOFactory();
		return null;
	}
	
}
