package informatics.gui;

import javax.swing.table.AbstractTableModel;

import informatics.members.CountOfStudentName;

public class StudentNameDataModel extends AbstractTableModel{

	private CountOfStudentName[] students;
	
	public StudentNameDataModel(CountOfStudentName[] students) {
		this.students = students;
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return students.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return students[rowIndex].getName();

		case 1:
			return students[rowIndex].getNumber();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Първо име";
					
		case 1: 
			return "Брой студенти";
		}
		
		return super.getColumnName(column);
	}
}
