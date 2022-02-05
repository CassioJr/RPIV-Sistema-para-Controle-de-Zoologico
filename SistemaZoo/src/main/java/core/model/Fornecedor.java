package core.model;

public class Fornecedor 
{
	private Long id;    	
	private String nomeFor;	
	private String endFor;	
	private float telefoneFor;
	
	private String emailFor;	
	private float cnpjFor;
	private String cidadeFor;	
	private String formaspFor;	


	public Fornecedor(Long id, String nomeFor, String endFor, float telefoneFor, String emailFor, float cnpjFor, String cidadeFor, String formaspFor)
	{
	    super();
	    this.id = id;
		this.nomeFor = nomeFor;
	    this.endFor = endFor;
	    this.telefoneFor = telefoneFor;
	    
		this.emailFor = emailFor;
		this.cnpjFor = cnpjFor;
		this.cidadeFor = cidadeFor;
		this.formaspFor = formaspFor;
	}
	
	public Fornecedor(String nomeFor, String endFor, float telefoneFor, String emailFor, float cnpjFor, String cidadeFor, String formaspFor) 
	{
	    super();
		this.nomeFor = nomeFor;
	    this.endFor = endFor;
	    this.telefoneFor = telefoneFor;
	    
		this.emailFor = emailFor;
		this.cnpjFor = cnpjFor;
		this.cidadeFor = cidadeFor;
		this.formaspFor = formaspFor;
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
	
	public String getEmailFor() 
	{
		return emailFor;
	}
	public void setEmailFor(String emailFor) {
		this.emailFor = emailFor;
	}
	
	public float getCnpjFor() {
		return cnpjFor;
	}

	public void setCnpjFor(float cnpjFor) {
		this.cnpjFor = cnpjFor;
	}

	public String getCidadeFor() {
		return cidadeFor;
	}

	public void setCidadeFor(String cidadeFor) {
		this.cidadeFor = cidadeFor;
	}

	public String getFormaspFor() {
		return formaspFor;
	}

	public void setFormaspFor(String formaspFor) {
		this.formaspFor = formaspFor;
	}

}