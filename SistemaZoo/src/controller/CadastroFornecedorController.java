package controller;

import java.io.IOException;
import java.util.Optional;
import dao.FornecedorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fornecedor;

public class CadastroFornecedorController 
{
	@FXML
	private AnchorPane anchorPane;
	@FXML 
	private TextField nomefor;	
	@FXML 
	private TextField endfor;
	@FXML 
	private TextField telfor;
	
	//1 Metodo que realiza o salvamento do cadastro de fornecedores faz um evento chamando outra tela
	public void salvarCadastroFR(ActionEvent event) throws IOException
	{
       if(MSGEscolha("Deseja salvar o cadastro?") == true) 
       {
    	   if(validarCampos() == true) 
    	   {
    	   pegarInformacoes();
    		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Fornecedor.fxml"));
            Scene Espera = new Scene(fxmlEspera);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(Espera);
    	   }
       }
    }
	
	//2 Metodo que apresenta uma msg ao usuario quando chamada
	public void MSG(String msg) 
	{
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
	
	//3Valida se os campos estao preenchidos 
	//sem datas
	//sem motivo saida
    public boolean validarCampos() 
    {
	    if(nomefor.getText().isEmpty() || endfor.getText().isEmpty() || telfor.getText().isEmpty()) 
	    {
	    	MSG("Voce deve preencher os campos em branco para poder salvar");
	    	return false;
    }else 
	    {
	    	return true;
	    }
    }
    
    //4Metodo para impedir que letras sejam escritas nos campos numericos
  	public void validarCamposNumericos() 
  	{
  		if (telfor.isFocused()) 
  		{
  			telfor.textProperty().addListener((observable, oldValue, newValue) -> 
  			{
  				if (!newValue.matches("\\d*")) 
  				{
  					telfor.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		} 
  	}
  	
    //5Metodo que eh responsavel por pegar as informacoes dos TextField da tela
    public void pegarInformacoes() 
    {
    	Fornecedor fr = new Fornecedor(nomefor.getText(), endfor.getText(), Float.parseFloat(telfor.getText()));
    	FornecedorDao dao = new FornecedorDao();
    	dao.addFornecedor(fr);
    }
  	
    //6Metodo que apresenta uma msg de escolha perguntando sim ou nao ao usuario 
	public boolean MSGEscolha(String msg) 
	{
		Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		Optional<ButtonType> result = alerta.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK)
		{
			return true;
		}
		return false;
	}
  	
    //7Metodo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException 
    {
    	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    

}
