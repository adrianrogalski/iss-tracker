package com.example.isstracker.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainPaneController {

    @FXML
    private Label latitudeTextField;

    @FXML
    private Label longitudeTextField;

    @FXML
    private Button peopleInSpaceResetButton;

    @FXML
    private ListView<?> peopleList;

    @FXML
    private Button peopleOnIssButton;

    @FXML
    private Label timestampTextField;

    @FXML
    private Label velocityTextField;

}
