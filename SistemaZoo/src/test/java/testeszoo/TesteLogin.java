package testeszoo;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.model.Login;
 
public class TesteLogin {
 
	@Before
	public void setUp() throws Exception{
	}
	
	@After
	public void tearDown() throws Exception{
	}
	
	//como chama o objeto login retorna uma mensagem e ver se o TDD funciona
	//@Test
	public void instantTemplate() {
		//classe de login
		Login login = new Login();
		//o assert verifica se o login não está null
		assertNotNull(login);
	}
	
	//teste passando String no login
	//@Test
	public void instantModeloString() {
		String s = "username";
		Login login = new Login(s);
		//o assert verifica se o login não está null
		assertNotNull(login);
	}
	
	//teste passando String no username
	//@Test
	public void instantModeloStringnome() {
		String s = "username";
		Login setNome = new Login(s);
		//o assert verifica se o login não está null
		assertNotNull(setNome);
	}
	
	//teste passando número integer no nome
	//@Test
	public void instantModeloNum() {
		Integer i = 7777777;
		Login setNome = new Login(i);
		//o assert verifica se o login não está null
		assertNotNull(setNome);
	}

}