package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.FuncionarioDao;
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
import model.Funcionario;

public class FuncionarioController implements Initializable{
	
@FXML 
private TextField barraPesquisaF;
@FXML 
private TableView<Funcionario> tabelaFuncionarios;
@FXML 
private TableColumn<Funcionario,String > nomeF, dtAdmissaoF, dtSaidaF, mtvSaidaF, enderecoF;
@FXML 
private TableColumn<Funcionario,Float> salarioF, telefoneF ;
@FXML 
private TableColumn<Funcionario,Long> codF;
private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();

//Método para listar os funcionarios na tabela
public void listarFuncionarios() {
	codF.setCellValueFactory(new PropertyValueFactory<Funcionario,Long>("codF"));
	nomeF.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("nomeF"));
	dtAdmissaoF.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("dtAdmissaoF"));
	dtSaidaF.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("dtSaidaF"));
	mtvSaidaF.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("mtvSaidaF"));
	enderecoF.setCellValueFactory(new PropertyValueFactory<Funcionario,String>("enderecoF"));
	salarioF.setCellValueFactory(new PropertyValueFactory<Funcionario,Float>("salarioF"));
	telefoneF.setCellValueFactory(new PropertyValueFactory<Funcionario,Float>("telefoneF"));
	tabelaFuncionarios.setItems(atualizaTabela());
}

//Método que atualiza a tabela com as informações dos funcionarios
	public ObservableList<Funcionario> atualizaTabela(){
		FuncionarioDao dao = new FuncionarioDao();
		funcionarios = FXCollections.observableArrayList(dao.getListFuncionario());
		return funcionarios;
	}
	
//Método que serve para buscar os funcionários cadastrados especificos no sistema
	public ObservableList<Funcionario> pesquisarFuncionario(){
		ObservableList<Funcionario> funcionariopesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<funcionarios.size();x++) {
			if(funcionarios.get(x).getNomeFuncionario().contains(barraPesquisaF.getText())) {
				funcionariopesquisado.add(funcionarios.get(x));
			}
		}
		return funcionariopesquisado;
	}

//Metódo que é executado na barra de pesquise, que enquanto o usuario digita o programa mostra o funcionário compativel com o nome
public void pesquisa() {
	tabelaFuncionarios.setItems(pesquisarFuncionario());
}

//Método que chama a view de cadastro de funcionario
public void cadastrarFuncionario(ActionEvent event) throws IOException{
	VBox fxmlEspera = (VBox) FXMLLoader.load(getClass().getResource("/view/View_CadastroFuncionario.fxml"));
    Scene Espera = new Scene(fxmlEspera);
    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    primaryStage.setScene(Espera);
}

/*
//Método que chama a view de edição de funcionário
public void editarFuncionario(ActionEvent event) throws IOException{
		Funcionario f = tabelaFuncionarios.getSelectionModel().getSelectedItem();
		if(f != null) {
		FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/view_editarFuncionario.fxml"));
		Parent root = fxmleditar.load();
		editarFuncionarioController editarFuncionario = fxmleditar.getController();
		editarFuncionario.inserirInformacoes(String.valueOf(f.getCodF()),a.getNomeFuncionario()));falta
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();}
		else {
			MSG("Por favor selecione um animal na tabela para realizar a edição");
		}	
}
*/

//Metódo que retrocede para a tela anterior
public void voltar(ActionEvent event) throws IOException {
	AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/telaApp.fxml"));
    Scene Espera = new Scene(fxmlEspera);
    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    primaryStage.setScene(Espera);
}

/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
 *você deseja apresentar na mensagem que sera apresentada ao usuario*/
public void MSG(String msg) {
	Alert alerta = new Alert(Alert.AlertType.WARNING);
	alerta.setTitle("Atenção");
	alerta.setHeaderText(null);
	alerta.setContentText(msg);
	alerta.showAndWait();
}

/*Metodo que apresenta uma msg de escolha perguntando sim ou não ao usuario quando chamada, 
 * ela recebe como parametro o conteudo que você deseja apresentar na mensagem que sera apresentada ao usuario*/
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

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
}
