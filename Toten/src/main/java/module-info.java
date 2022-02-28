module org.Toten {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;

    opens org.Toten to javafx.fxml;
    exports org.Toten;
    
    opens org.Toten.controller to javafx.fxml;
    exports org.Toten.controller;
}
