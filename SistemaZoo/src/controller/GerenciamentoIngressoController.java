package controller;

import java.io.IOException;

import dao.IngressoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Ingresso;

public class GerenciamentoIngressoController {

	@FXML private TextField barraPesquisa;

    @FXML private Label lblNomeUser;

    @FXML private TableView<Ingresso> tabelaVendaIngrsso;
	
    @FXML private TableColumn<Long, Ingresso> idColuna;

    @FXML private TableColumn<String,Ingresso> dataVendaColuna;

    @FXML private TableColumn<String, Ingresso> horaVendaColuna;
	
    @FXML private TableColumn<String, Ingresso> nomeColuna;

    @FXML private TableColumn<Long, Ingresso> quantidadeColuna;

    @FXML private TableColumn<String, Ingresso> tipoIngressoColuna1;

    @FXML private TableColumn<Double, Ingresso> valorColuna;
	
	private ObservableList<Ingresso> ingressos = FXCollections.observableArrayList();


	@FXML
	void btnCadastrarIngresso(ActionEvent event) {
		trocarTela(event, "View_GerenciamentoVendas");
	}

	@FXML
	void btnEditarIngresso(ActionEvent event) {
		trocarTela(event, "View_GerenciamentoVendas");
	}

	public void listar(){

	}
	
	//Método que serve para atualizar a tabela com as informaÃ§Ãµes dos animais
	public ObservableList<Ingresso> atualizaTabela(){
		ingressos = FXCollections.observableArrayList();
		return ingressos;
	}
	
	//Mï¿½todo que serve para buscar animais cadastrados especificos no sistema
	public ObservableList<Ingresso> pesquisarIngresso(){
		ObservableList<Ingresso> ingressopesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<ingressos.size();x++) {
			if(ingressos.get(x).getTipoIngresso().contains(barraPesquisa.getText()) || ingressos.get(x).getDataVenda().contains(barraPesquisa.getText())){
				ingressopesquisado.add(ingressos.get(x));
			}
		}
		return ingressopesquisado;
	}


	@FXML
	void pesquisa(ActionEvent event) {
		tabelaVendaIngrsso.setItems(pesquisarIngresso());
	}

	@FXML
	void voltar(ActionEvent event) throws IOException {
		trocarTela(event, "View_GerenciamentoVendas");
	}

	public void trocarTela(ActionEvent event, String tela){
    try{
		Parent fxml = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(fxml));
    }catch(Exception e){
      System.out.println("Erro ao carregar tela");
  }

	}
}