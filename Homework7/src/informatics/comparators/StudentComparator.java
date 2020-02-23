package informatics.comparators;

import java.util.Comparator;

import javax.swing.SortOrder;

import informatics.members.Student;

public abstract class StudentComparator implements Comparator<Student>{
	public static int sortOrder = 1; 

	public void setSortOrder(SortOrder order) {
		if (order.equals(SortOrder.DESCENDING)) {
			this.sortOrder = -1;
		} else {
			this.sortOrder = 1;
		}
	}
}
