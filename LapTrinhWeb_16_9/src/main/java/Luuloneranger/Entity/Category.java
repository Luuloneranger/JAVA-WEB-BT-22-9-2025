package Luuloneranger.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.NamedQuery;

import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@AllArgsConstructor

@NoArgsConstructor

@Data

@Entity

@Table(name = "categories")

@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	@Column(name = "categoryName", columnDefinition = "NVARCHAR(255)")

	private String categoryName;

	@Column(columnDefinition = "NVARCHAR(MAX)")

	private String images;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Category(int id, String categoryName, String images) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.images = images;
	}

	public Category() {
		super();
	}
	
	

}
