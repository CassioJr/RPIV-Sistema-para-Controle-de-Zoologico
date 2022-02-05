package core.model;

public class Alimento extends Venda{
    private String nomeAlimento;
    private double valorUnitario;
    
    
	public Alimento(String dataVenda, String horaVenda, Long quantidade, double valorTotal, String nomeAlimento,
			double valorUnitario) {
		super(dataVenda, horaVenda, quantidade, valorTotal);
		this.nomeAlimento = nomeAlimento;
		this.valorUnitario = valorUnitario;
	}

	public Alimento(){
		super();
	}



	public String getNomeAlimento() {
		return nomeAlimento;
	}


	public void setNomeAlimento(String nomeAlimento) {
		this.nomeAlimento = nomeAlimento;
	}


	public double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
    
    
    
}
