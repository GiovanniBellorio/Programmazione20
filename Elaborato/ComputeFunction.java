package Elaborato;

import java.util.Scanner;
import org.nfunk.jep.JEP;


/**
 * Classe di test che valuta una funzione f(x) e la sua derivata approssimata.
 * javac -classpath .:lib/jep-2.4.1.jar ComputeFunction.java
 * java -classpath .:lib/jep-2.4.1.jar ComputeFunction
 * 
 * @author Luca Marchetti
 * @version 1.0
 */
public class ComputeFunction {

    /**
     * Metodo main.
     *
     * @param args array degli argomenti all'avvio
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner tastiera;
        String f;
        double x,fx,fxp,fxm,tollZero;
        JEP parser;
        
        tastiera = new Scanner(System.in);
        parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.setImplicitMul(true);
        
        System.out.println("PROGRAMMA PER IL CALCOLO DEL VALORE DI f(x) ED f'(x)\n");

        System.out.print("Inserire la funzione: f(x) = ");
        f = tastiera.nextLine();

        System.out.print("Inserire il valore di x: ");
        x = tastiera.nextDouble();
        System.out.print("Inserire il valore della tolleranza per lo zero: ");
        tollZero = tastiera.nextDouble();

        // calcolo f(x)        
        parser.addVariable("x", x);
        parser.parseExpression(f);
        fx = parser.getValue();        
        if (parser.hasError()) {
            System.err.println("Errore durante il parsing della funzione per x = " + x);
            System.err.println(parser.getErrorInfo());
        } else
            System.out.println("f(" + x + ") = " + fx);
            
        // calcolo approssimato di f'(x) come [f(x+tollZero/2) - f(x-tollZero/2)] / tollZero
        parser.addVariable("x", x+tollZero/2);
        parser.parseExpression(f);
        fxp = parser.getValue();        
        if (parser.hasError()) {
            System.err.println("Errore durante il parsing della funzione per x = " + x);
            System.err.println(parser.getErrorInfo());
        } else {
            parser.addVariable("x", x-tollZero/2);
            parser.parseExpression(f);
            fxm = parser.getValue();        
            if (parser.hasError()) {
                System.err.println("Errore durante il parsing della funzione per x = " + x);
                System.err.println(parser.getErrorInfo());
            } else 
                System.out.println("f'(" + x + ") = " + (fxp-fxm)/tollZero);
        }
    }
    
}

