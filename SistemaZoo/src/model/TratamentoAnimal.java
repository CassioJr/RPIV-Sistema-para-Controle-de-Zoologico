package model;

public class TratamentoAnimal {
    Long id;
	String dataEntradaTratamento;
	String horarioTratamento;
    String nomeAnimalTratamento;
    String especieAnimalTratamento;
    String racaAnimalTratamento;
    String sexo;
    float pesoAnimalTratamento;
    String motivoInternacao;
	public TratamentoAnimal(Long id, String dataEntradaTratamento, String horarioTratamento,
			String nomeAnimalTratamento, String especieAnimalTratamento, String racaAnimalTratamento, String sexo,
			float pesoAnimalTratamento, String motivoInternacao) {
		super();
		this.id = id;
		this.dataEntradaTratamento = dataEntradaTratamento;
		this.horarioTratamento = horarioTratamento;
		this.nomeAnimalTratamento = nomeAnimalTratamento;
		this.especieAnimalTratamento = especieAnimalTratamento;
		this.racaAnimalTratamento = racaAnimalTratamento;
		this.sexo = sexo;
		this.pesoAnimalTratamento = pesoAnimalTratamento;
		this.motivoInternacao = motivoInternacao;
	}
	public TratamentoAnimal(String dataEntradaTratamento, String horarioTratamento, String nomeAnimalTratamento,
			String especieAnimalTratamento, String racaAnimalTratamento, String sexo, float pesoAnimalTratamento,
			String motivoInternacao) {
		super();
		this.dataEntradaTratamento = dataEntradaTratamento;
		this.horarioTratamento = horarioTratamento;
		this.nomeAnimalTratamento = nomeAnimalTratamento;
		this.especieAnimalTratamento = especieAnimalTratamento;
		this.racaAnimalTratamento = racaAnimalTratamento;
		this.sexo = sexo;
		this.pesoAnimalTratamento = pesoAnimalTratamento;
		this.motivoInternacao = motivoInternacao;
	}
	public TratamentoAnimal() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataEntradaTratamento() {
		return dataEntradaTratamento;
	}
	public void setDataEntradaTratamento(String dataEntradaTratamento) {
		this.dataEntradaTratamento = dataEntradaTratamento;
	}
	public String getHorarioTratamento() {
		return horarioTratamento;
	}
	public void setHorarioTratamento(String horarioTratamento) {
		this.horarioTratamento = horarioTratamento;
	}
	public String getNomeAnimalTratamento() {
		return nomeAnimalTratamento;
	}
	public void setNomeAnimalTratamento(String nomeAnimalTratamento) {
		this.nomeAnimalTratamento = nomeAnimalTratamento;
	}
	public String getEspecieAnimalTratamento() {
		return especieAnimalTratamento;
	}
	public void setEspecieAnimalTratamento(String especieAnimalTratamento) {
		this.especieAnimalTratamento = especieAnimalTratamento;
	}
	public String getRacaAnimalTratamento() {
		return racaAnimalTratamento;
	}
	public void setRacaAnimalTratamento(String racaAnimalTratamento) {
		this.racaAnimalTratamento = racaAnimalTratamento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public float getPesoAnimalTratamento() {
		return pesoAnimalTratamento;
	}
	public void setPesoAnimalTratamento(float pesoAnimalTratamento) {
		this.pesoAnimalTratamento = pesoAnimalTratamento;
	}
	public String getMotivoInternacao() {
		return motivoInternacao;
	}
	public void setMotivoInternacao(String motivoInternacao) {
		this.motivoInternacao = motivoInternacao;
	}
	
    
    
    
    
}
