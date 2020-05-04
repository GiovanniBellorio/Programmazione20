package Esempio02;

/**
 * ESERCIZIO 1
 * LEZIONE 1 LABORATORIO
 * GIOVANNI BELLORIO
 */

/*
 * Scrivere un programma Java che stampa una cornice 10 x 10
 * 
 */

public class Main {
	
	// MAIN
	public static void main(String[] args) {
		System.out.println("INIZIO ESECUZIONE: ESERCIZIO 1\n");
		
		// DATI
		
		// INPUT
		
		// EXECUTE
		for(int i=0; i<10; i++)
			System.out.print("@ ");
		System.out.println();
		for(int j=0; j<8; j++){
			System.out.print("@");
			for(int i=0; i<8; i++)
				System.out.print("  ");
			System.out.println(" @");
		}
		for(int i=0; i<10; i++)
			System.out.print("@ ");
		System.out.println();
		System.out.println();
		
		// OUTPUT
		System.out.println("FINE ESECUZIONE");
	}
	// FINE MAIN
}