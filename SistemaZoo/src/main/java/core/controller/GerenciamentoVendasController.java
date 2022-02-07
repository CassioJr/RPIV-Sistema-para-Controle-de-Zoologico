package core.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GerenciamentoVendasController{

	@FXML private Pane paneMapa;
	
	@FXML
	void btnSair(ActionEvent event) throws IOException {
		trocarTela(event, "View_Login");
	}

	@FXML
	void btnVendaIngresso(ActionEvent event) throws IOException {
		trocarTela(event, "View_GerenciamentoIngressos");

	}

	@FXML
	void btnVendaLanche(ActionEvent event) throws IOException {
		trocarTela(event, "View_GerenciamentoLanchonete");

	}

	@FXML
	void btnVendaLembranca(ActionEvent event) throws IOException {
		trocarTela(event, "View_GerenciamentoLojaLembraca");
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
	void btnVoltar(ActionEvent event) throws IOException {
		trocarTela(event, LoginController.tela);

	}

	public void trocarTela(ActionEvent event, String tela) throws IOException {
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
	
	}

}
