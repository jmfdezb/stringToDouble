package main.java.parser;

public class Evaluador {
	public static double leer(Nodo nodo) {
		if (nodo == null) return 0;
		switch(nodo.getLexema()) {
		case NUMERO:
		case ANS:
			return nodo.getValor();
		case MAS:
			return leer(nodo.getNodoIzq()) + leer(nodo.getNodoDcho());
		case MENOS:
			return leer(nodo.getNodoIzq()) - leer(nodo.getNodoDcho());
		case MULTIPLICACION:
			return leer(nodo.getNodoIzq()) * leer(nodo.getNodoDcho());
		case DIVISION:
			return leer(nodo.getNodoIzq()) / leer(nodo.getNodoDcho());
		case RAIZ:
			return Math.sqrt(leer(nodo.getNodoDcho()));
		case ELEVAR:
			return Math.pow(leer(nodo.getNodoIzq()), leer(nodo.getNodoDcho()));
		default:
			return 0;
		}
	}
	
	/**
	 * Formata la cadena de texto introducida para que pueda ser evaluada por el parser.
	 * @param expresion Cadena a evaluar
	 * @param anterior Resultado anterior que será substituido en los "Ans" de la cadena dada.
	 * @return String cadena con formato adecuado para el parser
	 */
	private static String formatearExpresion(String expresion, double anterior) {
		if (expresion.charAt(0) == ' ') { return "0";}
		int contarParentesis = 0;
		expresion = expresion.replaceAll("Ans", String.format("%f", anterior)); //Sustituye Ans por el valor calculado anteriormente
		for(int i = 0; i < expresion.length(); i++) {
			switch (expresion.charAt(i)) {
			case '(':
			case 'r':
				//Número precedido por '(' o raiz, intercala un '*'
				if ( i > 0 && (Character.isDigit(expresion.charAt(i-1))) ) { expresion = expresion.substring(0, i)+"*"+expresion.substring(i); }  
				// '(' o raiz, seguida de un '+¡, elimina este último.
				if ( i < expresion.length()+1 && (expresion.charAt(i+1) == '+') ) { expresion = expresion.substring(0, i+1)+expresion.substring(i+2);  } 
				break;
			case ')':
				if (i < expresion.length()-1 ) {
					// Entre ')' y '^' se intercala un ')' siempre que contarParentesis sea superior a 0.
					if ( contarParentesis > 0 && expresion.charAt(i+1) != '^') { expresion = expresion.substring(0, i)+")"+expresion.substring(i); contarParentesis--; i++;}
					// Entre ')' y '-' se intercala un '+'
					if ( expresion.charAt(i+1) == '-') { expresion = expresion.substring(0, i+1)+"+"+expresion.substring(i+1); i++; }
					// Entre ')' y número, raiz o '(' se intercala '*'
					if ( (Character.isDigit(expresion.charAt(i+1)) || expresion.charAt(i+1) == 'r' || expresion.charAt(i+1) == '(')) { 
						expresion = expresion.substring(0, i+1)+"*"+expresion.substring(i+1); 
					}
				} 
				break;
			case '-':
				// Entre '(' y '-' se intercala '(' y se añade uno a contarParentesis
				if ( expresion.charAt(i+1) == '(') { expresion = expresion.substring(0, i+1)+"("+expresion.substring(i+1); contarParentesis++; } 
				// Entre '-' y número se intercala '+' siempre que después de '-' haya un número.
				if ( i > 0 && Character.isDigit(expresion.charAt(i-1)) && Character.isDigit(expresion.charAt(i+1))) { expresion = expresion.substring(0, i)+"+"+expresion.substring(i); i++; }
				// Entre '-' y raiz, se intercala '+-1*'
				if ( expresion.charAt(i+1) == 'r' ) { expresion = expresion.substring(0, i)+"+-1*"+expresion.substring(i+1); } 
				// Entre '-' y '(' se intercala '+'
				if ( i > 0 && expresion.charAt(i+1) == '(' ) { expresion = expresion.substring(0, i)+"+"+expresion.substring(i); i++; } 
				break;
			case '^':
				// En caso de que se hayan introducido '(' y falten ')'
				if (contarParentesis > 0) { 
					int posicion = i+1;
					// Mientras estemos recorriendo un número avanzamos posicion
					while (posicion < expresion.length() && (expresion.charAt(posicion) == '.' || expresion.charAt(posicion) == '-' || Character.isDigit(expresion.charAt(posicion))) ) { posicion++; }
					// Si nos encontramos una expresión entre paréntesis, seguimos avanzando posicion
					if (expresion.charAt(posicion) == '(') {
						while (posicion < expresion.length() && expresion.charAt(posicion) != ')') { posicion++; }
					}
					// Insertamos ')' al final y restamos uno al contarParentesis
					expresion = expresion.substring(0, posicion)+")"+expresion.substring(posicion);
					contarParentesis--;
				}
				break;
			}
			// Si contarParentesis > 0 y hemos llegado a la última posición de la cadena, insertamos los ')' restantes hasta dejar contarParentesis a 0
			if (i == expresion.length()-1 && contarParentesis > 0) { for (int j = contarParentesis; j > 0; j--) { expresion +=")"; contarParentesis--; } }
		}
		// Remplazamos "--" por '+'
		expresion = expresion.replaceAll("--","\\+");
		// Remplazamos "++" por '+'
		expresion = expresion.replaceAll("\\+\\+","\\+");
		if (expresion.charAt(0) == '+') { expresion = expresion.substring(1); }
		return expresion;
	}
	
	
	/**
	 * Evalua la cadena de texto dada y calcula su resultado
	 * @param cadena String con la expresión matemática a evaluar.
	 * @return Double resultado de calcular la expresión matemática obtenida.
	 * @throws Exception Lanzada cuando la expresión no se puede evaluar.
	 */
	public static double stringToDoble(String cadena) throws Exception {
		return stringToDoble(cadena,0.0);
	}
	
	/**
	 * Evalua la cadena de texto dada y calcula su resultado
	 * @param cadena String con la expresión matemática a evaluar.
	 * @param anterior Valor de un resultado obtenido en una ejecución anterior (Tecla Ans de la calculadora)
	 * @return Double resultado de calcular la expresión matemática obtenida.
	 * @throws Exception Lanzada cuando la expresión no se puede evaluar.
	 */
	public static double stringToDoble(String cadena, double anterior) throws Exception {
		Lexer lexer = new Lexer(formatearExpresion(cadena, anterior), anterior);
		Parser parser = new Parser(lexer);
		Nodo raiz = parser.parse();
		try {
			return Evaluador.leer(raiz);
		} catch ( Exception e) {
			throw new Exception();
		}
	}
}
