package core.controller;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import core.dao.AnimalDao;
import core.dao.TratamentoAnimaisDao;
import core.model.Animal;
import core.model.TratamentoAnimal;
import core.utils.Mensagens;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GerenciamentoAnimalController implements Initializable{

@FXML private Label lblNomeUser;
@FXML private TextField barraPesquisa;
@FXML private TableView<Animal> tabelaAnimais;
@FXML private TableColumn<Animal,String > nomeColuna, sexoColuna, especieColuna,situacaoColuna, instituicaoOrigem,instituicaoDestino,estadodeSaude,nomeDoenca,habitatNatural,localizacaoAbrigo,nomeAlimento,dataTransferencia;
@FXML private TableColumn<Animal,Integer> nmrAbrigo,idadeColuna;
@FXML private TableColumn<Animal,Float> tamanhoAbrigo,quantidadeAlimento,medidaAlimento;
@FXML private TableColumn<Animal,Long> idColuna;
private ObservableList<Animal> animais = FXCollections.observableArrayList();
private AnimalDao dao = AnimalDao.getInstance();

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
		TratamentoAnimaisDao tdao = TratamentoAnimaisDao.getInstance();
		if(Mensagens.MSGEscolha("Você deseja levar o animal para consulta?") == true && animal.getConsultando() != true){
		FXMLLoader fxmlInternar = new FXMLLoader(getClass().getResource("/view/telaInternacao.fxml"));
		Parent root = fxmlInternar.load();
		GerenciamentoInternacaoController internar = fxmlInternar.getController();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		dao.updateEstadoConsulta(true, animal.getId());
		tdao.addTratamento(data,hora,"Consultando", ta);
		internar.listaAnimaisTratamento();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		}else{
			Mensagens.MSG("Animal já está em estado de consulta");
		}
		}catch(NullPointerException e){
			Mensagens.MSG("Você deve selecionar um animal para manda-lo para consulta");
			System.out.println(e);
		}
	}

	
	//Método que é executado na barra de pesquise, que enquanto o usuario digita os dados o programa mostra os animais compativel com o nome
	public void pesquisa() {
		tabelaAnimais.setItems(pesquisarAnimal());
	}
	
    //Método que chama a view de cadastro de animal
    public void cadastrarAnimal(ActionEvent event) throws IOException{
    	trocarTela(event,"View_CadastroAnimal");
    }
    
    //Método que chama a view de edição de animal
    public void editarAnimal(ActionEvent event) throws IOException{
    	Animal a = tabelaAnimais.getSelectionModel().getSelectedItem();
		if(a != null) {
		FXMLLoader fxmleditar = new FXMLLoader(getClass().getResource("/view/View_EditarAnimal.fxml"));
		Parent root = fxmleditar.load();
		EditarAnimalController editarAnimal = fxmleditar.getController();
		editarAnimal.inserirInformacoes(a);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		}else {
			Mensagens.MSG("Por favor selecione um animal na tabela para realizar a edição");
		}
    }
    
    @FXML
    //Met�do que retrocede para a tela anterior
    public void voltar(ActionEvent event)  {
    	trocarTela(event, LoginController.tela);
    }
    
    
    public void trocarTela(ActionEvent event, String tela){
        try{
    		Parent fxml = FXMLLoader.load(getClass().getResource("/view/" + tela + ".fxml"));
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		stage.setScene(new Scene(fxml));
        }catch(IOException e){
          System.out.println("Erro ao carregar tela");
      }
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarAnimais();	
		lblNomeUser.setText(LoginController.nomeFunc);
	}

}