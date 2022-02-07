package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.PedidoDao;
import core.model.Pedido;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CadastroPedidoController implements Initializable{
	@FXML private Label lblNomeUser;
	@FXML private AnchorPane anchorPane;
	@FXML private TextField alimentop;
	@FXML private DatePicker datap;
	@FXML private TextField fornecedorp;
	@FXML private TextField quantidadep;
	@FXML private MenuButton situacaop;
	@FXML private MenuItem abertop, fechadop;

	// 1Metodo que realiza o salvamento do cadastro de pedidos faz um evento
	// chamando outra tela
	public void salvarPedido(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar o pedido?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}

	// 3Valida se os campos estao preenchidos
	// sem datas
	// sem motivo saida
	public boolean validarCampos() {
		if (alimentop.getText().isEmpty() || fornecedorp.getText().isEmpty() || quantidadep.getText().isEmpty()
				|| situacaop.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
			return false;
		} else if (datap.isVisible() && datap.getValue() == null) {
			Mensagens.MSG("Preencha com uma data valida");
			return false;
		} else {
			return true;
		}
	}

	// 4Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCamposNumericos() {
		if (quantidadep.isFocused()) {
			quantidadep.textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("\\d*")) {
					quantidadep.setText(newValue.replaceAll("[^\\d]", ""));
				}
			});
		}
	}

	// Os metodos item servem pra quando algo for selecionado no MenuButton, mostra
	// os opções para selecionar
	public void pedidoAberto() {
		situacaop.setText(abertop.getText());
	}

	public void pedidoFechado() {
		situacaop.setText(fechadop.getText());
	}

	// 5Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Pedido p = new Pedido(alimentop.getText(), datap.getValue().toString(), fornecedorp.getText(),
				Integer.parseInt(quantidadep.getText()), situacaop.getText());
		PedidoDao dao = new PedidoDao();
		dao.addPedido(p);
	}

	// 7Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_PedidoAlimento.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}

	// 5Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCampospedidoNumericos() {
		if (quantidadep.isFocused()) {
			quantidadep.textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("\\d*")) {
					quantidadep.setText(newValue.replaceAll("[^\\d]", ""));
				}
			});
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
		
	}

}
