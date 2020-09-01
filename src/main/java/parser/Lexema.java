package main.java.parser;

public enum Lexema {
	NUMERO("numero"),
	MAS("+"),
	MENOS("-"),
	MULTIPLICACION("*"),
	DIVISION("/"),
	RAIZ("r"),
	ANS("a"),
	ELEVAR("^"),
	PARENTESIS_IZQ("("),
	PARENTESIS_DCHO(")"),
	FINAL("FIN");
	
	String token;
	
	private Lexema(String caracter) {
		this.token = caracter;
	}
	
	public String toString() {
		return token;
	}
}
