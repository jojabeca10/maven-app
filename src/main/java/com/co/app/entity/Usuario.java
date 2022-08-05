package com.co.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_generator")
	@SequenceGenerator(name="usr_generator", sequenceName = "usr_seq", allocationSize=50)
	private Long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;
	
	@Column(name = "created")
	private Date created;
	
	@Column(name = "modified")
	private Date modified;
	
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Column(name = "token", length =700)
	private String token;
	
	@Column(name = "isactive")
	private Integer isactive = 1;
	
	@Transient
	private List<Telefono> phones = new ArrayList<>();

	public Usuario() {
		super();
	}

}
