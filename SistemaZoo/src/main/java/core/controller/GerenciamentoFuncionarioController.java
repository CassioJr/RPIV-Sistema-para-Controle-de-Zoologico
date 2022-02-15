package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.FuncionarioDao;
import core.model.Funcionario;
import core.utils.Mensagens;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GerenciamentoFuncionarioController implements Initializable {

	@FXML private Label lblNomeUser;
	@FXML private TextField barraPesquisar;
	@FXML private TableView<Funcionario> tabelaFuncionarios;
	@FXML private TableColumn<Funcionario, Long> idColuna;
	@FXML private TableColumn<Funcionario, String> nomeColuna, dtAdmissaoColuna, dtSaidaColuna, mtvSaidaColuna,
			enderecoColuna, funcaoColuna;
	@FXML private TableColumn<Funcionario, Float> telefoneColuna, salarioColuna;
	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();

	// 1Metodo para listar os funcionarios na tabela
	public void listarFuncionarios() {
		idColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, Long>("id"));
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nomeF"));
		dtAdmissaoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("dtAdmissaoF"));
		dtSaidaColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("dtSaidaF"));
		mtvSaidaColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("mtvSaidaF"));
		funcaoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("funcaoF"));
		enderecoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("enderecoF"));
		telefoneColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, Float>("telefoneF"));
		salarioColuna.setCellValueFactory(new PropertyValueFactory<Funcionario, Float>("salarioF"));
		tabelaFuncionarios.setItems(atualizaTabela());
	}

	// 2Metodo que atualiza a tabela com as informacoes dos funcionarios
	public ObservableList<Funcionario> atualizaTabela() {
		FuncionarioDao dao = new FuncionarioDao();
		funcionarios = FXCollections.observableArrayList(dao.getListFuncionario());
		return funcionarios;
	}

	// 3Metodo que busca os funcionarios cadastrados especificos no sistema
	public ObservableList<Funcionario> pesquisarFuncionario() {
		ObservableList<Funcionario> funcionariopesquisado = FXCollections.observableArrayList();
		for (int x = 0; x < funcionarios.size(); x++) {
			if (funcionarios.get(x).getNomeF().contains(barraPesquisar.getText())) {
				funcionariopesquisado.add(funcionarios.get(x));
			}
		}
		return funcionariopesquisado;
	}

	// 4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita
	// o programa mostra o funcionario compativel com o nome
	public void pesquisa() {
		tabelaFuncionarios.setItems(pesquisarFuncionario());
	}

	// 5Metodo que chama a view de cadastro de funcionario
	public void cadastrarFuncionario(ActionEvent event) throws IOException {
		trocarTela(event, "View_CadastroFuncionario");
	}

	// Metodo que chama a view de edicao de funcionario
	public void editarFuncionario(ActionEvent event) throws IOException {
		Funcionario f = tabelaFuncionarios.getSelectionModel().getSelectedItem();
		if (f != null) {
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/View_EditarFuncionario.fxml"));
			Parent root = fxmleditar.load();
			EditarFuncionarioController editarFuncionario = fxmleditar.getController();
			editarFuncionario.inserirInformacoes(f);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} else {
			Mensagens.MSG("Por favor selecione um funcionario na tabela para realizar a edicao");
		}
	}

	// Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException {
		trocarTela(event, LoginController.tela);
	}
	public void trocarTela(ActionEvent event, String tela) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
		} catch (IOException e) {
			System.out.println("Erro ao carregar tela");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarFuncionarios();
		lblNomeUser.setText(LoginController.nomeFunc);
	}
}
