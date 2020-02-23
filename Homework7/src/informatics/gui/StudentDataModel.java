package informatics.gui;

import javax.swing.table.AbstractTableModel;

import informatics.members.Student;

public class StudentDataModel extends AbstractTableModel{

	private Student[] students;
	
	public StudentDataModel(Student[] students) {
		this.students = students;
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return students.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return students[rowIndex].getfName();
			case 1:
				return students[rowIndex].getsName();
			case 2:
				return students[rowIndex].getlName();
			case 3:
				return students[rowIndex].getSpeciality();
			case 4:
				return students[rowIndex].getFacNumber();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
			case 0:
				return "���";
			case 1:
				return "�������";
			case 2:
				return "�������";
			case 3:
				return "�����������";
			case 4:
				return "���. �����";
	
			default:
				return super.getColumnName(column);
		}
	}
}
