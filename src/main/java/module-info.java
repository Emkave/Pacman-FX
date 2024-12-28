module com.emkave.pacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires jdk.compiler;
    requires com.fasterxml.jackson.databind;
    requires java.management;
    requires java.sql;

    opens com.emkave.pacman to javafx.fxml;
    exports com.emkave.pacman;
    exports com.emkave.pacman.scene;
    opens com.emkave.pacman.scene to javafx.fxml;
    exports com.emkave.pacman.ui;
    opens com.emkave.pacman.ui to javafx.fxml;
    exports com.emkave.pacman.handler;
    opens com.emkave.pacman.handler to javafx.fxml;
}