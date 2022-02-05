package core.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
	@FXML private Pane paneMapa;

	@FXML
	void btnInterna(ActionEvent event) {
		trocarTela(event, "TelaInternacao");
	}

	@FXML
	void btnGerenciaAnimal(ActionEvent event) {
		trocarTela(event, "view_GerenciamentoAnimal");
	}

	@FXML
	void btnGerenciaFuncionario(ActionEvent event) {
		trocarTela(event, "View_Funcionario");
	}

	@FXML
	void btnGerenciaFornecedor(ActionEvent event) {
		trocarTela(event, "View_Fornecedor");
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
	void btnSair(ActionEvent event) {
		trocarTela(event, "View_Login");
	}

	@FXML
	void btnGerenciaVendas(ActionEvent event) {
		trocarTela(event, "View_GerenciamentoVendas");
	}

	public void trocarTela(ActionEvent event, String tela) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Erro ao carregar tela");
		}
	}

}
