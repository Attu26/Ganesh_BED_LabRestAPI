package com.greatlearning.student.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "users")
@Data

public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;

	@ManyToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private List<Role> roles = new ArrayList<>();

	//@OneToMany(mappedBy = "user",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private Set<Role> roles;

	//public void addRole(Role role) {
	//if(this.roles==null) {
	//this.roles = new HashSet<>();

	//this.roles.add(role);
	//role.setUser(this);



}





