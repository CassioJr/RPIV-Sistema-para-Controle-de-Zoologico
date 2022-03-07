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
import core.model.Pedido;

public class PedidoDao {
	private Connection con;

	// 1Construtor responsavel por iniciar a conexao com o BD
	public PedidoDao() {
		this.con = DatabaseFactory.getInstance().getDatabase("postgres").conectar();	}

	// 2Metodo de persistencia com o BD, adiciona os dados na classe e recebe como
	// parametro um objeto do tipo p
	public boolean addPedido(Pedido p) {
		String comando = "INSERT INTO pedido(alimentop, datap, fornecedorp, quantidadep, situacaop) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, p.getAlimentoPed());
			stmt.setString(2, p.getDataPed());
			stmt.setString(3, p.getFornecedorPed());
			stmt.setInt(4, p.getQuantidadePed());
			stmt.setString(5, p.getSituacaoPed());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	// 3Metodo responsavel por pegar todas as informacoes da tabela pedido
	public List<Pedido> getListPedido() {
		List<Pedido> pedidos = new ArrayList<>();
		String comando = "SELECT * FROM pedido";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Pedido p = new Pedido();
				p.setId(rs.getLong("id"));
				p.setAlimentoPed(rs.getString("alimentop"));
				p.setDataPed(rs.getString("datap"));
				p.setFornecedorPed(rs.getString("fornecedorp"));
				p.setQuantidadePed(rs.getInt("quantidadep"));
				p.setSituacaoPed(rs.getString("situacaop"));
				pedidos.add(p);
			}
			
		} catch (SQLException e) {
			return null;
		}
		return pedidos;
	}

	// 4Metodo de persistencia com o BD para dados alterados dos pedidos
	public boolean alterar(Pedido pedido) {
		String sql = "UPDATE pedidos SET alimentop=?, datap=?, fornecedorp=?, quantidadep=?, situacaop=? WHERE id=;?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, pedido.getAlimentoPed());
			stmt.setString(2, pedido.getDataPed());
			stmt.setString(3, pedido.getFornecedorPed());
			stmt.setInt(4, pedido.getQuantidadePed());
			stmt.setString(5, pedido.getSituacaoPed());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	// 5Metodo que realiza que faz a persistencia dos dados alterados
	public boolean updatePedido(Pedido pedido) {
		String comando = "UPDATE pedido SET alimentop=?, datap=?, fornecedorp=?, quantidadep =?, situacaop=? WHERE id =?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, pedido.getAlimentoPed());
			stmt.setString(2, pedido.getDataPed());
			stmt.setString(3, pedido.getFornecedorPed());
			stmt.setFloat(4, pedido.getQuantidadePed());
			stmt.setString(5, pedido.getSituacaoPed());
			stmt.setLong(6, pedido.getId());

			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}
}
