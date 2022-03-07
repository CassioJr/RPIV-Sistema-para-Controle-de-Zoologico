package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.DatabaseFactory;
import model.Alimento;

public class LanchoneteVendaDao {
private Connection con;

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	public LanchoneteVendaDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}

	public boolean add(Alimento alimento) {
		String comando = "INSERT INTO alimento_venda(nome_alimento, valor_unitario, data_venda, hora_venda, quantidade, valor_total) VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, alimento.getNomeAlimento());
			stmt.setDouble(2, alimento.getValorUnitario());
			stmt.setString(3, alimento.getDataVenda());
			stmt.setString(4, alimento.getHoraVenda());
			stmt.setLong(5, alimento.getQuantidade());
			stmt.setDouble(6, alimento.getValorTotal());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(IngressoVendaDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public List<Alimento> getListVendasLanchonete() {
		List<Alimento> alimentos = new ArrayList<>();
		String comando = "SELECT * FROM alimento_venda";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Alimento ali = new Alimento();
				ali.setIdVenda(rs.getLong("id"));
				ali.setNomeAlimento(rs.getString("nome_alimento"));
				ali.setValorUnitario(rs.getDouble("valor_unitario"));
				ali.setDataVenda(rs.getString("data_venda"));
				ali.setHoraVenda(rs.getString("hora_venda"));
				ali.setQuantidade(rs.getLong("quantidade"));
				ali.setValorTotal(rs.getDouble("valor_total"));
				alimentos.add(ali);
			}
			stmt.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			return null;
		}
		return alimentos;
	}
}
