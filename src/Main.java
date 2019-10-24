/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * Main.java - Programa para la creacion de un camino hamiltoniano a partir de un vertex cover
 * @author Grupo 2 - Juan, Richard, Jesus
 */
public class Main {

	public static void main(String[] args) {
		CircuitoHam hc = new CircuitoHam();
		hc.cargarCircuito();
	    hc.resolverCircuito();
	}
}
