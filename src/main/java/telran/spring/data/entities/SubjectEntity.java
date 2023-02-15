package telran.spring.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects_Eclipce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectEntity {
	@Id
	long id;
	
	@Column(unique = true)
	String subject;
	
	
	
}
