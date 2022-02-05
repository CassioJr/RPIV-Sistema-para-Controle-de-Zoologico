package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.controller.LoginController;
import core.database.DatabaseFactory;
import core.model.Login;

public class LoginDao {
	private Connection con;

	// Construtor reponsavel por inciar a conexao com o banco de dados
	public LoginDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}

	// Método que realiza a persistencia do login no banco de dados
	public boolean addLogin(Login l) {
		String comando = "INSERT INTO login(nome, senha, funcao) VALUES (?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, l.getNome());
			stmt.setString(2, l.getSenha());
			stmt.setString(3, l.getFuncao());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	// Método que retorna true se o usuario existe o sistema
	public boolean getLogin(String nmrusuario, String senha) {
		String comando = "SELECT * FROM login WHERE id=? AND senha=?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, nmrusuario);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			boolean status = rs.isBeforeFirst();
			stmt.close();
			rs.close();
			con.close();
			return status;
		} catch (SQLException e) {
			return false;
		}
	}

	// Método que retorna a função do funcionario no banco de dados, ele realiza um
	// select e retorna a funcao do resultado
	public String getFuncao(String nmrusuario, String senha) {
		String comando = "SELECT * FROM login WHERE id=? AND senha=?;";
		String funcao = null;
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, nmrusuario);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				funcao = rs.getString("funcao");
				LoginController.nomeFunc = rs.getString("nome");
			}
			stmt.close();
			rs.close();
			con.close();
			return funcao;
		} catch (SQLException e) {
			return null;
		}
	}
}
