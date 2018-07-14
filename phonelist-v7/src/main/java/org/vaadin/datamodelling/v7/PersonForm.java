package org.vaadin.datamodelling.v7;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import org.vaadin.datamodelling.common.PhoneNumberRegex;

public class PersonForm extends FormLayout {

	@PropertyId("name")
	private final TextField nameField = new TextField("Name");

	@PropertyId("firstName")
	private final TextField firstNameField = new TextField("Firstname");

	@PropertyId("phone")
	private final TextField phoneField = new TextField("Phone");

	private final FieldGroup binder = new FieldGroup();

	private final Button saveButton = new Button("save", event -> save(event));
	private final Button cancelButton = new Button("cancel", event -> cancel(event));
	private final HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, saveButton);

	private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

	public PersonForm() {

		setEnabled(false);

		buttonLayout.setMargin(true);
		buttonLayout.setSpacing(true);

		nameField.setRequired(true);
		firstNameField.setRequired(true);
		phoneField.setRequired(true);

		phoneField.addValidator(new RegexpValidator(PhoneNumberRegex.REGEX_STRING, "Invalid Format"));

		addComponents(nameField, firstNameField, phoneField, buttonLayout);

		binder.setBuffered(true);
	}

	public void setPerson(Item item) {

		this.setEnabled(true);

		binder.setItemDataSource(item);
		binder.bindMemberFields(this);
	}

	private void save(Button.ClickEvent event) {
		try {
			binder.commit();
		} catch (FieldGroup.CommitException e) {
			e.printStackTrace();
		}

		binder.clear();
		this.setEnabled(false);
	}

	private void cancel(Button.ClickEvent event) {
		binder.discard();
	}

	public void addSaveButtonClickListener(Button.ClickListener clickListener) {
		saveButton.addClickListener(clickListener);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		if (!enabled) binder.clear();
	}
}
