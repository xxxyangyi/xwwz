package com.hand.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity(name="user")
public class User implements Serializable {
	@Id
	@Column(name="mail")
	@Expose
	private String mail;
	
	@Column(name="password")
	@Expose
	private String password;
	
	@Column(name="name")
	@Expose
	private String name;
	
	@Column(name="isused")
	@Expose
	private Integer isUsed;

	//  0 管理员  1 专家   2 用户
	@Column(name="identity")
	@Expose
	private Integer identity;
	
	@Column(name="sex")
	@Expose
	private Integer sex;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

}
