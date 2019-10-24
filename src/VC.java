import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * VC.java - Clase para representar una entrada del vertex cover, hereda de Grafo
 * @author Grupo 2 - Juan, Richard, Jesus
*/
public class VC extends Grafo {

	private ArrayList<Nodo> cubrimiento;

	/**
	 * Contructor por defecto
	 */
	public VC (){
		super("VC");
		this.cubrimiento = new ArrayList<Nodo>();
	}

	/**
	 * Constructor copia
	 * @param original Vertex Cover original
	 */
	public VC (VC original){
		this.id = original.getId();
		this.nodos = original.getNodos();
		this.aristas = original.getAristas();
	}

	/**
	 * Metodo para cargar un vertex cover desde un fichero de texto
	 * @param nomFichero Nombre del fichero
	 * @throws IOException Manejo errores I/O
	 */
	public void cargarVC (String nombreFichero) throws IOException
	{
		// La primera linea corresponde al numero de nodos del grafo
		String linea;
		BufferedReader reader = new BufferedReader(new FileReader(nombreFichero));		
		linea = reader.readLine();
		int numNodos = Integer.parseInt(linea);
		this.generarNodos(numNodos);
  		
		// La segunda linea corresponde a los nodos pertenecientes al vertex cover
		linea = reader.readLine();
		String [] indiceNodosCubrimiento = linea.split(" ");
		for (int i = 0; i < indiceNodosCubrimiento.length; i++)
		{
			int indice = Integer.parseInt(indiceNodosCubrimiento[i]);
			Nodo nodoCubrimiento = new Nodo (this.getNodos().get(indice - 1));
			this.cubrimiento.add(nodoCubrimiento);
		}

		// El resto del fichero es una linea por cada una de las aristas del grafo
		while ((linea = reader.readLine()) != null)
		{
			String [] indicesNodos = linea.split(" ");
			int indiceNodoA = Integer.parseInt(indicesNodos[0]);
			int indiceNodoB = Integer.parseInt(indicesNodos[1]);
			Nodo a = this.getNodos().get(indiceNodoA - 1);
			Nodo b = this.getNodos().get(indiceNodoB - 1);
			
			Arista aux = new Arista();
			aux.setNodoA(a);
			aux.setNodoB(b);
			this.addArista(aux); 
        }
		reader.close();
		System.out.println(this);
	}

	/**
	 * Metodo para generar los nodos del vertex cover
	 * @param numNodos
	 */
	public void generarNodos(int numNodos)
	{
		for (int i = 0; i < numNodos; i++)
		{
			Nodo aux = new Nodo();
			String idNodo = "vc" + (i+1);
			aux.setId(idNodo);
			this.addNodo(aux);
		}
	}
	
	/**
	 * Metodo para comprobar si un nodo es parte del cubrimiento
	 * @param valor Nodo a comprobar
	 * @return true en caso de que si sea parte del cubrimiento
	 */
	public boolean enCubrimiento (Nodo valor){
		for (int i = 0; i < this.getCubrimiento().size(); i++) {
			if (this.getCubrimiento().get(i).getId().equals(valor.getId()))
				return true;
		}
		return false;
	}

	/**
	 * Metodo toString de la clase VC
	 */
	public String toString()
	{
		String salida = "";
		salida += this.getId() + ":\n";
		salida += "Numero de nodos: " + this.getNodos().size() + "\nCubrimiento: ";
		for (int i = 0; i < this.getCubrimiento().size(); i++)
		{
			salida += this.getCubrimiento().get(i).getId() + " ";
		}
		salida += "\nAristas:\n";
		for(int i = 0; i < this.getAristas().size(); i++)
		{
			salida += this.getAristas().get(i) + "\n"; 
		}
		return salida;
	}

	/**
	 * Setter para el array de los nodos de cubrimiento
	 * @param valor Array de los nodos de cubrimiento
	 */
	public void setCubrimiento(ArrayList<Nodo> valor){
		this.cubrimiento = valor;
	}

	/**
	 * Getter del conjunto de nodos de cubrimiento
	 * @return Array con los nodos de cubrimiento
	 */
	public ArrayList<Nodo> getCubrimiento(){
		return this.cubrimiento;
	}
}
