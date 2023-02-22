package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import telran.spring.data.entities.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

//	@Query(value = "SELECT subject, suid FROM subjects WHERE suid IN (SELECT suid  FROM marks GROUP BY suid"
//			+ " HAVING COUNT(suid) > :markCountLess)", nativeQuery = true)
	@Query(""" 
			SELECT subjects FROM SubjectEntity subjects 
			WHERE subjects.id IN (SELECT subject.id 
			FROM MarkEntity 
			GROUP BY subject.id 
			HAVING COUNT(mark) > :markCountLess) 
			""" )
	List<SubjectEntity> leastPopularSubjects(double markCountLess);
}
