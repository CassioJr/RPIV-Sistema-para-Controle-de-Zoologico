package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Animal;

public class AnimalDao {
	private Connection con;

	public boolean addAnimal(Animal animal) {
	String comando = "INSERT INTO animal(nome,sexo,idade,situacao,instituicao,estadoSaude,nomeEspecie,habitat,nmrAbrigo,localizacaoAbrigo,tamanhoAbrigo,nomeAlimento,quantidadeAlimento,medidaAlimento, dataTransferencia) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
	try {
		PreparedStatement stmt = con.prepareStatement(comando);
		stmt.setString(1, animal.getNomeAnimal());
		stmt.setInt(2, animal.getSexoAnimal());
		stmt.setInt(3, animal.getIdadeAnimal());
		stmt.setString(4, animal.getTipoTransferencia());
		stmt.setString(5, animal.getInstituicaoOrigem());
		stmt.setString(6, animal.getInstituicaoDestino());
		stmt.setString(7, animal.getEstadoSaude());
		stmt.setString(8, animal.getNomeEspecie());
		stmt.setString(9, animal.getHabitatEspecie());
		stmt.setString(10, animal.getLocalizacaoAbrigo());
		stmt.setFloat(11, animal.getTamanhoAbrigo());
		stmt.setInt(12, animal.getNumeroAbrigo());
		stmt.setString(13, animal.getNomeAlimento());
		stmt.setFloat (14, animal.getQuantidadeDiaria_Alimento());
		stmt.setFloat(15, animal.getMedidaQuantidade_Alimento());
		stmt.setString(16, animal.getDatatransfenciaInstituicao());
		stmt.execute();
		return true;
	}catch(SQLException e) {
		Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE,null, e);
		return false;
	}		
	}
	
}
