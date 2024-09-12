package com.hungryhub.entites;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Category_id;
	private String Category_name;
	private String slug;
	private String Description;
	private String Status;
	private LocalDateTime AddOn;
	private LocalDateTime UpdatedOn;

	public Category() {
		super();
	}

	public Category(int Category_id, String Category_name, String slug, String Description, String Status,
			LocalDateTime AddOn, LocalDateTime UpdatedOn) {
		this.Category_id = Category_id;
		this.Category_name = Category_name;
		this.slug = slug;
		this.Description = Description;
		this.Status = Status;
		this.AddOn = AddOn;
		this.UpdatedOn = UpdatedOn;
	}

	public int getCategory_id() {
		return this.Category_id;
	}

	public void setCategory_id(int Category_id) {
		this.Category_id = Category_id;
	}

	public String getCategory_name() {
		return this.Category_name;
	}

	public void setCategory_name(String Category_name) {
		this.Category_name = Category_name;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public String getStatus() {
		return this.Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public LocalDateTime getAddOn() {
		return this.AddOn;
	}

	public void setAddOn(LocalDateTime AddOn) {
		this.AddOn = AddOn;
	}

	public LocalDateTime getUpdatedOn() {
		return this.UpdatedOn;
	}

	public void setUpdatedOn(LocalDateTime UpdatedOn) {
		this.UpdatedOn = UpdatedOn;
	}

}
