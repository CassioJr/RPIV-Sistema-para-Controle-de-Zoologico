package controller;

import java.io.IOException;
import dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Mensagens;

public class LoginController {

	@FXML private TextField loginText;
	@FXML private PasswordField senhaText;
	protected static String tela;
	public static String nomeFunc;

	// Método que realiza a ação de realizar o login dentro da Pane FXML
	public void realizarLogin(ActionEvent event) throws IOException {
		LoginDao ldao = new LoginDao();
		try {
			if (ldao.getLogin(loginText.getText(), senhaText.getText()) == true) {
				tela = "View_" + validarFuncao();
				AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/"+tela+".fxml"));
				Scene Espera = new Scene(fxmlEspera);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(Espera);
			} else {
				Mensagens.MSG("Numero do usuário ou senha está errado!");
			}
		} catch (NullPointerException e) {
			Mensagens.MSG("O usuário que está realizando o login não possui um função definida ou funcão atribuida inexistente");
		}
	}

	// Método que valida a função existe, então ele retorna a função do funcionario
	public String validarFuncao() {
		LoginDao ldao = new LoginDao();
		String funcao = ldao.getFuncao(loginText.getText(), senhaText.getText());
		if (funcao != null)
			return funcao;
		else
			return null;
	}

}
