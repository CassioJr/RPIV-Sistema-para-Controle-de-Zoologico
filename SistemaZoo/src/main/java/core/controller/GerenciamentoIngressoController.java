package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.IngressoVendaDao;
import core.model.Ingresso;
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
import javafx.stage.Stage;

public class GerenciamentoIngressoController implements Initializable{

    @FXML private Label lblNomeUser;

    @FXML private  TextField barraPesquisa;
    
    @FXML private TableView<Ingresso> tabelaVendaIngresso;
    
    @FXML private TableColumn<Venda,Long> idColuna;
    
    @FXML private TableColumn<Ingresso,Long> nSerieIngresso;

    @FXML private TableColumn<Venda,String> dataVendaColuna, horaVendaColuna;
    
    @FXML private TableColumn<Venda, Long> quantidadeColuna;

    @FXML private TableColumn<Ingresso, String> nomeColuna;

    @FXML private TableColumn<Ingresso, String> tipoIngressoColuna1;

    @FXML private TableColumn<Ingresso, Double> valorColuna;
    
    @FXML private TableColumn<Venda, Double> valorTotal;
    
    private ObservableList<Ingresso> ingressos = FXCollections.observableArrayList();


    @FXML
    void btnCadastrarIngresso(ActionEvent event) {
        trocarTela(event, "View_CadastroVendaIngresso");
    }

    @FXML
    void btnEditarIngresso(ActionEvent event) throws IOException {
       	Ingresso in = tabelaVendaIngresso.getSelectionModel().getSelectedItem();
		if(in != null) {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/View_EditarVendaIngresso.fxml"));
		Parent root = fxml.load();
		EditarVendaIngressoController editar = fxml.getController();
        editar.inserirInformacoes(in);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		}else {
			Mensagens.MSG("Por favor selecione um Ingresso na tabela para realizar a ediÃ§Ã£o");
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
        valorColuna.setCellValueFactory(new PropertyValueFactory<Ingresso,Double>("valorUnitario"));
        nomeColuna.setCellValueFactory(new PropertyValueFactory<Ingresso,String>("tipoIngresso"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<Venda,Double>("valorTotal"));
		tabelaVendaIngresso.setItems(atualizaTabela());
	}

    public void pesquisa(ActionEvent event) {
        tabelaVendaIngresso.setItems(pesquisarIngresso());
    }

    
    //Método que serve para atualizar a tabela com as informaÃ§Ãµes dos animais
	public ObservableList<Ingresso> atualizaTabela(){
        IngressoVendaDao venda = IngressoVendaDao.getInstance();
        ingressos = FXCollections.observableArrayList(venda.getListVendasIngresso());
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



    public void trocarTela(ActionEvent event, String tela){
    try{
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxml));
    }catch(Exception e){
      System.out.println("Erro ao carregar tela");
  }
	}

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listar();	
		lblNomeUser.setText(LoginController.nomeFunc);
	}
}