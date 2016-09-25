package com.hand.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity(name="category")
public class Category implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	@Expose
	private int id;
	
	@Expose
	@Column(name="categoryName")
	private String categoryName;

	@ManyToMany(mappedBy="category",cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Set<News> news = new HashSet<>();


	public Category(){}

	public Category(int id){
		this.id = id;
	}

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

	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}
}
