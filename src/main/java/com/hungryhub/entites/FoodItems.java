package com.hungryhub.entites;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "FOOD_ITEMS_TABLE")
public class FoodItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Item_id;
	@Column(nullable = false)
	private String Item_name;
	@Column(nullable = false)
	private int Item_price;
	@Lob
	@Column(nullable = false)
	private Blob Item_photo;
	@Column(nullable = false)
	private String Category;
	@Column(nullable = false)
	private String Description;
	@Column(nullable = false)
	private String Status;
	@Column(nullable = false)
	private String Type;

	public FoodItems() {
		super();
	}

	public FoodItems(int Item_id, String Item_name, int Item_price, Blob Item_photo, String Category,
			String Description) {
		this.Item_id = Item_id;
		this.Item_name = Item_name;
		this.Item_price = Item_price;
		this.Item_photo = Item_photo;
		this.Category = Category;
		this.Description = Description;
	}

	public int getItem_id() {
		return this.Item_id;
	}

	public void setItem_id(int Item_id) {
		this.Item_id = Item_id;
	}

	public String getItem_name() {
		return this.Item_name;
	}

	public void setItem_name(String Item_name) {
		this.Item_name = Item_name;
	}

	public int getItem_price() {
		return this.Item_price;
	}

	public void setItem_price(int Item_price) {
		this.Item_price = Item_price;
	}

	public Blob getItem_photo() {
		return this.Item_photo;
	}

	public void setItem_photo(Blob Item_photo) {
		this.Item_photo = Item_photo;
	}

	public String getCategory() {
		return this.Category;
	}

	public void setCategory(String Category) {
		this.Category = Category;
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

	public String getType() {
		return this.Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public FoodItems get() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'get'");
	}

}