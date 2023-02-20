package telran.spring.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.spring.data.proj.IntervalMarksCount;
import telran.spring.data.proj.MarkProj;
import telran.spring.data.proj.StudentAvgMark;
import telran.spring.data.proj.StudentName;
import telran.spring.data.proj.StudentSubjectMark;
import telran.spring.data.service.CollegeService;

import java.util.*;

@RestController
@RequestMapping("college")
public class CollegeController {
	@Autowired
	CollegeService collegeService;

	@GetMapping("marks")
	List<MarkProj> getMarksByNameSubject(@RequestParam(name = "subject") String subject,
			@RequestParam(name = "name") String name) {
		return collegeService.getMarksByNameSubject(name, subject);
	}

	@GetMapping("marks/subjects")
	List<StudentSubjectMark> getMarksByName(@RequestParam(name = "name") String name) {
		return collegeService.getMarksByName(name);
	}
	
	@GetMapping("marks/avgmark")
	List<StudentAvgMark>  sdtudentsAvgMark()  {
		return collegeService.getStudentsAvgMark();
	}
	
	@GetMapping("marks/best")
	List<StudentName>  bestStudents(@RequestParam(name = "nStudents", defaultValue = "0") int nStudents,
			@RequestParam(name = "subject", defaultValue = "") String subject)  {
		List<StudentName> res = null;
		if (nStudents==0 && subject.isEmpty()) {
			res = collegeService.getBestStudents();
		} else if (nStudents!=0) {
			if (subject.isEmpty()) {
				res = collegeService.getTopBestStudents(nStudents);
			} else {
				res = collegeService.getTopBestStudentsSubject(nStudents, subject);
			}
		}
		return res;
	}
	
	@GetMapping("marks/worst")
	List<StudentSubjectMark> worstStudents(@RequestParam(name = "nStudents", defaultValue = "0") int nStudents) {
		return collegeService.getMarksOfWorstStudents(nStudents);
	}
	
	@GetMapping("marks/marks_distr")
	List<IntervalMarksCount> marksDistribution(@RequestParam(defaultValue="10", name="interval") int interval)  {
		return collegeService.marksDistibution(interval);
	}
}
