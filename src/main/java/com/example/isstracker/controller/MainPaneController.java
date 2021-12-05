package com.example.isstracker.controller;

import com.example.isstracker.model.astros.Person;
import com.example.isstracker.model.issnow.Velocity;
import com.example.isstracker.service.iss.IssLocationService;
import com.example.isstracker.service.iss.IssLocationServiceApi;
import com.example.isstracker.service.iss.IssVelocityService;
import com.example.isstracker.service.iss.IssVelocityServiceApi;
import com.example.isstracker.service.people.PeopleInSpaceService;
import com.example.isstracker.service.people.PeopleInSpaceServiceApi;
import com.example.isstracker.util.HibernateUtil;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.hibernate.SessionFactory;

public class MainPaneController {
    final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    final IssVelocityService velocityCalculator = new IssVelocityServiceApi(sessionFactory);
    final IssLocationService issLocationService = new IssLocationServiceApi(sessionFactory);
    final PeopleInSpaceService peopleInSpaceService = new PeopleInSpaceServiceApi(sessionFactory);
    @FXML
    private Label latitudeTextField;

    @FXML
    private Label longitudeTextField;

    @FXML
    private Button peopleInSpaceResetButton;

    @FXML
    private ListView<Person> peopleList;

    @FXML
    private Button peopleOnIssButton;

    @FXML
    private Label timestampTextField;

    @FXML
    private Label velocityTextField;

    @FXML
    private Button velocityButton;

    public void initialize() {
        String labelText = latitudeTextField.getText();
        System.out.println(labelText);
        latitudeTextField.setText(String.valueOf(issLocationService.getLatitude()));
        longitudeTextField.setText(String.valueOf(issLocationService.getLongitude()));
        timestampTextField.setText(issLocationService.getTimestamp());
        for (Person person : peopleInSpaceService.peopleInSpace()) {
            peopleList.getItems().add(person);
        }
        peopleOnIssButton.setOnAction(e -> {
            peopleList.getItems().clear();
            for (Person person : peopleInSpaceService.peopleOnIss()) {
                peopleList.getItems().add(person);
            }
        });
        peopleInSpaceResetButton.setOnAction(e -> {
            peopleList.getItems().clear();
            for (Person person : peopleInSpaceService.peopleInSpace()) {
                peopleList.getItems().add(person);
            }
        });

        velocityButton.setOnAction(e -> {
            Velocity velocity = velocityCalculator.getVelocity(5000);
            velocityTextField.setText(String.valueOf(velocity.getValue()));
        });
    }

}
