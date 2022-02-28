package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.FornecedorDao;
import core.dao.LoginDao;
import core.model.Fornecedor;
import core.model.Login;
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

public class CadastroFornecedorController implements Initializable{
	@FXML private Label lblNomeUser;
	@FXML private AnchorPane anchorPane;
	@FXML private TextField nomefor;
	@FXML private TextField endfor;
	@FXML private TextField telfor;
	@FXML private TextField emailfor;
	@FXML private TextField cnpjfor;  
	@FXML private TextField cidadefor;
	@FXML private TextField formaspfor;

	// 1 Metodo que realiza o salvamento do cadastro de fornecedores faz um evento
	// chamando outra tela
	public void salvarCadastroFR(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar o cadastro?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}
	
	// 2Valida se os campos estao preenchidos
	public boolean validarCampos() {
		if (nomefor.getText().isEmpty() || endfor.getText().isEmpty() || telfor.getText().isEmpty() 
				|| emailfor.getText().isEmpty() || cnpjfor.getText().isEmpty()
				|| cidadefor.getText().isEmpty() || formaspfor.getText().isEmpty()) {
				Mensagens.MSG("Você deve preencher todos os campos para salvar!"); 
		return false;
		} 
	return true;
	}
	
	// 3Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCamposNumericos() {
		if (telfor.isFocused()) {
			telfor.textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("\\d*")) {
					telfor.setText(newValue.replaceAll("[^\\d]", ""));
				}
			});
		} else if (cnpjfor.isFocused()) {
			cnpjfor.textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("\\d*")) {
					cnpjfor.setText(newValue.replaceAll("[^\\d]", ""));
				}
			});
		}
	}
 
	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Fornecedor fr = new Fornecedor(nomefor.getText(), endfor.getText(), Float.parseFloat(telfor.getText()), 
				emailfor.getText(), Float.parseFloat(cnpjfor.getText()),  cidadefor.getText(), formaspfor.getText());
		FornecedorDao dao = FornecedorDao.getInstance();
		dao.addFornecedor(fr);
	}
	
	// 5Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoFornecedor.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
	}
}