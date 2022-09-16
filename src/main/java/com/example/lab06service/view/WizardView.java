package com.example.lab06service.view;

import com.example.lab06service.pojo.Wizard;
import com.example.lab06service.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Route("mainPage.it")
public class WizardView extends VerticalLayout {
    private final Wizards wizards;
    public int itemIndex = 0;
    public TextField name;
    public NumberField dollars;
    public ComboBox<String> position, school, house;
    public RadioButtonGroup<String> gender;
    public Button prev, create, update, del, next;

    public WizardView(){
        this.wizards = new Wizards();

        VerticalLayout container = new VerticalLayout();
        HorizontalLayout btn = new HorizontalLayout();
        name = new TextField();
        name.setPlaceholder("Full name");

        Div dollarPrefix = new Div();
        dollarPrefix.setText("$");
        dollars = new NumberField();
        dollars.setLabel("Dollars");
        dollars.setPrefixComponent(dollarPrefix);
        dollars.setWidthFull();

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
        house.setPlaceholder("House");

        prev = new Button("<<<");
        next = new Button(">>>");

        prev.addClickListener(e -> {
            itemIndex = Math.max(itemIndex - 1, 0);
            fetch();
        });

        next.addClickListener(e -> {
           itemIndex = Math.min(itemIndex + 1, wizards.getWiz().size() - 1);
           fetch();
        });

        create = new Button("Create");
        update = new Button("Update");
        del = new Button("Delete");

        create.addClickListener(e -> action("add"));
        update.addClickListener(e -> action("update"));
        del.addClickListener(e -> {
            WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard")
                    .body(Mono.just(wizards.getWiz().get(itemIndex)), Wizard.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            fetch();
        });

        btn.add(prev, create, update, del, next);
        container.add(name, gender, position, dollars, school, house, btn);
        container.setWidth("auto");
        container.setJustifyContentMode(JustifyContentMode.CENTER);
        container.setAlignItems(Alignment.STRETCH);
        add(container);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        fetch();
    }

    public void action(String act){
        String id = wizards.getWiz().get(itemIndex).get_id();
        String sex = gender.getValue().equals("Male") ? "m" : "f";
        String name = this.name.getValue();
        String school = this.school.getValue();
        String house = this.house.getValue() == null ? "" : this.house.getValue();
        int money = dollars.getValue().intValue();
        String position = this.position.getValue().equals("Student") ? "student" : "teacher";

        Wizard wiz = new Wizard(act.equals("update") ? id : null, sex, name, school, house, money, position);

        WebClient
                .create()
                .post()
                .uri("http://localhost:8080/" + act + "Wizard")
                .body(Mono.just(wiz), Wizard.class)
                .retrieve()
                .bodyToMono(Wizard.class)
                .block();

        fetch();
    }

    public void fetch(){
        List<Wizard> wiz = WebClient
                .create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Wizard>>() {})
                .block();

        wizards.setWiz(wiz);

        prev.setEnabled(!(itemIndex == 0));
        next.setEnabled(!(itemIndex == wizards.getWiz().size() - 1));
        name.setValue(wizards.getWiz().get(itemIndex).getName());
        gender.setValue(wizards.getWiz().get(itemIndex).getSex().equals("m") ? "Male" : "Female");
        position.setValue(wizards.getWiz().get(itemIndex).getPosition().equals("student") ? "Student" : "Teacher");
        dollars.setValue(Double.parseDouble(wizards.getWiz().get(itemIndex).getMoney()+""));
        school.setValue(wizards.getWiz().get(itemIndex).getSchool());
        house.setValue(wizards.getWiz().get(itemIndex).getHouse());
    }
}
