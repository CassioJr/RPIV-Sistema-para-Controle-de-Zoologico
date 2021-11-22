package controller;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class cadastroAnimalController{
	  	@FXML
	    private TextField nomeAnimal;
	  	
	    @FXML
	    private TextField idadeAnimal;

	    @FXML
	    private TextField instituicaoAnimal;

	    @FXML
	    private MenuButton situacaoAnimal;

	    @FXML
	    private MenuButton estadoSaude;

	    @FXML
	    private MenuButton sexoAnimal;

	    @FXML
	    private TextField habitatAnimal;
	    
	    @FXML
	    private DatePicker dataTransferencia;
	    @FXML
	    private TextField numeroAbrigoAnimal;

	    @FXML
	    private TextField localizacaoAnimal;

	    @FXML
	    private TextField tamanhoAnimal;

	    @FXML
	    private TextField nomeEspecieAnimal;

	    @FXML
	    private TextField nomeAlimentoAnimal;

	    @FXML
	    private TextField quantidadeAlimentoAnimal;

	    @FXML
	    private TextField medidaAlimentoAnimal;
    
	    
	    public void salvarCadastro(){
       if(MSGEscolha("Você deseja salvar o cadastro?") == true) {
    	   validarCampos();
       }
    }

    public void validarCampos() {
    if(nomeAnimal.getText().isEmpty() && idadeAnimal.getText().isEmpty() && sexoAnimal.getText().isEmpty() && situacaoAnimal.getText().isEmpty() && instituicaoAnimal.getText().isEmpty()
       && estadoSaude.getText().isEmpty() && nomeEspecieAnimal.getText().isEmpty() && habitatAnimal.getText().isEmpty() && habitatAnimal.getText().isEmpty() && localizacaoAnimal.getText().isEmpty()
       && tamanhoAnimal.getText().isEmpty() && nomeAlimentoAnimal.getText().isEmpty() && quantidadeAlimentoAnimal.getText().isEmpty() && medidaAlimentoAnimal.getText().isEmpty()) {
    	MSG("Você deve preencher os campos em branco para poder salvar");
    }
    }
    
    //Método que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/view_Animal.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    /*Metodo que apresenta uma msg de escolha perguntando sim ou nÃ£o ao usuario quando chamada, 
     * ela recebe como parametro o conteudo que vocÃª deseja apresentar na mensagem que sera apresentada ao usuario*/
	public boolean MSGEscolha(String msg) {
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		Optional<ButtonType> result = alerta.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			return true;
		}
		return false;
	}
	
	/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	* você deseja apresentar na mensagem que sera apresentada ao usuario*/
		public void MSG(String msg) {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Atenção");
			alerta.setHeaderText(null);
			alerta.setContentText(msg);
			alerta.showAndWait();
		}
}