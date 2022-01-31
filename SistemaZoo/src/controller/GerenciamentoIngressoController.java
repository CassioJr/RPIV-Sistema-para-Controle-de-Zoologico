package controller;

import java.io.IOException;

import dao.IngressoDao;
import dao.IngressoVendaDao;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Ingresso;
import model.Venda;

public class GerenciamentoIngressoController {

@FXML private TextField barraPesquisa;

    @FXML private Label lblNomeUser;

    @FXML private TableView<Ingresso> tabelaVendaIngrsso;
    
    @FXML private TableColumn<Venda,Long> idColuna;

    @FXML private TableColumn<Venda,String> dataVendaColuna;

    @FXML private TableColumn<Venda, String> horaVendaColuna;
    
    @FXML private TableColumn<Ingresso, String> nomeColuna;

    @FXML private TableColumn<Venda, Long> quantidadeColuna;

    @FXML private TableColumn<Ingresso, String> tipoIngressoColuna1;

    @FXML private TableColumn<Ingresso, Double> valorColuna;
    
    @FXML private TableColumn<Venda, Double> valorTotal;
    
    private ObservableList<Ingresso> ingressos = FXCollections.observableArrayList();


    @FXML
    void btnCadastrarIngresso(ActionEvent event) {
        trocarTela(event, "View_CadastroVendaIngresso");
    }

    @FXML
    void btnEditarIngresso(ActionEvent event) {
        trocarTela(event, "View_EditarVendaIngresso");
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        trocarTela(event, "View_GerenciamentoVendas");
    }

	@FXML
    public void listar(){
        idColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("idVenda"));
        dataVendaColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("dataVenda"));
        horaVendaColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("horaVenda"));
        quantidadeColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("quantidade"));
        valorColuna.setCellValueFactory(new PropertyValueFactory<Ingresso,Double>("valorUnitario"));
        nomeColuna.setCellValueFactory(new PropertyValueFactory<Ingresso,String>("tipoIngresso"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<Venda,Double>("valorTotal"));
		tabelaVendaIngrsso.setItems(atualizaTabela());
	}

    @FXML
    void pesquisa(ActionEvent event) {
        tabelaVendaIngrsso.setItems(pesquisarIngresso());
    }

    
    //Método que serve para atualizar a tabela com as informaÃ§Ãµes dos animais
    @FXML
	public ObservableList<Ingresso> atualizaTabela(){
        IngressoVendaDao venda = new IngressoVendaDao();
        ingressos = FXCollections.observableArrayList(venda.getListVendasIngresso());
        return ingressos;
    }
    
    //Mï¿½todo que serve para buscar animais cadastrados especificos no sistema
    @FXML
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
}