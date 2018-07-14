package org.vaadin.datamodelling.common;

import java.util.Collection;
import java.util.HashSet;

public class PersonService {

	private static final Collection<Person> dataList = new HashSet<>();

	static {
		dataList.add(new Person("Simpson", "Homer", "+1-342-13343-2232")) ;
		dataList.add(new Person("Hood", "Robin", "+43-231-226-876")) ;
		dataList.add(new Person("Babba", "Ali", "+98-2345-234-947")) ;
		dataList.add(new Person("Time", "Justin", "+49-143-1233-4562")) ;
	}

	public Collection<Person> getPersonList() {
		return dataList ;
	}
}
