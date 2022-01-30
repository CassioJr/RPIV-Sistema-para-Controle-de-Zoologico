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
import javafx.stage.Stage;

public class GerenciamentoVendasController {
	
	    @FXML
	    private Pane paneMapa;
	    @FXML
	    void btnMapa(ActionEvent event) {
	    	paneMapa.setVisible(true);
	    }

	    @FXML
	    void btnSair(ActionEvent event) throws IOException {
	    	BorderPane fxmlInternacao = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_Login.fxml"));
	         Scene Interna = new Scene(fxmlInternacao);
	         Stage inter = (Stage) ((Node) event.getSource()).getScene().getWindow();
	         inter.setScene(Interna);
		    }
	    

	    @FXML
	    void btnVendaIngresso(ActionEvent event) throws IOException {
	    	AnchorPane fxmlGerenciamentoIngresso = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoIngressos.fxml"));
	         Scene Ingresso = new Scene(fxmlGerenciamentoIngresso);
	         Stage ingresso = (Stage) ((Node) event.getSource()).getScene().getWindow();
	         ingresso.setScene(Ingresso);

	    }

	    @FXML
	    void btnVendaLanche(ActionEvent event) throws IOException {
	    	AnchorPane fxmlGerenciamentoLanchonete = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoLanchonete.fxml"));
	         Scene Lanchonete = new Scene(fxmlGerenciamentoLanchonete);
	         Stage lanchonete = (Stage) ((Node) event.getSource()).getScene().getWindow();
	         lanchonete.setScene(Lanchonete);

	    }

	    @FXML
	    void btnVendaLembranca(ActionEvent event) throws IOException {
	    	AnchorPane fxmlGegrenciamentoLojaLembranca = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoLojaLembraca.fxml"));
	         Scene LojaLembraca = new Scene(fxmlGegrenciamentoLojaLembranca);
	         Stage lojalembranca = (Stage) ((Node) event.getSource()).getScene().getWindow();
	         lojalembranca.setScene(LojaLembraca);

	    }

	    @FXML
	    void btnVolta(ActionEvent event) {
	    	paneMapa.setVisible(false);
	    }
	    
	    @FXML
	    void btnVoltar(ActionEvent event) throws IOException {
	    	AnchorPane fxmlApp = (AnchorPane) FXMLLoader.load(getClass().getResource(LoginController.tela));
			Scene App = new Scene(fxmlApp);
			Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
			app.setScene(App);

	    }

	}

	


