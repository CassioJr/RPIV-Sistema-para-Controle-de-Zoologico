package core.controller;

import java.io.IOException;

import core.dao.IngressoVendaDao;
import core.model.Ingresso;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarVendaIngressoController {

    @FXML private Label lblNomeVet;

    @FXML private Label idlabelfr;

    @FXML private TextField txtDataVenda;

    @FXML private TextField txtHoraVenda;

    @FXML private TextField txtQuantidade;

    @FXML private TextField txtTipoIngresso;

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
        IngressoVendaDao venda = IngressoVendaDao.getInstance();
        Ingresso v = new Ingresso(txtTipoIngresso.getText(), Double.parseDouble(txtValorUni.getText()),  txtDataVenda.getText(), txtHoraVenda.getText(), Long.parseLong(txtQuantidade.getText()), calcularTotal());
        venda.update(v, Long.parseLong(idlabelfr.getText()));
    }

    public void inserirInformacoes(Ingresso in){
        idlabelfr.setText(String.valueOf(in.getIdVenda()));
        txtDataVenda.setText(in.getDataVenda());
        txtHoraVenda.setText(in.getHoraVenda());
        txtQuantidade.setText(String.valueOf(in.getQuantidade()));
        txtTipoIngresso.setText(in.getTipoIngresso());
        txtValorUni.setText(String.valueOf(in.getValorUnitario()));
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
    	Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoIngressos.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
    }
    
    @FXML
    boolean validarCampos(){
    if(txtTipoIngresso.getText().isEmpty() || txtValorUni.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtHoraVenda.getText().isEmpty() || txtDataVenda.getText().isEmpty()){
        Mensagens.MSG("Você deve preencher todos os campos"); 
    return false;
    } 
    return true;
    }
    
    public Double calcularTotal(){
        Double total = Long.parseLong(txtQuantidade.getText()) * Double.parseDouble(txtValorUni.getText());
        return total;
    }

}

