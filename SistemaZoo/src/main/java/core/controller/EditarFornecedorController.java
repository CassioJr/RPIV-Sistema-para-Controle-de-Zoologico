package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditarFornecedorController implements Initializable{
	
	@FXML private AnchorPane anchorPane;
	@FXML private TextField nomefornecedor;
	@FXML private TextField endfornecedor;
	@FXML private TextField telfornecedor;
	@FXML private TextField emailfornecedor;
	@FXML private TextField cnpjfornecedor;
	@FXML private TextField cidadefornecedor;
	@FXML private TextField formaspfornecedor;
	@FXML private Label idlabelfr,lblNomeUser;

	// 1Metodo que realiza validacao e o salvamento do cadastro de fornecedores
	/* E realiza um evento que chama outra tela
	public void salvarAlt(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar a edi��o?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}*/

	// 2Valida se os campos estao preenchidos
	public boolean validarCampos() {
		if (nomefornecedor.getText().isEmpty() || endfornecedor.getText().isEmpty()
				|| telfornecedor.getText().isEmpty() || emailfornecedor.getText().isEmpty()||cnpjfornecedor.getText().isEmpty() || cidadefornecedor.getText().isEmpty() || formaspfornecedor.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
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

	/*
	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Fornecedor fr = new Fornecedor(Long.parseLong(idlabelfr.getText()), nomefornecedor.getText(),
				endfornecedor.getText(), Float.parseFloat(telfornecedor.getText()));
		
		
		FornecedorDao dao = new FornecedorDao();
		dao.updateFornecedor(fr);
	}
	*/

	/*
	 * 5Metodo para impedir que letras sejam escritas nos campos numericos public
	 * void validarCamposNumericos() { if (telfornecedor.isFocused()) {
	 * telfornecedor.textProperty().addListener((observable, oldValue, newValue) ->
	 * { if (!newValue.matches("\\d*")) {
	 * telfornecedor.setText(newValue.replaceAll("[^\\d]", "")); } });} }
	 */

	// 6Metodo que serve para adicionar as informacoes do fornecedor selecionado da
	// tabela nos TextField
	public void inserirInformacoes(String id, String nomeed2, String ended2, String teled2, String emailed2, String cnpjed2, String cidadeed2, String formasped2) {
		idlabelfr.setText(id);
		nomefornecedor.setText(nomeed2);
		endfornecedor.setText(ended2);
		telfornecedor.setText(teled2);
		emailfornecedor.setText(emailed2);
		cnpjfornecedor.setText(cnpjed2);
		cidadefornecedor.setText(cidadeed2);
		formaspfornecedor.setText(formasped2);
		// TODO Auto-generated method stub
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
	}

	
}
