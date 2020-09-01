package main.java.parser;


public class Nodo {
	private Lexema lexema;
	private Double valor;
	private Nodo izq;
	private Nodo dcho;
	
	public Nodo(Lexema lexema) {
		this.lexema = lexema;
		izq = null;
		dcho = null;
	}
	
	public String toString() {
		if ( lexema == Lexema.ANS || lexema == Lexema.NUMERO ) {
			return "[ '"+lexema+"', "+valor+" ]";
		} else {
			return "[ '"+lexema+"' ]";
		}
	}
	
	public Double getValor() {
		return valor;
	}
	
	public Nodo getNodoIzq() {
		return izq;
	}
	
	public Nodo getNodoDcho() {
		return dcho;
	}
	
	public Lexema getLexema() {
		return lexema;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void setNodoIzq(Nodo nuevoNodo) {
		izq = nuevoNodo;
	}
	
	public void setNodoDcho(Nodo nuevoNodo) {
		dcho = nuevoNodo;
	}
} 

