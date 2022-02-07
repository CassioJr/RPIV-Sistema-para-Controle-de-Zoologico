package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.LembrancaVendaDao;
import core.model.Lembranca;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarVendaLembrancaController implements Initializable{

    @FXML private Label lblNomeUser;

    @FXML private Label idlabelfr;

    @FXML private TextField txtDataVenda;

    @FXML private TextField txtHoraVenda;

    @FXML private TextField txtQuantidade;

    @FXML private TextField txtNomeLembranca;

    @FXML private TextField txtValorUni;

    @FXML
    void salvarCadastro(ActionEvent event) throws IOException {
    	if(Mensagens.MSGEscolha("Você deseja salvar a venda do ingresso?") == true){
            if(validarCampos() == true){
                pegarInformacoes();
                voltar(event);
                Mensagens.MSG("Venda salva com sucesso!");
            }
        }
    }
    
    @FXML
    void pegarInformacoes(){
        LembrancaVendaDao venda = new LembrancaVendaDao();
        Lembranca v = new Lembranca(txtDataVenda.getText(), txtHoraVenda.getText(),Long.parseLong(txtQuantidade.getText()), calcularTotal(), txtNomeLembranca.getText(), Double.parseDouble(txtValorUni.getText()));
        venda.update(v, Long.parseLong(idlabelfr.getText()));
    }

    public void inserirInformacoes(String id, String data,String hora,String quantidade,String tipo,String valorUnit){
    idlabelfr.setText(id);
    txtDataVenda.setText(data);
    txtHoraVenda.setText(hora);
    txtQuantidade.setText(quantidade);
    txtNomeLembranca.setText(tipo);
    txtValorUni.setText(valorUnit);
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
    	Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoLojaLembraca.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
    }
    
    @FXML
    boolean validarCampos(){
    if(txtNomeLembranca.getText().isEmpty() || txtValorUni.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtHoraVenda.getText().isEmpty() || txtDataVenda.getText().isEmpty()){
        Mensagens.MSG("Você deve preencher todos os campos"); 
    return false;
    } 
    return true;
    }
    
    public Double calcularTotal(){
        Double total = Long.parseLong(txtQuantidade.getText()) * Double.parseDouble(txtValorUni.getText());
        return total;
    }
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblNomeUser.setText(LoginController.nomeFunc);
    }
}
