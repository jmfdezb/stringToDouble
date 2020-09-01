package main.java.parser;

public class Parser {
	Lexer lexer;
	static boolean invertir = false;
	
	public Parser(Lexer lexer) {
		this.lexer = lexer;
	}
	
	public Nodo parse() throws RuntimeException, NumberFormatException, ArithmeticException, Exception {
		Nodo raiz = expresion();
		if(lexer.token != Lexema.FINAL)
			throw new RuntimeException("Expresión inválida.");
		return raiz;
	}
	
	// <E> ::= <P> ( '+' <E> | '-' <E> | e )
	private Nodo expresion() {
		Nodo temp = product();
		Nodo nodo;
		switch(lexer.token){
		case MAS:
			lexer.leer(Lexema.MAS);
			nodo = new Nodo(Lexema.MAS);
			nodo.setNodoIzq(temp);
			nodo.setNodoDcho(expresion());
			return nodo;
		case MENOS:
			lexer.leer(Lexema.MENOS);
			nodo = new Nodo(Lexema.MENOS);
			nodo.setNodoIzq(temp);
			nodo.setNodoDcho(expresion());
			return nodo;
		default:
			return temp;
		}
	}

	// <P> ::= <F> ( '*' <P> | '/' <P> | '%' <P> | e )
	private Nodo product() {
		Nodo temp = factor();
		Nodo nodo;
		switch(lexer.token){
		case MULTIPLICACION:
			lexer.leer(Lexema.MULTIPLICACION);
			nodo = new Nodo(Lexema.MULTIPLICACION);
			nodo.setNodoIzq(temp);
			nodo.setNodoDcho(product());
			return nodo;
		case DIVISION:
			lexer.leer(Lexema.DIVISION);
			nodo = new Nodo(Lexema.DIVISION);
			nodo.setNodoIzq(temp);
			nodo.setNodoDcho(product());
			return nodo;
		default:
			return temp;
		}
	}
	// <F> ::= <N> ( '^' <F> | e )
	private Nodo factor() {
		Nodo nodo;
		Nodo temp = number();
		switch(lexer.token){
		case ELEVAR:
			lexer.leer(Lexema.ELEVAR);
			if (invertir) {
				invertir = false;
				nodo = new Nodo(Lexema.MULTIPLICACION);
				Nodo aux2 = new Nodo(Lexema.NUMERO);
				nodo.setNodoDcho(aux2);
				aux2.setValor(-1);
				Nodo aux3 = new Nodo(Lexema.ELEVAR);
				aux3.setNodoIzq(temp);
				aux3.setNodoDcho(factor());
				nodo.setNodoIzq(aux3);
			} else {
				nodo = new Nodo(Lexema.ELEVAR);
				nodo.setNodoIzq(temp);
				nodo.setNodoDcho(factor());
			}
			return nodo;
		default:
			return temp;
		}
	}

	// <N> ::= n | '(' <E> ')' | '-' <N>
	private Nodo number() {
		Nodo nodo;
		switch(lexer.token){
		case PARENTESIS_IZQ:
			lexer.leer(Lexema.PARENTESIS_IZQ);
			if (invertir) { 
				invertir = false;
				nodo = new Nodo(Lexema.MULTIPLICACION);
				nodo.setNodoIzq(expresion());
				Nodo aux2 = new Nodo(Lexema.NUMERO);
				aux2.setValor(-1);
				nodo.setNodoDcho(aux2);
			}else{
				nodo = expresion();				
			}
			lexer.leer(Lexema.PARENTESIS_DCHO);
			return nodo;
		case MENOS:
			lexer.leer(Lexema.MENOS);
			invertir = true;
			return expresion();
		case NUMERO:
			lexer.leer(Lexema.NUMERO);
			nodo = new Nodo(Lexema.NUMERO);
			if (invertir && lexer.token != Lexema.ELEVAR) { lexer.valor_token = lexer.valor_token * -1; invertir = false; }
			nodo.setValor(lexer.valor_token);
			return nodo;
		case ANS:
			lexer.leer(Lexema.ANS);
			nodo = new Nodo(Lexema.ANS);
			if (invertir) { lexer.valor_token = lexer.valor_token * -1; invertir = false; }
			nodo.setValor(lexer.valor_token);
			return nodo;
		case RAIZ:
			lexer.leer(Lexema.RAIZ);
			nodo = new Nodo(Lexema.RAIZ);
			nodo.setNodoDcho(factor());
			return nodo;
		default:
			throw new RuntimeException("Caracter inesperado '"+lexer.token+"'");
		}
	}
}
