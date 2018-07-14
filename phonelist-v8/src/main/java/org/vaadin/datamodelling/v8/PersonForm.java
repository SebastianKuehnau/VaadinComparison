package org.vaadin.datamodelling.v8;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.vaadin.datamodelling.common.Person;
import org.vaadin.datamodelling.common.PhoneNumberRegex;

public class PersonForm extends FormLayout {

	private final TextField nameField = new TextField() ;

	private final TextField firstNameField = new TextField() ;

	@PropertyId("phone")
	private final TextField phoneField = new TextField() ;

	final private Button saveButton = new Button("save", this::saveButtonClicked) ;
	final private Button cancelButton = new Button("cancel", this::cancelButtonClicked);

	final private HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, saveButton);

	private final Binder<Person> binder = new Binder<>(Person.class);

	private SaveButtonClickListener saveButtonClickListener ;


	private Person currentPerson;

	public PersonForm(SaveButtonClickListener saveButtonClickListener) {
		this.saveButtonClickListener = saveButtonClickListener;

		this.setEnabled(false);

		binder.forField(firstNameField)
				.asRequired()
				.bind(Person::getFirstName, Person::setFirstName);

		binder.forField(nameField)
				.asRequired()
				.bind("name");

		binder.forField(phoneField)
				.withValidator(new RegexpValidator("Invalid Number", PhoneNumberRegex.REGEX_STRING))
				.asRequired()
				.bind("phone");

		buttonLayout.setSpacing(true);
		buttonLayout.setMargin(true);

		addComponents(
				nameField,
				firstNameField,
				phoneField,
				buttonLayout);
	}

	public void setCurrentPerson(Person currentPerson) {
		this.currentPerson = currentPerson;

		binder.readBean(currentPerson);
		this.setEnabled(currentPerson != null);
	}

	private void saveButtonClicked(Button.ClickEvent event) {
		binder.writeBeanIfValid(currentPerson);
		saveButtonClickListener.saveButtonClicked(currentPerson);
	}

	private void cancelButtonClicked(Button.ClickEvent event) {
		binder.readBean(currentPerson);
	}

	public interface SaveButtonClickListener {
		void saveButtonClicked(Person person) ;
	}
}
