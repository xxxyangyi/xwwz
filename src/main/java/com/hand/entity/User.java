package com.hand.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity(name="user")
public class User implements Serializable {
	@Id
	@GeneratedValue
	@Column(name="id")
	@Expose
	private int id;

	@Column(name="accountName")
	@Expose
	private String accountName;

	@Column(name="nickName")
	@Expose
	private String nickName;

	@Column(name="realName")
	@Expose
	private String realName;

	@Column(name="age")
	@Expose
	private int age;

	@Column(name="sex")
	@Expose
	private Integer sex; // 0 ： 女的  1：男的

	@Column(name="IDcard")// 身份证
	@Expose
	private String IDcard;

	@Column(name="password")
	@Expose
	private String password;

	//  1 会员  2 记者  3 管理员
	@Column(name="identity")
	@Expose
	private Integer identity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIDcard() {
		return IDcard;
	}

	public void setIDcard(String IDcard) {
		this.IDcard = IDcard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", accountName='" + accountName + '\'' +
				", nickName='" + nickName + '\'' +
				", realName='" + realName + '\'' +
				", age=" + age +
				", sex=" + sex +
				", IDcard='" + IDcard + '\'' +
				", password='" + password + '\'' +
				", identity=" + identity +
				'}';
	}
}
