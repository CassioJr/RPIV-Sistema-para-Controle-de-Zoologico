package core.controller.bridge;

import java.io.IOException;

import core.controller.CadastroVendaAlimentoController;
import core.dao.LanchoneteVendaDao;
import core.model.Alimento;
import core.utils.Mensagens;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastroVendaAlimentoBridge implements  GerenciaBridge {

    @Override
	public void cadastrar(ActionEvent event, String[] obj) throws IOException {
		if (Mensagens.MSGEscolha("Você deseja salvar a venda do alimento?") == true) {
			if (validarCampos(obj) == true) {
				pegarInformacoes(obj);
				voltar(event);
				Mensagens.MSG("Venda salva com sucesso!");
			}
		}
	}

	@Override
	public void pegarInformacoes(String[] obj) {
		LanchoneteVendaDao venda = LanchoneteVendaDao.getInstance();
		Alimento v = new Alimento(obj[0], obj[1],Long.parseLong(obj[2]), calcularTotal(Long.parseLong(obj[2]),Double.parseDouble(obj[4])), obj[3], Double.parseDouble(obj[4]));
		venda.add(v);
		
	}

	@Override
	public Double calcularTotal(Long quantidade, Double valorUnit) {
		Double total = quantidade * valorUnit;
		return total;
	}

	@Override
	public boolean validarCampos(String[] obj) {
		if (obj[3].isEmpty() || obj[4].isEmpty() || obj[2].isEmpty()
				|| obj[1].isEmpty() || obj[0].isEmpty()) {
			Mensagens.MSG("Você deve preencher todos os campos");
			return false;
		}
		return true;
	}

	@Override
	public void voltar(ActionEvent event) throws IOException {
		Parent fxmlEspera = FXMLLoader.load(getClass().getResource("/view/View_GerenciamentoLanchonete.fxml"));
		Scene Espera = new Scene(fxmlEspera);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(Espera);
	}
}
