module com.example.isstracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires java.naming;
    requires java.sql;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;

    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires java.persistence;

    opens com.example.isstracker.controller to javafx.fxml, org.hibernate.orm.core, com.fasterxml.jackson.databind;
    opens com.example.isstracker.model.issnow to org.hibernate.orm.core, com.fasterxml.jackson.databind;
    opens com.example.isstracker.model.astros to org.hibernate.orm.core, com.fasterxml.jackson.databind;

    exports com.example.isstracker;
    exports com.example.isstracker.model.astros to com.fasterxml.jackson.databind;
    exports com.example.isstracker.model.issnow to com.fasterxml.jackson.databind;
}