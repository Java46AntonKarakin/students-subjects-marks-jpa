package telran.spring.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students_Eclipce")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity {
	@Id
	long id;
	
	@Column(unique = true)
	String name;
	
	
	
}
