module edu.syslocacar {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires org.hibernate.orm.core;
    //requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;
    //requires java.sql; // Adicione esta linha
    // requires org.hibernate.orm.core; // Módulo do Hibernate
    //requires java.naming; // Adicione essa linha
    //requires org.hibernate.orm.core;
    //requires jakarta.persistence;

    opens edu.syslocacar.model.entity;
    exports edu.syslocacar.model.entity;
    opens edu.syslocacar.controller;
    exports edu.syslocacar;
    exports edu.syslocacar.model.services;

    exports edu.syslocacar.controller to javafx.fxml;
}