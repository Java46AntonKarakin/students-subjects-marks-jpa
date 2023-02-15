package telran.spring.data.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import telran.spring.data.entities.MarkEntity;
import telran.spring.data.entities.StudentEntity;
import telran.spring.data.entities.SubjectEntity;
import telran.spring.data.model.*;
import telran.spring.data.repo.*;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CollegeServiceImpl implements CollegeService {

	StudentRepository studentRepository;
	SubjectRepository subjectRepository;
	MarkRepository markRepository;

	@Override
	@Transactional(readOnly = false)
	public void addStudent(Student student) {
		if (studentRepository.existsById(student.id)) {
			throw new IllegalStateException(String.format("Student with id%d already exists", student.id));
		}
		studentRepository.save(new StudentEntity(student.id, student.name));
	}

	@Override
	@Transactional(readOnly = false)
	public void addSubject(Subject subject) {
		if (subjectRepository.existsById(subject.id)) {
			throw new IllegalStateException(String.format("Subject with id%d already exists", subject.id));
		}
		subjectRepository.save(new SubjectEntity(subject.id, subject.subject));
	}

	@Override
	@Transactional(readOnly = false)
	public void addMark(Mark mark) {
		StudentEntity studentEntity = studentRepository.findById(mark.stid).orElse(null);
		if (studentEntity == null) {
			throw new NoSuchElementException(String.format("Student with id%d does not exist", mark.stid));
		}
		SubjectEntity subjectEntity = subjectRepository.findById(mark.suid).orElse(null);
		if (subjectEntity == null) {
			throw new NoSuchElementException(String.format("Subject with id%d does not exist", mark.suid));
		}
		
		MarkEntity markEntity = new MarkEntity(studentEntity, subjectEntity, mark.mark);
		
		markRepository.save(markEntity);
	}

}
