package org.vaadin.datamodelling.v8;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.datamodelling.common.Person;
import org.vaadin.datamodelling.common.PersonService;

public class ListView extends HorizontalLayout implements View {

	public static final String VIEW_NAME = "";

	private final PersonService personService = new PersonService() ;

	private final Grid<Person> personGrid = new Grid<>(Person.class);
	private final PersonForm personForm = new PersonForm(person -> personGrid.getDataProvider().refreshItem(person)) ;

	public ListView() {
		personGrid.setItems(personService.getPersonList());

		personGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
		personGrid.addSelectionListener(this::selectionEvent);

		addComponents(personGrid, personForm);
	}

	private void selectionEvent(SelectionEvent<Person> personSelectionEvent) {
		personForm.setEnabled(personSelectionEvent.getFirstSelectedItem().isPresent());
		personForm.setCurrentPerson(personSelectionEvent.getFirstSelectedItem().orElse(null));
	}
}
