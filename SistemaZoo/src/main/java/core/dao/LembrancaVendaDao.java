package core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.database.DatabaseFactory;
import core.model.Lembranca;

public class LembrancaVendaDao {
	private Connection con;
	private static LembrancaVendaDao instance;
	public static LembrancaVendaDao getInstance(){
		if(instance == null) {
			instance = new LembrancaVendaDao();
		}
		return instance;
	}

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	public LembrancaVendaDao() {
		this.con = DatabaseFactory.getInstance().getDatabase("postgresql").conectar();
		}

	public boolean add(Lembranca lembranca) {
		String comando = "INSERT INTO lembranca_venda(nome_lembranca, valor_unitario, data_venda, hora_venda, quantidade, valor_total) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, lembranca.getNomeLembranca());
			stmt.setDouble(2, lembranca.getValorUnitario());
			stmt.setString(3, lembranca.getDataVenda());
			stmt.setString(4, lembranca.getHoraVenda());
			stmt.setLong(5, lembranca.getQuantidade());
			stmt.setDouble(6, lembranca.getValorTotal());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(LembrancaVendaDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

		//Metodo de persistencia com o BD para dados alterados dos funcionarios
		public boolean update(Lembranca lembranca, Long id){
    	String comando = "UPDATE lembranca_venda SET nome_lembranca =?, valor_unitario =?, data_venda =?, hora_venda=?, quantidade=?, valor_total=? WHERE id=?;";
        try 
        {
		PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, lembranca.getNomeLembranca());
			stmt.setDouble(2, lembranca.getValorUnitario());
			stmt.setString(3, lembranca.getDataVenda());
			stmt.setString(4, lembranca.getHoraVenda());
			stmt.setLong(5, lembranca.getQuantidade());
			stmt.setDouble(6, lembranca.getValorTotal());
			stmt.setLong(7, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(LembrancaVendaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
		}
		}

	public List<Lembranca> getListVendasLembrancas() {
		List<Lembranca> lembrancas = new ArrayList<>();
		String comando = "SELECT * FROM lembranca_venda";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Lembranca lem = new Lembranca();
				lem.setIdVenda(rs.getLong("id"));
				lem.setNomeLembranca(rs.getString("nome_lembranca"));
				lem.setValorUnitario(rs.getDouble("valor_unitario"));
				lem.setDataVenda(rs.getString("data_venda"));
				lem.setHoraVenda(rs.getString("hora_venda"));
				lem.setQuantidade(rs.getLong("quantidade"));
				lem.setValorTotal(rs.getDouble("valor_total"));
				lembrancas.add(lem);
			}
			
		} catch (SQLException e) {
			return null;
		}
		return lembrancas;
	}
}
