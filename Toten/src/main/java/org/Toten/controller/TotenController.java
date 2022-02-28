package org.Toten.controller;

import java.io.IOException;
import java.util.Optional;

import org.Toten.App;
import org.Toten.dao.TotemDao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TotenController {
	@FXML private Button btnEmergencia;

	@FXML private Button btnEstouPerdido;

	@FXML private Button btnPerdiAlguem;

	@FXML private Button btnSeguranca;
	private TotemDao t;

	public void btnEstouPerdido(ActionEvent event) throws Exception {
		chamarTela(event, "View_Mapa");
	}

	public void btnVoltar(ActionEvent event) throws Exception {
		chamarTela(event, "View_Toten");
	}

	public void btnPerdiAlguem() {
		if(MSGEscolha("Você deseja chamar a segurança?") == true){
		realizaChamado("perdi_alguem");
		alerta("Chamando a segurança");
	}
	}

	public void btnSeguranca() {
		if(MSGEscolha("Você deseja chamar a segurança?") == true){
		realizaChamado("seguranca");
		alerta("Chamando a segurança");
		}
	}

	public void btnEstouPerdidoSeguranca(ActionEvent event) throws Exception {
		if(MSGEscolha("Você deseja chamar a segurança?") == true){
		realizaChamado("estou_perdido");
		alerta("Chamando a segurança");
		btnVoltar(event);
		}
	}


	public void realizaChamado(String campo){
		t= new TotemDao();
		t.addChamado(campo,"Requisitando");
	}

	public void btnEmergencia() {
		if(MSGEscolha("Você deseja chamar a segurança?") == true){
		realizaChamado("emergencia");
		alerta("Chamando Medicos");
		}
	}

	public void chamarTela(ActionEvent event, String tela) {
		try {
			Parent fxml = FXMLLoader.load(App.class.getResource(tela + ".fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(new Scene(fxml));
		} catch (IOException e) {
			System.out.println("Erro ao carregar tela");
		}
	}

	public void alerta(String msg) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Atenção");
		a.setHeaderText(null);
		a.setContentText(msg);
		a.showAndWait();
	}

	public static boolean MSGEscolha(String msg) {
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

}
