package informatics.members;

import java.util.concurrent.ThreadLocalRandom;
public class Student {
	private String fName;
	private String sName;
	private String lName;
	private String speciality;
	private int facNumber;
	
	public Student(String fName, String sName, String lName, String speciality, int facNumber) {
		super();
		this.fName = fName;
		this.sName = sName;
		this.lName = lName;
		this.speciality = speciality;
		this.facNumber = facNumber;
	}

	public Student(String fName, String sName, String lName) {
		super();
		this.fName = fName;
		this.sName = sName;
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public int getFacNumber() {
		return facNumber;
	}

	public void setFacNumber(int facNumber) {
		this.facNumber = facNumber;
	}
	
	public static class StudentGenerator {
		private static String[] fNames = {"Ivan", "Mariya", "Stoyan", "Petyr", "Petya"};
		private static String[] sNames = {"Ivanov", "Hristova", "Petrov", "Kirilov", "Yordanova"};
		private static String[] lNames = {"Petrov", "Ivanova", "Petrov", "Korchev", "Petrova"};
		private static String[] specialities = {"Informatics", "STD", "BIT", "Mathematics"};
		
		public static Student make() {
			int arrayIndex = ThreadLocalRandom.current().nextInt(0, fNames.length);
			String fname = fNames[arrayIndex];
			String sname = sNames[arrayIndex];
			String lname = lNames[arrayIndex];
			
			arrayIndex = ThreadLocalRandom.current().nextInt(0, specialities.length);
			String speciality = specialities[arrayIndex];
	
			int facNumber = ThreadLocalRandom.current().nextInt(111111, 999999);
				
			return new Student(fname, sname, lname, speciality, facNumber);
		}
	}

}
