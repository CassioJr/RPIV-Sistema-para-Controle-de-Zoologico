package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DatabaseFactory;
import database.DatabasePostgreSQL;
import model.Funcionario;

public class FuncionarioDao 
{
    private Connection connection;
    
    //1Construtor responsavel por iniciar a conexao com o BD
  	public FuncionarioDao() 
  	{
		this.connection = new DatabaseFactory().getDatabase("postgresql").conectar();
  	}
  	
    //2Metodo de persistencia com o BD, adiciona os dados na classe e recebe como parametro um objeto do tipo f
    public boolean addFuncionario(Funcionario f) 
    {
        String comando = "INSERT INTO funcionario(nome, dtadmissao, dtsaida, mtvsaida, funcao, endereco, telefone, salario, situacao, tipo_contrato) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try 
        {
            PreparedStatement stmt = connection.prepareStatement(comando);
            stmt.setString(1, f.getNomeF());
            stmt.setString(2, f.getDtAdmissaoF());
            stmt.setString(3, f.getDtSaidaF());
            stmt.setString(4, f.getMtvSaidaF());
            stmt.setString(5, f.getFuncaoF());
            stmt.setString(6, f.getEnderecoF());
            stmt.setFloat(7, f.getTelefoneF());
            stmt.setFloat(8, f.getSalarioF());
			stmt.setString(9, f.getSituacao());
			stmt.setString(10, f.getTipoContrato());
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
	//3Metodo responsavel por pegar todas as informacoes da tabela funcionario
	public List<Funcionario> getListFuncionario()
	{
		List<Funcionario> funcionarios = new ArrayList<>();
		String comando = "SELECT * FROM funcionario";
		try 
		{ 
			PreparedStatement stmt = connection.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) 
			{
				Funcionario f = new Funcionario();
				f.setId(rs.getLong("id"));
				f.setNomeF(rs.getString("nome"));
				f.setDtAdmissaoF(rs.getString("dtadmissao"));
				f.setDtSaidaF(rs.getString("dtsaida"));
				f.setMtvSaidaF(rs.getString("mtvsaida"));
				f.setEnderecoF(rs.getString("endereco"));
				f.setFuncaoF(rs.getString("funcao"));
				f.setTelefoneF(rs.getFloat("telefone"));
				f.setSalarioF(rs.getFloat("salario"));
				f.setSituacao("situacao");
				f.setTipoContrato("tipo_contrato");
				funcionarios.add(f);
			}
			stmt.close();
			rs.close();
			connection.close();
		}catch(SQLException e) 
		{
			return null;
		}	
		return funcionarios;
	}
    
    //4Metodo de persistencia com o BD para dados alterados dos funcionarios
    public boolean alterar(Funcionario funcionario) 
    {
    	String sql = "UPDATE funcionarios SET nome=?, dtadmissao, dtsaida=?, mtvsaida=?, funcao=?, endereco=? telefone=? salario=? WHERE id=;?";
        try 
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomeF());
            stmt.setString(2, funcionario.getDtAdmissaoF());
            stmt.setString(3, funcionario.getDtSaidaF());
            stmt.setString(4, funcionario.getMtvSaidaF());
            stmt.setString(5, funcionario.getFuncaoF());
            stmt.setString(6, funcionario.getEnderecoF());
            stmt.setFloat(7, funcionario.getTelefoneF());
            stmt.setFloat(8, funcionario.getSalarioF());
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	
	//5Metodo que realiza que faz a persistencia dos dados alterados
	public boolean updateFuncionario(Funcionario funcionario)
	{
		String comando = "UPDATE funcionario SET nome=?, dtadmissao=?, dtsaida=?, mtvsaida =?, endereco=?, funcao =?, telefone =?, salario =? WHERE id =?;";
		try 
		{
			PreparedStatement stmt = connection.prepareStatement(comando);
			stmt.setString(1, funcionario.getNomeF());
			stmt.setString(2, funcionario.getDtAdmissaoF());
			stmt.setString(3, funcionario.getDtSaidaF());
			stmt.setString(4, funcionario.getMtvSaidaF());
			stmt.setString(5, funcionario.getEnderecoF());
			stmt.setString(6, funcionario.getFuncaoF());
			stmt.setFloat(7, funcionario.getTelefoneF());
			stmt.setFloat(8, funcionario.getSalarioF());
			stmt.setLong(9, funcionario.getId());
			stmt.execute();
				return true;
		}catch(SQLException e) 
		{
			Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}		
	}
}
    