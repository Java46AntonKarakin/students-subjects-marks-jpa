package telran.spring.data;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import telran.spring.data.model.*;
import telran.spring.data.service.CollegeService;

@Log 
@Component
public class RandomDbCreation {

	@Value("${app.marks.amount}")
	int dbCapacity;
	@Value("${app.marks.maxMark}")
	private int maxMark;
	@Value("${app.marks.minMark}")
	private int minMark;

	String studentNames[] = { "Abraham", "Sarah", "Itshak", "Rahel", "Asaf", "Yacob", "Rivka", "Yosef", "Benyanim",
			"Dan", "Ruben", "Moshe", "Aron", "Yehashua", "David", "Salomon", "Nefertity", "Naftaly", "Natan", "Asher" };

	String subjects[] = { "Java core", "Java Technologies", "Spring Data", "Spring Security", "Spring Cloud", "CSS",
			"HTML", "JS", "React", "Material-UI" };

	@Autowired
	CollegeService collegeService;

	@PostConstruct
	void dbCreationHW() {
		addStudents();
		addSubjects();
		addMarks();
	}

	private void addMarks() {
		int[] markAmount = { 0 };
		try {
			IntStream.range(0, dbCapacity).forEach(i -> {
				int whoGetsTheMostFairMark = ThreadLocalRandom.current().nextInt(1, studentNames.length + 1);
				int subjectNum = ThreadLocalRandom.current().nextInt(1, subjects.length + 1);
				int theMostFairMark = ThreadLocalRandom.current().nextInt(minMark, maxMark);
				Mark mark = new Mark(whoGetsTheMostFairMark, subjectNum, theMostFairMark);
//				log.fine(String.format("mark (stid=%d; suid=%d, mark=%d) has been created", whoGetsTheMostFairMark, subjectNum, theMostFairMark));
				collegeService.addMark(mark);
//				log.fine(String.format("mark (stid=%d; suid=%d, mark=%d) has been added toDB", whoGetsTheMostFairMark,
//						subjectNum, theMostFairMark));
				markAmount[0]++;
			});
			log.fine(String.format("Marks (%d) have been created and placed to DB", markAmount[0]));
		} catch (Exception e) {
			log.fine(String.format("Mark could'n be created: %s", e.getMessage()));
		}
	}

	private void addSubjects() {
		IntStream.range(0, subjects.length).forEach(suid -> {
			try {
				collegeService.addSubject(new Subject(suid + 1, subjects[suid]));
			} catch (IllegalStateException e) {
				log.fine(String.format("Subject with id_%d already exists", suid));
			}
		});
		log.fine(String.format("Subjects (%d pieces) from array 'subjects' have been created and added to BD",
				subjects.length));
	}

	private void addStudents() {
		IntStream.range(0, studentNames.length).forEach(stid -> {
			try {
				collegeService.addStudent(new Student(stid + 1, studentNames[stid]));
			} catch (IllegalStateException e) {
				log.fine(String.format("Student with id_%d already exists", stid));
			}
		});
		log.fine(String.format("Students (%d pieces) from array 'studentNames' have been created and added to BD",
				studentNames.length));
	}

//	@PostConstruct
//	void dbCreation() {
//		int idNum = 1;
//		try {
//			Student std = new Student(idNum, "Vasya");
//			log.fine(String.format("Student with id_%d has been created", std.id));
//			collegeService.addStudent(std);
//			log.fine(String.format("Student with id_%d has been added to repo", std.id));
//		} catch (IllegalStateException e) {
//			log.fine(String.format("Student with id_%d already exists", idNum));
//		}
//		try {
//			Subject sub = new Subject(idNum, "Java");
//			log.fine(String.format("Subject with id_%d has been created", sub.id));
//			collegeService.addSubject(sub);
//			log.fine(String.format("Subject with id_%d has been added to repo", sub.id));
//		} catch (IllegalStateException e) {
//			log.fine(String.format("Subject with id_%d already exists", idNum));
//		}
//
//		try {
//			Mark mark = new Mark(1, 1, 95);
//			log.fine(String.format("Mark with stid_'%d' and suid_'%d' has been created", mark.stid, mark.suid));
//			collegeService.addMark(mark);
//			log.fine(String.format("Mark with stid_'%d' and suid_'%d' has been added to repo", mark.stid, mark.suid));
//		} catch (NoSuchElementException e) {
//			log.fine(String.format("Mark could'n be created: ", e.getMessage()));
//		}
//	}
}
