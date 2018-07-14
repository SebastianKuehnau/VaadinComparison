package org.vaadin.datamodelling.v10;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.RegexpValidator;
import org.vaadin.datamodelling.common.Person;
import org.vaadin.datamodelling.common.PhoneNumberRegex;

public class PersonForm extends FormLayout {

	final private Binder<Person> personBinder = new Binder<>(Person.class);

	final private TextField nameField = new TextField("Name") ;
	final private TextField firstNameField = new TextField("Firstname") ;
	@PropertyId("phone")
	final private TextField phoneField = new TextField("Phone");

	final private Button saveButton = new Button("save", this::saveButtonClicked);
	final private Button cancelButton = new Button("cancel", this::cancelButtonClicked);

	private Person currentPerson = null;

	private SaveButtonClickListener saveButtonClickListener ;

	public PersonForm(SaveButtonClickListener saveButtonClickListener) {
		this.saveButtonClickListener = saveButtonClickListener;

		personBinder.forField(nameField)
				.asRequired()
				.bind("name");

		personBinder.forField(firstNameField)
			.asRequired()
			.bind(Person::getFirstName, Person::setFirstName);

		personBinder.forField(phoneField)
				.asRequired()
				.withValidator(new RegexpValidator("Invalid Number", PhoneNumberRegex.REGEX_STRING))
				.bind(Person::getPhone, Person::setPhone);

		add(nameField, firstNameField, phoneField,
				new HorizontalLayout(cancelButton, saveButton));
	}

	public void setPerson(Person person) {
		this.currentPerson = person ;

		personBinder.readBean(currentPerson);
		this.setEnabled(currentPerson != null);
	}

	private void saveButtonClicked(ClickEvent event) {
		personBinder.writeBeanIfValid(currentPerson);
		saveButtonClickListener.saveButtonClicked(currentPerson) ;
	}

	private void cancelButtonClicked(ClickEvent event) {
		personBinder.readBean(currentPerson);
	}

	public interface SaveButtonClickListener {
		void saveButtonClicked(Person currentPerson) ;
	}
}
