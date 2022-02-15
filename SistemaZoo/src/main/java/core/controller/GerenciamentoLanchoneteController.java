package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.LanchoneteVendaDao;
import core.model.Alimento;
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

public class GerenciamentoLanchoneteController implements Initializable{

	@FXML private TextField barraPesquisa;

	@FXML private Label lblNomeUser;

	@FXML private TableView<Alimento> tabelaAlimentos;
	
	@FXML private TableColumn<Venda, String> dataPedidoColuna, horaPedidoColuna;

	@FXML private TableColumn<Venda, Long> idColuna;

	@FXML private TableColumn<Alimento, String> nomeColuna;

	@FXML private TableColumn<Venda, Long> quantidadeColuna;

	@FXML private TableColumn<Alimento, Double> valorColuna;
	 
	@FXML private TableColumn<Venda, Double> valorTotal;

	private ObservableList<Alimento> alimentos = FXCollections.observableArrayList();

	@FXML
	void btnCadastrarVendaAlimento(ActionEvent event) {
	trocarTela(event, "View_CadastroVendaAlimento");
	}

	@FXML
	void btnEditarVendaAlimento(ActionEvent event) throws IOException{
	    Alimento ali = tabelaAlimentos.getSelectionModel().getSelectedItem();
		if(ali != null) {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/view/View_EditarVendaAlimento.fxml"));
		Parent root = fxml.load();
		EditarVendaAlimentoController editar = fxml.getController();
        editar.inserirInformacoes(ali);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		}else {
			Mensagens.MSG("Por favor selecione um Alimento na tabela para realizar a edição");
		}
	}

	@FXML
	void voltar(ActionEvent event) throws IOException {
	trocarTela(event, "View_GerenciamentoVendas");
	}

	 public void listar(){
        idColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("idVenda"));
        dataPedidoColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("dataVenda"));
        horaPedidoColuna.setCellValueFactory(new PropertyValueFactory<Venda,String>("horaVenda"));
        quantidadeColuna.setCellValueFactory(new PropertyValueFactory<Venda,Long>("quantidade"));
        valorColuna.setCellValueFactory(new PropertyValueFactory<Alimento,Double>("valorUnitario"));
        nomeColuna.setCellValueFactory(new PropertyValueFactory<Alimento,String>("nomeAlimento"));
        valorTotal.setCellValueFactory(new PropertyValueFactory<Venda,Double>("valorTotal"));
		tabelaAlimentos.setItems(atualizaTabela());
	}

    public void pesquisa(ActionEvent event) {
        tabelaAlimentos.setItems(pesquisarAlimento());
    }

    
    //Método que serve para atualizar a tabela com as informaÃ§Ãµes dos animais
	public ObservableList<Alimento> atualizaTabela(){
        LanchoneteVendaDao venda = new LanchoneteVendaDao();
        alimentos = FXCollections.observableArrayList(venda.getListVendasLanchonete());
        return alimentos;
    }
    
    //Mï¿½todo que serve para buscar animais cadastrados especificos no sistema
	public ObservableList<Alimento> pesquisarAlimento(){
        ObservableList<Alimento> alimentopesquisado =  FXCollections.observableArrayList();
        for(int x=0; x<alimentos.size();x++) {
            if(alimentos.get(x).getNomeAlimento().contains(barraPesquisa.getText()) || alimentos.get(x).getDataVenda().contains(barraPesquisa.getText())){
                alimentopesquisado.add(alimentos.get(x));
            }
        }
        return alimentopesquisado;
    }

	public void trocarTela(ActionEvent event, String tela) {
		try {
			Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
		} catch (Exception e) {
			System.out.println("Erro ao carregar tela");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	listar();
	lblNomeUser.setText(LoginController.nomeFunc);
	}
}
