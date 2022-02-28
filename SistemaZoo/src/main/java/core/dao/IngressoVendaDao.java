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
import core.model.Ingresso;

public class IngressoVendaDao {
       private Connection con;
	   private static IngressoVendaDao instance;
	   public static IngressoVendaDao getInstance(){
		   if(instance == null){
			   instance = new IngressoVendaDao();
		   }
		   return instance;
	   }

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	private IngressoVendaDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}
	
	public boolean add(Ingresso ingresso){
    String comando = "INSERT INTO ingresso_venda(tipo_ingresso, valor_unitario, data_venda, hora_venda, quantidade, valor_total) VALUES (?,?,?,?,?,?);";
    try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, ingresso.getTipoIngresso());
			stmt.setDouble(2, ingresso.getValorUnitario());
			stmt.setString(3, ingresso.getDataVenda());
			stmt.setString(4, ingresso.getHoraVenda());
			stmt.setLong(5, ingresso.getQuantidade());
			stmt.setDouble(6, ingresso.getValorTotal());
            stmt.execute();
			return true; 
		}catch(SQLException e) {
			Logger.getLogger(IngressoVendaDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}
		}


			//4Metodo de persistencia com o BD para dados alterados dos funcionarios
		public boolean update(Ingresso ingresso, Long id){
    	String comando = "UPDATE ingresso_venda SET tipo_ingresso =?, valor_unitario =?, data_venda =?, hora_venda=?, quantidade=?, valor_total=? WHERE id=?;";
        try 
        {
		PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, ingresso.getTipoIngresso());
			stmt.setDouble(2, ingresso.getValorUnitario());
			stmt.setString(3, ingresso.getDataVenda());
			stmt.setString(4, ingresso.getHoraVenda());
			stmt.setLong(5, ingresso.getQuantidade());
			stmt.setDouble(6, ingresso.getValorTotal());
			stmt.setLong(7, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
		}
		}

    public List<Ingresso> getListVendasIngresso(){
            List<Ingresso> ingressos = new ArrayList<>();
			String comando = "SELECT * FROM ingresso_venda";
            try {
				PreparedStatement stmt = con.prepareStatement(comando);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
				Ingresso in = new Ingresso();
				in.setIdVenda(rs.getLong("id"));
                in.setTipoIngresso(rs.getString("tipo_ingresso"));
				in.setValorUnitario(rs.getDouble("valor_unitario"));
				in.setDataVenda(rs.getString("data_venda"));
				in.setHoraVenda(rs.getString("hora_venda"));
				in.setQuantidade(rs.getLong("quantidade"));
				in.setValorTotal(rs.getDouble("valor_total"));
				ingressos.add(in);
				}
                stmt.close();
				rs.close();
				con.close();
                }catch(SQLException e) {
				return null;
			}	
            return ingressos;
        }
        
}

