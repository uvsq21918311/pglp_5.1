package fr.uvsq.pglp_5_1;

public class PersonnelGroupeSerialDAO extends SerialDAO<PersonnelGroupe> {
	
	public String getFilename(String id) {
		return id + ".personnelgroupe";
	}
	
	public String getFilename(PersonnelGroupe obj) {
		return getFilename(obj.getId());
	}
}
