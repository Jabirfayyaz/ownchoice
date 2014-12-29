/**
 * 
 */
package com.buyer.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Jay
 *
 */
@Entity
@javax.persistence.Table(name="REF_USER_TYPE")
public class UserType {
	
	private static String ADMIN_USER_TYPE_ID = "1";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ref_user_type_id")
	@JsonIgnore
	private int id;	
	
	private String name;
	
	@JsonIgnore
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
