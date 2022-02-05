package core.controller;

import java.io.IOException;
import java.time.LocalDate;

import core.dao.AnimalDao;
import core.model.Animal;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditarAnimalController{
	  	
	  @FXML private Label labeldAnimal,labelConsulta;
	  @FXML private TextField nomeAnimal;	
	  @FXML private TextField idadeAnimal;
	  @FXML private MenuButton situacaoAnimal,sexoAnimal,medidaAlimentoAnimal;
	  @FXML private MenuItem macho,femea;
	  @FXML private TextField instituicaoOrigemAnimal;
	  @FXML private TextField instituicaoDestinoAnimal;
	  @FXML private MenuItem transferidodeoutra,transferidoparaoutra,comprado,nascidoLocalmente;
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
	  @FXML private Text labelinstituicaoDestino,labelDataTransferencia,labelOrigem;
	  @FXML private Text labelNomeDoenca;
	  @FXML private MenuItem medidaKg, medidaLitros,medidaMl,medidaGramas;
	 
	/*Metódo que chama os metodos de validações e realiza o salvamento do cadastro editado do animal, 
	ele realiza um evento que seria o de chamar outra tela assim como feito no metodo de realizarCadastro na outra controller*/
	public void salvarEdicao(ActionEvent event) throws IOException{
       if(Mensagens.MSGEscolha("Você deseja salvar o cadastro?") == true) {
    	   if(validarCampos() == true) {
    	   pegarInformacoes();
    	   voltar(event);
    	   }	
       }
    }
	
	//Metódo que valida se os campos estão todos preenchidos 
	public boolean validarCampos() {
		if (nomeAnimal.getText().isEmpty() || idadeAnimal.getText().isEmpty() || sexoAnimal.getText().isEmpty()
				|| situacaoAnimal.getText().isEmpty() || estadoSaude.getText().isEmpty()
				|| nomeEspecieAnimal.getText().isEmpty() || habitatAnimal.getText().isEmpty()
				|| habitatAnimal.getText().isEmpty() || localizacaoAnimal.getText().isEmpty()
				|| tamanhoAnimal.getText().isEmpty() || nomeAlimentoAnimal.getText().isEmpty()
				|| quantidadeAlimentoAnimal.getText().isEmpty() || medidaAlimentoAnimal.getText().isEmpty() || instituicaoOrigemAnimal.isVisible() 
				&& instituicaoOrigemAnimal.getText().isEmpty()|| instituicaoDestinoAnimal.isVisible() && instituicaoDestinoAnimal.getText().isEmpty()) {
			Mensagens.MSG("Você deve preencher os campos em branco para poder salvar");
			return false;
		}else if(dataTransferencia.isVisible() && dataTransferencia.getValue() == null){
			Mensagens.MSG("Preencha com uma data válida");
			return false;
		}else {
			return true;
		}
	}
    
    //Metódo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoAnimal.fxml"));
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(fxmlEspera));
    }
    
  //Método que é responsavel por pegar as informações dos TextField da tela
    public void pegarInformacoes() {
    	Animal a = new Animal(nomeAnimal.getText(), nomeAlimentoAnimal.getText(), situacaoAnimal.getText(), localizacaoAnimal.getText(), instituicaoOrigemAnimal.getText(), instituicaoDestinoAnimal.getText(), nomeEspecieAnimal.getText(), habitatAnimal.getText(), estadoSaude.getText(), nomeDoenca.getText(), 
    			Integer.parseInt(idadeAnimal.getText()),sexoAnimal.getText(), Integer.parseInt(numeroAbrigoAnimal.getText()), dataTransferencia(),medidaAlimentoAnimal.getText(), Float.parseFloat(tamanhoAnimal.getText()), Float.parseFloat(quantidadeAlimentoAnimal.getText()), Boolean.parseBoolean(labelConsulta.getText()));
    	AnimalDao dao = new AnimalDao();
    	dao.updateAnimal(a, Long.parseLong(labeldAnimal.getText()));
    }
    
    //Metódo para mostrar os campos de situacao e estado de saude que estão false do animal que vai ser editado 
    //Isso previne um erro que quando clica no animal ele não mostra os campos referentes a sua situação e estado de saude
    public void habilitaCamposItem() {	
    	if(situacaoAnimal.getText().equals("Transferido de outra instituição")) {
    		item1Transferencia();
    	}else if(situacaoAnimal.getText().equals("Transferido para outra instituição")) {
    		item2Transferencia();
    	}else if (situacaoAnimal.getText().equals("Comprado")) {
    		item3Transferencia();
    	}else if (situacaoAnimal.getText().equals("Nascido no local")) {
    		item4Transferencia();
    	}
    	if(estadoSaude.getText().equals("Doente")) {
			item2StatusSaude();
		}
    }
    
    //Método que serve para adicionar as informações do animal selecionado na tabela nos TextField
    public void inserirInformacoes(String id,String lblConsulta,String nome,String idade,String sexo,String situacao,String instOrigem,String instDestino, String estado, String nomedaDoenca,String especie, String habitat,
    		String datatransferenciaString, String numeroAbrigo, String localizacaoJaula, String tamanhoJaula, String nomeAlimento, String quantidade, String medida) {
    	labeldAnimal.setText(id);
    	labelConsulta.setText(lblConsulta);
    	nomeAnimal.setText(nome);
		idadeAnimal.setText(idade);
		sexoAnimal.setText(sexo);
		situacaoAnimal.setText(situacao);
		instituicaoOrigemAnimal.setText(instOrigem);
		instituicaoDestinoAnimal.setText(instDestino);
		estadoSaude.setText(estado);
		nomeDoenca.setText(nomedaDoenca);
		nomeEspecieAnimal.setText(especie);
		habitatAnimal.setText(habitat);
		if(datatransferenciaString != null) {
		dataTransferencia.setValue(LocalDate.parse(datatransferenciaString));
		}
		numeroAbrigoAnimal.setText(numeroAbrigo);
		localizacaoAnimal.setText(localizacaoJaula);
		tamanhoAnimal.setText(tamanhoJaula);
		nomeAlimentoAnimal.setText(nomeAlimento);
		quantidadeAlimentoAnimal.setText(quantidade);
		medidaAlimentoAnimal.setText(medida);
    }

	public String dataTransferencia() {
		if(dataTransferencia.getValue() != null) {
			return dataTransferencia.getValue().toString();
		}
		return null;
	}
    
  //Método para impedir que letras sejam escritas nos campos n�mericos
  	public void validarCamposNumericos() {
  		if (idadeAnimal.isFocused()) {
  			idadeAnimal.textProperty().addListener((observable, oldValue, newValue) -> {
  				if (!newValue.matches("\\d*")) {
  					idadeAnimal.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		} else if (tamanhoAnimal.isFocused()) {
  			tamanhoAnimal.textProperty().addListener((observable, oldValue, newValue) -> {
  				if (!newValue.matches("\\d*")) {
  					tamanhoAnimal.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});

  		} else if (numeroAbrigoAnimal.isFocused()) {
  			numeroAbrigoAnimal.textProperty().addListener((observable, oldValue, newValue) -> {
  				if (!newValue.matches("\\d*")) {
  					numeroAbrigoAnimal.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		} else if (quantidadeAlimentoAnimal.isFocused()) {
  			quantidadeAlimentoAnimal.textProperty().addListener((observable, oldValue, newValue) -> {
  				if (!newValue.matches("\\d*")) {
  					quantidadeAlimentoAnimal.setText(newValue.replaceAll("[^\\d]", ""));
  				}
  			});
  		}
  	}
    
  	/*
	 * Os metodos item servem pra quando algo for selecionado nos MenuButton, eles
	 * apareçam como selecionados e para que mostrem algum campo caso a opção
	 * referente for selecionada
	 */
	public void item1SexoAnimal() {
		sexoAnimal.setText(macho.getText());
	}

	public void item2SexoAnimal() {
		sexoAnimal.setText(femea.getText());
	}

	public void item1Transferencia() {
		situacaoAnimal.setText(transferidodeoutra.getText());
		labelinstituicaoDestino.setVisible(false);
		labelOrigem.setVisible(true);
		instituicaoDestinoAnimal.setVisible(false);
		dataTransferencia.setVisible(true);
		instituicaoOrigemAnimal.setVisible(true);
		instituicaoDestinoAnimal.setText("");
	}

	public void item2Transferencia() {
		situacaoAnimal.setText(transferidoparaoutra.getText());
		labelDataTransferencia.setVisible(true);
		labelOrigem.setVisible(true);
		dataTransferencia.setVisible(true);
		instituicaoOrigemAnimal.setVisible(true);
		labelinstituicaoDestino.setVisible(true);
		instituicaoDestinoAnimal.setVisible(true);
	}

	public void item3Transferencia() {
		situacaoAnimal.setText(comprado.getText());
		labelDataTransferencia.setVisible(true);
		labelOrigem.setVisible(true);
		dataTransferencia.setVisible(true);
		instituicaoOrigemAnimal.setVisible(true);
		labelinstituicaoDestino.setVisible(false);
		instituicaoDestinoAnimal.setVisible(false);
		instituicaoDestinoAnimal.setText("");
	}

	public void item4Transferencia() {
		situacaoAnimal.setText(nascidoLocalmente.getText());
		labelDataTransferencia.setVisible(false);
		labelOrigem.setVisible(false);
		dataTransferencia.setVisible(false);
		instituicaoOrigemAnimal.setVisible(false);
		labelinstituicaoDestino.setVisible(false);
		instituicaoDestinoAnimal.setVisible(false);
		instituicaoDestinoAnimal.setText("");
		dataTransferencia.setValue(null);
		instituicaoOrigemAnimal.setText("");
	}

	public void item1StatusSaude() {
		estadoSaude.setText(saudavel.getText());
		labelNomeDoenca.setVisible(false);
		nomeDoenca.setVisible(false);
		nomeDoenca.setText("");
	}

	public void item2StatusSaude() {
		estadoSaude.setText(doente.getText());
		labelNomeDoenca.setVisible(true);
		nomeDoenca.setVisible(true);
	}

	public void item1MedidaAlimentar() {
		medidaAlimentoAnimal.setText(medidaKg.getText());
	}

	public void item2MedidaAlimentar() {
		medidaAlimentoAnimal.setText(medidaMl.getText());
	}

	public void item3MedidaAlimentar() {
		medidaAlimentoAnimal.setText(medidaGramas.getText());
	}

	public void item4MedidaAlimentar() {
		medidaAlimentoAnimal.setText(medidaLitros.getText());
	}
}