package controller;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AnimalDao;
import dao.TratamentoAnimaisDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Animal;
import model.TratamentoAnimal;

public class AnimalController implements Initializable{
	
@FXML private TextField barraPesquisa;
@FXML private TableView<Animal> tabelaAnimais;
@FXML private TableColumn<Animal,String > nomeColuna, sexoColuna, especieColuna,situacaoColuna, instituicaoOrigem,instituicaoDestino,estadodeSaude,nomeDoenca,habitatNatural,localizacaoAbrigo,nomeAlimento,dataTransferencia;
@FXML private TableColumn<Animal,Integer> nmrAbrigo,idadeColuna;
@FXML private TableColumn<Animal,Float> tamanhoAbrigo,quantidadeAlimento,medidaAlimento;
@FXML private TableColumn<Animal,Long> idColuna;
private ObservableList<Animal> animais = FXCollections.observableArrayList();


	//Método para listar os animais na tabela
	public void listarAnimais() {
		idColuna.setCellValueFactory(new PropertyValueFactory<Animal,Long>("id"));
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Animal,String>("nomeAnimal"));
		idadeColuna.setCellValueFactory(new PropertyValueFactory<Animal,Integer>("idadeAnimal"));
		sexoColuna.setCellValueFactory(new PropertyValueFactory<Animal,String>("sexoAnimal"));
		especieColuna.setCellValueFactory(new PropertyValueFactory<Animal,String>("nomeEspecie"));
		situacaoColuna.setCellValueFactory(new PropertyValueFactory<Animal,String>("tipoTransferencia"));
		instituicaoOrigem.setCellValueFactory(new PropertyValueFactory<Animal,String>("instituicaoOrigem"));
		instituicaoDestino.setCellValueFactory(new PropertyValueFactory<Animal,String>("instituicaoDestino"));
		dataTransferencia.setCellValueFactory(new PropertyValueFactory<Animal,String>("datatransfenciaInstituicao"));
		estadodeSaude.setCellValueFactory(new PropertyValueFactory<Animal,String>("estadoSaude"));
		nomeDoenca.setCellValueFactory(new PropertyValueFactory<Animal,String>("nomeDoenca"));
		habitatNatural.setCellValueFactory(new PropertyValueFactory<Animal,String>("habitatEspecie"));
		localizacaoAbrigo.setCellValueFactory(new PropertyValueFactory<Animal,String>("localizacaoAbrigo"));
		nmrAbrigo.setCellValueFactory(new PropertyValueFactory<Animal,Integer>("numeroAbrigo"));
		tamanhoAbrigo.setCellValueFactory(new PropertyValueFactory<Animal,Float>("tamanhoAbrigo"));
		nomeAlimento.setCellValueFactory(new PropertyValueFactory<Animal,String>("nomeAlimento"));
		quantidadeAlimento.setCellValueFactory(new PropertyValueFactory<Animal,Float>("quantidadeDiaria_Alimento"));
		medidaAlimento.setCellValueFactory(new PropertyValueFactory<Animal,Float>("medidaQuantidade_Alimento"));
		tabelaAnimais.setItems(atualizaTabela());
	}

	//M�todo que serve para atualizar a tabela com as informações dos animais
	public ObservableList<Animal> atualizaTabela(){
		AnimalDao dao = new AnimalDao();
		animais = FXCollections.observableArrayList(dao.getListAnimal());
		return animais;
	}
	
	//M�todo que serve para buscar animais cadastrados especificos no sistema
	public ObservableList<Animal> pesquisarAnimal(){
		ObservableList<Animal> animalpesquisado =  FXCollections.observableArrayList();
		for(int x=0; x<animais.size();x++) {
			if(animais.get(x).getNomeAnimal().contains(barraPesquisa.getText()) || animais.get(x).getNomeEspecie().contains(barraPesquisa.getText())) {
				animalpesquisado.add(animais.get(x));
			}
		}
		return animalpesquisado;
	}

	//Método para mandar o animal para internação
	public void mandarInternacao(ActionEvent event) throws IOException {
		Animal animal = tabelaAnimais.getSelectionModel().getSelectedItem();
		try{
		TratamentoAnimal ta = new TratamentoAnimal(animal.getNomeAnimal(),null, null, null, null,null,animal.getNomeEspecie(),null,animal.getEstadoSaude(), animal.getNomeDoenca(),animal.getIdadeAnimal(),animal.getSexoAnimal(),animal.getNumeroAbrigo(), null,null,0,0,true,animal.getId(),null,null,null,null,null,null,null,null,null, null);
		AnimalDao adao = new AnimalDao();
		if(MSGEscolha("Você deseja levar o animal para consulta?") == true && animal.getConsultando() != true){
		FXMLLoader fxmlInternar = new FXMLLoader(getClass().getResource("/view/telaInternacao.fxml"));
		Parent root = fxmlInternar.load();
		InternacaoController internar = fxmlInternar.getController();
		TratamentoAnimaisDao tdao = new TratamentoAnimaisDao();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		adao.updateEstadoConsulta(true, animal.getId());
		tdao.addTratamento(data,hora,"Consultando", ta);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		internar.listaAnimaisTratamento();
		primaryStage.setScene(scene);
		primaryStage.show();
		}else{
			MSG("Animal já está em estado de consulta");
		}
		}catch(NullPointerException e){
			MSG("Você deve selecionar um animal para manda-lo para consulta");
		}
	}

	
	//Método que é executado na barra de pesquise, que enquanto o usuario digita os dados o programa mostra os animais compativel com o nome
	public void pesquisa() {
		tabelaAnimais.setItems(pesquisarAnimal());
	}
	
    //Método que chama a view de cadastro de animal
    public void cadastrarAnimal(ActionEvent event) throws IOException{
    	BorderPane fxmlEspera = (BorderPane) FXMLLoader.load(getClass().getResource("/view/View_CadastroAnimal.fxml"));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
    
    //Método que chama a view de edi��o de animal
    public void editarAnimal(ActionEvent event) throws IOException{
    	Animal a = tabelaAnimais.getSelectionModel().getSelectedItem();
		if(a != null) {
		FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/view_EditarAnimal.fxml"));
		Parent root = fxmleditar.load();
		EditarAnimalController editarAnimal = fxmleditar.getController();
		editarAnimal.inserirInformacoes(String.valueOf(a.getId()),String.valueOf(a.getConsultando()),a.getNomeAnimal(), String.valueOf(a.getIdadeAnimal()),a.getSexoAnimal(),a.getTipoTransferencia(),a.getInstituicaoOrigem(),a.getInstituicaoDestino(),a.getEstadoSaude(),a.getNomeDoenca(),
				a.getNomeEspecie(),a.getHabitatEspecie(),a.getDatatransfenciaInstituicao(),String.valueOf(a.getNumeroAbrigo()),a.getLocalizacaoAbrigo(),String.valueOf(a.getTamanhoAbrigo()),a.getNomeAlimento(),String.valueOf(a.getQuantidadeDiaria_Alimento()),a.getMedidaQuantidade_Alimento());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();}
		else {
			MSG("Por favor selecione um animal na tabela para realizar a edição");
		}
    }

    
    //Met�do que retrocede para a tela anterior
    public void voltar(ActionEvent event) throws IOException {
    	AnchorPane fxmlEspera = (AnchorPane) FXMLLoader.load(getClass().getResource(LoginController.tela));
        Scene Espera = new Scene(fxmlEspera);
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setScene(Espera);
    }
	
	/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	 *voc� deseja apresentar na mensagem que sera apresentada ao usuario*/
	public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
    
    /*Metodo que apresenta uma msg de escolha perguntando sim ou n�o ao usuario quando chamada, 
     * ela recebe como parametro o conteudo que voc� deseja apresentar na mensagem que sera apresentada ao usuario*/
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarAnimais();	
	}

}