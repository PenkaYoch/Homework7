package informatics.comparators;

import informatics.members.Student;

public class FirstNameComparator extends StudentComparator{

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getfName().equals(stu2.getfName())) {
			return 0;
		} else {
			return (stu1.getfName().compareTo(stu2.getfName()) * sortOrder);
		}
	}
}
