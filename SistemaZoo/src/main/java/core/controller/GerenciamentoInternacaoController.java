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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GerenciamentoInternacaoController implements Initializable {

	@FXML
	private TableView<TratamentoAnimal> tabelaInternados;

	@FXML
	private TableColumn<Animal, Long> idColuna;

	@FXML
	private TableColumn<Animal, String> nomeColuna;

	@FXML
	private TableColumn<Animal, Integer> idadeColuna;

	@FXML
	private TableColumn<Animal, String> especieColuna;

	@FXML
	private TableColumn<Animal, String> sexoColuna;

	@FXML
	private TableColumn<Animal, Integer> nAbrigoColuna;

	@FXML
	private TableColumn<TratamentoAnimal, String> entradaColuna;

	@FXML
	private TableColumn<TratamentoAnimal, String> horaColuna;

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
	private Label lblIdent, lblEspecie, lblId, lblIdade, lblNumeroAbrigo, lblSexo, lblSituacao, lblnomeAnimal, lblData,
			lblHora, lblNomeUser;

	@FXML
	private ToggleGroup grupo;

	@FXML
	private TextArea taEvolucaoQuadro, taMotivoConsulta, taMotivoInternacao, taProcedimento, taResultado,
			taResultadoAtendimento, taTratamento, taVacina;

	@FXML
	private Pane panaCad, paneConsulta;
	private ObservableList<TratamentoAnimal> tratamentos = FXCollections.observableArrayList();

	@FXML
	void btnAlta(ActionEvent event) {
		if (Mensagens.MSGEscolha("Você deseja mudar a situação desse animal para alta") == true) {
			TratamentoAnimal tat = tabelaInternados.getSelectionModel().getSelectedItem();
			alterarEstados(tat, "Alta", "Saudável", false);
			listaAnimaisTratamento();
			Mensagens.MSG("Alteração feita com sucesso!");
		}
	}

	@FXML
	void btnObito(ActionEvent event) {
		if (Mensagens.MSGEscolha("Você deseja mudar a situação desse animal para óbito") == true) {
			TratamentoAnimal tat = tabelaInternados.getSelectionModel().getSelectedItem();
			alterarEstados(tat, "Obito", "Morto", false);
			listaAnimaisTratamento();
			Mensagens.MSG("Alteração feita com sucesso!");
		}
	}

	@FXML
	void btnInternacao(ActionEvent event) {
		TratamentoAnimal ta = tabelaInternados.getSelectionModel().getSelectedItem();
		if (Mensagens.MSGEscolha("Você deseja internar esse animal") == true && !ta.getSituacao().equals("Internado")) {
			colocarInformacoesInternar(ta);
			Mensagens.MSG("Animal internado com sucesso!");
		} else {
			Mensagens.MSG("Animal já esta internado veja seu prontoario!");
			colocarInformacoesInternar(ta);
		}
	}

	public void alterarEstados(TratamentoAnimal tat, String altaObito, String estadoSaude, boolean estado) {
		AnimalDao a = AnimalDao.getInstance();
		TratamentoAnimaisDao ta = TratamentoAnimaisDao.getInstance();
		ta.updateEstadoAltaObto(altaObito, tat.getIdent());
		a.updateEstadoSaude(estadoSaude, tat.getMotivoInternacao(), tat.getId());
		a.updateEstadoConsulta(estado, tat.getId());
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
		TratamentoAnimaisDao dao = TratamentoAnimaisDao.getInstance();
		tratamentos = FXCollections.observableArrayList(dao.listarTratamentoAnimais());
		return tratamentos;
	}

	@FXML
	void btnSair(ActionEvent event) throws IOException {
		Parent fxmlApp = FXMLLoader.load(getClass().getResource("/view/" + LoginController.tela + ".fxml"));
		Stage app = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app.setScene(new Scene(fxmlApp));
	}

	@FXML
	void btnSalvar(ActionEvent event) {
		if (Mensagens.MSGEscolha("Você quer salvar as alterações") == true) {
			TratamentoAnimaisDao atdao = TratamentoAnimaisDao.getInstance();
			if (paneConsulta.isVisible()) {
				atdao.updateTratamento(salvarInformacoes());
			} else if (panaCad.isVisible()) {
				atdao.updateInternacao(salvarInformacoes());
			}
			Mensagens.MSG("Alterações feitas com sucesso!");
			panaCad.setVisible(false);
			paneConsulta.setVisible(false);
			listaAnimaisTratamento();
		} else {
			Mensagens.MSG("Alterações descartadas!");
		}

	}

	@FXML
	public TratamentoAnimal salvarInformacoes() {
		TratamentoAnimal ta = new TratamentoAnimal(lblnomeAnimal.getText(), null, null, null, null, null,
				lblEspecie.getText(), null, lblSituacao.getText(), taMotivoConsulta.getText(),
				Integer.parseInt(lblIdade.getText()), lblSexo.getText(), Integer.parseInt(lblNumeroAbrigo.getText()),
				null, null, 0, 0, false, Long.parseLong(lblIdent.getText()), Long.parseLong(lblIdent.getText()),
				lblData.getText(), lblHora.getText(), taMotivoInternacao.getText(), lblSituacao.getText(),
				taProcedimento.getText(), taEvolucaoQuadro.getText(), taResultado.getText(), taTratamento.getText(),
				taResultadoAtendimento.getText(), taVacina.getText());
		return ta;
	}

	@FXML
	void btnCancela(ActionEvent event) {
		if (Mensagens.MSGEscolha("Você quer cancelar as alterações") == true)
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
			Mensagens.MSG("Você deve selecionar um animal para consultar!");
		}
	}

	public void colocarInformacoesInternar(TratamentoAnimal ta) {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listaAnimaisTratamento();
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		lblData.setText(data);
		lblHora.setText(hora);
		lblNomeUser.setText(LoginController.nomeFunc);
	}

}
