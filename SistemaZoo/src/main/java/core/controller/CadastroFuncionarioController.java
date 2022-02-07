package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.FuncionarioDao;
import core.dao.LoginDao;
import core.model.Funcionario;
import core.model.Login;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CadastroFuncionarioController implements Initializable{
	@FXML private Label lblNomeUser;
	@FXML private TextField nomef;
	@FXML private DatePicker dtadmissao;
	@FXML private DatePicker dtsaida;
	@FXML private TextArea mtvsaida;
	@FXML private TextField funcao;
	@FXML private TextField endereco;
	@FXML private TextField telefone;
	@FXML private TextField salario;
	@FXML private MenuButton menuFuncao, tipoContrato, menuSituacao;
	@FXML private MenuItem itemCotratExterno, itemFuncParq, itemFuncao1, itemFuncao2, itemFuncao3, itemFuncao4, itemFuncao5,
			itemFuncao6, itemFuncao7, itemFuncao8, itemContratado, itemDemitido;
	@FXML private Text lblDataSaida, lblMotivoSaida, lblData, lblDataAdmicao;

	// 1Metodo que realiza o salvamento do cadastro de funcionarios faz um evento
	// chamando outra tela
	public void salvarCadastroF(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar o cadastro?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}

	// 2Valida se os campos estao preenchidos
	public boolean validarCampos() {
		if (nomef.getText().isEmpty() || menuFuncao.getText().isEmpty() || endereco.getText().isEmpty()
				|| telefone.getText().isEmpty() || salario.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
			return false;
		} else if (dtadmissao.isVisible() && dtadmissao.getValue() == null) {
			Mensagens.MSG("Preencha com uma data válida");
			return false;
		} else {
			return true;
		}
	}

	// 3Metodo para impedir que letras sejam escritas nos campos numericos
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

	public void itemFunc1() {
		menuFuncao.setText(itemFuncao1.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc2() {
		menuFuncao.setText(itemFuncao2.getText());
		tipoContrato.setVisible(true);
	}

	public void itemFunc3() {
		menuFuncao.setText(itemFuncao3.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc4() {
		menuFuncao.setText(itemFuncao4.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc5() {
		menuFuncao.setText(itemFuncao5.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc6() {
		menuFuncao.setText(itemFuncao6.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc7() {
		menuFuncao.setText(itemFuncao7.getText());
		tipoContrato.setVisible(false);
	}

	public void itemFunc8() {
		menuFuncao.setText(itemFuncao8.getText());
		tipoContrato.setVisible(false);
	}

	public void itemTipoContrato1() {
		tipoContrato.setText(itemFuncParq.getText());
	}

	public void itemTipoContrato2() {
		tipoContrato.setText(itemCotratExterno.getText());
	}

	public void itemSituacaoFunc1() {
		menuSituacao.setText(itemContratado.getText());
		lblDataSaida.setVisible(false);
		lblMotivoSaida.setVisible(false);
		dtsaida.setVisible(false);
		mtvsaida.setVisible(false);
		dtadmissao.setVisible(true);
		lblDataAdmicao.setVisible(true);
	}

	public void itemSituacaoFunc2() {
		menuSituacao.setText(itemDemitido.getText());
		lblDataSaida.setVisible(true);
		lblMotivoSaida.setVisible(true);
		dtsaida.setVisible(true);
		mtvsaida.setVisible(true);
		dtadmissao.setVisible(false);
		lblDataAdmicao.setVisible(false);
	}

	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Funcionario f = new Funcionario(nomef.getText(), dtadmissao.getValue().toString(), dataSaida(),
				mtvsaida.getText(), menuFuncao.getText(), endereco.getText(), Float.parseFloat(telefone.getText()),
				Float.parseFloat(salario.getText()), tipoContrato.getText(), menuSituacao.getText());
		FuncionarioDao dao = new FuncionarioDao();
		dao.addFuncionario(f);
		criarLogin();
	}

		// 6Metodo que cadastro um login que gera um id unico e senha padrão para o funcionario
		// e pega informações referentes a nome e funcao do mesmo
	public void criarLogin() {
		LoginDao l = new LoginDao();
		if (menuFuncao.getText().equals("Gerente") || menuFuncao.getText().equals("Veterinario") || menuFuncao.getText().equals("Cuidador") 
			|| menuFuncao.getText().equals("Bilheterista")) {
			Login login = new Login(nomef.getText(), "123", menuFuncao.getText());
			l.addLogin(login);
		}
	}

	// 5Preencher null
	public String dataSaida() {
		if (dtsaida.getValue() != null) {
			return dtsaida.getValue().toString();
		}
		return null;
	}

	// 9Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_Funcionario.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
		
	}
}