package core.model;

public class Login {
	Long id;
	String nome;
	String senha;
	String funcao;
	
	public Login(Long id, String nome, String senha, String funcao) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.funcao = funcao;
	}
	
	public Login(String nome, String senha, String funcao) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.funcao = funcao;
	}
	
	public Login() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	
	
}
