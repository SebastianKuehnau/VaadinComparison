package org.vaadin.datamodelling.v10;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import org.vaadin.datamodelling.common.Person;
import org.vaadin.datamodelling.common.PersonService;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("")
public class MainView extends HorizontalLayout {

    private final PersonService personService = new PersonService();

    private final Grid<Person> personGrid = new Grid<>(Person.class) ;
    private final PersonForm personForm = new PersonForm(person -> personGrid.getDataProvider().refreshItem(person));

    public MainView() {

        personGrid.setItems(personService.getPersonList());
        personGrid.addSelectionListener(event -> {
            personForm.setPerson(event.getFirstSelectedItem().orElse(null));
        });

        add(personGrid, personForm);

        setMargin(true);
        setSpacing(true);
    }
}
