module com.example.pacman {
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
    requires java.sql;

    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
    exports com.example.pacman.scene;
    opens com.example.pacman.scene to javafx.fxml;
    exports com.example.pacman.ui;
    opens com.example.pacman.ui to javafx.fxml;
    exports com.example.pacman.handler;
    opens com.example.pacman.handler to javafx.fxml;
}