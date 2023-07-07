module project.itss.group8.itss {
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
    requires poi;
    requires poi.ooxml;
    requires poi.ooxml.schemas;
//    requires org.junit.jupiter;

    opens project.itss.group8.itss to javafx.fxml;
    exports project.itss.group8.itss;
    exports project.itss.group8.itss.controller;
    opens project.itss.group8.itss.controller to javafx.fxml;
    opens project.itss.group8.itss.model to javafx.base;
    
    exports project.itss.group8.itss.view;
	opens project.itss.group8.itss.view to javafx.graphics, javafx.fxml, javafx.base;
    exports project.itss.group8.itss.subsystem.Impl;
    exports project.itss.group8.itss.subsystem;
    exports project.itss.group8.itss.helper.Impl;
    exports project.itss.group8.itss.utils;
    exports project.itss.group8.itss.helper;


}