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
import core.model.Fornecedor;
//single
public  class FornecedorDao 
{
    private Connection con;
	private static FornecedorDao instance;
	public static FornecedorDao getInstance(){
		if(instance == null){
			instance = new FornecedorDao();
			}
			return instance;
		}


    //1Construtor responsavel por iniciar a conexao com o BD
  	private FornecedorDao() 
  	{
  		this.con = DatabaseFactory.getInstance().getDatabase("postgresql").conectar(); 	}
    //2Metodo de persistencia com o BD, adiciona os dados na classe e recebe como parametro um objeto do tipo fr
    public boolean addFornecedor(Fornecedor fr) 
    {
        String comando = "INSERT INTO fornecedor(nomefor, endfor, telfor, emailfor, cnpjfor, cidadefor, formaspfor) VALUES(?,?,?,?,?,?,?)";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(comando);
            stmt.setString(1, fr.getNomeFor()); 
            stmt.setString(2, fr.getEndFor());
            stmt.setFloat(3, fr.getTelefoneFor());
            stmt.setString(4, fr.getEmailFor());
            stmt.setFloat(5, fr.getCnpjFor());
            stmt.setString(6, fr.getCidadeFor());
            stmt.setString(7, fr.getFormaspFor());
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //3Metodo responsavel por pegar todas as informacoes da tabela fornecedor
  	public List<Fornecedor> getListFornecedor()
  	{
  		List<Fornecedor> fornecedores = new ArrayList<>();
  		String comando = "SELECT * FROM fornecedor";
  		try 
  		{ 
  			PreparedStatement stmt = con.prepareStatement(comando);
  			ResultSet rs = stmt.executeQuery(); 
  			while(rs.next()) 
  			{
  				Fornecedor fr = new Fornecedor();
  				fr.setId(rs.getLong("id"));
  				fr.setNomeFor(rs.getString("nomefor"));
  				fr.setEndFor(rs.getString("endfor"));
  				fr.setTelefoneFor(rs.getFloat("telfor"));
  				fr.setEmailFor(rs.getString("emailfor"));
  				fr.setCnpjFor(rs.getFloat("cnpjfor"));
  				fr.setCidadeFor(rs.getString("cidadefor"));
  				fr.setFormaspFor(rs.getString("formaspfor"));
  				fornecedores.add(fr);
  			}
  			
  		}catch(SQLException e) 
  		{
  			return null;
  		}	
  		return fornecedores;
  	}
  	
    //4Metodo de persistencia com o BD para dados alterados dos fornecedores
    public boolean alterar(Fornecedor fornecedor) 
    {
    	String sql = "UPDATE fornecedores SET nomefor=?, endfor=? telfor=?, emailfor=?, cnpjfor=?, cidadefor=?, formaspfor=? WHERE id=;?";
        try 
        {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNomeFor());
            stmt.setString(2, fornecedor.getEndFor());
            stmt.setFloat(3, fornecedor.getTelefoneFor());
            stmt.setString(4, fornecedor.getEmailFor());
            stmt.setFloat(5, fornecedor.getCnpjFor());
            stmt.setString(6, fornecedor.getCidadeFor());
            stmt.setString(7, fornecedor.getFormaspFor());
            stmt.execute();
            return true;
        } catch (SQLException ex) 
        {
            Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
	//5Metodo que realiza que faz a persistencia dos dados alterados
	public boolean updateFornecedor(Fornecedor fornecedor)
	{
		String comando = "UPDATE fornecedor SET nomefor=?, endfor=?, telfor =?, emailfor=?, cnpjfor=?, cidadefor=?, formaspfor=? WHERE id =?;";
		try 
		{
			PreparedStatement stmt = con.prepareStatement(comando);

			stmt.setString(1, fornecedor.getNomeFor());
			stmt.setString(2, fornecedor.getEndFor());
			stmt.setFloat(3, fornecedor.getTelefoneFor());
			stmt.setString(4, fornecedor.getEmailFor());
            stmt.setFloat(5, fornecedor.getCnpjFor());
            stmt.setString(6, fornecedor.getCidadeFor());
            stmt.setString(7, fornecedor.getFormaspFor());
			stmt.setLong(8, fornecedor.getId());
			stmt.execute();
				return true;
		}catch(SQLException e) 
		{
			Logger.getLogger(FornecedorDao.class.getName()).log(Level.SEVERE,null, e);
			return false;
		}		
	}
}
