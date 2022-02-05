package core.controller;

import java.io.IOException;
import java.time.LocalDate;

import core.dao.FuncionarioDao;
import core.model.Funcionario;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditarFuncionarioController {

	@FXML private Label idlabel;
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

	// 1Metodo que realiza validacao e o salvamento do cadastro de funcionarios
	// E realiza um evento que chama outra tela
	public void salvarEdicao2(ActionEvent event) throws IOException {
		if (Mensagens.MSGEscolha("Deseja salvar a edição?") == true) {
			if (validarCampos() == true) {
				pegarInformacoes();
				voltar(event);
			}
		}
	}

	// 2Valida se os campos estao preenchidos
	// sem datas
	public boolean validarCampos() {
		if (nomef.getText().isEmpty() || funcao.getText().isEmpty()
		// || mtvsaida.getText().isEmpty()
				|| endereco.getText().isEmpty() || telefone.getText().isEmpty() || salario.getText().isEmpty()) {
			Mensagens.MSG("Voce deve preencher os campos em branco para poder salvar");
			return false;
		} else if (dtadmissao.isVisible() && dtadmissao.getValue() == null) {
			Mensagens.MSG("Preencha com uma data válida");
			return false;
		} else {
			return true;
		}
	}

	// 3Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/view_Funcionario.fxml"));
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(new Scene(fxmlEspera));
	}

	// 4Metodo que eh responsavel por pegar as informacoes dos TextField da tela
	public void pegarInformacoes() {
		Funcionario f = new Funcionario(nomef.getText(), dtadmissao.getValue().toString(), dataSaida(),
				mtvsaida.getText(), funcao.getText(), endereco.getText(), Float.parseFloat(telefone.getText()),
				Float.parseFloat(salario.getText()), tipoContrato.getText(), menuSituacao.getText());
		FuncionarioDao dao = new FuncionarioDao();
		dao.updateFuncionario(f);
	}

	// 5Metodo para impedir que letras sejam escritas nos campos numericos
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

	// 6Metodo que serve para adicionar as informacoes do funcionario selecionado da
	// tabela nos TextField
	public void inserirInformacoes(String id, String nomeed, String dtadmissaoed, String dtsaidaed, String mtvsaidaed,
			String funcaoed, String enderecoed, String telefoneed, String salarioed) {
		idlabel.setText(id);
		nomef.setText(nomeed);
		if (dtsaidaed != null) {
			dtsaida.setValue(LocalDate.parse(dtsaidaed));
		}
		if (dtadmissaoed != null) {
			dtadmissao.setValue(LocalDate.parse(dtadmissaoed));
		}
		mtvsaida.setText(mtvsaidaed);
		funcao.setText(funcaoed);
		endereco.setText(enderecoed);
		telefone.setText(telefoneed);
		salario.setText(salarioed);
	}

	public String dataAdmissao() {
		if (dtadmissao.getValue() != null) {
			return dtadmissao.getValue().toString();
		}
		return null;
	}

	public String dataSaida() {
		if (dtsaida.getValue() != null) {
			return dtsaida.getValue().toString();
		}
		return null;
	}

}
