package model;
public class Animal{

Long id;
String nomeAnimal;
String nomeAlimento;
String tipoTransferencia;
String localizacaoAbrigo;
String instituicaoOrigem;
String instituicaoDestino;
String nomeEspecie;
String habitatEspecie;
String estadoSaude;
String nomeDoenca;
int idadeAnimal;
int sexoAnimal;
int numeroAbrigo;
String datatransfenciaInstituicao;
String dataMorte;
float medidaQuantidade_Alimento;
float tamanhoAbrigo;
float quantidadeDiaria_Alimento;

public Animal(Long id, String nomeAnimal, String nomeAlimento, String tipoTransferencia, String localizacaoAbrigo,
		String instituicaoOrigem, String instituicaoDestino, String nomeEspecie, String habitatEspecie,
		String estadoSaude, String nomeDoenca, int idadeAnimal, int sexoAnimal, int numeroAbrigo,
		String datatransfenciaInstituicao, String dataMorte, float medidaQuantidade_Alimento, float tamanhoAbrigo,
		float quantidadeDiaria_Alimento) {
	super();
	this.id = id;
	this.nomeAnimal = nomeAnimal;
	this.nomeAlimento = nomeAlimento;
	this.tipoTransferencia = tipoTransferencia;
	this.localizacaoAbrigo = localizacaoAbrigo;
	this.instituicaoOrigem = instituicaoOrigem;
	this.instituicaoDestino = instituicaoDestino;
	this.nomeEspecie = nomeEspecie;
	this.habitatEspecie = habitatEspecie;
	this.estadoSaude = estadoSaude;
	this.nomeDoenca = nomeDoenca;
	this.idadeAnimal = idadeAnimal;
	this.sexoAnimal = sexoAnimal;
	this.numeroAbrigo = numeroAbrigo;
	this.datatransfenciaInstituicao = datatransfenciaInstituicao;
	this.dataMorte = dataMorte;
	this.medidaQuantidade_Alimento = medidaQuantidade_Alimento;
	this.tamanhoAbrigo = tamanhoAbrigo;
	this.quantidadeDiaria_Alimento = quantidadeDiaria_Alimento;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNomeAnimal() {
	return nomeAnimal;
}
public void setNomeAnimal(String nomeAnimal) {
	this.nomeAnimal = nomeAnimal;
}
public String getNomeAlimento() {
	return nomeAlimento;
}
public void setNomeAlimento(String nomeAlimento) {
	this.nomeAlimento = nomeAlimento;
}
public String getTipoTransferencia() {
	return tipoTransferencia;
}
public void setTipoTransferencia(String tipoTransferencia) {
	this.tipoTransferencia = tipoTransferencia;
}
public String getLocalizacaoAbrigo() {
	return localizacaoAbrigo;
}
public void setLocalizacaoAbrigo(String localizacaoAbrigo) {
	this.localizacaoAbrigo = localizacaoAbrigo;
}
public String getInstituicaoOrigem() {
	return instituicaoOrigem;
}
public void setInstituicaoOrigem(String instituicaoOrigem) {
	this.instituicaoOrigem = instituicaoOrigem;
}
public String getInstituicaoDestino() {
	return instituicaoDestino;
}
public void setInstituicaoDestino(String instituicaoDestino) {
	this.instituicaoDestino = instituicaoDestino;
}
public String getNomeEspecie() {
	return nomeEspecie;
}
public void setNomeEspecie(String nomeEspecie) {
	this.nomeEspecie = nomeEspecie;
}
public String getHabitatEspecie() {
	return habitatEspecie;
}
public void setHabitatEspecie(String habitatEspecie) {
	this.habitatEspecie = habitatEspecie;
}
public String getEstadoSaude() {
	return estadoSaude;
}
public void setEstadoSaude(String estadoSaude) {
	this.estadoSaude = estadoSaude;
}
public String getNomeDoenca() {
	return nomeDoenca;
}
public void setNomeDoenca(String nomeDoenca) {
	this.nomeDoenca = nomeDoenca;
}
public int getIdadeAnimal() {
	return idadeAnimal;
}
public void setIdadeAnimal(int idadeAnimal) {
	this.idadeAnimal = idadeAnimal;
}
public int getSexoAnimal() {
	return sexoAnimal;
}
public void setSexoAnimal(int sexoAnimal) {
	this.sexoAnimal = sexoAnimal;
}
public int getNumeroAbrigo() {
	return numeroAbrigo;
}
public void setNumeroAbrigo(int numeroAbrigo) {
	this.numeroAbrigo = numeroAbrigo;
}
public String getDatatransfenciaInstituicao() {
	return datatransfenciaInstituicao;
}
public void setDatatransfenciaInstituicao(String datatransfenciaInstituicao) {
	this.datatransfenciaInstituicao = datatransfenciaInstituicao;
}
public String getDataMorte() {
	return dataMorte;
}
public void setDataMorte(String dataMorte) {
	this.dataMorte = dataMorte;
}
public float getMedidaQuantidade_Alimento() {
	return medidaQuantidade_Alimento;
}
public void setMedidaQuantidade_Alimento(float medidaQuantidade_Alimento) {
	this.medidaQuantidade_Alimento = medidaQuantidade_Alimento;
}
public float getTamanhoAbrigo() {
	return tamanhoAbrigo;
}
public void setTamanhoAbrigo(float tamanhoAbrigo) {
	this.tamanhoAbrigo = tamanhoAbrigo;
}
public float getQuantidadeDiaria_Alimento() {
	return quantidadeDiaria_Alimento;
}
public void setQuantidadeDiaria_Alimento(float quantidadeDiaria_Alimento) {
	this.quantidadeDiaria_Alimento = quantidadeDiaria_Alimento;
}



}