package com.mdf.springjpa.Spring.Jpa.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tbl_students", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_address"))
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long studentId;
	@Column(columnDefinition = "varchar(50)")
	@NotBlank(message = "field 'firstName' is mandatory")
	@Size(min = 1, max = 25, message = "firstName debe ser menor a 25 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "firstName debe ser un nombre.")
	private String firstName;
	private String lastName;
	@Column(name = "email_address", nullable = false)
	@NotBlank(message = "field 'eMailId' is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "email no valido.")
	private String eMailId;

	@Embedded
	private Guardian guardian;

//	El Set<> es un array que no puede tener valores duplicados
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Course> courses;
}
