package main.java.junit;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.java.parser.Evaluador;

@RunWith(value = Parameterized.class)
public class TestParser {

	@Parameters(name="{0}")
	public static Iterable<Object[]> obtenerDatos() {
		return Arrays.asList(new Object[][] {
			{ "+5", 5.0 },
			{ "-5", -5.0 },
			{ "++5", 5.0 },
			{ "--5", 5.0 },
			{ "5-6", -1.0 },
			{ "(1+2)*(3/4)^(5+6)", 0.126705 },
			{ "34,5*(23+1,5)/2", 422.625000 },
			{ "5+((1+2)*4)-3", 14.0 },
			{ "3/2+4*(12+3)", 61.5 },
			{ "((2*(6-1))/2)*4", 20.0 },
			{ "11^-7", 5.1315811823070673E-8 },
			{ "-8+5", -3.0 },
			{ "1-(-2^2)-1", 4.0 },
			{ "(9-18+5)-3(5-r16+32/(4)2)+12", -7.0 },
			{ "(9-18+5)-3(5-r16+32/(4)^2)+12", -1.0 },
			{ "3-(2-3-(2+8)-2+13)-4", -1.0 },
			{ "2-(7-5+8)-(3-(2+7-1)+3)", -6.0 },
			{ "7-2*(2-3-3*(2+8)-2+13)-4", 43.0 },
			{ "20/(15-5)-(3-(2+7-1)+3)", 4.0 },
			{ "3^2-r(5-3)^4*(-2^2)", 25.0 },
			{ "3r5^2-3^2+4^3/(1^23+3^0)^4", 10.0 },
			{ "5-2*7-(-1^4)*(3^2-(-2)^2*(-5)/(-2^2))", -5.0 },
			{ "-(-2)^(3+1)", -16.0 },
			{ "1-(-5)+2", 8.0 },
			{ "5r2-(4r2+7r2)", -8.485281374238571 },
			{ "-5r12-2r2*3r6", -38.1051177665153 },
			{ "r48-(5r3-8r3)^2+8r6/-2r2", -27.0 }
		});
	}
	
	private String cadena;
	private double esperado;
	
	public TestParser(String cadena, double esperado) {
		this.cadena = cadena;
		this.esperado = esperado;
	}
	
	@Test
	public void testResultado() throws Exception {
		assertEquals(esperado,Evaluador.stringToDoble(cadena), 0.001 );
	}
}
