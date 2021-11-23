package controller;

import java.io.IOException;
import java.util.Optional;

import dao.FuncionarioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Funcionario;

public class CadastroFuncionarioController {
	@FXML
	private AnchorPane anchorPane;
	@FXML 
	private TextField nomeF;	
	@FXML 
	private DatePicker dtAdmissao;
	@FXML 
	private DatePicker dtSaida;
	@FXML 
	private TextArea mtvSaida;
	@FXML 
	private TextField funcao;
	@FXML 
	private TextField endereco;
	@FXML 
	private TextField telefone;
	@FXML 
	private TextField salario;
	
	//Metodo que ealiza o salvamento do cadastro de funcionarios
	//E realiza um evento que chama outra tela
	public void salvarCadastroF(ActionEvent event) throws IOException{
       if(MSGEscolha("Deseja salvar o cadastro?") == true) {
    	   if(validarCampos() == true) {
    	   pegarInformacoes();
    		BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/view_Funcionario.fxml"));
            Scene Espera = new Scene(fxmlEspera);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(Espera);
    	   }
       }
    }
	
	//Valida se os campos estao preenchidos 
	//sem datas
    public boolean validarCampos() {
    if(nomeF.getText().isEmpty() || mtvSaida.getText().isEmpty() || funcao.getText().isEmpty()
       || endereco.getText().isEmpty() || telefone.getText().isEmpty() || salario.getText().isEmpty()) {
    	MSG("Voce deve preencher os campos em branco para poder salvar");
    	return false;
    }
    else {
    	return true;
    }
    }
    
    //Metodo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    //Metodo que eh responsavel por pegar as informacoes dos TextField da tela
    public void pegarInformacoes() {
    	Funcionario f = new Funcionario(null, nomeF.getText(), dtAdmissao.getValue().toString(), dtSaida.getValue().toString(), 
    			mtvSaida.getText(), funcao.getText(), 
    			endereco.getText(), Float.parseFloat(telefone.getText()), Float.parseFloat(salario.getText()));
    	FuncionarioDao dao = new FuncionarioDao();
    	dao.addFuncionario(f);
    }
    
    //Metodo que apresenta uma msg de escolha perguntando sim ou nao ao usuario 
	public boolean MSGEscolha(String msg) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		Optional<ButtonType> result = alerta.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			return true;
		}
		return false;
	}
	
	/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	* vc deseja apresentar na mensagem que sera apresentada ao usuario*/
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
		}

}