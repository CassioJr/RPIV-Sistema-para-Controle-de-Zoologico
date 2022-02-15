module core {
	requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires org.postgresql.jdbc;

	
    opens core.application to javafx.fxml;
    exports core.application;
    opens core.controller to javafx.fxml;
    exports core.controller;
    opens core.model to javafx.base;
}
