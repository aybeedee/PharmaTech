/*module PharmaTechJavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens pharmacy to javafx.graphics, javafx.fxml;
}*/

open module PharmaTechJavaFX {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;

	exports businessLogic;
}
