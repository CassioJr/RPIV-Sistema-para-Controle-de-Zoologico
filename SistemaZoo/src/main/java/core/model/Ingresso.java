package core.model;

public class Ingresso extends Venda{
	private String tipoIngresso;
	private double valorUnitario;
	
	
	public Ingresso(String tipoIngresso,double valorUnitario, String dataVenda, String horaVenda, Long quantidade, double valorTotal) {
		super(dataVenda, horaVenda, quantidade, valorTotal);
		this.tipoIngresso = tipoIngresso;
        this.valorUnitario =valorUnitario;
	}

	public Ingresso(){
		super();
	}

	public String getTipoIngresso() {
		return tipoIngresso;
	}


	public void setTipoIngresso(String tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}


	public double getValorUnitario() {
		return valorUnitario;
	}


	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	
	
}
