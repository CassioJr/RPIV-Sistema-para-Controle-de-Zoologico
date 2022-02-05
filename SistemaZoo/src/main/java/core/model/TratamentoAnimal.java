package core.model;

public class TratamentoAnimal extends Animal {

	private Long ident;
	private String dataEntradaTratamento;
	private String horarioTratamento;
	private String motivoInternacao;
	private String situacao;
	private String procedimento;
	private String evolucaoQuadro;
	private String resultados;
	private String tratamento;
	private String resultadosAtendimento;
	private String vacinacaoVermufucacao;

	public TratamentoAnimal(String nomeAnimal, String nomeAlimento, String tipoTransferencia, String localizacaoAbrigo,
			String instituicaoOrigem, String instituicaoDestino, String nomeEspecie, String habitatEspecie,
			String estadoSaude, String nomeDoenca, int idadeAnimal, String sexoAnimal, int numeroAbrigo,
			String datatransfenciaInstituicao, String medidaQuantidade_Alimento, float tamanhoAbrigo,
			float quantidadeDiaria_Alimento, boolean consultando, Long id, Long ident, String dataEntradaTratamento,
			String horarioTratamento, String motivoInternacao, String situacao, String procedimento,
			String evolucaoQuadro, String resultados, String tratamento, String resultadosAtendimento,
			String vacinacaoVermufucacao) {
		super(nomeAnimal, nomeAlimento, tipoTransferencia, localizacaoAbrigo, instituicaoOrigem, instituicaoDestino,
				nomeEspecie, habitatEspecie, estadoSaude, nomeDoenca, idadeAnimal, sexoAnimal, numeroAbrigo,
				datatransfenciaInstituicao, medidaQuantidade_Alimento, tamanhoAbrigo, quantidadeDiaria_Alimento,
				consultando);
		this.ident = ident;
		this.horarioTratamento = horarioTratamento;
		this.motivoInternacao = motivoInternacao;
		this.situacao = situacao;
		this.procedimento = procedimento;
		this.evolucaoQuadro = evolucaoQuadro;
		this.resultados = resultados;
		this.tratamento = tratamento;
		this.resultadosAtendimento = resultadosAtendimento;
		this.vacinacaoVermufucacao = vacinacaoVermufucacao;
	}

	public TratamentoAnimal(String nomeAnimal, String nomeAlimento, String tipoTransferencia, String localizacaoAbrigo,
			String instituicaoOrigem, String instituicaoDestino, String nomeEspecie, String habitatEspecie,
			String estadoSaude, String nomeDoenca, int idadeAnimal, String sexoAnimal, int numeroAbrigo,
			String datatransfenciaInstituicao, String medidaQuantidade_Alimento, float tamanhoAbrigo,
			float quantidadeDiaria_Alimento, boolean consultando, Long id, String dataEntradaTratamento,
			String horarioTratamento, String motivoInternacao, String situacao, String procedimento,
			String evolucaoQuadro, String resultados, String tratamento, String resultadosAtendimento,
			String vacinacaoVermufucacao) {
		super(nomeAnimal, nomeAlimento, tipoTransferencia, localizacaoAbrigo, instituicaoOrigem, instituicaoDestino,
				nomeEspecie, habitatEspecie, estadoSaude, nomeDoenca, idadeAnimal, sexoAnimal, numeroAbrigo,
				datatransfenciaInstituicao, medidaQuantidade_Alimento, tamanhoAbrigo, quantidadeDiaria_Alimento,
				consultando);
		this.dataEntradaTratamento = dataEntradaTratamento;
		this.horarioTratamento = horarioTratamento;
		this.motivoInternacao = motivoInternacao;
		this.situacao = situacao;
		this.procedimento = procedimento;
		this.evolucaoQuadro = evolucaoQuadro;
		this.resultados = resultados;
		this.tratamento = tratamento;
		this.resultadosAtendimento = resultadosAtendimento;
		this.vacinacaoVermufucacao = vacinacaoVermufucacao;
	}

	public TratamentoAnimal() {

	}

	public Long getIdent() {
		return ident;
	}

	public void setIdent(Long ident) {
		this.ident = ident;
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