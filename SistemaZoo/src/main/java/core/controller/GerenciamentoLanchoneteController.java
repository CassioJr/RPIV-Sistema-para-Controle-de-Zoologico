package core.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GerenciamentoLanchoneteController {

	@FXML
	private TextField barraPesquisa;

	@FXML
	private TableColumn<?, ?> dataPedidoColuna;

	@FXML
	private TableColumn<?, ?> horaPedidoColuna;

	@FXML
	private TableColumn<?, ?> idColuna;

	@FXML
	private Label lblNomeUser;

	@FXML
	private TableColumn<?, ?> nomeColuna;

	@FXML
	private TableColumn<?, ?> quantidadeColuna;

	@FXML
	private TableView<?> tabelaAlimentos;

	@FXML
	private TableColumn<?, ?> valorColuna;

	@FXML
	void btnCadastrarVendaAlimento(ActionEvent event) {

	}

	@FXML
	void btnEditarVendaAlimento(ActionEvent event) {

	}

	@FXML
	void btnFechaCaixa(ActionEvent event) {

	}

	@FXML
	void pesquisa(KeyEvent event) {

	}

	@FXML
	void voltar(ActionEvent event) throws IOException {
		trocarTela(event, "View_GerenciamentoVendas");

	}

	public void trocarTela(ActionEvent event, String tela) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
		} catch (Exception e) {
			System.out.println("Erro ao carregar tela");
		}
	}
}
