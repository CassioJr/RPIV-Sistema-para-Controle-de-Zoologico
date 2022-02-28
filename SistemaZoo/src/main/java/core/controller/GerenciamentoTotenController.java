package core.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import core.dao.TotenDao;
import core.model.Toten;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GerenciamentoTotenController implements Initializable {
	@FXML
	private TableColumn<Toten, String> alguem;

	@FXML
	private TableColumn<Toten, String> emergencia;

	@FXML
	private TableColumn<Toten, Long> id;

	@FXML
	private Label lblNomeUser;

	@FXML
	private TableColumn<Toten, String> perdido;

	@FXML
	private TableColumn<Toten, String> seguranca;

	@FXML
	private TableView<Toten> tabelaToten;

	private ObservableList<Toten> requisicoes = FXCollections.observableArrayList();
	@FXML
	private ListView<Toten> lista;

	public void listarRequisicoes() {
		id.setCellValueFactory(new PropertyValueFactory<Toten, Long>("id"));
		emergencia.setCellValueFactory(new PropertyValueFactory<Toten, String>("emergencia"));
		perdido.setCellValueFactory(new PropertyValueFactory<Toten, String>("estouPerdido"));
		seguranca.setCellValueFactory(new PropertyValueFactory<Toten, String>("seguranca"));
		alguem.setCellValueFactory(new PropertyValueFactory<Toten, String>("perdiAlguem"));
		tabelaToten.setItems(atualizaTabela());
	}

	public ObservableList<Toten> atualizaTabela() {
		TotenDao dao = new TotenDao();
		requisicoes = FXCollections.observableArrayList(dao.getListRequisicoes());
		return requisicoes;
	}

	@FXML
	void btnSair(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_Login.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
	}

	public void autoAtualizar() {
		final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
				listarRequisicoes();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNomeUser.setText(LoginController.nomeFunc);
		listarRequisicoes();
		autoAtualizar();
	}

}
