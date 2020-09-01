Parser matemático
===========================

Esta librería calcula el resultado de una expresión matemática almacenada en una cadena de texto.

Uso
-------------

Se incluye un ejemplo básico en la clase main y unos sets de prueba en JUnit. Hay que instanciar un objeto "Evaluador" y utilizar el método "stringToDouble(String).
También es posible pasarle un segundo parámetro con un valor calculado previamente imitando la función "ANS" de cualquier calculadora.

A la hora de confeccionar la expresión matemática, hay que tener las consideraciones siguientes:

 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a = ANS  
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;r = Raiz  
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;^ = Elevado a  

En JUnit se prueban las expresiones siguientes:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+5"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"-5"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"++5"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"--5"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"5-6"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"(1+2)*(3/4)^(5+6)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"34,5*(23+1,5)/2"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"5+((1+2)*4)-3"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"3/2+4*(12+3)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"((2*(6-1))/2)*4"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"11^-7"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"-8+5"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"1-(-2^2)-1"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"(9-18+5)-3(5-r16+32/(4)2)+12"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"(9-18+5)-3(5-r16+32/(4)^2)+12"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"3-(2-3-(2+8)-2+13)-4"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"2-(7-5+8)-(3-(2+7-1)+3)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"7-2*(2-3-3*(2+8)-2+13)-4"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"20/(15-5)-(3-(2+7-1)+3)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"3^2-r(5-3)^4*(-2^2)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"3r5^2-3^2+4^3/(1^23+3^0)^4"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"5-2*7-(-1^4)*(3^2-(-2)^2*(-5)/(-2^2))"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"-(-2)^(3+1)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"1-(-5)+2"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"5r2-(4r2+7r2)"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"-5r12-2r2*3r6"  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"r48-(5r3-8r3)^2+8r6/-2r2"  
