package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import dao.FuncionarioDao;
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
import model.Funcionario;

public class FuncionarioController implements Initializable
{
	@FXML 
	private TextField barraPesquisar;
	@FXML 
	private TableView<Funcionario> tabelaFuncionarios;
	@FXML 
	private TableColumn<Funcionario,Long> idColuna;
	@FXML 
	private TableColumn<Funcionario,String > nomeColuna, dtAdmissaoColuna, dtSaidaColuna, mtvSaidaColuna, enderecoColuna, funcaoColuna;
	@FXML 
	private TableColumn<Funcionario,Float>  telefoneColuna, salarioColuna;
	private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();

	//1Metodo para listar os funcionarios na tabela
	public void listarFuncionarios() 
	{
		idColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,Long>("id"));
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("nomeF"));
		dtAdmissaoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("dtAdmissaoF"));
		dtSaidaColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("dtSaidaF"));
		mtvSaidaColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("mtvSaidaF"));
		funcaoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("funcaoF"));
		enderecoColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("enderecoF"));
		telefoneColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,Float>("telefoneF"));
		salarioColuna.setCellValueFactory(new PropertyValueFactory<Funcionario,Float>("salarioF"));
		tabelaFuncionarios.setItems(atualizaTabela());
	}

	//2Metodo que atualiza a tabela com as informacoes dos funcionarios
	public ObservableList<Funcionario> atualizaTabela()
	{
		FuncionarioDao dao = new FuncionarioDao();
		funcionarios = FXCollections.observableArrayList(dao.getListFuncionario());
		return funcionarios;
	}
	
	//3Metodo que busca os funcionarios cadastrados especificos no sistema
	public ObservableList<Funcionario> pesquisarFuncionario()
	{
		ObservableList<Funcionario> funcionariopesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<funcionarios.size();x++) 
		{
			if(funcionarios.get(x).getNomeF().contains(barraPesquisar.getText()))
			{
				funcionariopesquisado.add(funcionarios.get(x));
			}
		}
		return funcionariopesquisado;
	}

	//4Metodo que eh executado na barra de pesquisa, que enquanto o usuario digita o programa mostra o funcionario compativel com o nome
	public void pesquisa() 
	{
		tabelaFuncionarios.setItems(pesquisarFuncionario());
	}

	//5Metodo que chama a view de cadastro de funcionario
	public void cadastrarFuncionario(ActionEvent event) throws IOException
	{
		VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_CadastroFuncionario.fxml"));
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

	//Metodo que chama a view de edicao de funcionario
	public void editarFuncionario(ActionEvent event) throws IOException
	{
			Funcionario f = tabelaFuncionarios.getSelectionModel().getSelectedItem();
			if(f != null) 
			{
			FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/view_editarFuncionario.fxml"));
			Parent root = fxmleditar.load();
			EditarFuncionarioController editarFuncionario = fxmleditar.getController();
			
			editarFuncionario.inserirInformacoes(String.valueOf(f.getId()),String.valueOf(f.getNomeF()),
					f.getDtAdmissaoF(),f.getDtSaidaF(),f.getMtvSaidaF(),f.getFuncaoF(), f.getEnderecoF(),
					String.valueOf(f.getTelefoneF()),String.valueOf(f.getSalarioF()));
			
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			}
			else 
			{
				MSG("Por favor selecione um funcionario na tabela para realizar a edicao");
			}	
	}

	//Metodo que retrocede para a tela anterior
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
		listarFuncionarios();
		
	}
}
