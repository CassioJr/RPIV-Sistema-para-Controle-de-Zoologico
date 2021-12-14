package controller;

import java.io.IOException;
import java.util.Optional;

import dao.PedidoDao;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pedido;

public class CadastroPedidoController {
	@FXML
	private AnchorPane anchorPane;
	@FXML 
	private TextField alimentop;
	@FXML 
	private DatePicker datap;
	@FXML 
	private TextField fornecedorp;
	@FXML 
	private TextField quantidadep;
	@FXML 
	private MenuButton situacaop;
	@FXML 
	private MenuItem abertop, fechadop;
	
	//1Metodo que realiza o salvamento do cadastro de pedidos faz um evento chamando outra tela
	public void salvarPedido(ActionEvent event) throws IOException
	{
		if(MSGEscolha("Deseja salvar o pedido?") == true) 
	    {
	       if(validarCampos() == true) 
	       {
	       pegarInformacoes();
	       VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_PedidoAlimento.fxml"));
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
	    if(alimentop.getText().isEmpty() || fornecedorp.getText().isEmpty() || quantidadep.getText().isEmpty() || situacaop.getText().isEmpty())
	    {
	    	MSG("Voce deve preencher os campos em branco para poder salvar");
	    	return false;
	    }
	    else if(datap.isVisible() && datap.getValue() == null)
	    {
			MSG("Preencha com uma data valida");
			return false;
		} 
		else 
		{
			return true;
		}
    }
	    
    //4Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCamposNumericos() 
	{
		if (quantidadep.isFocused()) 
		{
	  		quantidadep.textProperty().addListener((observable, oldValue, newValue) -> 
	  		{
	  			if (!newValue.matches("\\d*")) 
	  			{
	  				quantidadep.setText(newValue.replaceAll("[^\\d]", ""));
	  			}
	  		});
	  	} 
  	}
	
	//Os metodos item servem pra quando algo for selecionado no MenuButton, mostra os opções para selecionar
	public void pedidoAberto() {
		situacaop.setText(abertop.getText());
	}

	public void pedidoFechado() {
		situacaop.setText(fechadop.getText());
	}
	  	
	//5Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() 
	{
	   	Pedido p = new Pedido(alimentop.getText(), datap.getValue().toString(), fornecedorp.getText(), Integer.parseInt(quantidadep.getText()), situacaop.getText());
	   	PedidoDao dao = new PedidoDao();
	   	dao.addPedido(p);
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
	  	AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/view_Gerente.fxml"));
	    Scene Espera = new Scene(fxmlEspera);
	    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    primaryStage.setScene(Espera);
	}
	    
	//5Metodo para impedir que letras sejam escritas nos campos numericos
	public void validarCampospedidoNumericos() 
	{
	  		if (quantidadep.isFocused()) 
	  		{
	  			quantidadep.textProperty().addListener((observable, oldValue, newValue) -> 
	  			{
	  			if (!newValue.matches("\\d*")) 
	  				{
	  					quantidadep.setText(newValue.replaceAll("[^\\d]", ""));
	  				}
	  		});}
	  	}
	  
	}
