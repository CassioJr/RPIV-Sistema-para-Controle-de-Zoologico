package core.model;

public class Toten {
	private Long id;
	private String seguranca;
	private String emergencia;
	private String perdiAlguem;
	private String estouPerdido;

	public Toten() {

	}

	public String getEmergencia() {
		return emergencia;
	}

	public Long getId() {
		return id;
	}

	public String getEstouPerdido() {
		return estouPerdido;
	}

	public String getPerdiAlguem() {
		return perdiAlguem;
	}

	public String getSeguranca() {
		return seguranca;
	}

	public void setEmergencia(String emergencia) {
		this.emergencia = emergencia;
	}

	public void setEstouPerdido(String estouPerdido) {
		this.estouPerdido = estouPerdido;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPerdiAlguem(String perdiAlguem) {
		this.perdiAlguem = perdiAlguem;
	}

	public void setSeguranca(String seguranca) {
		this.seguranca = seguranca;
	}

}
