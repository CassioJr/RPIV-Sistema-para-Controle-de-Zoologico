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

public class CadastroAnimalController {

	@FXML private TextField nomeAnimal;
	@FXML private TextField idadeAnimal;
	@FXML private MenuButton situacaoAnimal, sexoAnimal, medidaAlimentoAnimal;
	@FXML private MenuItem macho, femea;
	@FXML private TextField instituicaoOrigemAnimal;
	@FXML private TextField instituicaoDestinoAnimal;
	@FXML private MenuItem transferidodeoutra, transferidoparaoutra, comprado, nascidoLocalmente;
	@FXML private MenuButton estadoSaude;
	@FXML private MenuItem saudavel, doente;
	@FXML private TextField nomeEspecieAnimal;
	@FXML private TextField habitatAnimal;
	@FXML private TextField nomeDoenca;
	@FXML private DatePicker dataTransferencia;
	@FXML private TextField numeroAbrigoAnimal;
	@FXML private TextField localizacaoAnimal;
	@FXML private TextField tamanhoAnimal;
	@FXML private TextField nomeAlimentoAnimal;
	@FXML private TextField quantidadeAlimentoAnimal;
	@FXML private Text labelinstituicaoDestino, labelDataTransferencia, labelOrigem;
	@FXML private Text labelNomeDoenca;
	@FXML private MenuItem medidaKg, medidaLitros, medidaMl, medidaGramas;

	/*
	 * Metódo que chama os metodos de validações e realiza o salvamento do cadastro
	 * do animal, ele realiza um evento que seria o de chamar outra tela
	 */
	public void salvarCadastro(ActionEvent event) throws IOException {
		if (MSGEscolha("Você deseja salvar o cadastro?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoAnimal.fxml"));
				Scene Espera = new Scene(fxmlEspera);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(Espera);
				MSG("Cadastro salvo com sucesso!");
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
					|| quantidadeAlimentoAnimal.getText().isEmpty() || medidaAlimentoAnimal.getText().isEmpty() || instituicaoOrigemAnimal.isVisible() && instituicaoOrigemAnimal.getText().isEmpty()|| instituicaoDestinoAnimal.isVisible() && instituicaoDestinoAnimal.getText().isEmpty()) {
				MSG("Você deve preencher os campos em branco para poder salvar");
				return false;
			}else if(dataTransferencia.isVisible() && dataTransferencia.getValue() == null){
				MSG("Preencha com uma data válida");
				return false;
			}else {
				return true;
			}
		}

	// Método para impedir que letras sejam escritas nos campos numericos
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

	// Metódo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoAnimal.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
	}

	// Método que é responsavel por pegar as informações dos TextField da tela
	public void pegarInformacoes() {
		Animal a = new Animal(nomeAnimal.getText(), nomeAlimentoAnimal.getText(), situacaoAnimal.getText(),
				localizacaoAnimal.getText(), instituicaoOrigemAnimal.getText(), instituicaoDestinoAnimal.getText(),
				nomeEspecieAnimal.getText(), habitatAnimal.getText(), estadoSaude.getText(), nomeDoenca.getText(),
				Integer.parseInt(idadeAnimal.getText()), sexoAnimal.getText(),
				Integer.parseInt(numeroAbrigoAnimal.getText()), dataTransferencia(), medidaAlimentoAnimal.getText(),
				Float.parseFloat(tamanhoAnimal.getText()), Float.parseFloat(quantidadeAlimentoAnimal.getText()),
				Boolean.parseBoolean("false"));
		AnimalDao dao = new AnimalDao();
		dao.addAnimal(a);
	}

	public String dataTransferencia() {
		if (dataTransferencia.getValue() != null) {
			return dataTransferencia.getValue().toString();
			}
		return null;
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

	/*
	 * Metodo que apresenta uma msg de escolha perguntando sim ou não ao usuario
	 * quando chamada, ela recebe como parametro o conteudo que você deseja
	 * apresentar na mensagem que sera apresentada ao usuario
	 */
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

	/*
	 * Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como
	 * parametro o conteudo que você deseja apresentar na mensagem que sera
	 * apresentada ao usuario
	 */
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
}