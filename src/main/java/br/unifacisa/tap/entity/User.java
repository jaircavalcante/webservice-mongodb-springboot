package br.unifacisa.tap.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
/**
 * 
 * @author jaircavalcante
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;
	
	private String profession;
	
	public User() {
	}

	public User(String id, String name, String profession) {
		this.id = id;
		this.name = name;
		this.profession = profession;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfession() {
		return profession;
	}

	public void setAddress(String profession) {
		this.profession = profession;
	}

}
