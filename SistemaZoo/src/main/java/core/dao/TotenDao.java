package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.database.DatabaseFactory;
import core.model.Animal;
import core.model.Toten;

public class TotenDao {
	private Connection con;

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	public  TotenDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}

	// MÃ©todo que realiza que ï¿½ responsavel por pegar todas as informaï¿½ï¿½es da tabela
	// animal
	public List<Toten> getListRequisicoes() {
		List<Toten> requisicoes = new ArrayList<>();
		String comando = "SELECT * FROM toten";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Toten t = new Toten();
				t.setId(rs.getLong("id"));
				t.setEmergencia(rs.getString("emergencia"));
				t.setEstouPerdido(rs.getString("estou_perdido"));
				t.setPerdiAlguem(rs.getString("perdi_alguem"));
				t.setSeguranca(rs.getString("seguranca"));
				requisicoes.add(t);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			return null;
		}
		return requisicoes;
	}

}
