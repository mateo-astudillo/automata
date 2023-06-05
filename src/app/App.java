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
		String entrada = "";
		String[] alfabeto;

		System.out.print("Inserte los símbolos separados por un espacio\n> ");
		entrada = scanner.nextLine();
		alfabeto = entrada.split(" ");

		automata.setAlfabeto(alfabeto);
	}

	private static void cargarEstados(Automata automata, Scanner scanner) {
		String entrada = "";
		String[] estados;
		ArrayList<String> estados_finales = new ArrayList<String>();

		System.out.print("Inserte los estados separados por un espacio\n"
			+ "El primero será considerado inicial\n> ");
		entrada = scanner.nextLine();
		estados = entrada.split(" ");
		for (String e : estados) {
			System.out.print(e + " es final? (si o no)\n> ");
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("si"))
				estados_finales.add(e);
		}
		automata.setEstadoInicial(estados[0]);
		automata.setEstados(estados);
		automata.setEstadosFinales(estados_finales.toArray(new String[estados_finales.size()]));

	}

	private static void cargarTransiciones(Automata automata, Scanner scanner) {
		String entrada = "";
		String deEstado = "";
		String simbolo = "";
		String aEstado = "";
		
		System.out.println("Transiciones\n(de estado) (simbolo) (a estado)");
		while (true) {
			System.out.print("Transición\n> ");
			entrada = scanner.nextLine();
			try {
				deEstado = entrada.split(" ")[0];
				simbolo = entrada.split(" ")[1];
				aEstado = entrada.split(" ")[2];
				System.out.println(deEstado + " " + simbolo + " " + aEstado);
			} catch (Exception e) {
				System.out.println("Error al agregar transición\nEj: A 1 B");
				continue;
			}
			if (!automata.setTransicion(deEstado, simbolo, aEstado))
				System.out.print("No se agregó la transición");

			System.out.print("Terminar? (si o no)\n> ");
			entrada = scanner.nextLine();
			if (entrada.equalsIgnoreCase("s") || entrada.equalsIgnoreCase("si"))
				break;
		}
	}
}