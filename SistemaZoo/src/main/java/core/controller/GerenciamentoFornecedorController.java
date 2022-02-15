package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.FornecedorDao;
import core.model.Fornecedor;
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

public class GerenciamentoFornecedorController implements Initializable {
	
	@FXML private Label lblNomeUser;
	@FXML private TextField barraPesquisaFor;
	@FXML private TableView<Fornecedor> tabelaFornecedores;
	@FXML private TableColumn<Fornecedor, Long> idColunaFor;
	@FXML private TableColumn<Fornecedor, String> nomeColunaFor, endColunaFor;
	@FXML private TableColumn<Fornecedor, Float> telColunaFor;
	@FXML private TableColumn<Fornecedor, String> emailColunaFor;
	@FXML private TableColumn<Fornecedor, Float> cnpjColunaFor;
	@FXML private TableColumn<Fornecedor, String> cidadeColunaFor;
	@FXML private TableColumn<Fornecedor, String> formaspColunaFor;
	private ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();

	// 1Metodo para listar os fornecedores na tabela
	public void listarFornecedores() {
		idColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, Long>("id"));
		nomeColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nomeFor"));
		endColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endFor"));
		telColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, Float>("telefoneFor"));
		emailColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("emailFor"));
		cnpjColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, Float>("cnpjFor"));
		cidadeColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("cidadeFor"));
		formaspColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("formaspFor"));
		tabelaFornecedores.setItems(atualizaTabela());
	}

	// 2Metodo que atualiza a tabela com as informacoes dos fornecedores
	public ObservableList<Fornecedor> atualizaTabela() {
		FornecedorDao dao = new FornecedorDao();
		fornecedores = FXCollections.observableArrayList(dao.getListFornecedor());
		return fornecedores;
	}

	// 3Metodo que busca os fornecedores cadastrados especificos no sistema
	public ObservableList<Fornecedor> pesquisarFornecedor() {
		ObservableList<Fornecedor> fornecedorpesquisado = FXCollections.observableArrayList();
		for (int x = 0; x < fornecedores.size(); x++) {
			if (fornecedores.get(x).getNomeFor().contains(barraPesquisaFor.getText())) {
				fornecedorpesquisado.add(fornecedores.get(x));
			}
		}
		return fornecedorpesquisado;
	}

	public void realizarpedido() {
	}

	// 4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita
	// o programa mostra o fornecedor compativel com o nome
	public void pesquisa() {
		tabelaFornecedores.setItems(pesquisarFornecedor());
	}
	
	// 5
	// 5Metodo que chama a view de cadastro de fornecedor
	public void cadastrarFornecedor(ActionEvent event) throws IOException {
		trocarTela(event, "View_CadastroFornecedor");
	}

	// 5.1Metodo que chama a view de pedidos
	public void abrirPedidos(ActionEvent event) throws IOException {
		trocarTela(event, "View_PedidoAlimento");
	}
	
	// 6Metodo que retrocede para a tela anterior
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

	// 7Metodo que chama a view de edicao de fornecedor
	public void editarFornecedor(ActionEvent event) throws IOException {
		Fornecedor fr = tabelaFornecedores.getSelectionModel().getSelectedItem();
		if (fr != null) {
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/View_EditarFornecedor.fxml"));
			Parent root = fxmleditar.load();
			EditarFornecedorController editarFornecedor = fxmleditar.getController();
			editarFornecedor.inserirInformacoes(fr);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} else {
			Mensagens.MSG("Por favor selecione um fornecedor na tabela para realizar a edicao");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarFornecedores();
		lblNomeUser.setText(LoginController.nomeFunc);
	}
}