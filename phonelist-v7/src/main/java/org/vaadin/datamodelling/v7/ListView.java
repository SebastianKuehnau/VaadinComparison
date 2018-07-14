package org.vaadin.datamodelling.v7;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.datamodelling.common.PersonService;

public class ListView extends HorizontalLayout implements View {

	public static final String VIEW_NAME = "" ;

	private final PersonService personService = new PersonService() ;

	private final PersonGrid personGrid = new PersonGrid(personService);
	private final PersonForm personForm = new PersonForm();

	public ListView() {

		personGrid.setSizeFull();
		personGrid.addSelectionListener(event -> personForm.setEnabled(!event.getSelected().isEmpty()));
		personGrid.addItemClickListener(event -> personForm.setPerson(event.getItem()));

		personForm.addSaveButtonClickListener(event -> personGrid.select(null)) ;

		addComponents(personGrid, personForm);

		setSpacing(true);
		setMargin(true);
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {

	}
}
