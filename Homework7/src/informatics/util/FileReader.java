package informatics.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import informatics.members.Student;

public class FileReader {

	private static final String FILE_EXTENSION = ".file";
	private static final String STUDENT_FILE_NAME = "students";
	private static final String FILE_PATH = "src/informatics/files/";
	private static final String FULL_PATH = FILE_PATH + STUDENT_FILE_NAME + FILE_EXTENSION;

	private static ArrayList<Student> studentList = new ArrayList<>();

	public static void createStudentsFile() {
		File file = new File(FULL_PATH);
		file.getParentFile().mkdirs();

		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("The file cannot be created.");
			e.printStackTrace();
		}
	}

	public static boolean isFileExists() {
		File file = new File(FULL_PATH);
		return file.exists();
	}

	public static Student[] readStudentInfo() {
		try {
			FileInputStream fileStream = new FileInputStream(FULL_PATH);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
			String stringLine;

			while ((stringLine = bufferedReader.readLine()) != null) {
				String[] data = stringLine.split("\t");
				addStudent(data);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Student[] people = new Student[studentList.size()];
		return studentList.toArray(people);
	}

	private static void addStudent(String[] data) {
		Student student = new Student(data[0], data[1], data[2], "Informatics", 1234);
		studentList.add(student);
	}

	public static void writePeople(Student[] students) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(FULL_PATH, true));

			for (Student student : students) {
				writer.append(student.getfName() + "\t");
				writer.append(student.getsName() + "\t");
				writer.append(student.getlName() + "\t");
				writer.append("\n");

				System.out.printf("%s %s %s is added to the students'file!\n ", student.getfName(), student.getsName(),
						student.getlName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println("The writer cannot be closed.");
					e.printStackTrace();
				}
			}
		}
	}
}
