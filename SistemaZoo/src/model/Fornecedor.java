package model;

public class Fornecedor 
{
	private Long id;    	
	private String nomeFor;	
	private String endFor;	
	private float telefoneFor;

	public Fornecedor(Long id, String nomeFor, String endFor, float telefoneFor)
	{
	    super();
	    this.id = id;
		this.nomeFor = nomeFor;
	    this.endFor = endFor;
	    this.telefoneFor = telefoneFor;
	}
	
	public Fornecedor(String nomeFor, String endFor, float telefoneFor) 
	{
	    super();
		this.nomeFor = nomeFor;
	    this.endFor = endFor;
	    this.telefoneFor = telefoneFor;
	}

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}

	//gets e sets
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getNomeFor() 
	{
		return nomeFor;
	}
	public void setNomeFor(String nomeFor) 
	{
		this.nomeFor = nomeFor;
	}
	public String getEndFor() 
	{
		return endFor;
	}
	public void setEndFor(String endFor) 
	{
		this.endFor = endFor;
	}
	public float getTelefoneFor() 
	{
		return telefoneFor;
	}
	public void setTelefoneFor(float telefoneFor) 
	{
		this.telefoneFor = telefoneFor;
	}	
}
