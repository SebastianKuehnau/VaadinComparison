package org.vaadin.datamodelling.common;

public class Person {

	private String name;
	private String firstName ;
	private String phone ;

	public Person(String name, String firstName, String phone) {
		this.name = name;
		this.firstName = firstName;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
