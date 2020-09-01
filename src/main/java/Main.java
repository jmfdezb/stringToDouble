package main.java;

import main.java.parser.Evaluador;

public class Main {

	public static void main(String[] args) {
		try { System.out.println(Evaluador.stringToDoble("7-2r2*(2-3-3*(2+8)-2+13)-4"));
		} catch (Exception e) { e.printStackTrace(); }
	}

}
