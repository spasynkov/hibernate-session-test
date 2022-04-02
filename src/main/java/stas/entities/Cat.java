package stas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cat {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private int age;
}
