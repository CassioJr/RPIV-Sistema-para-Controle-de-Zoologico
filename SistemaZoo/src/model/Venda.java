package model;

public class Venda{
	private Long idVenda;
	private String dataVenda;
	private String horaVenda;
	private Long quantidade;
	private double valorTotal;

	public Venda(String dataVenda, String horaVenda, Long quantidade,
			double valorTotal) {
		super();
		this.dataVenda = dataVenda;
		this.horaVenda = horaVenda;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
	}

	public Venda(){
		super();
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getHoraVenda() {
		return horaVenda;
	}

	public void setHoraVenda(String horaVenda) {
		this.horaVenda = horaVenda;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	 }