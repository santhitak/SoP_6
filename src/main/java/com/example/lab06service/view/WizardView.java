package com.example.lab06service.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("mainPage.it")
public class WizardView extends VerticalLayout {
    TextField name, dollars;
    ComboBox<String> position, school, house;
    RadioButtonGroup<String> gender;
    Button prev, create, update, del, next;

    public WizardView(){
        VerticalLayout container = new VerticalLayout();
        HorizontalLayout btn = new HorizontalLayout();
        name = new TextField();
        name.setPlaceholder("Full name");

        dollars = new TextField();
        dollars.setLabel("Dollars");

        gender = new RadioButtonGroup<>();
        gender.setLabel("Gender");
        gender.setItems("Male", "Female");

        position = new ComboBox<>();
        position.setItems("Student", "Teacher");
        position.setPlaceholder("Position");

        school = new ComboBox<>();
        school.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        school.setPlaceholder("School");

        house = new ComboBox<>();
        house.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin");
        house.setPlaceholder("School");

        prev = new Button("<<<");
        create = new Button("Create");
        update = new Button("Update");
        del = new Button("Delete");
        next = new Button(">>>");

        btn.add(prev, create, update, del, next);
        container.add(name, gender, position, dollars, school, house, btn);
        container.setWidth("auto");
        container.setJustifyContentMode(JustifyContentMode.CENTER);
        container.setAlignItems(Alignment.CENTER);
        add(container);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
