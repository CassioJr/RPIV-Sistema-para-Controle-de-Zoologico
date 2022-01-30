package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GerenciamentoLanchoneteController {

	 @FXML
	    private TextField barraPesquisa;

	    @FXML
	    private TableColumn<?, ?> dataPedidoColuna;

	    @FXML
	    private TableColumn<?, ?> horaPedidoColuna;

	    @FXML
	    private TableColumn<?, ?> idColuna;

	    @FXML
	    private Label lblNomeUser;

	    @FXML
	    private TableColumn<?, ?> nomeColuna;

	    @FXML
	    private TableColumn<?, ?> quantidadeColuna;

	    @FXML
	    private TableView<?> tabelaAlimentos;

	    @FXML
	    private TableColumn<?, ?> valorColuna;

    @FXML
    void btnCastrarIngresso(ActionEvent event) {

    }

    @FXML
    void btnEditarIngresso(ActionEvent event) {

    }

    @FXML
    void btnFechaCaixa(ActionEvent event) {

    }

    @FXML
    void pesquisa(KeyEvent event) {

    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
    	AnchorPane fxmlApp = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoVendas.fxml"));
		Scene App = new Scene(fxmlApp);
		Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app.setScene(App);

    }

}
