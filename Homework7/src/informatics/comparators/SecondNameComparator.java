package informatics.comparators;

import informatics.members.Student;

public class SecondNameComparator extends StudentComparator{

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getsName().equals(stu2.getsName())) {
			return 0;
		} else {
			return (stu1.getsName().compareTo(stu2.getsName()) * sortOrder);
		}
	}

}
