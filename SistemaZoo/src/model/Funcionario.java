package model;

public class Funcionario{
	//9

	private Long id;    
	
	private String nomeF;
	
	private String dtAdmissaoF;
	
	private String dtSaidaF;
	
	private String mtvSaidaF;
	
	private String funcaoF;
	
	private String enderecoF;
	
	private float telefoneF;
	
	private float salarioF;
    
	private String situacao;

	private String tipoContrato;
    
	public Funcionario(String nomeF, String dtAdmissaoF, String dtSaidaF, String mtvSaidaF, String funcaoF, String enderecoF, float telefoneF, float salarioF, String situcaoFunc, String tipoContratoFunc) {
        super();
    	this.nomeF = nomeF;
        this.dtAdmissaoF = dtAdmissaoF;
        this.dtSaidaF = dtSaidaF;
        this.mtvSaidaF = mtvSaidaF;
        this.funcaoF = funcaoF;
        this.enderecoF = enderecoF;
        this.telefoneF = telefoneF;
        this.salarioF = salarioF;
		this.situacao= situcaoFunc;
		this.tipoContrato = tipoContratoFunc;
    }
    
    //gets and sets

    public Funcionario() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeF() {
		return nomeF;
	}

	public void setNomeF(String nomeF) {
		this.nomeF = nomeF;
	}

	public String getDtAdmissaoF() {
		return dtAdmissaoF;
	}

	public void setDtAdmissaoF(String dtAdmissaoF) {
		this.dtAdmissaoF = dtAdmissaoF;
	}

	public String getDtSaidaF() {
		return dtSaidaF;
	}

	public void setDtSaidaF(String dtSaidaF) {
		this.dtSaidaF = dtSaidaF;
	}

	public String getMtvSaidaF() {
		return mtvSaidaF;
	}

	public void setMtvSaidaF(String mtvSaidaF) {
		this.mtvSaidaF = mtvSaidaF;
	}

	public String getFuncaoF() {
		return funcaoF;
	}

	public void setFuncaoF(String funcaoF) {
		this.funcaoF = funcaoF;
	}

	public String getEnderecoF() {
		return enderecoF;
	}

	public void setEnderecoF(String enderecoF) {
		this.enderecoF = enderecoF;
	}

	public float getTelefoneF() {
		return telefoneF;
	}

	public void setTelefoneF(float telefoneF) {
		this.telefoneF = telefoneF;
	}

	public float getSalarioF() {
		return salarioF;
	}

	public void setSalarioF(float salarioF) {
		this.salarioF = salarioF;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}


}
