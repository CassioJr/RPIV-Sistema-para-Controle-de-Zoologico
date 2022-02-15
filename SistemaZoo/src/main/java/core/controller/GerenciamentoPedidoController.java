package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.PedidoDao;
import core.model.Pedido;
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

public class GerenciamentoPedidoController implements Initializable {
	@FXML private Label lblNomeUser;
	@FXML private TextField barraPesquisapedido;
	@FXML private TableView<Pedido> tabelaPedidos;
	@FXML private TableColumn<Pedido, Long> idColunas;
	@FXML private TableColumn<Pedido, String> alimentoColunas, dataColunas, fornecedorColunas, situacaoColunas;
	@FXML private TableColumn<Pedido, Integer> quantidadeColunas;
	private ObservableList<Pedido> pedidos = FXCollections.observableArrayList();

	// 1Metodo para listar os pedidos na tabela
	public void listarPedidos() {
		idColunas.setCellValueFactory(new PropertyValueFactory<Pedido, Long>("id"));
		alimentoColunas.setCellValueFactory(new PropertyValueFactory<Pedido, String>("alimentoPed"));
		dataColunas.setCellValueFactory(new PropertyValueFactory<Pedido, String>("dataPed"));
		fornecedorColunas.setCellValueFactory(new PropertyValueFactory<Pedido, String>("fornecedorPed"));
		quantidadeColunas.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("quantidadePed"));
		situacaoColunas.setCellValueFactory(new PropertyValueFactory<Pedido, String>("situacaoPed"));
		tabelaPedidos.setItems(atualizaTabela());
	}

	// 2Metodo que atualiza a tabela com as informacoes dos pedidos
	public ObservableList<Pedido> atualizaTabela() {
		PedidoDao dao = new PedidoDao();
		pedidos = FXCollections.observableArrayList(dao.getListPedido());
		return pedidos;
	}

	// 3Metodo que busca os pedidos cadastrados especificos no sistema
	public ObservableList<Pedido> pesquisarPedido() {
		ObservableList<Pedido> pedidopesquisado = FXCollections.observableArrayList();
		for (int x = 0; x < pedidos.size(); x++) {
			if (pedidos.get(x).getAlimentoPed().contains(barraPesquisapedido.getText())) {
				pedidopesquisado.add(pedidos.get(x));
			}
		}
		return pedidopesquisado;
	}

	// 4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita
	// o programa mostra o pedido compativel com o nome
	public void pesquisa() {
		tabelaPedidos.setItems(pesquisarPedido());
	}

	// 5Metodo que chama a view de cadastro de pedido
	public void cadastrarPedido(ActionEvent event) throws IOException {
		trocarTela(event, "View_CadastroPedidoAlimento");
	}

	// 5.1Metodo que chama a view de fornecedores
	public void checarFornecedores(ActionEvent event) throws IOException {
		trocarTela(event, "View_Fornecedor");
	}

	// 8Metodo que chama a view de edicao de pedido
	public void editarPedido(ActionEvent event) throws IOException {
		Pedido p = tabelaPedidos.getSelectionModel().getSelectedItem();
		if (p != null) {
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/View_editarPedido.fxml"));
			Parent root = fxmleditar.load();
			EditarPedidoController editarPedido = fxmleditar.getController();
			editarPedido.inserirInformacoes(p);
			editarPedido.habilitaCamposItem();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(root));
		} else {
			Mensagens.MSG("Por favor selecione um pedido na tabela para realizar a edicao");
		}
	}

	// 9Metodo que retrocede para a tela anterior
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
		lblNomeUser.setText(LoginController.nomeFunc);
		listarPedidos();
	}
}
