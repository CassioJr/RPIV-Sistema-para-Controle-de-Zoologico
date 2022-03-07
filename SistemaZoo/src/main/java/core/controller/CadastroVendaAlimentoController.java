package core.controller;

import java.net.URL;
import java.util.ResourceBundle;

import core.controller.bridge.CadastroVendaAlimentoBridge;
import core.controller.bridge.GerenciaBridge;
import core.controller.bridge.Gerenciamento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class CadastroVendaAlimentoController implements Initializable, Gerenciamento {
    protected GerenciaBridge bridge;
	
	@FXML
	public TextField txtDataVenda, txtHoraVenda, txtQuantidade, txtNomeAlimento, txtValorUni;

	@FXML
	private Label idlabelfr, lblNomeUser;


	@Override
	public void salvarCadastro(ActionEvent event) throws Exception{
		String[] obj= new String[5];
		obj[0] =  txtDataVenda.getText();
		obj[1] =  txtHoraVenda.getText();
		obj[2] =  txtQuantidade.getText();
		obj[3] =  txtNomeAlimento.getText();
		obj[4] =  txtValorUni.getText();
		System.out.println(obj.toString());
	    bridge.cadastrar(event, obj);
		
	}

	@Override
	public void voltar(ActionEvent event)throws Exception {
		bridge.voltar(event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
		this.bridge = new CadastroVendaAlimentoBridge();
	}

}
