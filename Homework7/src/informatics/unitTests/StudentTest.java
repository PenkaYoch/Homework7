package informatics.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import informatics.comparators.FirstNameComparator;
import informatics.gui.StudentDataGUI;
import informatics.members.Student;
import informatics.util.FileReader;

public class StudentTest {

	@Test
	public void testRun() {
		StudentDataGUI.main(null);
	}
	
	@Test
	public void testReadingFromFile() {
		Student[] students = FileReader.readStudentInfo();
		assertNotNull(students);
	}
	
	@Test
	public void testWritingInFile(){
		StudentDataGUI.createStudentData();
		Student[] students = FileReader.readStudentInfo();
		assertNotNull(students);
	}
	
	@Test
	public void testCreateStudents() {
		StudentDataGUI gui = new StudentDataGUI();
		gui.createStudents();
		assertNotNull(gui.students);
	}
}
