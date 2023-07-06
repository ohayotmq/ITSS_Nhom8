module project.itss.group11.itss {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
//  requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.apache.logging.log4j;
	requires opencsv;
	requires javafx.base;


    opens project.itss.group11.itss to javafx.fxml;
    exports project.itss.group11.itss;
    exports project.itss.group11.itss.controller;
    opens project.itss.group11.itss.controller to javafx.fxml;
    opens project.itss.group11.itss.model to javafx.base;
    
    exports project.itss.group11.itss.view;
    //opens project.itss.group11.itss.view to javafx.fxml;
	opens project.itss.group11.itss.view to javafx.graphics, javafx.fxml, javafx.base;
    exports project.itss.group11.itss.repository.Impl;
    exports project.itss.group11.itss.repository;
    exports  project.itss.group11.itss.service.Impl;
    exports project.itss.group11.itss.Until;
    exports  project.itss.group11.itss.service;


}