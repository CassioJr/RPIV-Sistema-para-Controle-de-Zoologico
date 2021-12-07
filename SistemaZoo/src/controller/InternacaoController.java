package controller;

import java.io.IOException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.management.remote.NotificationResult;

import dao.AnimalDao;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Animal;
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
	private TableColumn<Animal, Long> idColuna;

	@FXML
	private TableColumn<TratamentoAnimal, String> entradaColuna;

	@FXML
	private TableColumn<TratamentoAnimal, String> horaColuna;

	@FXML
	private TableColumn<Animal, String> nomeColuna;

	@FXML
	private TableColumn<Animal, Integer>  idadeColuna;

	@FXML
	private TableColumn<Animal, String> especieColuna;

	@FXML
	private TableColumn<Animal, String> sexoColuna;

	@FXML
	private TableColumn<Animal, Integer> nAbrigoColuna;
	
	@FXML
    private TableColumn<TratamentoAnimal, String> evolucaoQuadroColuna;

	 @FXML
	    private TableColumn<TratamentoAnimal, String> motivoConsulColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> motivoInternacaoColuna;
	    
	    @FXML
	    private TableColumn<TratamentoAnimal, String> procedimentoColuna;
	    
	    @FXML
	    private TableColumn<TratamentoAnimal, String> tratamentoColuna;

	    @FXML
	    private TableColumn<TratamentoAnimal, String> resultConsulColuna;
	    
	    @FXML
	    private TableColumn<TratamentoAnimal, String> resultInternacaoColuna;
	    
	    @FXML
	    private TableColumn<TratamentoAnimal, Long> idConsultaColuna;


	@FXML
	private TableColumn<TratamentoAnimal, String> situacaoColuna;
	

    @FXML
    private Label lblIdent;

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
		if(MSGEscolha("Você deseja mudar a situação desse animal para alta")==true) {
		TratamentoAnimal tat = tabelaInternados.getSelectionModel().getSelectedItem();
		AnimalDao a = new AnimalDao();
		TratamentoAnimaisDao ta = new TratamentoAnimaisDao();		
		a.updateEstadoConsulta(false, tat.getId());
		ta.updateEstadoAltaObto("Alta", tat.getIdent());
		a.updateEstadoSaude("Saúdavel"," ", tat.getId());
		listaAnimaisTratamento();
		MSG("Alteração feita com sucesso!");
		}
	}

	@FXML
	void btnInternacao(ActionEvent event) {
		TratamentoAnimal ta = tabelaInternados.getSelectionModel().getSelectedItem();
		if (MSGEscolha("Você deseja internar esse animal")==true && !ta.getSituacao().equals("Internado")) {
			colocarInformacoesInternar(ta);
			MSG("Animal internado com sucesso!");
		}else{
			MSG("Animal já esta internado veja seu prontoario!");
			colocarInformacoesInternar(ta);
		}
    	
	}

	@FXML
	void btnObito(ActionEvent event) {
		if(MSGEscolha("Você deseja mudar a situação desse animal para óbito")==true) {
		TratamentoAnimal tat = tabelaInternados.getSelectionModel().getSelectedItem();
		AnimalDao a = new AnimalDao();
		TratamentoAnimaisDao ta = new TratamentoAnimaisDao();		
		a.updateEstadoConsulta(false, tat.getId());
		ta.updateEstadoAltaObto("Obito", tat.getIdent());
		a.updateEstadoSaude("Morto",tat.getMotivoInternacao(), tat.getId());
		listaAnimaisTratamento();
		MSG("Alteração feita com sucesso!");
		}
			
		
	}

	public void listaAnimaisTratamento() {
		idConsultaColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, Long>("ident"));
		idColuna.setCellValueFactory(new PropertyValueFactory<Animal, Long>("id"));
		entradaColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("dataEntradaTratamento"));
		horaColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("horarioTratamento"));
		nomeColuna.setCellValueFactory(new PropertyValueFactory<Animal, String>("nomeAnimal"));
		idadeColuna.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("idadeAnimal"));
		especieColuna.setCellValueFactory(new PropertyValueFactory<Animal, String>("nomeEspecie"));
		sexoColuna.setCellValueFactory(new PropertyValueFactory<Animal, String>("sexoAnimal"));
		nAbrigoColuna.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("numeroAbrigo"));
		motivoConsulColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("nomeDoenca"));
		tratamentoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("tratamento"));
		resultConsulColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("resultadosAtendimento"));
		motivoInternacaoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("motivoInternacao"));
		procedimentoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("procedimento"));
		evolucaoQuadroColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("evolucaoQuadro"));
		resultInternacaoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("resultados"));
		situacaoColuna.setCellValueFactory(new PropertyValueFactory<TratamentoAnimal, String>("situacao"));		
		tabelaInternados.setItems(atualizaTabela());
	}

	// M�todo que serve para atualizar a tabela com as informa��es dos animais
	public ObservableList<TratamentoAnimal> atualizaTabela() {
		TratamentoAnimaisDao dao = new TratamentoAnimaisDao();
		tratamentos = FXCollections.observableArrayList(dao.listarTratamentoAnimais());
		return tratamentos;
	}

	@FXML
	void btnSair(ActionEvent event) throws IOException {
		AnchorPane fxmlApp = (AnchorPane) FXMLLoader.load(getClass().getResource(LoginController.tela));
		Scene App = new Scene(fxmlApp);
		Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app.setScene(App);
	}

	@FXML
	void btnSalvar(ActionEvent event) {
		if (MSGEscolha("Você quer salvar as alterações") == true) {
			TratamentoAnimaisDao atdao = new TratamentoAnimaisDao();
			if(paneConsulta.isVisible()){
			TratamentoAnimal ta = new TratamentoAnimal(lblnomeAnimal.getText(), null, null, null, null, null, lblEspecie.getText(), null, lblSituacao.getText(), taMotivoConsulta.getText(),Integer.parseInt(lblIdade.getText()), lblSexo.getText(), Integer.parseInt(lblNumeroAbrigo.getText()), null, null,0,0,false,Long.parseLong(lblId.getText()),Long.parseLong(lblId.getText()),lblData.getText(),lblHora.getText(),taMotivoInternacao.getText(),lblSituacao.getText(),taProcedimento.getText(),taEvolucaoQuadro.getText(),taResultado.getText(),taTratamento.getText(), taResultadoAtendimento.getText(),taVacina.getText()); // passar os parametros dos dados de internação
			atdao.updateTratamento(ta);
			}else if (panaCad.isVisible()){
			TratamentoAnimal ta = new TratamentoAnimal(lblnomeAnimal.getText(), null, null, null, null, null, lblEspecie.getText(), null, lblSituacao.getText(), taMotivoConsulta.getText(),Integer.parseInt(lblIdade.getText()), lblSexo.getText(), Integer.parseInt(lblNumeroAbrigo.getText()), null, null,0,0,false,Long.parseLong(lblIdent.getText()),Long.parseLong(lblIdent.getText()),lblData.getText(),lblHora.getText(),taMotivoInternacao.getText(),lblSituacao.getText(),taProcedimento.getText(),taEvolucaoQuadro.getText(),taResultado.getText(),taTratamento.getText(), taResultadoAtendimento.getText(),taVacina.getText()); // passar os parametros dos dados de internação
			atdao.updateInternacao(ta);
			}	
			MSG("Alterações feitas com sucesso!");
			panaCad.setVisible(false);
			paneConsulta.setVisible(false);
			listaAnimaisTratamento();
		} else {
			MSG("Alterações descartadas!");
		}

	}

	@FXML
	void btnCancela(ActionEvent event) {
		if(MSGEscolha("Você quer cancelar as alterações") == true)
		panaCad.setVisible(false);
		paneConsulta.setVisible(false);
	}

	@FXML
	void colocarInformacoesConsulta() {
		TratamentoAnimal ta = tabelaInternados.getSelectionModel().getSelectedItem();
		if (ta != null) {
			lblId.setText(String.valueOf(ta.getIdent()));
			lblnomeAnimal.setText(ta.getNomeAnimal());
			lblIdade.setText(String.valueOf(ta.getIdadeAnimal()));
			lblEspecie.setText(ta.getNomeEspecie());
			lblNumeroAbrigo.setText(String.valueOf(ta.getNumeroAbrigo()));
			lblSexo.setText(ta.getSexoAnimal());
			lblSituacao.setText(ta.getSituacao());
			taMotivoConsulta.setText(ta.getNomeDoenca());
			taTratamento.setText(ta.getTratamento());
			taResultadoAtendimento.setText(ta.getResultadosAtendimento());
			taVacina.setText(ta.getVacinacaoVermufucacao());
			paneConsulta.setVisible(true);
			panaCad.setVisible(false);
		} else {
			MSG("Você deve selecionar um animal para consultar!");
		}
	}

	@FXML
	void colocarInformacoesInternar(TratamentoAnimal ta) {
			ToggleGroup group = new ToggleGroup();
			RadioButton radioButton1 = new RadioButton("Estavel");
			radioButton1.setToggleGroup(group);
			radioButton1.setSelected(true);
			lblIdent.setText(String.valueOf(ta.getIdent()));
			RadioButton radioButton2 = new RadioButton("Urgente");
			radioButton2.setToggleGroup(group);
			RadioButton radioButton3 = new RadioButton("Emergencia");
			radioButton3.setToggleGroup(group);
			lblSituacao.setText("Internado");
			taMotivoInternacao.setText(ta.getMotivoInternacao());
			taProcedimento.setText(ta.getProcedimento());
			taEvolucaoQuadro.setText(ta.getEvolucaoQuadro());
			taResultado.setText(ta.getResultados());
			panaCad.setVisible(true);
			paneConsulta.setVisible(false);
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
		listaAnimaisTratamento();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		lblData.setText(data);
		lblHora.setText(hora);

	}

}
