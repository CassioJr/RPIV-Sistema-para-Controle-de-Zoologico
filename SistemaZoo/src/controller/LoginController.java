package controller;

import java.io.IOException;

import dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML private TextField loginText;
    @FXML private PasswordField senhaText;
    protected static String tela;
    
    //Método que realiza a ação de realizar o login dentro da Pane FXML
    public void realizarLogin(ActionEvent event) throws IOException {
    	LoginDao ldao = new LoginDao();
    	try {
    	if(ldao.getLogin(loginText.getText(), senhaText.getText()) == true) {
    		tela = "/view/View_"+validarFuncao()+".fxml";
			AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource(tela));
			Scene Espera = new Scene(fxmlEspera);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setScene(Espera);
    		}else{
    		MSG("Numero do usuário ou senha está errado!");
    	}
    	}catch(NullPointerException e) {
    		MSG("O usuário que está realizando o login não possui um função definida ou funcão atribuida inexistente");
    	}
    }
    
    //Método que valida a função existe, então ele retorna a função do funcionario
    public String validarFuncao() {
    	LoginDao ldao = new LoginDao();
    	String funcao = ldao.getFuncao(loginText.getText(), senhaText.getText());
    	if(funcao !=null)
    	return funcao;
    	else 
    	return null;
    }
    
    /*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	 *você deseja apresentar na mensagem que sera apresentada ao usuario*/
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
}
