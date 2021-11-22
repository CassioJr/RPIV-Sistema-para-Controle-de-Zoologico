package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InternacaoController implements Initializable {
	
	
	  @FXML
	    private Label lblData;

	    @FXML
	    private Label lblHora;

	    @FXML
	    private Label lblNomeVet;
	    
	    @FXML
	    private Pane panaCad;

	    @FXML
	    private TableView<String> tabelaInternados;

	    @FXML
	    void btnAlta(ActionEvent event) {

	    }

	    @FXML
	    void btnInternacao(ActionEvent event) {
	    	panaCad.setVisible(true);
	    }

	    @FXML
	    void btnObito(ActionEvent event) {

	    }

	    @FXML
	    void btnSair(ActionEvent event) throws IOException {
	    	 AnchorPane fxmlApp = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/telaApp.fxml"));
	         Scene App = new Scene(fxmlApp);
	         Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
	         app.setScene(App);
	    }

	    @FXML
	    void btnSalvar(ActionEvent event) {

	    }
	    
	    @FXML
	    void btnCancela(ActionEvent event) {
	    	panaCad.setVisible(false);
	    }
	    
	    @FXML
	    void btnConsulta(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
			String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
			lblData.setText(data);
			lblHora.setText(hora);
			
		}

}
