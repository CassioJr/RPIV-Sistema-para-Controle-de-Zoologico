package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.database.DatabaseFactory;
import core.model.Toten;

public class GerenciadorTotenDao {
 private Connection connection;
    
    //1Construtor responsavel por iniciar a conexao com o BD
  	public GerenciadorTotenDao() 
  	{
		this.connection = new DatabaseFactory().getDatabase("postgresql").conectar();
  	}
  	
   	// 3Metodo responsavel por pegar todas as informacoes da tabela pedido
	public List<Toten> getListRequisicoes() {
		List<Toten> requisicoes = new ArrayList<>();
		String comando = "SELECT * FROM toten";
		try {
			PreparedStatement stmt = connection.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Toten toten = new Toten();
				toten.setId(rs.getLong("id"));
				toten.setSeguranca(rs.getString("seguranca"));
				toten.setEmergencia(rs.getString("emergencia"));
				toten.setPerdiAlguem(rs.getString("perdi_alguem"));
				toten.setEstouPerdido(rs.getString("estou_perdido"));
				requisicoes.add(toten);
            }
			
		} catch (SQLException e) {
			return null;
		}
		return requisicoes;
	}
}
