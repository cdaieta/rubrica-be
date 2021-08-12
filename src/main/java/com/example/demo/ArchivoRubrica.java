package com.example.demo;

public class ArchivoRubrica {

	private String nombreArchivo;
	private byte[] contenido;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public byte[] getContenido() {
		return contenido;
	}
	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}
	@Override
	public String toString() {
		return "ArchivoRubrica [nombreArchivo=" + nombreArchivo + "]";
	}
	
}
