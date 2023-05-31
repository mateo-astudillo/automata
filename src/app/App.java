package app;

import automata.Automata;

public class App {
	public static void main(String[] args) {

		Automata automata = new Automata();

		String[] alfabeto = {"0","1"};
		automata.setAlfabeto(alfabeto);

		automata.setEstado_inicial("A");

		String[] estados_finales = {"B","C"};
		automata.setEstados_finales(estados_finales);

		String[] estados = {"A","B","C","D","E"};
		automata.setEstados(estados);

		/*
		String[][] transiciones = {
			//A   B   C   D   E
			{"C","A","A","E","E"}, // 0
			{"C","E","B","C","E"}  // 1
		};
		automata.setTransiciones(transiciones);
		*/

		automata.setTransicion("A", "0", "B");
		automata.setTransicion("D", "1", "D");
		automata.setTransicion("C", "0", "B");
		automata.setTransicion("A", "1", "C");
		automata.setTransicion("B", "0", "B");
		automata.setTransicion("B", "1", "C");

		String cadenas[] = {"001", "1100", "1"};
		for (String cadena : cadenas) {
			System.out.println("Cadena: " + cadena);
			if (automata.pertenece(cadena)) {
				System.out.println("Si pertenece\n");
			} else {
				System.out.println("No pertenece\n");
			}
		}
	}
}