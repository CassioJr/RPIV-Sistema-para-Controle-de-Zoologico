package core.controller.bridge;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;

public interface GerenciaBridge {

    void cadastrar(ActionEvent event, String[] obj) throws IOException;
    void pegarInformacoes(String[] a);
	Double calcularTotal(Long quantidade, Double valorUnit);
    boolean validarCampos(String[] obj);
	void voltar(ActionEvent event) throws IOException;
}
