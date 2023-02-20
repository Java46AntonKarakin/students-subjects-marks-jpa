package telran.spring.data.entities;
import jakarta.persistence.*;
@Entity
@Table(name="students")
public class StudentEntity {
	@Id
	@Column(name = "stid")
	long id;
	@Column(unique = true)
	String name;
	public StudentEntity(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public StudentEntity() {
	}
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	
}
