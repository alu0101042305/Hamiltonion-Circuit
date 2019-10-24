import java.util.ArrayList;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * Gadget.java - Clase para representar los cover testing del circuito hamiltoniano, hereda de Grafo
 * @author Grupo 2 - Juan, Richard, Jesus
 */

public class Gadget extends Grafo {

	private static final int NUM_NODOS = 12;
	
	private ArrayList<Integer> extLibres; // Extremos por los que puede ser conectado el gadget
	private ArrayList<Integer> extOcupados; // Extremos de los libres pero que ya estan conectados
	
	/**
	 * Constructor del gadget
	 * @param nombre Identificador del gadget
	 */
	public Gadget (String nombre) {
		super(nombre);
		this.generarNodos(nombre);
		this.extLibres = new ArrayList<Integer>();
		this.extOcupados = new ArrayList<Integer>();
		this.extLibres.add(new Integer(0));
		this.extLibres.add(new Integer(5));
		this.extLibres.add(new Integer(6));
		this.extLibres.add(new Integer(11));
		this.generarAristas();
	}

	/**
	 * Metodo para generar los nodos del gadget
	 * @param nombre Identificador del gadget
	 */
	private void generarNodos(String nombre) {		
		for (int i = 0; i < NUM_NODOS; i++) {
			String idNodo = nombre + "_" + (i+1);
			Nodo aux = new Nodo (idNodo);
			this.addNodo(aux);
		}		
	}
	
	/**
	 * Metodo para generar las aristas del gadget
	 */
	private void generarAristas() {		
		// Aristas superiores
		for (int i = 0; i < 5; i++) {
			Arista aux = new Arista();
			aux.setNodoA(this.getNodos().get(i));
			aux.setNodoB(this.getNodos().get(i+1));
			this.addArista(aux);
		}
		
		// Aristas inferiores
		for (int i = 6; i < 11; i++) {
			Arista aux = new Arista();
			aux.setNodoA(this.getNodos().get(i));
			aux.setNodoB(this.getNodos().get(i+1));
			this.addArista(aux);
		}
		
		// Aristas cruzadas
		Arista c1 = new Arista();
	    c1.setNodoA(this.getNodos().get(0));
	    c1.setNodoB(this.getNodos().get(8));
	    this.addArista(c1);
	    Arista c2 = new Arista();
	    c2.setNodoA(this.getNodos().get(2));
	    c2.setNodoB(this.getNodos().get(6));
	    this.addArista(c2);
	    Arista c3 = new Arista();
	    c3.setNodoA(this.getNodos().get(3));
	    c3.setNodoB(this.getNodos().get(11));
	    this.addArista(c3);
	    Arista c4 = new Arista();
	    c4.setNodoA(this.getNodos().get(5));
	    c4.setNodoB(this.getNodos().get(9));
	    this.addArista(c4);
	}
	
	/**
	 * Metodo para conectar Gadgets
	 * @return extConectar Indice del nodo situado en el extremo libre a conectar
	 */
	public int conectGadgets () {
		Integer extConectar = this.getExtLibres().get(0);
		this.getExtOcupados().add(extConectar);
		this.getExtLibres().remove(extConectar);
		return extConectar;
	}

	/**
	 * Getter Array de ext. libres
	 * @return Array de ext. libres
	 */
	public ArrayList<Integer> getExtLibres() {
		return extLibres;
	}

	/**
	 * Setter Array de ext. libres
	 * @param extLibres
	 */
	public void setExtLibres(ArrayList<Integer> extLibres) {
		this.extLibres = extLibres;
	}

	/**
	 * Getter Array de ext. ocupados
	 * @return Array de ext. ocupados
	 */
	public ArrayList<Integer> getExtOcupados() {
		return extOcupados;
	}

	/**
	 * Setter Array de ext. ocupados
	 * @param extLibres
	 */
	public void setExtOcupados(ArrayList<Integer> extOcupados) {
		this.extOcupados = extOcupados;
	}
}
