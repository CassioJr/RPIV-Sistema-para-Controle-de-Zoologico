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
    public boolean addTratamento(String data, String horario, String situacao,TratamentoAnimal ta) {
        String sql = "INSERT INTO tratamento(id,dataentrada,horarioentrada,nome,idade,especie,sexo,numeroabrigo,motivointernacao,situacao,procedimento,evolucaoquadro,resultados,motivoconsulta,tratamento,resultadosatendimento,vacinacao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, ta.getId());
            stmt.setString(2, data);//Pega a data local
            stmt.setString(3, horario);//Pega o horario local
            stmt.setString(4, ta.getNomeAnimal());
            stmt.setInt(5, ta.getIdadeAnimal());
            stmt.setString(6, ta.getNomeEspecie());
            stmt.setString(7, ta.getSexoAnimal());
			stmt.setInt(8, ta.getNumeroAbrigo());
            stmt.setString(9, "");
			stmt.setString(10, situacao);
			stmt.setString(11, "" );
			stmt.setString(12,"");
			stmt.setString(13, "");
			stmt.setString(14, ta.getNomeDoenca());
			stmt.setString(15, "");
			stmt.setString(16, "");
			stmt.setString(17,"");
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TratamentoAnimaisDao.class.getName()).log(Level.SEVERE, null, ex);
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
			ta.setIdent(rs.getLong("ident"));
			ta.setId(rs.getLong("id"));
			ta.setDataEntradaTratamento(rs.getString("dataentrada"));
			ta.setHorarioTratamento(rs.getString("horarioentrada"));
			ta.setNomeAnimal(rs.getString("nome"));
			ta.setIdadeAnimal(rs.getInt("idade"));
			ta.setNomeEspecie(rs.getString("especie"));
			ta.setSexoAnimal(rs.getString("sexo"));
			ta.setNumeroAbrigo(rs.getInt("numeroabrigo"));
			ta.setMotivoInternacao(rs.getString("motivointernacao"));
			ta.setSituacao(rs.getString("situacao"));
			ta.setProcedimento(rs.getString("procedimento"));
			ta.setEvolucaoQuadro(rs.getString("evolucaoquadro"));
			ta.setResultados(rs.getString("resultados"));
			ta.setNomeDoenca(rs.getString("motivoconsulta"));
			ta.setTratamento(rs.getString("tratamento"));
			ta.setResultadosAtendimento(rs.getString("resultadosatendimento"));
			ta.setVacinacaoVermufucacao(rs.getString("vacinacao"));
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
		String comando = "UPDATE tratamento SET nome =?,idade=?,especie=?,sexo=?,numeroabrigo=?,situacao=?,tratamento=?,motivoconsulta=?,resultadosatendimento=?,vacinacao=? WHERE ident =?;";
		try {
			PreparedStatement stmt = connection.prepareStatement(comando);
            stmt.setString(1, t.getNomeAnimal());
            stmt.setInt(2, t.getIdadeAnimal());
            stmt.setString(3, t.getNomeEspecie());
            stmt.setString(4, t.getSexoAnimal());
			stmt.setInt(5, t.getNumeroAbrigo());
			stmt.setString(6, t.getSituacao());
			stmt.setString(7, t.getTratamento());
			stmt.setString(8, t.getNomeDoenca());
			stmt.setString(9, t.getResultadosAtendimento());
			stmt.setString(10, t.getVacinacaoVermufucacao());
			stmt.setLong(11, t.getIdent());
            stmt.execute();
            return true;
		}catch(SQLException e) {
			Logger.getLogger(TratamentoAnimaisDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}		
	}

public boolean updateInternacao(TratamentoAnimal t) {
		String comando = "UPDATE tratamento SET motivointernacao=?,situacao=?,procedimento=?,evolucaoquadro=?,resultados=? WHERE ident=?;";
	try {
			PreparedStatement stmt = connection.prepareStatement(comando);
			stmt.setString(1, t.getMotivoInternacao());
			stmt.setString(2, t.getSituacao());
			stmt.setString(3, t.getProcedimento());
			stmt.setString(4, t.getEvolucaoQuadro());
			stmt.setString(5, t.getResultados());
			stmt.setLong(6, t.getIdent());
            stmt.execute();
            return true;
		}catch(SQLException e) {
			Logger.getLogger(TratamentoAnimaisDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}		
}

//Método que realiza que faz a persistencia dos dados alterados dos animais
public boolean updateEstadoAltaObto(String situacao, Long long1) {
	String comando = "UPDATE tratamento SET situacao =? WHERE ident=?;";
	try {
		PreparedStatement stmt = connection.prepareStatement(comando);
		stmt.setString(1,situacao);
		stmt.setLong(2, long1);
		stmt.execute();
		return true;
	}catch(SQLException e) {
		Logger.getLogger(TratamentoAnimaisDao.class.getName()).log(Level.SEVERE,null, e);
		return false;
	}		
}

}
