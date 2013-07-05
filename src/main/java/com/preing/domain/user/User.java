package com.preing.domain.user;

public class User {

	private String code;
	private String name;
	private String pwd;
	private String mobile;
	private String email;
	private Gender gender;

	public User(String code, String name, String pwd, String mobile, String email, Gender gender) {
		this.code = code;
		this.name = name;
		this.pwd = pwd;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getPwd() {
		return pwd;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public Gender getGender() {
		return gender;
	}

	public enum Gender {
		MAN("01"), WOMEN("02");
		private String code;

		private Gender(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}
}
