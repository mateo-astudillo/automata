package app;

import java.util.Scanner;
import java.util.ArrayList;
import automata.Automata;

public class App {
	public static void main(String[] args) {
		String entrada = "";
		Scanner scanner = new Scanner(System.in);
		Automata automata = new Automata();
		cargarAlfabeto(automata, scanner);
		cargarEstados(automata, scanner);
		cargarTransiciones(automata, scanner);
		while(true) {
			System.out.print("Cadena\n> ");
			entrada = scanner.nextLine();
			if (automata.pertenece(entrada)) {
				System.out.println("Sí pertenece al lenguaje");
			} else {
				System.out.println("No pertenece al lenguaje");
			}
			System.out.print("Terminar (si o no)\n> ");
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("si"))
				break;
		}
		scanner.close();
	}

	private static void cargarAlfabeto(Automata automata, Scanner scanner) {
		ArrayList<String> alfabeto = new ArrayList<String>();
		String entrada = "";

		System.out.println("Alfabeto\n(\"fin\" para terminar)");
		while (true) {
			System.out.print("Simbolo\n> ");
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("fin"))
				break;
			alfabeto.add(entrada);
		}
		automata.setAlfabeto(alfabeto.toArray(new String[alfabeto.size()]));
	}

	private static void cargarEstados(Automata automata, Scanner scanner) {
		String entrada = "";
		String estado_auxiliar = "";
		String estado_inicial = "";
		ArrayList<String> estados = new ArrayList<String>();
		ArrayList<String> estados_finales = new ArrayList<String>();

		System.out.print("Estado inicial\n> ");
		estado_inicial = scanner.nextLine();
		estados.add(estado_inicial);
		System.out.print("Es final? (si o no)\n> ");
		entrada = scanner.nextLine();
		if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("si"))
			estados_finales.add(estado_inicial);

		System.out.println("Estados\n(\"fin\" para terminar)");
		while (true) {
			System.out.print("Estado\n> ");
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("fin"))
				break;
			estados.add(entrada);

			System.out.print("Es final? (si o no)\n> ");
			estado_auxiliar = entrada;
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("si"))
				estados_finales.add(estado_auxiliar);
		}

		automata.setEstadoInicial(estado_inicial);
		automata.setEstados(estados.toArray(new String[estados.size()]));
		automata.setEstadosFinales(estados_finales.toArray(new String[estados_finales.size()]));
	}

	private static void cargarTransiciones(Automata automata, Scanner scanner) {
		String deEstado = "";
		String simbolo = "";
		String aEstado = "";
		
		System.out.println("Transiciones");
		while (true) {
			System.out.print("de estado\n> ");
			deEstado = scanner.nextLine();
			System.out.print("simbolo\n> ");
			simbolo = scanner.nextLine();
			System.out.print("a estado\n> ");
			aEstado = scanner.nextLine();
			if (!automata.existeEstado(deEstado) || !automata.existeEstado(aEstado))
				continue;
			if (!automata.existeSimbolo(simbolo))
				continue;
			automata.setTransicion(deEstado, simbolo, aEstado);
			System.out.print("Terminar (si o no)\n> ");
			if (scanner.nextLine().equalsIgnoreCase("si"))
				break;
		}
	}
}