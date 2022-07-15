package com.JdbcTemplate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table
@Entity
public class Novel {

	@Id
	private int id;
	private String title;
	private String description;
	private boolean published;
	
	public Novel(String title, String description, boolean published) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.published = published;
	}

	public Novel() {
		// TODO Auto-generated constructor stub
	}

	
}
