package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import dao.FornecedorDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fornecedor;

public class FornecedorController implements Initializable
{
	@FXML 
	private TextField barraPesquisaFor;
	@FXML 
	private TableView<Fornecedor> tabelaFornecedores;
	@FXML 
	private TableColumn<Fornecedor, Long> idColunaFor;
	@FXML 
	private TableColumn<Fornecedor, String > nomeColunaFor, endColunaFor;
	@FXML 
	private TableColumn<Fornecedor,Float>  telColunaFor;
	private ObservableList<Fornecedor> fornecedores = FXCollections.observableArrayList();
	
	//1Metodo para listar os fornecedores na tabela
	public void listarFornecedores() 
	{
		idColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor,Long>("id"));
		nomeColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor,String>("nomeFor"));
		endColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor,String>("endFor"));
		telColunaFor.setCellValueFactory(new PropertyValueFactory<Fornecedor,Float>("telefoneFor"));
		tabelaFornecedores.setItems(atualizaTabela());
	}
	
	//2Metodo que atualiza a tabela com as informacoes dos fornecedores
	public ObservableList<Fornecedor> atualizaTabela()
	{
		FornecedorDao dao = new FornecedorDao();
		fornecedores = FXCollections.observableArrayList(dao.getListFornecedor());
		return fornecedores;
	}
	
	//3Metodo que busca os fornecedores cadastrados especificos no sistema
	public ObservableList<Fornecedor> pesquisarFornecedor()
	{
		ObservableList<Fornecedor> fornecedorpesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<fornecedores.size();x++) 
		{
			if(fornecedores.get(x).getNomeFor().contains(barraPesquisaFor.getText()))
			{
				fornecedorpesquisado.add(fornecedores.get(x));
			}
		}
		return fornecedorpesquisado;
	}

	public void realizarpedido(){}
	
	//4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita o programa mostra o fornecedor compativel com o nome
	public void pesquisa() 
	{
		tabelaFornecedores.setItems(pesquisarFornecedor());
	}
	
	//5Metodo que chama a view de cadastro de fornecedor
	public void cadastrarFornecedor(ActionEvent event) throws IOException
	{
		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_CadastroFornecedor.fxml"));
	    Scene Espera = new Scene(fxmlEspera);
	    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    primaryStage.setScene(Espera);
	}
	
	//5.1Metodo que chama a view de pedidos
	public void abrirPedidos(ActionEvent event) throws IOException
	{
		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_PedidoAlimento.fxml"));
	    Scene Espera = new Scene(fxmlEspera);
	    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    primaryStage.setScene(Espera);
	}
	
	//6Metodo que apresenta uma msg de escolha perguntando sim ou nao ao usuario quando chamada, 
	//Recebe como parametro o conteudo da mensagem que sera apresentada ao usuario
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
	
	//7Metodo que apresenta uma msg ao usuario quando chamada
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atencao");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
	
	//8Metodo que chama a view de edicao de fornecedor
	public void editarFornecedor(ActionEvent event) throws IOException
	{
			Fornecedor fr = tabelaFornecedores.getSelectionModel().getSelectedItem();
			if(fr != null) 
			{
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/view_editarFornecedor.fxml"));
			Parent root = fxmleditar.load();
			EditarFornecedorController editarFornecedor = fxmleditar.getController();
			
			editarFornecedor.inserirInformacoes(String.valueOf(fr.getId()),String.valueOf(fr.getNomeFor()), fr.getEndFor(),	
																String.valueOf(fr.getTelefoneFor()));
			
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			else 
			{
				MSG("Por favor selecione um fornecedor na tabela para realizar a edicao");
			}	
	}
	
	//9Metodo que retrocede para a tela anterior
	public void voltar(ActionEvent event) throws IOException 
	{
		AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource(LoginController.tela));
	    Scene Espera = new Scene(fxmlEspera);
	    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    primaryStage.setScene(Espera);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		listarFornecedores();
		
	}
}