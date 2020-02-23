package informatics.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import informatics.comparators.FirstNameComparator;
import informatics.comparators.SecondNameComparator;
import informatics.comparators.StudentComparator;
import informatics.members.CountOfStudentName;
import informatics.members.Student;
import informatics.util.FileReader;

public class StudentDataGUI {

	public static Student[] students;
	public JTable table;
	JTable secondTable;
	StudentDataModel studentDataModel;
	StudentNameDataModel studentNameDataModel;
	JRadioButton descendingRadioB = new JRadioButton("Низходящ ред");

	public static void main(String[] args) {
//		createStudents();
//		createStudentData();
		students = FileReader.readStudentInfo();
		StudentDataGUI gui = new StudentDataGUI();
		gui.createAndShowStudentTable();
	}

	public static void createStudentData() {
		if (!FileReader.isFileExists()) {
			FileReader.createStudentsFile();
		}

		FileReader.writePeople(students);
	}

	public static void createStudents() {
		students = new Student[10];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student.StudentGenerator().make();
		}
	}

	public void createAndShowStudentTable() {
		JFrame frame = new JFrame("Таблица със студенти");
		frame.setSize(1000, 500);

		studentDataModel = new StudentDataModel(students);
		table = new JTable(studentDataModel);

		JScrollPane scroll = new JScrollPane(table);

		JPanel mainButtonsPanel = new JPanel();
		JRadioButton ascendingRadioB = new JRadioButton("Възходящ ред", true);
//		JRadioButton descendingRadioB = new JRadioButton("Низходящ ред");
		JButton sortSecondName = new JButton("Сортирай по фамилно име");
		JButton sortFirstName = new JButton("Сортирай по малко име");

		JTextField searchStudent = new JTextField();
		searchStudent.setText("Фамилия на студента");
		searchStudent.setForeground(Color.GRAY);
		JButton filterName = new JButton("Търсене/Филтриране по фамилия на студента");

		mainButtonsPanel.setLayout(new GridLayout(3, 2));
		mainButtonsPanel.add(ascendingRadioB);
		mainButtonsPanel.add(descendingRadioB);
		mainButtonsPanel.add(sortSecondName);
		mainButtonsPanel.add(sortFirstName);
		mainButtonsPanel.add(searchStudent);
		mainButtonsPanel.add(filterName);

		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new BorderLayout());
		firstPanel.add(scroll, BorderLayout.CENTER);
		firstPanel.add(mainButtonsPanel, BorderLayout.SOUTH);

		JTabbedPane tab = new JTabbedPane();
		tab.add(firstPanel, "Таблица");
		JPanel secondPanel = new JPanel();
		JLabel titleLabel = new JLabel("Справка за студенти с еднакво първо име");
		CountOfStudentName[] countedStudentNames = getNameCount(students);
		studentNameDataModel = new StudentNameDataModel(countedStudentNames);
		secondTable = new JTable(studentNameDataModel);
		JScrollPane secondScroll = new JScrollPane(secondTable);

		secondPanel.setLayout(new BorderLayout());
		secondPanel.add(titleLabel, BorderLayout.CENTER);
		secondPanel.add(secondScroll, BorderLayout.CENTER);
		tab.add(secondPanel, "Справка");
		
		frame.add(tab);

		ascendingRadioB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (descendingRadioB.isSelected()) {
					descendingRadioB.setSelected(false);
				}
			}
		});

		descendingRadioB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ascendingRadioB.isSelected()) {
					ascendingRadioB.setSelected(false);
				}
			}
		});

		searchStudent.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {				
				if (searchStudent.getText().isEmpty()) {
					searchStudent.setForeground(Color.GRAY);
					searchStudent.setText("Фамилия на студента");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (searchStudent.getText().equals("Фамилия на студента")) {
					searchStudent.setText("");
					searchStudent.setForeground(Color.BLACK);
				}
			}
		});

		sortSecondName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sortTable(2, table, students);
			}
		});

		sortFirstName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sortTable(1, table, students);
			}
		});

		filterName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				filterData(students, searchStudent.getText());
			}
		});

		frame.setVisible(true);
	}

	public void sortTable(int intValue, JTable table, Student[] students) {
		StudentComparator comparator = null;
		if (descendingRadioB.isSelected()) {
			StudentComparator.sortOrder = -1;
		} else {
			StudentComparator.sortOrder = 1;
		}

		switch (intValue) {
		case 1:
			comparator = new FirstNameComparator();
			break;
		case 2:
			comparator = new SecondNameComparator();
			break;
		}

		if (comparator == null) {
			Arrays.sort(students);
		} else {
			Arrays.sort(students, comparator);
		}
		table.repaint();
	}

	private void filterData(Student[] students, String searchedName) {
		ArrayList<Student> filteredData = new ArrayList<Student>();

		for (int i = 0; i < students.length; i++) {
			if (students[i].getlName().equals(searchedName)) {
				filteredData.add(students[i]);
			}
		}

		Student[] filteredDataArray = new Student[filteredData.size()];
		filteredDataArray = filteredData.toArray(filteredDataArray);
		this.studentDataModel = new StudentDataModel(filteredDataArray);
		table.setModel(studentDataModel);
		table.repaint();
	}
	
	private CountOfStudentName[] getNameCount(Student[] students) {
		Set<CountOfStudentName> countStudent = new HashSet<>();
		Arrays.sort(students, new FirstNameComparator());
		int count = 0;
		for(int i = 0; i < students.length; i++) {
			for(int j = 0; j < students.length - 1; j++) {
				if(students[i].getfName().equals(students[j].getfName()))
					count++;
			}
			CountOfStudentName currentName = new CountOfStudentName(students[i].getfName(), count);
			countStudent.add(currentName);
			count = 0;
		}
		CountOfStudentName[] studentsFName = new CountOfStudentName[countStudent.size()];
		studentsFName = countStudent.toArray(studentsFName);
		return studentsFName;
	}
}
