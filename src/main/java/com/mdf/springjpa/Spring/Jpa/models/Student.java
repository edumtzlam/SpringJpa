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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tbl_students", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_address"))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	@Column(name = "student_id")
	@EqualsAndHashCode.Include
	private Long studentId;

	@Column(columnDefinition = "varchar(50)")
	@NotBlank(message = "field 'firstName' is mandatory")
	@Size(min = 1, max = 25, message = "firstName debe ser menor a 25 caracteres.")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "firstName debe ser un nombre.")
	private String firstName;

	private String lastName;
	
	@Column(name = "email_address", nullable = false)
	@NotBlank(message = "field 'emailId' is mandatory")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "email no valido.")
	private String emailId;
	
	@NotBlank(message = "field 'password' is mandatory")
	private String password;
	
	@NotBlank(message = "field 'role' is mandatory")
	private String role;

	@Embedded
	private Guardian guardian;

//	El Set<> es un array que no puede tener valores duplicados
//	Con todas esta instrucciones se crea automaticamente la relacion con la tabla cursos
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<Course> courses;

	@ManyToMany
	@JoinTable(name = "student_authorities_table", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "student_id") }, inverseJoinColumns = {
					@JoinColumn(name = "authorities_id", referencedColumnName = "authorities_id") })
	@ToString.Exclude
	private Set<Authority> authorities;
}
