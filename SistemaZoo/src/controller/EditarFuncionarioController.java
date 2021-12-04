package controller;

import java.io.IOException;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Funcionario;

public class EditarFuncionarioController {
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
	@FXML
	private Label idlabel;
	
	
	//1Metodo que realiza validacao e o salvamento do cadastro de funcionarios
	//E realiza um evento que chama outra tela 
	public void salvarEdicao2(ActionEvent event) throws IOException{
       if(MSGEscolha("Deseja salvar a edição?") == true) {
    	   if(validarCampos() == true) {
    	   pegarInformacoes();
    		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_Funcionario.fxml"));
            Scene Espera = new Scene(fxmlEspera);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(Espera);
    	   }
       }
    }
	
	//2Valida se os campos estao preenchidos 
		//sem datas
	    public boolean validarCampos() {
	    if(nomef.getText().isEmpty() || funcao.getText().isEmpty() 
	    							|| mtvsaida.getText().isEmpty()
	    							|| endereco.getText().isEmpty() 
	    							|| telefone.getText().isEmpty() 
	    							|| salario.getText().isEmpty()) {
	    	MSG("Voce deve preencher os campos em branco para poder salvar");
	    	return false;
	    }
	    else {
	    	return true;
	    }
	    }
	    
	    //3Metodo que retrocede para a tela anterior
	    public void voltar(ActionEvent event) throws IOException {
	    	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_Funcionario.fxml"));
	        Scene Espera = new Scene(fxmlEspera);
	        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        primaryStage.setScene(Espera);
	    }
	    
	    //4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	    public void pegarInformacoes() {
	    	Funcionario f = new Funcionario(Long.parseLong(idlabel.getText()), nomef.getText(), 
	    											dtadmissao.getValue().toString(), dtsaida.getValue().toString(), 
	    											mtvsaida.getText(), funcao.getText(), 
	    											endereco.getText(), Float.parseFloat(telefone.getText()), 
	    											Float.parseFloat(salario.getText()));
	    	FuncionarioDao dao = new FuncionarioDao();
	    	dao.updateFuncionario(f);
	    }
	    
	    //5Metodo para impedir que letras sejam escritas nos campos numericos
	  	public void validarCamposNumericos() {
	  		if (telefone.isFocused()) {
	  			telefone.textProperty().addListener((observable, oldValue, newValue) -> {
	  				if (!newValue.matches("\\d*")) {
	  					telefone.setText(newValue.replaceAll("[^\\d]", ""));
	  				}
	  			});
	  		} else if (salario.isFocused()) {
	  			salario.textProperty().addListener((observable, oldValue, newValue) -> {
	  				if (!newValue.matches("\\d*")) {
	  					salario.setText(newValue.replaceAll("[^\\d]", ""));
	  				}
	  			});
	  		}
	  	}
	    
	    
	  //6Metodo que serve para adicionar as informacoes do funcionario selecionado da tabela nos TextField
	    public void inserirInformacoes(String id,String nomeed ,String dtadmissaoed,String dtsaidaed,String mtvsaidaed,String funcaoed,String enderecoed, String telefoneed, String salarioed) {
	    	idlabel.setText(id);
	    	nomef.setText(nomeed);
	    	if(dtsaidaed != null) {
				dtsaida.setValue(LocalDate.parse(dtsaidaed));
			}
	    	if(dtadmissaoed != null) {
				dtadmissao.setValue(LocalDate.parse(dtadmissaoed));
			}
			mtvsaida.setText(mtvsaidaed);
			funcao.setText(funcaoed);
			endereco.setText(enderecoed);
			telefone.setText(telefoneed);
			salario.setText(salarioed);
	    }
	    
	    public String dataAdmissao() {
			if(dtadmissao.getValue() != null) {
				return dtadmissao.getValue().toString();
			}
			return null;
		}
	    public String dataSaida() {
			if(dtsaida.getValue() != null) {
				return dtsaida.getValue().toString();
			}
			return null;
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

	}
	    
	    
	    
	    
	    
	    