package main.java.parser;

import java.time.format.DecimalStyle;

public class Lexer {
	String cadena;
	int pos = 0;
	Lexema token;
	double valor_token, anterior;
	
	public Lexer(String cadena, double anterior) {
		this.cadena = cadena;
		this.anterior = anterior;
		siguiente();
	}
	
	public void leer(Lexema token) {
		if (this.token == token) { siguiente(); }
		else { throw new RuntimeException("Encontramos: "+this.token+", pero se esperaba: "+token); }
	}
	
	private void siguiente() {
		while( pos < cadena.length() && cadena.charAt(pos) == ' ') { pos++; }
		if (pos == cadena.length()) { token = Lexema.FINAL; return; }
		switch (cadena.charAt(pos)) {
		case '+':
			pos++;
			token = Lexema.MAS;
			break;
		case '-':
			pos++;
			token = Lexema.MENOS;
			break;
		case '*':
			pos++;
			token = Lexema.MULTIPLICACION;
			break;
		case '/':
			pos++;
			token = Lexema.DIVISION;
			break;
		case '^':
			pos++;
			token = Lexema.ELEVAR;
			break;
		case 'r':
			pos++;
			token = Lexema.RAIZ;
			break;
		case 'a':
			pos++;
			token = Lexema.ANS;
			valor_token = anterior;
			break;
		case '(':
			pos++;
			token = Lexema.PARENTESIS_IZQ;
			break;
		case ')':
			pos++;
			token = Lexema.PARENTESIS_DCHO;
			break;
		default:
			if (!Character.isDigit(cadena.charAt(pos))) { throw new RuntimeException("Caracter no contemplado: '"+cadena.charAt(pos)+"'"); }
			String aux = "";
			
			while (pos < cadena.length() && ( Character.isDigit(cadena.charAt(pos)) || cadena.charAt(pos) == DecimalStyle.ofDefaultLocale().getDecimalSeparator())) { aux += cadena.charAt(pos++); }
			
			token = Lexema.NUMERO;
			if (DecimalStyle.ofDefaultLocale().getDecimalSeparator() == ',') { aux = aux.replaceAll("\\,","\\."); }
			valor_token = Double.parseDouble(aux);
			break;
		}
	}
}
