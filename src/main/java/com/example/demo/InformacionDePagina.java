package com.example.demo;

public class InformacionDePagina {
	private String numeroDePagina;

	public String getNumeroDePagina() {
		return numeroDePagina;
	}

	public void setNumeroDePagina(String numeroDePagina) {
		this.numeroDePagina = numeroDePagina;
	}

	public InformacionDePagina(String numeroDePagina) {
		super();
		this.numeroDePagina = numeroDePagina;
	}

	public InformacionDePagina() {
		super();
	}

	@Override
	public String toString() {
		return "InformacionDePagina [numeroDePagina=" + numeroDePagina + "]";
	}
}
