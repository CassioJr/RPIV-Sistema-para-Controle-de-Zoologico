package controller;

import java.io.IOException;
import java.util.Optional;
import dao.FuncionarioDao;
import dao.LoginDao;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Funcionario;
import model.Login;

public class CadastroFuncionarioController 
{
	@FXML
	private AnchorPane anchorPane;
	@FXML 
	private TextField nomef;	
	@FXML 
	private DatePicker dtadmissao;
	@FXML 
	private DatePicker dtsaida;
	@FXML 
	private TextArea mtvsaida;
	@FXML 
	private TextField funcao;
	@FXML 
	private TextField endereco;
	@FXML 
	private TextField telefone;
	@FXML 
	private TextField salario;
	
	//Metodo que realiza o salvamento do cadastro de funcionarios faz um evento chamando outra tela
	public void salvarCadastroF(ActionEvent event) throws IOException
	{
       if(MSGEscolha("Deseja salvar o cadastro?") == true) 
       {
    	   if(validarCampos() == true) 
    	   {
    	   pegarInformacoes();
    		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
            Scene Espera = new Scene(fxmlEspera);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(Espera);
    	   }
       }
    }
	
	//Valida se os campos estao preenchidos 
	//sem datas
	//sem motivo saida
    public boolean validarCampos() 
    {
	    if(nomef.getText().isEmpty() || funcao.getText().isEmpty() || endereco.getText().isEmpty() || telefone.getText().isEmpty() || salario.getText().isEmpty()) 
	    {
	    	MSG("Voce deve preencher os campos em branco para poder salvar");
	    	return false;
    }
	    else if(dtadmissao.isVisible() && dtadmissao.getValue() == null)
	    {
			MSG("Preencha com uma data válida");
			return false;
	    } 
	    else 
	    {
		return true;
	    }
    }
    
    //Metodo para impedir que letras sejam escritas nos campos numericos
  	public void validarCamposNumericos() 
  	{
  		if (telefone.isFocused()) 
  		{
  			telefone.textProperty().addListener((observable, oldValue, newValue) -> 
  			{
  				if (!newValue.matches("\\d*")) 
  				{
  					telefone.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		} else if (salario.isFocused()) 
  		{
  			salario.textProperty().addListener((observable, oldValue, newValue) -> 
  			{
  				if (!newValue.matches("\\d*")) 
  				{
  					salario.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		}
  	}
    
    //Metodo que eh responsavel por pegar as informacoes dos TextField da tela
    public void pegarInformacoes() 
    {
    	Funcionario f = new Funcionario(nomef.getText(), dtadmissao.getValue().toString(), dtsaida.getValue().toString(), 
    			mtvsaida.getText(), funcao.getText(), 
    			endereco.getText(), Float.parseFloat(telefone.getText()), Float.parseFloat(salario.getText()));
    	FuncionarioDao dao = new FuncionarioDao();
    	dao.addFuncionario(f);
    	cadastroLogin(nomef.getText(), funcao.getText());
    }
    
	//Metodo que cadastro um login que gera um id unico e senha padrão para o funcionario
  	//e pega informações referentes a nome e funcao do mesmo
  	public void cadastroLogin(String nome, String funcao) {
  		LoginDao l = new LoginDao();
  		Login login = new Login(nome,"123",funcao);
  		l.addLogin(login);
  	}
    
    //Metodo que apresenta uma msg de escolha perguntando sim ou nao ao usuario 
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
	
	//Metodo que apresenta uma msg ao usuario quando chamada
	public void MSG(String msg) 
	{
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
	
    //Metodo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException 
    {
    	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }

}