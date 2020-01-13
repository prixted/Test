package com.kh.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
	/**
	 * 
	 */
	/*
	  VO(Value Object)
	  
	  DTO(Date Transfer Ojbect)
	  DO(Domain Object)
	  Entity --> Strut에서는 이 용어로 사용 
	  bean(--> EJB)
	  
	  VO조건
	  1) 반드시 캡슐화를 적용할 것 : 모든 필드는 private
	  2) 기본 생성자와 매개변수 생성자 작성
	  3) 모든 필드에 대한 Getter&Setter 필요
	  4) Serializable(직렬화) 처리 필수 (Network상의 데이터 처리를 위함)
	*/

	// 필드는  DB컬럼 정보와 동일하게 작업
	
	private static final long serialVersionUID = 8978922866488324624L;
	
	private String userId;
	private String password;
	private String userName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate; // 이제는 java.sql.Date() 사용
	
	public Member() {}

	public Member(String userId, String password, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		// 회원가입 시에는 오늘 날짜를 굳이 기입하지 않아도 DB에서 sysdate로 들어간다.
	}

	public Member(String userId, String password, String userName, String gender, int age, String email, String phone,
			String address, String hobby, Date enrollDate) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return userId + ", " + password + ", " + userName + ", " + gender
				+ ", " + age + ", " + email + ", " + phone + ", " + address + ", " + hobby
				+ ", " + enrollDate;
	}
	
	

}
