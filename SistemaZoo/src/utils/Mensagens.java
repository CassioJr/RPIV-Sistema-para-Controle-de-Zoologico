package utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Mensagens {
	
	/*Metodo que apresenta uma msg ao usuario quando chamada, ela recebe como parametro o conteudo que 
	 *voc� deseja apresentar na mensagem que sera apresentada ao usuario*/
	public static void MSG(String msg) {
		Alert alerta = new Alert(Alert.AlertType.WARNING);
		alerta.setTitle("Atenção");
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
    
    /*Metodo que apresenta uma msg de escolha perguntando sim ou n�o ao usuario quando chamada, 
     * ela recebe como parametro o conteudo que voc� deseja apresentar na mensagem que sera apresentada ao usuario*/
	public static boolean MSGEscolha(String msg) {
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
}
