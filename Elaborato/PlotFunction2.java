package Elaborato;

import java.util.Scanner;
import org.nfunk.jep.JEP;
import org.jfree.chart.*;
import org.jfree.data.xy.*;
import org.jfree.chart.plot.*;


/**
 * Classe di test che crea un grafico di una funzione f(x).
 * javac -classpath .:lib/jep-2.4.1.jar:lib/jfreechart-1.0.19.jar:lib/jcommon-1.0.23.jar PlotFunction2.java 
 * java -classpath .:lib/jep-2.4.1.jar:lib/jfreechart-1.0.19.jar:lib/jcommon-1.0.23.jar PlotFunction2
 *
 * 
 * @author Luca Marchetti
 * @version 1.0
 */
public class PlotFunction2 {

	/**
	 * Metodo main.
	 *
	 * @param args array degli argomenti all'avvio
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner tastiera;
		double a,b,x,fx,tollZero,tollInf;
        String f;
        JEP parser;
        XYSeries series;
        XYSeriesCollection dataset;
        JFreeChart XYLineChart;
        
        String titoloGrafico = "Grafico di f(x)";
        String etichettaAsseX = "x";
        String etichettaAsseY = "f(x)";
        
        tastiera = new Scanner(System.in);
        parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.setImplicitMul(true);
        
        System.out.println("PROGRAMMA PER IL PLOT DI UNA FUNZIONE f(x) NELL'INTERVALLO [a,b]\n");

        System.out.print("Inserire la funzione: f(x) = ");
        f = tastiera.nextLine();

        System.out.print("Inserire il valore di a: ");
        a = tastiera.nextDouble();
        System.out.print("Inserire il valore di b: ");
        b = tastiera.nextDouble();
        System.out.print("Inserire il valore della tolleranza per lo zero: ");
        tollZero = tastiera.nextDouble();
        System.out.print("Inserire il valore della tolleranza per l'infinito: ");
        tollInf = tastiera.nextDouble();

        // codice necessario a creare il plot con JFreeChart
		x = a;
        series = new XYSeries("f(x)");
		while (x <= b){
			parser.addVariable("x", x);
			parser.parseExpression(f);
			fx = parser.getValue();        
			if (parser.hasError()) {
				System.err.println("Errore durante il parsing della funzione per x = " + x);
				System.err.println(parser.getErrorInfo());
				break;
			}

			if (fx > tollInf)
			    fx = tollInf; // per evitare grafici con esplosioni di valori...
			if (fx < -tollInf)
			    fx = -tollInf; // per evitare grafici con esplosioni di valori...

            series.add(x, fx);
			x += tollZero;
		}
        dataset = new XYSeriesCollection(series);
        XYLineChart = ChartFactory.createXYLineChart(titoloGrafico, etichettaAsseX, etichettaAsseY, dataset, PlotOrientation.VERTICAL, true, true, false);

        // codice necessario per creare la finestra con il plot
        final ChartFrame frame = new ChartFrame(titoloGrafico, XYLineChart);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.pack();
                frame.setVisible(true);
            }
        });

        // esempio di codice che viene eseguito dopo che la finestra di plot e' stata creata
        // questo codice andra' in parallelo alla finestra, il programma poi termina se 
        // il codice che segue finisce e la finestra del plot viene chiusa dall'utente
        System.out.print("Inserire un double di prova: ");
        double value = tastiera.nextDouble();
        System.out.println("Valore inserito " + value);
	}
}

