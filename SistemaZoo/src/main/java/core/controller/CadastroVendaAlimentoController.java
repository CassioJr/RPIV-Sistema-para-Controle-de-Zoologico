package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.IngressoVendaDao;
import core.dao.LanchoneteVendaDao;
import core.model.Alimento;
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

public class CadastroVendaAlimentoController implements Initializable{
    
    @FXML private Label idlabelfr, lblNomeUser;

    @FXML private TextField txtDataVenda, txtHoraVenda, txtQuantidade, txtNomeAlimento, txtValorUni;

    @FXML
    void salvarCadastro(ActionEvent event) throws IOException {
        if(Mensagens.MSGEscolha("VocÃª deseja salvar a venda do ingresso?") == true){
            if(validarCampos() == true){
                pegarInformacoes();
                voltar(event);
                Mensagens.MSG("Venda salva com sucesso!");
            }
        }
    }

    @FXML
    void pegarInformacoes(){
        LanchoneteVendaDao  venda = new LanchoneteVendaDao();
        Alimento v = new Alimento( txtDataVenda.getText(), txtHoraVenda.getText(),Long.parseLong(txtQuantidade.getText()), calcularTotal(), txtNomeAlimento.getText(), Double.parseDouble(txtValorUni.getText()));
       venda.add(v);
    }

    public Double calcularTotal(){
        Double total = Long.parseLong(txtQuantidade.getText()) * Double.parseDouble(txtValorUni.getText());
        return total;
    }

    @FXML
    boolean validarCampos(){
    if(txtNomeAlimento.getText().isEmpty() || txtValorUni.getText().isEmpty() || txtQuantidade.getText().isEmpty() || txtHoraVenda.getText().isEmpty() || txtDataVenda.getText().isEmpty()){
        Mensagens.MSG("VocÃª deve preencher todos os campos"); 
    return false;
    } 
    return true;
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoLanchonete.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);	
	}

}
