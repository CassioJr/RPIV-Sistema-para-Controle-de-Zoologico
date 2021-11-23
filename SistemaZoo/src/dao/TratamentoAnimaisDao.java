package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.DatabasePostgreSQL;
import model.Animal;
import model.TratamentoAnimal;

public class TratamentoAnimaisDao {
    private Connection connection;
    
    //Construtor reponsavel por iniciar a conexão com o BD
  	public TratamentoAnimaisDao() {
  		this.connection = new DatabasePostgreSQL().conectar();
  	}

 //Implementado todos métodos de persistencia com o BD 'inserir' para as classes
    public boolean addTratamento(TratamentoAnimal t) {
        String sql = "INSERT INTO tratamento(dataentrada,horarioentrada,nome,especie,raca,sexo,peso,motivointernacao) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, t.getDataEntradaTratamento());
            stmt.setString(2, t.getHorarioTratamento());
            stmt.setString(3, t.getNomeAnimalTratamento());
            stmt.setString(4, t.getEspecieAnimalTratamento());
            stmt.setString(5, t.getRacaAnimalTratamento());
            stmt.setFloat(6, t.getPesoAnimalTratamento());
            stmt.setString(7, t.getMotivoInternacao());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<TratamentoAnimal> listarTratamentoAnimais(){
        List<TratamentoAnimal> tratamentos = new ArrayList<>();
        String comando = "SELECT * FROM tratamento";
        	try {
			PreparedStatement stmt = connection.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			TratamentoAnimal ta = new TratamentoAnimal();
			ta.setId(rs.getLong("id"));
			ta.setDataEntradaTratamento(rs.getString("dataentrada"));
			ta.setHorarioTratamento(rs.getString("horarioentrada"));
			ta.setNomeAnimalTratamento(rs.getString("nome"));
			ta.setEspecieAnimalTratamento(rs.getString("especie"));
			ta.setRacaAnimalTratamento(rs.getString("raca"));
			ta.setSexo(rs.getString("sexo"));
			ta.setPesoAnimalTratamento(rs.getFloat("peso"));
			ta.setMotivoInternacao(rs.getString("motivointernacao"));
			tratamentos.add(ta);
			}
			stmt.close();
			rs.close();
			connection.close();
		}catch(SQLException e) {
			return null;
		}	
		return tratamentos;
	}

	public boolean updateTratamento(TratamentoAnimal t) {
		String comando = "UPDATE animal SET dataentrada =?,horarioentrada =?,nome =?,especie =?,raca =?,sexo =?,peso =?,motivointernacao =? WHERE id =?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(comando);
			stmt.setString(1, t.getDataEntradaTratamento());
			stmt.setString(2, t.getHorarioTratamento());
			stmt.setString(3, t.getEspecieAnimalTratamento());
			stmt.setString(4, t.getRacaAnimalTratamento());
			stmt.setString(5, t.getSexo());
			stmt.setFloat(6, t.getPesoAnimalTratamento());
			stmt.setString(7, t.getMotivoInternacao());
			stmt.setLong(8, t.getId());
			stmt.execute();
			return true;
		}catch(SQLException e) {
			Logger.getLogger(TratamentoAnimaisDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}		
	}

}
