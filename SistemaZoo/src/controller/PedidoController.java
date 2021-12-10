package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import dao.PedidoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import model.Pedido;

public class PedidoController implements Initializable
{
	@FXML 
	private TextField barraPesquisapedido;
	@FXML 
	private TableView<Pedido> tabelaPedidos;
	@FXML 
	private TableColumn<Pedido, Long> idColunas;
	@FXML 
	private TableColumn<Pedido, String > alimentoColunas, dataColunas, fornecedorColunas;
	@FXML 
	private TableColumn<Pedido, Integer>  quantidadeColunas;
	private ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
	
	//1Metodo para listar os pedidos na tabela
	public void listarPedidos() 
	{
		idColunas.setCellValueFactory(new PropertyValueFactory<Pedido,Long>("id"));
		alimentoColunas.setCellValueFactory(new PropertyValueFactory<Pedido,String>("alimentoPed"));
		dataColunas.setCellValueFactory(new PropertyValueFactory<Pedido,String>("dataPed"));
		fornecedorColunas.setCellValueFactory(new PropertyValueFactory<Pedido,String>("fornecedorPed"));
		quantidadeColunas.setCellValueFactory(new PropertyValueFactory<Pedido,Integer>("quantidadePed"));
		tabelaPedidos.setItems(atualizaTabela());
	}
	
	
	//2Metodo que atualiza a tabela com as informacoes dos pedidos
	public ObservableList<Pedido> atualizaTabela()
	{
		PedidoDao dao = new PedidoDao();
		pedidos = FXCollections.observableArrayList(dao.getListPedido());
		return pedidos;
	}
	
	//3Metodo que busca os pedidos cadastrados especificos no sistema
	public ObservableList<Pedido> pesquisarPedido()
	{
		ObservableList<Pedido> pedidopesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<pedidos.size();x++) 
		{
			if(pedidos.get(x).getAlimentoPed().contains(barraPesquisapedido.getText()))
			{
				pedidopesquisado.add(pedidos.get(x));
			}
		}
		return pedidopesquisado;
	}
	
	//4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita o programa mostra o pedido compativel com o nome
	public void pesquisa() 
	{
		tabelaPedidos.setItems(pesquisarPedido());
	}
	
	//5Metodo que chama a view de cadastro de pedido
	public void cadastrarPedido(ActionEvent event) throws IOException
	{
		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_CadastroPedidoAlimento.fxml"));
	    Scene Espera = new Scene(fxmlEspera);
	    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    primaryStage.setScene(Espera);
	}
	
	//5.1Metodo que chama a view de fornecedores
	public void checarFornecedores(ActionEvent event) throws IOException
	{
		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/view_Fornecedor.fxml"));
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
	
	/*8Metodo que chama a view de edicao de pedido
	public void editarPedido(ActionEvent event) throws IOException
	{
			Pedido p = tabelaPedidos.getSelectionModel().getSelectedItem();
			if(p != null) 
			{
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/view_editarPedido.fxml"));
			Parent root = fxmleditar.load();
			EditarPedidoController editarPedido = fxmleditar.getController();
			
			editarPedido.inserirInformacoes(String.valueOf(p.getId()),String.valueOf(p.getAlimentoPed()), String.valueOf(p.getDataPed()),	
					String.valueOf(p.getFornecedorPed()), String.valueOf(p.getQuantidadePed()));
			
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			else 
			{
				MSG("Por favor selecione um fornecedor na tabela para realizar a edicao");
			}	
	}*/
	
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
		listarPedidos();
		
	}
}









