package controller;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController{
	 @FXML
	    private Pane paneMapa;
	
	 @FXML
	    void btnInterna(ActionEvent event) throws IOException {
		 AnchorPane fxmlInternacao = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaInternacao.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	    }
	 
	 @FXML
	 void btnGerenciaAnimal(ActionEvent event) throws IOException {
		 AnchorPane fxmlInternacao = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/view_GerenciamentoAnimal.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	 }

	 @FXML
	 void btnGerenciaFuncionario(ActionEvent event) throws IOException {
		 VBox fxmlInternacao = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	 }
	 
	 @FXML
	 void btnGerenciaFornecedor(ActionEvent event) throws IOException {
		 VBox fxmlInternacao = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Fornecedor.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	 }
	 
	 @FXML
	    void btnMapa(ActionEvent event) {
		 	paneMapa.setVisible(true);
	    }
	 
	 @FXML
	    void btnVolta(ActionEvent event) {
		 paneMapa.setVisible(false);
	    }
	 
	 @FXML
	    void btnSair(ActionEvent event) throws IOException {
		 BorderPane fxmlInternacao = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_Login.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	    }
	 
	 @FXML
	    void btnGerenciaVendas(ActionEvent event) throws IOException {
		 AnchorPane fxmlInternacao = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoVendas.fxml"));
         Scene Interna = new Scene(fxmlInternacao);
         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
         inter.setScene(Interna);
	    }
	 
}
