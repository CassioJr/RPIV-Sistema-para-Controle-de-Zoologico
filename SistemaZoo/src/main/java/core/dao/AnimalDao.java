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
import core.model.Animal;

public class AnimalDao {
	private Connection con;
	private static AnimalDao instance;
	
	public static AnimalDao getInstance(){
		if(instance == null){
			instance = new AnimalDao();
		}
		return instance;
	}

	// Construtor reponsavel por inciar a conex�o com o banco de dados
	private AnimalDao() {
		this.con = DatabaseFactory.getInstance().getDatabase("postgresql").conectar();
	}

	// Método que realiza a persistencia da classe animal dentro do banco de dados,
	// ela recebe como parametro um objeto do tipo animal
	public boolean addAnimal(Animal animal) {
		String comando = "INSERT INTO animal(nomeanimal,sexoanimal,idadeanimal,tipotransferencia,instituicaoorigem,instituicaodestino,estadosaude,nomedoenca,nomeespecie,habitatespecie,localizacaoabrigo,tamanhoabrigo,numeroabrigo,nomealimento,quantidadeDiariaalimento,medidaquantidadealimento,datatransferencia,consultando) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, animal.getNomeAnimal());
			stmt.setString(2, animal.getSexoAnimal());
			stmt.setInt(3, animal.getIdadeAnimal());
			stmt.setString(4, animal.getTipoTransferencia());
			stmt.setString(5, animal.getInstituicaoOrigem());
			stmt.setString(6, animal.getInstituicaoDestino());
			stmt.setString(7, animal.getEstadoSaude());
			stmt.setString(8, animal.getNomeDoenca());
			stmt.setString(9, animal.getNomeEspecie());
			stmt.setString(10, animal.getHabitatEspecie());
			stmt.setString(11, animal.getLocalizacaoAbrigo());
			stmt.setFloat(12, animal.getTamanhoAbrigo());
			stmt.setInt(13, animal.getNumeroAbrigo());
			stmt.setString(14, animal.getNomeAlimento());
			stmt.setFloat(15, animal.getQuantidadeDiaria_Alimento());
			stmt.setString(16, animal.getMedidaQuantidade_Alimento());
			stmt.setString(17, animal.getDatatransfenciaInstituicao());
			stmt.setBoolean(18, animal.getConsultando());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	// Método que realiza que � responsavel por pegar todas as informa��es da tabela
	// animal
	public List<Animal> getListAnimal() {
		List<Animal> animais = new ArrayList<>();
		String comando = "SELECT * FROM animal";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Animal a = new Animal();
				a.setId(rs.getLong("id"));
				a.setNomeAnimal(rs.getString("nomeanimal"));
				a.setIdadeAnimal(rs.getInt("idadeanimal"));
				a.setSexoAnimal(rs.getString("sexoanimal"));
				a.setTipoTransferencia(rs.getString("tipotransferencia"));
				a.setInstituicaoOrigem(rs.getString("instituicaoorigem"));
				a.setInstituicaoDestino(rs.getString("instituicaodestino"));
				a.setEstadoSaude(rs.getString("estadosaude"));
				a.setNomeDoenca(rs.getString("nomedoenca"));
				a.setNomeEspecie(rs.getString("nomeespecie"));
				a.setHabitatEspecie(rs.getString("habitatespecie"));
				a.setNumeroAbrigo(rs.getInt("numeroabrigo"));
				a.setLocalizacaoAbrigo(rs.getString("localizacaoabrigo"));
				a.setTamanhoAbrigo(rs.getFloat("tamanhoabrigo"));
				a.setNomeAlimento(rs.getString("nomealimento"));
				a.setQuantidadeDiaria_Alimento(rs.getFloat("quantidadediariaalimento"));
				a.setMedidaQuantidade_Alimento(rs.getString("medidaquantidadealimento"));
				a.setDatatransfenciaInstituicao(rs.getString("datatransferencia"));
				a.setConsultando(rs.getBoolean("consultando"));
				animais.add(a);
			}
			
		} catch (SQLException e) {
			return null;
		}
		return animais;
	}

	// Método que realiza que faz a persistencia dos dados alterados dos animais
	public boolean updateAnimal(Animal animal, Long id) {
		String comando = "UPDATE animal SET nomeanimal =?,sexoanimal =?,idadeanimal =?,tipotransferencia =?,instituicaoorigem =?,instituicaodestino =?,estadosaude =?,nomedoenca =?,nomeespecie =?,habitatespecie =?,localizacaoabrigo =?,tamanhoabrigo =?,numeroabrigo =?,nomealimento =?,quantidadeDiariaalimento =?,medidaquantidadealimento =?,datatransferencia =? WHERE id =?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, animal.getNomeAnimal());
			stmt.setString(2, animal.getSexoAnimal());
			stmt.setInt(3, animal.getIdadeAnimal());
			stmt.setString(4, animal.getTipoTransferencia());
			stmt.setString(5, animal.getInstituicaoOrigem());
			stmt.setString(6, animal.getInstituicaoDestino());
			stmt.setString(7, animal.getEstadoSaude());
			stmt.setString(8, animal.getNomeDoenca());
			stmt.setString(9, animal.getNomeEspecie());
			stmt.setString(10, animal.getHabitatEspecie());
			stmt.setString(11, animal.getLocalizacaoAbrigo());
			stmt.setFloat(12, animal.getTamanhoAbrigo());
			stmt.setInt(13, animal.getNumeroAbrigo());
			stmt.setString(14, animal.getNomeAlimento());
			stmt.setFloat(15, animal.getQuantidadeDiaria_Alimento());
			stmt.setString(16, animal.getMedidaQuantidade_Alimento());
			stmt.setString(17, animal.getDatatransfenciaInstituicao());
			stmt.setLong(18, id);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	// Método que realiza que faz a persistencia dos dados alterados dos animais
	public boolean updateEstadoConsulta(boolean estado, Long long1) {
		String comando = "UPDATE animal SET consultando =? WHERE id =?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setBoolean(1, estado);
			stmt.setLong(2, long1);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	// MÃ©todo que realiza que faz a alteração do estado de saude do animal
	public boolean updateEstadoSaude(String estadosaude, String nomedoenca, Long long1) {
		String comando = "UPDATE animal SET estadosaude =?, nomedoenca =? WHERE id =?;";
		try {
			PreparedStatement stmt = con.prepareStatement(comando);
			stmt.setString(1, estadosaude);
			stmt.setString(2, nomedoenca);
			stmt.setLong(3, long1);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(AnimalDao.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

}
