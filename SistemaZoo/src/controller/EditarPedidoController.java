package controller;

import java.io.IOException;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Pedido;

public class EditarPedidoController 
{
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private Label idlabele;
	@FXML 
	private TextField alimentoe;
	@FXML 
	private DatePicker dtpedidoe;
	@FXML 
	private TextField fornecedore;
	@FXML 
	private TextField quantidadee;
	@FXML 
	private MenuButton situacaomenu;
	@FXML 
	private MenuItem abertomenu, fechadomenu;

	//1Metodo que realiza validacao e o salvamento do cadastro de pedidos
	//E realiza um evento que chama outra tela 
	public void salvarEdicaop(ActionEvent event) throws IOException
	{
		if(MSGEscolha("Deseja salvar a edição?") == true) 
		{
			if(validarCampos() == true) 
			{
	    	   pegarInformacoes();
	    		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_PedidoAlimento.fxml"));
	            Scene Espera = new Scene(fxmlEspera);
	            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            primaryStage.setScene(Espera);
	    	}
		}
	}
		
	//2Valida se os campos estao preenchidos 
	public boolean validarCampos() 
	{
	  	if(alimentoe.getText().isEmpty() || fornecedore.getText().isEmpty() || quantidadee.getText().isEmpty())
	   	{
	   		MSG("Voce deve preencher os campos em branco para poder salvar");
	   		return false;
	   	}
	  	else if(dtpedidoe.isVisible() && dtpedidoe.getValue() == null)
	  	{
	  		MSG("Preencha com uma data vÃ¡lida");
	  		return false;
	  	} 
	  	else 
	  	{
	  		return true;
	  	}
	}
		
	//3Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException 
	    {
	    	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Fornecedor.fxml"));
	        Scene Espera = new Scene(fxmlEspera);
	        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        primaryStage.setScene(Espera);
	    }
		
	//4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
    public void pegarInformacoes() 
    {
    	Pedido p = new Pedido(Long.parseLong(idlabele.getText()), 
    							alimentoe.getText(), 
    							dtpedidoe.getValue().toString(),
    							fornecedore.getText(),
    							situacaomenu.getText(),
    							Integer.parseInt(quantidadee.getText())
    			);
	   	PedidoDao dao = new PedidoDao();
	   	dao.updatePedido(p);
    }
	    
    /*5Metodo para impedir que letras sejam escritas nos campos numericos
  	public void validarCamposNumericos() 
  	{
  		if (quantidadee.isFocused()) 
  		{
  			quantidadee.textProperty().addListener((observable, oldValue, newValue) -> 
  			{
  			if (!newValue.matches("\\d*")) 
  				{
  					quantidadee.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});}
  	}*/
    
    
    /*
	 * Os metodos item servem pra quando algo for selecionado nos MenuButton, eles
	 * aparecam como selecionados e para que mostrem algum campo caso a opcao
	 * referente for selecionada
	 */
    
	public void pedidomenuAberto() {
		situacaomenu.setText(abertomenu.getText());
	}

	public void pedidomenuFechado() {
		situacaomenu.setText(fechadomenu.getText());
	}
    
  	
	//6Metodo que serve para adicionar as informacoes do fornecedor selecionado da tabela nos TextField
    public void inserirInformacoes(String id, String alimentoe2 , String dtpedidoe2, String fornecedore2, String quantidadee2, String situacaoe2) 
    {
    	idlabele.setText(id);
    	alimentoe.setText(alimentoe2);
    	if(dtpedidoe != null) {
			dtpedidoe.setValue(LocalDate.parse(dtpedidoe2));
		}
    	fornecedore.setText(fornecedore2);
    	quantidadee.setText(quantidadee2);
    	situacaomenu.setText(situacaoe2);
    }
    
    //7Metodo que apresenta uma msg de escolha perguntando sim ou nao ao usuario 
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
		
	/*8Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	* vc deseja apresentar na mensagem que sera apresentada ao usuario*/
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
	
	//Metodo para habilitar os campos do status do pedido
	public void habilitaCamposItem() {
		//if(situacaomenu.getText().equals("abertomenu")) {
	//		pedidomenuAberto();
		//} else if (situacaomenu.getText().equals("fechadomenu")) {
		//	pedidomenuFechado();
    	}
	}
