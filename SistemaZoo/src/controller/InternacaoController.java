package controller;

import java.io.IOException;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dao.TratamentoAnimaisDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.TratamentoAnimal;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Label;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
	
public class InternacaoController implements Initializable {
	    @FXML
	    private TableView<TratamentoAnimal> tabelaInternados;
	    
		@FXML
	    private TableColumn<TratamentoAnimal, Long> idColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> entradaColuna;

	    @FXML
	    private TableColumn <TratamentoAnimal,String> horaColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> nomeColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, Integer> idadeColuna;
	    
		@FXML
	    private TableColumn<TratamentoAnimal, String> especieColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> sexoColuna;
	    
		@FXML
	    private TableColumn<TratamentoAnimal, Integer> nAbrigoColuna;
	    
		@FXML
	    private TableColumn<TratamentoAnimal, String> motivoColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> situacaoColuna;
	    
	    @FXML
	    private ToggleGroup grupo;
	   
	    @FXML
	    private Label lblEspecie;
	    
	    @FXML
	    private Label lblId;

	    @FXML
	    private Label lblIdade;
	    
	    @FXML
	    private Label lblNumeroAbrigo;

	    @FXML
	    private Label lblSexo;

	    @FXML
	    private Label lblSituacao;

	    @FXML
	    private Label lblnomeAnimal;

	    @FXML
	    private TextArea taEvolucaoQuadro;

	    @FXML
	    private TextArea taMotivoConsulta;

	    @FXML
	    private TextArea taMotivoInternacao;

	    @FXML
	    private TextArea taProcedimento;

	    @FXML
	    private TextArea taResultado;

	    @FXML
	    private TextArea taResultadoAtendimento;

	    @FXML
	    private TextArea taTratamento;

	    @FXML
	    private TextArea taVacina;

	    @FXML
	    private Label lblData;

	    @FXML
	    private Label lblHora;

	    @FXML
	    private Label lblNomeVet;
	    
	    @FXML
	    private Pane panaCad, paneConsulta;
        private ObservableList<TratamentoAnimal> tratamentos = FXCollections.observableArrayList();

	    @FXML
	    void btnAlta(ActionEvent event) {

	    }

	    @FXML
	    void btnInternacao(ActionEvent event) {
	    	panaCad.setVisible(true);
	    	paneConsulta.setVisible(false);
	    }

	    @FXML
	    void btnObito(ActionEvent event) {

	    }

		public void listaAnimaisTratamento(){
		idColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,Long>("id"));
		entradaColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("dataEntradaTratamento"));
		horaColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("horarioTratamento"));
		nomeColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("nomeAnimalTratamento"));
		idadeColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,Integer>("idadeAnimalTratamento"));
		especieColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("especieAnimalTratamento"));
		sexoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("sexo"));
		nAbrigoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,Integer>("numeroAbrigo"));
		motivoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("motivoConsulta"));
		situacaoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal,String>("situacao"));
		tabelaInternados.setItems(atualizaTabela());
		}

		//M�todo que serve para atualizar a tabela com as informa��es dos animais
	public ObservableList<TratamentoAnimal> atualizaTabela(){
		TratamentoAnimaisDao dao = new TratamentoAnimaisDao();
		tratamentos = FXCollections.observableArrayList(dao.listarTratamentoAnimais());
		return tratamentos;
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
			TratamentoAnimaisDao atdao = new TratamentoAnimaisDao();
			TratamentoAnimal ta = new TratamentoAnimal(Long.parseLong(lblId.getText()), lblData.getText(), lblHora.getText(), lblnomeAnimal.getText(),lblIdade.getText(),lblEspecie.getText(),lblSexo.getText(),
			Integer.parseInt(lblNumeroAbrigo.getText()),taMotivoInternacao.getText(), "Consultando" ,taProcedimento.getText(), taEvolucaoQuadro.getText(),taResultado.getText(),taMotivoConsulta.getText(),taTratamento.getText(),taResultadoAtendimento.getText(),taVacina.getText());//passar os parametros dos dados de internação
	   		atdao.updateTratamento(ta);
	    }
	    
	    @FXML
	    void btnCancela(ActionEvent event) {
	    	panaCad.setVisible(false);
	    	paneConsulta.setVisible(false);
	    }

		@FXML
		void colocarInformacoesConsulta(){
			TratamentoAnimal ta = tabelaInternados.getSelectionModel().getSelectedItem();
			if(ta != null){
			lblId.setText(String.valueOf(ta.getId()));
			lblnomeAnimal.setText(ta.getNomeAnimalTratamento());
			lblIdade.setText(ta.getIdadeAnimalTratamento());
			lblEspecie.setText(ta.getEspecieAnimalTratamento());
			lblNumeroAbrigo.setText(String.valueOf(ta.getNumeroAbrigo()));
			lblSexo.setText(ta.getSexo());
			lblSituacao.setText(ta.getSituacao());
			taMotivoConsulta.setText(ta.getMotivoConsulta());
			taTratamento.setText(ta.getTratamento());
			taResultadoAtendimento.setText(ta.getResultadosAtendimento());
			taVacina.setText(ta.getVacinacaoVermufucacao());
			paneConsulta.setVisible(true);
			}else{
				MSG("Você deve selecionar um animal para consultar!");
			}
		}
	    
		@FXML
		void colocarInformacoesInternar(){
			
		}

	   
		@FXML
	    void btnConsulta(ActionEvent event) {
			colocarInformacoesConsulta();
	    }

		public void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
    

		public void initialize(URL arg0, ResourceBundle arg1) {
			listaAnimaisTratamento();
			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
			String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
			lblData.setText(data);
			lblHora.setText(hora);
		}

}
