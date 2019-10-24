import java.util.ArrayList;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * Grafo.java - Clase para representar un grafo
 * @author Grupo 2 - Juan, Richard, Jesus
 */
public class Grafo {
	
	protected String id; // Identificador del grado
	protected ArrayList<Nodo> nodos; // Array que contiene los nodos del grafo
	protected ArrayList<Arista> aristas; // Array que contiene las aristas del grafo
	
	/**
	 * Constructor por defecto del grafo
	 */
	public Grafo () {
		this.id = null;
		this.nodos = new ArrayList<Nodo>();
		this.aristas = new ArrayList<Arista>();
	}
	
	/**
	 * Constructor del grafo indicandole su id
	 */
	public Grafo (String id) {
		this.id = id;
		this.nodos = new ArrayList<Nodo>();
		this.aristas = new ArrayList<Arista>();
	}
	
	/**
	 * Constructor copia
	 * @param original Grafo original
	 */
	public Grafo (Grafo original) {
		this.id = original.getId();
		this.nodos = original.getNodos();
		this.aristas = original.getAristas();
	}
	
	/**
	 * Metodo para agregar un nodo al grafo
	 * @param nodo Nodo que se deseea agregar
	 */
	public void addNodo (Nodo nodo) {
		nodos.add(nodo);
	}
	
	/**
	 * Metodo para agregar una arista al grafo
	 * @param arista Arista que se deseea agregar
	 */
	public void addArista (Arista arista) {
		aristas.add(arista);
	}
	
	/**
	 * Metodo toString de la clase Grafo
	 */
	public String toString () {
		String salida = "";
		salida += this.getId() + ":\n";
		salida += "Numero de nodos: " + this.getNodos().size();
		salida += "\nAristas:\n";
		for(int i = 0; i < this.getAristas().size(); i++)
		{
			salida += this.getAristas().get(i) + "\n"; 
		}
		return salida;
	}

	/**
	 * Getter para el id del grafo
	 * @return Id del grafo
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter para el id del grafo
	 * @param id Id del grafo
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * Getter del array de nodos
	 * @return Array de nodos
	 */
	public ArrayList<Nodo> getNodos() {
		return nodos;
	}

	/**
	 * Getter del array de aristas
	 * @return Array de aristas
	 */
	public ArrayList<Arista> getAristas() {
		return aristas;
	}

}
