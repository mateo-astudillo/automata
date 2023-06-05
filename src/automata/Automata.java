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

	public void setEstadoInicial(String estado_inicial) {
  	this.estado_inicial = estado_inicial;
  }

	public void setEstadosFinales(String[] estados_finales) {
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

	public boolean setTransicion(String deEstado, String simbolo, String aEstado) {
		if (!existeEstado(deEstado) || !existeEstado(aEstado))
			return false;
		if (!existeSimbolo(simbolo))
			return false;
		this.transiciones[obtenerIndiceEstado(deEstado)][obtenerIndiceSimbolo(simbolo)] = aEstado;
		return true;
	}

	public String[] getAlfabeto() {
		return this.alfabeto;
	}

	public String[] getEstados() {
		return this.estados;
	}

	public boolean pertenece(String cadena) {
		String s = "";
		String estado_actual = this.estado_inicial;
		for (char simbolo : cadena.toCharArray()) {
			s = "" + simbolo;
			System.out.print(estado_actual + ", " + simbolo + " -> ");
			estado_actual = transicion(estado_actual, s);
			if (estado_actual.equals("Error"))
				break;
		}
		System.out.println(estado_actual);
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
		int indice_estado, indice_simbolo;
		if (!existeEstado(estado) || !existeSimbolo(simbolo))
			return "Error";

		indice_estado = obtenerIndiceEstado(estado);
		indice_simbolo = obtenerIndiceSimbolo(simbolo);

		return this.transiciones[indice_estado][indice_simbolo];
	}

	private boolean existeEstado(String estado) {
		for (String e : this.estados) {
			if (estado.equals(e))
				return true;
		}
		return false;
	}

	private boolean existeSimbolo(String simbolo) {
		for (String s : this.alfabeto) {
			if (simbolo.equals(s))
				return true;
		}
		return false;
	}

	private int obtenerIndiceEstado(String estado) {
		int indice = 0;
		for (String e : this.estados) {
			if ( estado.equals(e) )
				break;
			indice++;
		}
		return indice;
	}

	private int obtenerIndiceSimbolo(String simbolo) {
		int indice = 0;
		for (String s : this.alfabeto) {
			if (simbolo.equals(s))
				break;
			indice++;
		}
		return indice;
	}
}
