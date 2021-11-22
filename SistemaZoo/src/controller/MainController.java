package controller;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController{
	 @FXML
	    void btnInterna(ActionEvent event) throws IOException {
		 AnchorPane fxmlInternacao = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaInternacao.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	    }
	 
	 @FXML
	 void btnGerenciaAnimal(ActionEvent event) throws IOException {
		 BorderPane fxmlInternacao = (BorderPane) FXMLLoader.load(getClass().getResource("/view/view_Animal.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	 }

}
