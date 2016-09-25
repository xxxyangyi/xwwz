package com.hand.entity;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name="news")
public class News implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@Expose
	private int id;

	@Column(name="title")
	@Expose
	private String title;

	@Column(name="context")
	@Expose
	private String context;

	@Column(name="createTime")
	@Expose
	private Date createTime;

	//  0 代表没有审核   1代表通过审核 2代表没有通过审核
	@Column(name="reviewed")
	@Expose
	private Integer reviewed;

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",referencedColumnName ="id",updatable=false)
	@Expose
	private User user_id ;

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name="REL_NEWS_CATEGORY",joinColumns={@JoinColumn(name="news_id")},inverseJoinColumns={@JoinColumn(name="category_id")})
	@Expose
	private Set<Category> category = new HashSet<Category>();

	public News(){}

	public News(int id){
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getReviewed() {
		return reviewed;
	}

	public void setReviewed(Integer reviewed) {
		this.reviewed = reviewed;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}
}
