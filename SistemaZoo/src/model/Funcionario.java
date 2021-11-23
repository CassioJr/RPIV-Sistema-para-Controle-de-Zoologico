package model;

public class Funcionario{
	//9
    private long codFuncionario;    
    private String nomeF;
    private String dtAdmissao;
    private String dtSaida;
    private String mtvSaida;
    private String funcao;
    private String endereco;
    private float telefone;
    private float salario;
    
    public Funcionario(Long codF, String nomeF, String dtAdmissao, String dtSaida, String mtvSaida, String funcao, String endereco, float telefone, float salario) {
        super();
        this.codFuncionario = codF;
    	this.nomeF = nomeF;
        this.dtAdmissao = dtAdmissao;
        this.dtSaida = dtSaida;
        this.mtvSaida = mtvSaida;
        this.funcao = funcao;
        this.endereco = endereco;
        this.telefone = telefone;
        this.salario = salario;
    }
    
    //gets and sets
    public long getCdFuncionario() {
        return codFuncionario;
    }

    public void setCdFuncionario(long cdFuncionario) {
        this.codFuncionario = cdFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeF;
    }

    public void setNomeFuncionario(String nome) {
        this.nomeF = nome;
    }
    
    public float getTelefone() {
        return telefone;
    }

    public void setTelefone(float telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return this.nomeF;
    }

	public String getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(String dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public String getDtSaida() {
		return dtSaida;
	}

	public void setDtSaida(String dtSaida) {
		this.dtSaida = dtSaida;
	}

	public String getMtvSaida() {
		return mtvSaida;
	}

	public void setMtvSaida(String mtvSaida) {
		this.mtvSaida = mtvSaida;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
