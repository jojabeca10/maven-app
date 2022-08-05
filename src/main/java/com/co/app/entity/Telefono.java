package com.co.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Telefono {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telp_generator")
	@SequenceGenerator(name="telp_generator", sequenceName = "telp_seq", allocationSize=50)
	private Long id;
	
	@Column(name = "telepNumber")
    private String number;
	
	@Column(name = "citycode")
    private String citycode;
	
	@Column(name = "contrycode")
    private String contrycode;
	
	@Column(name = "id_usr")
    private Long idUsr;

	public Telefono() {
		super();
		// TODO Auto-generated constructor stub
	}

}
