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

public class IngressoDao {
	private Connection con;

	// Construtor reponsavel por inciar a conexï¿½o com o banco de dados
	public IngressoDao() {
		this.con = new DatabaseFactory().getDatabase("postgresql").conectar();
	}

	public boolean add(Ingresso ingresso){
String comando = "INSERT INTO ingresso(tipo_ingresso, valor_unitario) VALUES (?,?);";
try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, ingresso.getTipoIngresso());
			stmt.setDouble(2, ingresso.getValorUnitario());
            stmt.execute();
			return true;
		}catch(SQLException e) {
			Logger.getLogger(IngressoDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}
		}


        public List<Ingresso> getListIngresso(){
            List<Ingresso> ingressos = new ArrayList<>();
			String comando = "SELECT * FROM ingresso";
            try {
				PreparedStatement stmt = con.prepareStatement(comando);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
				Ingresso in = new Ingresso();
                in.setTipoIngresso(rs.getString("tipo_ingresso"));
				in.setValorUnitario(rs.getDouble("valor_unitario"));
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

