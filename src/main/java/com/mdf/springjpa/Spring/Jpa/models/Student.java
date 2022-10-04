package com.mdf.springjpa.Spring.Jpa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "tbl_students", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_address"))
public class Student {
	@Id
	private Long studentId;
	@Column(columnDefinition = "varchar(50)")
	private String firstName;
	private String lastName;
	@Column(name = "email_address", nullable = false)
	private String eMailId;
	private String guardianEmail;
	private String guardianMobile;
}
