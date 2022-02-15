package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.LembrancaVendaDao;
import core.model.Lembranca;
import core.model.Venda;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class GerenciamemtoLojaLembrancaController implements Initializable{

	@FXML private Label lblNomeUser;
	
	@FXML private TextField barraPesquisa;

	@FXML private TableView<Lembranca> tabelaLembrancas;

	@FXML private TableColumn<Lembranca, String> nomeColuna;
	
	@FXML private TableColumn<Lembranca, Double> valorColuna;
	
	@FXML private TableColumn<Venda, Long> idColuna;

	@FXML private TableColumn<Venda, String> dataVendaColuna, horaVendaColuna;

	@FXML private TableColumn<Venda, Long> quantidadeColuna;

	@FXML private TableColumn<Venda, Double> valorTotal;

	private ObservableList<Lembranca> lembrancas = FXCollections.observableArrayList();


	@FXML void VendaLembranca(ActionEvent event) {
	trocarTela(event, "View_CadastroVendaLembranca");
	}

	@FXML
	void btnEditarVendaLembranca(ActionEvent event) throws IOException {
		Lembranca lem = tabelaLembrancas.getSelectionModel().getSelectedItem();
		if(lem != null) {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/View_EditarVendaLembranca.fxml"));
		Parent root = fxml.load();
		EditarVendaLembrancaController editar = fxml.getController();
        editar.inserirInformacoes(lem);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		}else {
			Mensagens.MSG("Por favor selecione uma Lembrança na tabela para realizar a edição");
		}
	}

	@FXML
	void voltar(ActionEvent event) throws IOException {
	trocarTela(event, "View_GerenciamentoVendas");
	}

	public void listar(){
        idColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("idVenda"));
        dataVendaColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("dataVenda"));
        horaVendaColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("horaVenda"));
        quantidadeColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("quantidade"));
        valorColuna.setCellValueFactory(new PropertyValueFactory<Lembranca,Double>("valorUnitario"));
        nomeColuna.setCellValueFactory(new PropertyValueFactory<Lembranca,String>("nomeLembranca"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<Venda,Double>("valorTotal"));
		tabelaLembrancas.setItems(atualizaTabela());
	}

    public void pesquisa(ActionEvent event) {
        tabelaLembrancas.setItems(pesquisarLembranca());
    }

    
    //Método que serve para atualizar a tabela com as informaÃ§Ãµes dos animais
	public ObservableList<Lembranca> atualizaTabela(){
        LembrancaVendaDao venda = new LembrancaVendaDao();
        lembrancas = FXCollections.observableArrayList(venda.getListVendasLembrancas());
        return lembrancas;
    }
    
    //Mï¿½todo que serve para buscar animais cadastrados especificos no sistema
	public ObservableList<Lembranca> pesquisarLembranca(){
        ObservableList<Lembranca> lembrancapesquisada =  FXCollections.observableArrayList();
        for(int x=0; x<lembrancas.size();x++) {
            if(lembrancas.get(x).getNomeLembranca().contains(barraPesquisa.getText()) || lembrancas.get(x).getDataVenda().contains(barraPesquisa.getText())){
                lembrancapesquisada.add(lembrancas.get(x));
            }
        }
        return lembrancapesquisada;
    }

	
	public void trocarTela(ActionEvent event, String tela){
	    try{
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
	    }catch(Exception e){
	      System.out.println("Erro ao carregar tela");
		  System.out.println(e);
	  }
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listar();
		lblNomeUser.setText(LoginController.nomeFunc);
	}
}
