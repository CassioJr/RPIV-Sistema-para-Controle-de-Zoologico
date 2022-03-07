package model;

public class Pedido {
	private Long id;    	
	private String alimentoPed;	
	private String dataPed;	
	private String fornecedorPed;
	private int quantidadePed;
	private String situacaoPed;

	public Pedido(Long id, String alimentoPed, String dataPed, String fornecedorPed, String situacaoPed,  int quantidadePed)
	{
	    super();
	    this.id = id;
		this.alimentoPed = alimentoPed;
	    this.dataPed = dataPed;
	    this.fornecedorPed = fornecedorPed;
	    this.quantidadePed = quantidadePed;
	    this.situacaoPed = situacaoPed;
	}
	
	public Pedido(String alimentoPed, String dataPed, String fornecedorPed, int quantidadePed, String situacaoPed) 
	{
		super();
		this.alimentoPed = alimentoPed;
	    this.dataPed = dataPed;
	    this.fornecedorPed = fornecedorPed;
	    this.quantidadePed = quantidadePed;
	    this.situacaoPed = situacaoPed;
	}
	
	//gets e sets
	
	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlimentoPed() {
		return alimentoPed;
	}

	public void setAlimentoPed(String alimentoPed) {
		this.alimentoPed = alimentoPed;
	}

	public String getDataPed() {
		return dataPed;
	}

	public void setDataPed(String dataPed) {
		this.dataPed = dataPed;
	}

	public String getFornecedorPed() {
		return fornecedorPed;
	}

	public void setFornecedorPed(String fornecedorPed) {
		this.fornecedorPed = fornecedorPed;
	}

	public int getQuantidadePed() {
		return quantidadePed;
	}

	public void setQuantidadePed(int quantidadePed) {
		this.quantidadePed = quantidadePed;
	}

	public String getSituacaoPed() {
		return situacaoPed;
	}

	public void setSituacaoPed(String situacaoPed) {
		this.situacaoPed = situacaoPed;
	}

}
