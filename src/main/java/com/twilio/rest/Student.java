package com.twilio.rest;

public class Student {

	private int id;
	private String lang;
	private String confirmation;

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	private String query;
	private String sessionId;
	private String timezone;
	private String firstName;
	private String lastName;
	private int age;

	// Must have no-argument constructor
	public Student() {

	}

	public Student(String fname, String lname, int age, int id, String query, String sessionId, String timezone,String confirmation) {
		this.firstName = fname;
		this.lastName = lname;
		this.age = age;
		this.id = id;
		this.query = query;
		this.sessionId = sessionId;
		this.confirmation = confirmation;
		this.timezone = timezone;

	}

	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lname) {
		this.lastName = lname;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return new StringBuffer(" confirmation : ").append(this.confirmation).append(this.id).toString();
	}
	



}