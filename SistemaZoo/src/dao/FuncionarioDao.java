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
import javafx.util.Callback;
import model.Funcionario;

public class FuncionarioDao {

    private Connection connection;
    
    //Construtor reponsavel por iniciar a conex�o com o BD
  	public FuncionarioDao() {
  		this.connection = new DatabasePostgreSQL().conectar();
  	}
    
    //Implementado todos m�todos de persist�ncia com o BD 'inserir' para as classes
    public boolean addFuncionario(Funcionario f) {
        String sql = "INSERT INTO funcionarios(nome, dtAdmissao, dtSaida, mtvSaida, funcao, endereco, telefone, salario) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, f.getNomeFuncionario());
            stmt.setString(2, f.getDtAdmissao());
            stmt.setString(3, f.getDtSaida());
            stmt.setString(4, f.getMtvSaida());
            stmt.setString(5, f.getFuncao());
            stmt.setString(6, f.getEndereco());
            stmt.setFloat(7, f.getTelefone());
            stmt.setFloat(8, f.getSalario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Implementado todos m�todos de persist�ncia com o BD 'alterar' para as classes	
    public boolean alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionarios SET nome=?, dtAdmissao=?, dtSaida=?, mtvSaida=?, funcao=?, endereco=? telefone=? salario=? WHERE cdFuncionario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNomeFuncionario());
            stmt.setString(2, funcionario.getDtAdmissao());
            stmt.setString(3, funcionario.getDtSaida());
            stmt.setString(4, funcionario.getMtvSaida());
            stmt.setString(5, funcionario.getFuncao());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setFloat(7, funcionario.getTelefone());
            stmt.setFloat(8, funcionario.getSalario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	
	//M�todo que realiza que � responsavel por pegar todas as informa��es da tabela funcionario
		public List<Funcionario> getListFuncionario(){
			List<Funcionario> funcionarios = new ArrayList<>();
			String comando = "SELECT * FROM funcionario";
			try { 
				PreparedStatement stmt = connection.prepareStatement(comando);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					//algo errado
				Funcionario f = new Funcionario(null, comando, comando, comando, comando, comando, comando, 0, 0);
				f.setCdFuncionario(rs.getLong("codF"));
				f.setNomeFuncionario(rs.getString("nomeF"));
				f.setDtAdmissao(rs.getString("dtAdmissaoF"));
				f.setDtSaida(rs.getString("dtSaidaF"));
				f.setMtvSaida(rs.getString("mtvSaidaF"));
				f.setEndereco(rs.getString("enderecoF"));
				f.setFuncao(rs.getString("funcaoF"));
				f.setTelefone(rs.getFloat("telefoneF"));
				f.setSalario(rs.getFloat("salarioF"));
				funcionarios.add(f);
				}
				stmt.close();
				rs.close();
				connection.close();
			}catch(SQLException e) {
				return null;
			}	
			return funcionarios;
		}
		
		//M�todo que realiza que faz a persistencia dos dados alterados
		public boolean updateFuncionario(Funcionario funcionario) {
			String comando = "UPDATE funcionario SET nomeF =?, dtAdmissaoF =?, dtSaidaF =?, mtvSaidaF =?, funcaoF =?, telefoneF =?, salarioF =?;";
			try {
				PreparedStatement stmt = connection.prepareStatement(comando);
				stmt.setString(1, funcionario.getNomeFuncionario());
				stmt.setString(2, funcionario.getDtAdmissao());
				stmt.setString(3, funcionario.getDtSaida());
				stmt.setString(4, funcionario.getMtvSaida());
				stmt.setString(5, funcionario.getEndereco());
				stmt.setString(6, funcionario.getFuncao());
				stmt.setFloat (7, funcionario.getTelefone());
				stmt.setFloat (8, funcionario.getSalario());
				stmt.setLong(9, funcionario.getCdFuncionario());
				stmt.execute();
				return true;
			}catch(SQLException e) {
				Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE,null, e);
				return false;
			}		
		}
	}
    
  //Implementado todos m�todos de persist�ncia com o BD 'remover' para as classes
   /* public boolean remover(Funcionario funcionario) {
        String sql = "DELETE FROM funcionarios WHERE cdFuncionario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, funcionario.getCdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    */