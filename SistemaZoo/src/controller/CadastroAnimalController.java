package controller;

import java.io.IOException;
import java.util.Optional;

import dao.AnimalDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Animal;

public class CadastroAnimalController{
	  @FXML private TextField nomeAnimal;	
	  @FXML private TextField idadeAnimal;
	  @FXML private MenuButton sexoAnimal;
	  @FXML private MenuItem macho,femea;
	  @FXML private MenuButton situacaoAnimal;
	  @FXML private TextField instituicaoOrigemAnimal;
	  @FXML private TextField instituicaoDestinoAnimal;
	  @FXML private MenuItem transferidodeoutra,transferidoparaoutra;
	  @FXML private MenuButton estadoSaude;
	  @FXML private MenuItem saudavel,doente;
	  @FXML private TextField nomeEspecieAnimal;
	  @FXML private TextField habitatAnimal;
	  @FXML private TextField nomeDoenca;
	  @FXML private DatePicker dataTransferencia;
	  @FXML private TextField numeroAbrigoAnimal;
	  @FXML private TextField localizacaoAnimal;
	  @FXML private TextField tamanhoAnimal;
	  @FXML private TextField nomeAlimentoAnimal;
	  @FXML private TextField quantidadeAlimentoAnimal;
	  @FXML private TextField medidaAlimentoAnimal;
	  @FXML private Text LabelinstituicaoDestino;
	  @FXML private Text LabelNomeDoenca;
	 
	/*Metódo que chama os metodos de validações e realiza o salvamento do cadastro do animal, 
	ele realiza um evento que seria o de chamar outra tela*/
	public void salvarCadastro(ActionEvent event) throws IOException{
       if(MSGEscolha("Você deseja salvar o cadastro?") == true) {
    	   if(validarCampos() == true) {
    	   pegarInformacoes();
    		BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/view_Animal.fxml"));
            Scene Espera = new Scene(fxmlEspera);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(Espera);
    	   }
       }
    }

	//Metódo que valida se os campos estão todos preenchidos 
    public boolean validarCampos() {
    if(nomeAnimal.getText().isEmpty() || idadeAnimal.getText().isEmpty() || sexoAnimal.getText().isEmpty() || situacaoAnimal.getText().isEmpty() || instituicaoOrigemAnimal.getText().isEmpty()
       || estadoSaude.getText().isEmpty() || nomeEspecieAnimal.getText().isEmpty() || habitatAnimal.getText().isEmpty() || habitatAnimal.getText().isEmpty() || localizacaoAnimal.getText().isEmpty()
       || tamanhoAnimal.getText().isEmpty() || nomeAlimentoAnimal.getText().isEmpty() || quantidadeAlimentoAnimal.getText().isEmpty() || medidaAlimentoAnimal.getText().isEmpty()) {
    	MSG("Você deve preencher os campos em branco para poder salvar");
    	return false;
    }
    else {
    	return true;
    }
    }
    
    //Metódo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/view_Animal.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    //Método que é responsavel por pegar as informações dos TextField da tela
    public void pegarInformacoes() {
    	Animal a = new Animal(nomeAnimal.getText(), nomeAlimentoAnimal.getText(), situacaoAnimal.getText(), localizacaoAnimal.getText(), instituicaoOrigemAnimal.getText(), instituicaoDestinoAnimal.getText(), nomeEspecieAnimal.getText(), habitatAnimal.getText(), estadoSaude.getText(), nomeDoenca.getText(), 
    			Integer.parseInt(idadeAnimal.getText()),sexoAnimal.getText(), Integer.parseInt(numeroAbrigoAnimal.getText()), dataTransferencia.getValue().toString(), Float.parseFloat(medidaAlimentoAnimal.getText()), Float.parseFloat(tamanhoAnimal.getText()), Float.parseFloat(quantidadeAlimentoAnimal.getText()));
    	AnimalDao dao = new AnimalDao();
    	dao.addAnimal(a);
    }
    
    /* Os metodos item servem pra quando algo for selecionado nos MenuButton, eles apareçam como selecionados e para que 
     * mostrem algum campo caso a opção referente for selecionada*/
    public void item1SexoAnimal() {
    	sexoAnimal.setText(macho.getText());
    }
    
    public void item2SexoAnimal() {
    	sexoAnimal.setText(femea.getText());
    }
    
    public void item1Transferencia() {
    	situacaoAnimal.setText(transferidodeoutra.getText());
      	LabelinstituicaoDestino.setVisible(false);
    	instituicaoDestinoAnimal.setVisible(false);
    }

    public void item2Transferencia() {
    	situacaoAnimal.setText(transferidoparaoutra.getText());
    	LabelinstituicaoDestino.setVisible(true);
    	instituicaoDestinoAnimal.setVisible(true);
    }
    
    public void item1StatusSaude() {
    	estadoSaude.setText(saudavel.getText());
    	LabelNomeDoenca.setVisible(false);
    	nomeDoenca.setVisible(false);
    }
    
    public void item2StatusSaude() {
    	estadoSaude.setText(doente.getText());
    	LabelNomeDoenca.setVisible(true);
    	nomeDoenca.setVisible(true);
    }
    
    /*Metodo que apresenta uma msg de escolha perguntando sim ou não ao usuario quando chamada, 
     * ela recebe como parametro o conteudo que você deseja apresentar na mensagem que sera apresentada ao usuario*/
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