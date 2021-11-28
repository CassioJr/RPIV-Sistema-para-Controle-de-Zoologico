package model;

public class TratamentoAnimal extends Animal {
    Long id;
	String dataEntradaTratamento;
	String horarioTratamento;
    String nomeAnimalTratamento;
    String idadeAnimalTratamento;
    String especieAnimalTratamento;
    String sexo; 
    int numeroAbrigo;    
    String motivoInternacao;
    String situacao;
	String procedimento;
	String evolucaoQuadro;
	String resultados;
    String motivoConsulta;
	String tratamento;
	String resultadosAtendimento;
    String vacinacaoVermufucacao;
	
    public TratamentoAnimal(Long id, String dataEntradaTratamento, String horarioTratamento,
			String nomeAnimalTratamento, String idadeAnimalTratamento, String especieAnimalTratamento, String sexo,
			int numeroAbrigo, String motivoInternacao, String situacao, String procedimento, String evolucaoQuadro,
			String resultados, String motivoConsulta, String tratamento, String resultadosAtendimento,
			String vacinacaoVermufucacao) {
		super();
		this.id = id;
		this.dataEntradaTratamento = dataEntradaTratamento;
		this.horarioTratamento = horarioTratamento;
		this.nomeAnimalTratamento = nomeAnimalTratamento;
		this.idadeAnimalTratamento = idadeAnimalTratamento;
		this.especieAnimalTratamento = especieAnimalTratamento;
		this.sexo = sexo;
		this.numeroAbrigo = numeroAbrigo;
		this.motivoInternacao = motivoInternacao;
		this.situacao = situacao;
		this.procedimento = procedimento;
		this.evolucaoQuadro = evolucaoQuadro;
		this.resultados = resultados;
		this.motivoConsulta = motivoConsulta;
		this.tratamento = tratamento;
		this.resultadosAtendimento = resultadosAtendimento;
		this.vacinacaoVermufucacao = vacinacaoVermufucacao;
	}
	public TratamentoAnimal(String dataEntradaTratamento, String horarioTratamento, String nomeAnimalTratamento,
			String idadeAnimalTratamento, String especieAnimalTratamento, String sexo, int numeroAbrigo,
			String motivoInternacao, String situacao, String procedimento, String evolucaoQuadro, String resultados,
			String motivoConsulta, String tratamento, String resultadosAtendimento, String vacinacaoVermufucacao) {
		super();
		this.dataEntradaTratamento = dataEntradaTratamento;
		this.horarioTratamento = horarioTratamento;
		this.nomeAnimalTratamento = nomeAnimalTratamento;
		this.idadeAnimalTratamento = idadeAnimalTratamento;
		this.especieAnimalTratamento = especieAnimalTratamento;
		this.sexo = sexo;
		this.numeroAbrigo = numeroAbrigo;
		this.motivoInternacao = motivoInternacao;
		this.situacao = situacao;
		this.procedimento = procedimento;
		this.evolucaoQuadro = evolucaoQuadro;
		this.resultados = resultados;
		this.motivoConsulta = motivoConsulta;
		this.tratamento = tratamento;
		this.resultadosAtendimento = resultadosAtendimento;
		this.vacinacaoVermufucacao = vacinacaoVermufucacao;
	}
    
    public TratamentoAnimal(){
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
	public String getIdadeAnimalTratamento() {
		return idadeAnimalTratamento;
	}
	public void setIdadeAnimalTratamento(String idadeAnimalTratamento) {
		this.idadeAnimalTratamento = idadeAnimalTratamento;
	}
	public String getEspecieAnimalTratamento() {
		return especieAnimalTratamento;
	}
	public void setEspecieAnimalTratamento(String especieAnimalTratamento) {
		this.especieAnimalTratamento = especieAnimalTratamento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getNumeroAbrigo() {
		return numeroAbrigo;
	}
	public void setNumeroAbrigo(int numeroAbrigo) {
		this.numeroAbrigo = numeroAbrigo;
	}
	public String getMotivoInternacao() {
		return motivoInternacao;
	}
	public void setMotivoInternacao(String motivoInternacao) {
		this.motivoInternacao = motivoInternacao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	public String getEvolucaoQuadro() {
		return evolucaoQuadro;
	}
	public void setEvolucaoQuadro(String evolucaoQuadro) {
		this.evolucaoQuadro = evolucaoQuadro;
	}
	public String getResultados() {
		return resultados;
	}
	public void setResultados(String resultados) {
		this.resultados = resultados;
	}
	public String getMotivoConsulta() {
		return motivoConsulta;
	}
	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}
	public String getTratamento() {
		return tratamento;
	}
	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}
	public String getResultadosAtendimento() {
		return resultadosAtendimento;
	}
	public void setResultadosAtendimento(String resultadosAtendimento) {
		this.resultadosAtendimento = resultadosAtendimento;
	}
	public String getVacinacaoVermufucacao() {
		return vacinacaoVermufucacao;
	}
	public void setVacinacaoVermufucacao(String vacinacaoVermufucacao) {
		this.vacinacaoVermufucacao = vacinacaoVermufucacao;
	}

    
    
    
}
