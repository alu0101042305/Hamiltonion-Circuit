/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * Nodo.java - Clase para representar un nodo de un grafo
 * @author Grupo 2 - Juan, Richard, Jesus
 */
public class Nodo {

	private String id; // Identificador del nodo
	
	/**
	 * Constructor por defecto de un nodo
	 */
	public Nodo () {
		this.id = null;
	}
	
	/**
	 * Constructor de un nodo indicando su id
	 */
	public Nodo (String id) {
		this.id = id;
	}
	
	/**
	 * Constructor copia
	 * @param original Nodo original
	 */
	public Nodo (Nodo original) {
		this.id = original.getId();
	}
	
	/**
	 * Metodo para mostrar un Nodo como String
	 */
	public String toString () {
		return this.getId();
	}

	/**
	 * Getter id del nodo
	 * @return id del nodo
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter para el id del nodo
	 * @param id id del nodo
	 */
	public void setId(String id) {
		this.id = id;
	}	
}
