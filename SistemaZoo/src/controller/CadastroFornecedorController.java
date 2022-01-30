package controller;

import java.io.IOException;
import dao.FornecedorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Fornecedor;
import utils.Mensagens;

public class CadastroFornecedorController {
	
	@FXML private AnchorPane anchorPane;
	@FXML private TextField nomefor;
	@FXML private TextField endfor;
	@FXML private TextField telfor;

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
	// sem datas
	// sem motivo saida
	public boolean validarCampos() {
		if (nomefor.getText().isEmpty() || endfor.getText().isEmpty() || telfor.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
			return false;
		} else {
			return true;
		}
	}

	// 3Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCamposNumericos() {
		if (telfor.isFocused()) {
			telfor.textProperty().addListener((observable, oldValue, newValue) -> {
				if (!newValue.matches("\\d*")) {
					telfor.setText(newValue.replaceAll("[^\\d]", ""));
				}
			});
		}
	}

	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Fornecedor fr = new Fornecedor(nomefor.getText(), endfor.getText(), Float.parseFloat(telfor.getText()));
		FornecedorDao dao = new FornecedorDao();
		dao.addFornecedor(fr);
	}
	
	// 5Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}
}
