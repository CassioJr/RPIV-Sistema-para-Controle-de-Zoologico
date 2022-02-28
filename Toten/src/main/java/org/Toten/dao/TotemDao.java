package org.Toten.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.database.DatabaseFactory;

public class TotemDao {
	private Connection con;

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	public TotemDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}

    public boolean addChamado (String campo, String valor){
        String comando = "INSERT INTO toten("+campo+") VALUES (?);";
        try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, valor);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(TotemDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
    }

}
