package controller;
import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Animal;

public class AnimalController{

@FXML
private TableView<Animal> tabelaAnimais;
@FXML
private TableColumn<String, Animal> idColuna, nomeColuna, sexoColuna, situacaoColuna;

/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
* você deseja apresentar na mensagem que sera apresentada ao usuario*/
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}

    //Método que chama a view de cadastro de animal
    public void cadastrarAnimal(ActionEvent event) throws IOException{
    	BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_CadastroAnimal.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    //Método que chama a view de edição de animal
    public void editarAnimal(){}

    //Metódo que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Sample.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
	/*Metodo que apresenta uma msg de escolha perguntando sim ou não ao usuario quando chamada, 
     * ela recebe como parametro o conteudo que você deseja apresentar na mensagem que sera apresentada ao usuario*/
	public boolean MSGEscolha(String msg) {
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

    //MÃ©todo que realiza a pesquisa do animal no sistema, utilizando de informaÃ§Ãµes escritas no textfield
//    public void pesquisarElemento() {
//		ObservableList<Cliente> items = FXCollections.observableArrayList();
//		String nome, categoria, linha;
//		long rg;
//		double credito;
//		try {
//			FileReader fr = new FileReader(Main.arquivoCliente());
//			BufferedReader br = new BufferedReader(fr);
//			while (br.ready()) {
//				linha = br.readLine();
//				String dados[] = linha.split(";");
//				rg = Long.parseLong(dados[0]);
//				nome = dados[1];
//				categoria = dados[2];
//				credito = Double.parseDouble(dados[3]);
//				items.add(new Cliente(rg, nome, categoria, credito));
//			}
//		} catch (IOException n) {
//			System.out.println("Erro na tabela de clientes");
//		}
//		tabelacliente.setItems(items);
//		rgcoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("rg"));
//		nomecoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("nome"));
//		categoriacoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("categoria"));
//		creditocoluna.setCellValueFactory(new PropertyValueFactory<String, Cliente>("credito"));
//		tabelacliente.refresh();
//
//		FilteredList<Cliente> itemsfilter = new FilteredList<>(items, b -> true);
//		barraPesquisaexibir.textProperty().addListener((observable, oldValue, newValue) -> {
//			itemsfilter.setPredicate(c -> {
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				String lowerCaseFilter = newValue.toLowerCase();
//				if (String.valueOf(c.getRg()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true;
//				} else if (c.getNome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true;
//				} else if (c.getCategoria().toLowerCase().indexOf(lowerCaseFilter) != -1)
//					return true;
//				else
//					return false;
//			});
//			SortedList<Cliente> cc = new SortedList<>(itemsfilter);
//			cc.comparatorProperty().bind(tabelacliente.comparatorProperty());
//			tabelacliente.setItems(cc);
//		});
//
}