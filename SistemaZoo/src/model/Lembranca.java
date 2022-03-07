package model;

public class Lembranca extends Venda{
    
    private String nomeLembranca;
    private double valorUnitario;
    
	public Lembranca(String dataVenda, String horaVenda, Long quantidade, double valorTotal, String nomeLembranca,
			double valorUnitario) {
		super(dataVenda, horaVenda, quantidade, valorTotal);
		this.nomeLembranca = nomeLembranca;
		this.valorUnitario = valorUnitario;
	}

	public Lembranca(){
		super();
	}

	public String getNomeLembranca() {
		return nomeLembranca;
	}

	public void setNomeLembranca(String nomeLembranca) {
		this.nomeLembranca = nomeLembranca;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}


    
}
