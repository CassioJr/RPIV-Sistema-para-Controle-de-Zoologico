package core.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

public class EditarPedidoController implements Initializable{
	
	@FXML private AnchorPane anchorPane;
	@FXML private Label idlabele, lblNomeUser;
	@FXML private TextField alimentoe;
	@FXML private DatePicker dtpedidoe;
	@FXML private TextField fornecedore;
	@FXML private TextField quantidadee;
	@FXML private MenuButton situacaomenu;
	@FXML private MenuItem abertomenu, fechadomenu;

	// 1Metodo que realiza validacao e o salvamento do cadastro de pedidos
	// E realiza um evento que chama outra tela
	public void salvarEdicaop(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar a edição?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}

	// 2Valida se os campos estao preenchidos
	public boolean validarCampos() {
		if (alimentoe.getText().isEmpty() || fornecedore.getText().isEmpty() || quantidadee.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
			return false;
		} else if (dtpedidoe.isVisible() && dtpedidoe.getValue() == null) {
			Mensagens.MSG("Preencha com uma data válida");
			return false;
		} else {
			return true;
		}
	}

	// 3Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_Fornecedor.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}

	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Pedido p = new Pedido(Long.parseLong(idlabele.getText()), alimentoe.getText(), dtpedidoe.getValue().toString(),
				fornecedore.getText(), situacaomenu.getText(), Integer.parseInt(quantidadee.getText()));
		PedidoDao dao = new PedidoDao();
		dao.updatePedido(p);
	}

	/*
	 * 5Metodo para impedir que letras sejam escritas nos campos numericos public
	 * void validarCamposNumericos() { if (quantidadee.isFocused()) {
	 * quantidadee.textProperty().addListener((observable, oldValue, newValue) -> {
	 * if (!newValue.matches("\\d*")) {
	 * quantidadee.setText(newValue.replaceAll("[^\\d]", "")); } });} }
	 */

	/*
	 * Os metodos item servem pra quando algo for selecionado nos MenuButton, eles
	 * aparecam como selecionados e para que mostrem algum campo caso a opcao
	 * referente for selecionada
	 */

	public void pedidomenuAberto() {
		situacaomenu.setText(abertomenu.getText());
	}

	public void pedidomenuFechado() {
		situacaomenu.setText(fechadomenu.getText());
	}

	// 6Metodo que serve para adicionar as informacoes do fornecedor selecionado da
	// tabela nos TextField
	public void inserirInformacoes(Pedido pedido) {
		idlabele.setText(String.valueOf(pedido.getId()));
		alimentoe.setText(pedido.getAlimentoPed());
		if (pedido.getDataPed() != null) {
			dtpedidoe.setValue(LocalDate.parse(pedido.getDataPed()));
		}
		fornecedore.setText(pedido.getFornecedorPed());
		quantidadee.setText(String.valueOf(pedido.getQuantidadePed()));
		situacaomenu.setText(pedido.getSituacaoPed());
	}

	// Metodo para habilitar os campos do status do pedido
	public void habilitaCamposItem() {
		// if(situacaomenu.getText().equals("abertomenu")) {
		// pedidomenuAberto();
		// } else if (situacaomenu.getText().equals("fechadomenu")) {
		// pedidomenuFechado();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);		
	}
}
