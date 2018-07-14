package org.vaadin.datamodelling.v7;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Grid;
import org.vaadin.datamodelling.common.Person;
import org.vaadin.datamodelling.common.PersonService;

public class PersonGrid extends Grid {

	private final PersonService personService;

	public PersonGrid(PersonService personService) {
		this.personService = personService;

		setSelectionMode(SelectionMode.SINGLE);

		BeanItemContainer<Person> personContainer = new BeanItemContainer<>(Person.class) ;
		personContainer.addAll(personService.getPersonList());

		setContainerDataSource(personContainer);
	}
}
