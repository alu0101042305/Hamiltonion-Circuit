/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * Arista.java - Clase para representar una arista de un grafo, conecta un nodo A con otro nodo B
 * @author Grupo 2 - Juan, Richard, Jesus
 */
public class Arista {
	
	private Nodo nodoA;
	private Nodo nodoB;

	/**
	 * Constructor de una arista
	 */
	public Arista () {
		this.nodoA = new Nodo();
		this.nodoB = new Nodo();
	}
	
	/**
	 * Constructor copia
	 * @param original Arista original
	 */
	public Arista (Arista original) {
		this.nodoA = original.getNodoA();
		this.nodoB = original.getNodoB();
	}
	
	/**
	 * Metodo para comprobar si una arista conecta un nodo con un id determinado
	 * @param id Id del nodo que se busca
	 * @return True en caso de que la arista conecte el nodo buscado
	 */
	public boolean contieneNodo (String valor) {
		if ((this.getNodoA().getId().equals(valor)) || (this.getNodoB().getId().equals(valor))) {
			return true;
		}
		else
			return false;
			
	}
	
	/**
	 * Metodo para mostrar una arista como cadena de caracteres
	 */
	public String toString () {
		return this.getNodoA().getId() + " <--> " + this.getNodoB().getId();	
	}

	/**
	 * Getter del nodo A de la arista
	 * @return Nodo A
	 */
	public Nodo getNodoA() {
		return nodoA;
	}
	
	/**
	 * Setter del nodo A de la arista
	 * @param nodoA Nodo A
	 */
	public void setNodoA(Nodo nodoA) {
		this.nodoA = nodoA;
	}

	/**
	 * Getter del nodo B de la arista
	 * @return Nodo B
	 */
	public Nodo getNodoB() {
		return nodoB;
	}

	/**
	 * Setter del nodo B de la arista
	 * @param nodoA Nodo B
	 */
	public void setNodoB(Nodo nodoB) {
		this.nodoB = nodoB;
	}
}
