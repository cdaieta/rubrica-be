package com.example.demo;

public class RubricaRequestDTO {

	private int desde;
	private int hasta;
	private String empresa;
	private String libro;
	private String RUC;
	private String tipoRubrica;

	public int getDesde() {
		return desde;
	}

	public void setDesde(int desde) {
		this.desde = desde;
	}

	public int getHasta() {
		return hasta;
	}

	public void setHasta(int hasta) {
		this.hasta = hasta;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getRUC() {
		return RUC;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
	}

	public String getTipoRubrica() {
		return tipoRubrica;
	}

	public void setTipoRubrica(String tipoRubrica) {
		this.tipoRubrica = tipoRubrica;
	}

	@Override
	public String toString() {
		return "RubricaRequestDTO [desde=" + desde + ", hasta=" + hasta + ", empresa=" + empresa + ", libro=" + libro
				+ ", RUC=" + RUC + ", tipoRubrica=" + tipoRubrica + "]";
	}

	public RubricaRequestDTO(int desde, int hasta, String empresa, String libro, String rUC, String tipoRubrica) {
		super();
		this.desde = desde;
		this.hasta = hasta;
		this.empresa = empresa;
		this.libro = libro;
		RUC = rUC;
		this.tipoRubrica = tipoRubrica;
	}
}
