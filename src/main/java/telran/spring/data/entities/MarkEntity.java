package telran.spring.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "marks_Eclipce")
@NoArgsConstructor
@Getter
public class MarkEntity {
	public MarkEntity(StudentEntity student, SubjectEntity subject, int mark) {
		super();
		this.student = student;
		this.subject = subject;
		this.mark = mark;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public long id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	public StudentEntity student;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	public SubjectEntity subject;
	
	public int mark;
}
