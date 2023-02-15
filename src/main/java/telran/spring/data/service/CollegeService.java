package telran.spring.data.service;

import telran.spring.data.model.Mark;
import telran.spring.data.model.Student;
import telran.spring.data.model.Subject;

public interface CollegeService {
	
	void addStudent(Student student);
	void addSubject(Subject subject);
	void addMark(Mark mark);
}
