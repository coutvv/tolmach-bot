package ru.coutvv.tolmach.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
	@NamedQuery(name = "User.getByUsername", query = "select g from User g where g.username = :username"),
	@NamedQuery(name = "User.getByName", query = "select g from User g where g.name = :name"),
	@NamedQuery(name = "User.getByFullName", query = "select g from User g where g.name = :name and g.lastname = :lastname")
})
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USER", columnDefinition = "serial")
	private Long idUser;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LASTNAME")
	private String lastname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
