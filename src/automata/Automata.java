package automata;

public class Automata {
	private String[] alfabeto;
	private String estado_inicial;
	private String[] estados_finales;
	private String[] estados;
	private String[][] transiciones;

	public Automata (){
		
	}

	public void setAlfabeto(String[] alfabeto) {
  	this.alfabeto = alfabeto;
  }

	public void setEstado_inicial(String estado_inicial) {
  	this.estado_inicial = estado_inicial;
  }

	public void setEstados_finales(String[] estados_finales) {
  	this.estados_finales = estados_finales;
  }

	public void setEstados(String[] estados) {
  	this.estados = estados;
		this.transiciones = new String[estados.length][alfabeto.length];
		for (int i = 0; i < estados.length; i++) {
			for (int j = 0; j < alfabeto.length; j++) {
				this.transiciones[i][j] = "Error";
			}
		}
  }

	public void setTransicion(String deEstado, String simbolo, String aEstado) {
		this.transiciones[obtenerIndiceEstado(deEstado)][obtenerIndiceSimbolo(simbolo)] = aEstado;
	}

	public void setTransiciones(String[][] transiciones) { // Sacar
  	this.transiciones = transiciones;
  }

	public boolean pertenece(String cadena) {
		// if (this.validarAutomata())
		// 	return false;

		String s = "";

		String estado_actual = this.estado_inicial;
		for (char simbolo : cadena.toCharArray()) {
			s = "" + simbolo;
			System.out.print(estado_actual + ", " + simbolo + " -> ");
			estado_actual = transicion(estado_actual, s);
			// if (estado_actual == null)
			// 	return false;
			if (estado_actual.equals("Error"))
				return false;
		}
		if (esFinal(estado_actual))
			return true;
		return false;
	}

	private boolean esFinal(String estado) {
		for (String e : this.estados_finales) {
			if (estado.equals(e))
				return true;
		}
		return false;
	}

	private String transicion(String estado, String simbolo) {
		int indice_estado = obtenerIndiceEstado(estado);
		int indice_simbolo = obtenerIndiceSimbolo(simbolo);

		if (indice_estado == -1 || indice_simbolo == -1)
			return "Error";

		return this.transiciones[indice_estado][indice_simbolo];
	}

	private int obtenerIndiceEstado(String estado) {
		int indice = 0;
		for (String e : this.estados) {
			if ( estado.equals(e) )
				return indice;
			indice++;
		}
		return -1;
	}

	private int obtenerIndiceSimbolo(String simbolo) {
		int indice = 0;
		for (String s : this.alfabeto) {
			if (simbolo.equals(s))
				return indice;
			indice++;
		}
		return -1;
	}

	private boolean validarAutomata() {
		if (this.alfabeto == null)
			return false;
		if (this.estado_inicial == null)
			return false;
		if (this.estados_finales == null)
			return false;
		if (this.estados == null)
			return false;
		if (this.transiciones == null)
			return false;
		return true;
	}
}
