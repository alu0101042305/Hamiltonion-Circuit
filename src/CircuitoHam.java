import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Universidad de La Laguna - Grado de Ingenieria Informatica <p>
 * Complejidad Computacional - Circuito Hamiltoniano <p>
 * CircuitoHam.java - Clase para la construccion del circuito hamiltoniano
 * @author Grupo 2 - Juan, Richard, Jesus
 */

public class CircuitoHam {
	
	ArrayList<Gadget> gadgets; // Gadgets del grafo
	ArrayList<String> idNodos; // Array que almacena los id de los nodos
	Grafo completo; // Grafo completo
	int numNodos; // Numero de nodos del grafo completo, incluidos los de los gadgets
	
	/**
	 * Constructor por defecto
	 */
	public CircuitoHam () {
		this.gadgets = new ArrayList<Gadget>();
		this.idNodos = new ArrayList<String>();
		this.completo = new Grafo("HC");
		this.numNodos = 0;
	}

	/**
	 * Metodo que genera el grafo completo sobre el que calcular el Camino Hamiltoniano
	 * Primero carga el vertex cover, luego agrega los selectores (1 por cada nodo del VC)
	 * y por ultimo genera y conecta los gadgets tanto entre si como con el resto de
	 * nodos libres.
	 */
	public void cargarCircuito() {
		String nombreFichero;
		VC vertex = new VC();
		
		// Solicitar nombre del fichero
		Scanner sc = new Scanner(System.in);
		System.out.println("Ruta del fichero que contiene la salida del Vertex Cover: ");
		nombreFichero = sc.nextLine();
		sc.close();
		
		// Cargar VC
		try {
			vertex.cargarVC(nombreFichero);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Agregar los gadgets, uno por cada arista
		for (int i = 0; i < vertex.getAristas().size(); i++) {
			String idGadget = "GD_";
			if (vertex.enCubrimiento(vertex.getAristas().get(i).getNodoA()))
				idGadget += vertex.getAristas().get(i).getNodoA().getId() + "-" + vertex.getAristas().get(i).getNodoB().getId();
			else
				idGadget += vertex.getAristas().get(i).getNodoB().getId() + "-" + vertex.getAristas().get(i).getNodoA().getId();
			Gadget aux = new Gadget (idGadget);
			this.addGadget(aux);
		}
		
		for (int i = 0; i < vertex.getCubrimiento().size(); i++)
		{
			// Incluir un nodo nuevo por cada uno en el cubrimiento (selectores)
			Nodo nuevo = new Nodo();
			String idNuevo = "nodoHC_" + (i+1);
			nuevo.setId(idNuevo);
			this.completo.addNodo(nuevo);
			
			// Si el gadget pertenece a una arista de ese vertice hacer las conexiones
			int indiceGadget1 = -1;
			int indiceGadget2 = -1;
			
			for (int j = 0; j < this.getGadgets().size(); j++)
			{
				if (this.getGadgets().get(j).getId().contains(vertex.getCubrimiento().get(i).getId()))
				{					
					if (indiceGadget1 == -1)
						indiceGadget1 = j;
					else if (indiceGadget2 == -1)
						indiceGadget2 = j;
					if ((indiceGadget2 != -1) && (indiceGadget1 != -1))
					{
						// Unir los gadgets correspondientes entre si
						Gadget gadget1 = this.getGadgets().get(indiceGadget1);
						Gadget gadget2 = this.getGadgets().get(indiceGadget2);
						
						int extConexion1 = gadget1.conectGadgets();
						int extConexion2 = gadget2.conectGadgets();
						
						Arista aux = new Arista();			
						aux.setNodoA(this.getGadgets().get(indiceGadget1).getNodos().get(extConexion1));
						aux.setNodoB(this.getGadgets().get(indiceGadget2).getNodos().get(extConexion2));	
						
						this.completo.addArista(aux);
						
						// Reiniciar los indices y dejar la j estatica de cara a la siguiente iteracion
						indiceGadget1 = -1; 
						indiceGadget2 = -1;
	                    j--;
					}
				}
			}
		}
		// Conectar los nodos libres y los selectores
	    this.conGadgetsNodo(this.completo);
	}

	/**
	 * Metodo con el algoritmo correspondiente para encontrar el camino hamiltoniano
	 */
	public void resolverCircuito() {		
		// Obtener todas las id de los nodos y el numero de nodos del camino final
	    this.generarIdNodos();
	    this.setNumNodos(this.getIdNodos().size());
	    System.out.println(this.getCompleto().getId() + ":");
	    System.out.println("Numero de nodos en el camino: " + this.getNumNodos());
	    
	    // Crear la matriz de adyacencia, un array de visitados y otro para guardar el camino recorrido	    
	    boolean [][] matAdy = new boolean [this.getNumNodos()][];
	    boolean [] visitado = new boolean [this.getNumNodos()];
	    int [] camino = new int [this.getNumNodos()];
	    
	    for(int i = 0;  i < this.getNumNodos(); i++)
	    {
	        matAdy[i] = new boolean[getNumNodos()];
	        camino[i] = -1;
	        visitado[i] = false;
	    }	    
	    
	    // Obtener las aristas asociadas a cada nodo
	    for (int i = 0; i < this.getNumNodos(); i++)
	    {
	    	ArrayList<Arista> aux = new ArrayList<Arista>();
	    	aux = this.getAristasNodo(this.getIdNodos().get(i));
	    	for (int j = 0; j < this.getNumNodos(); j++)
	    	{
	    		if (i == j)
	    			matAdy[i][j] = false;
	    		else if (this.adyacencia(this.getIdNodos().get(j), aux))
	    			matAdy[i][j] = true;
	    		else
	    			matAdy[i][j] = false;
	    	}
	    }
	    
	    // Agregar el id del primer nodo al camino
	    camino[0] = 0;
	    visitado[0] = true;
	    if (!this.calcularCircuito(matAdy, visitado, camino, 1))
	    	System.out.println("No existe solucion");
	    else
	    {
	    	System.out.println("Existe solucion:");
	    	for (int i = 0; i < this.getNumNodos(); i++)
	    		System.out.println(" " + this.getIdNodos().get(camino[i]));
	    	// Agregar el primer nodo que es a su vez el ultimo
	    	System.out.println(" " + this.getIdNodos().get(camino[0]));
	    }
	}

	/**
	 * Metodo recursivo para el calculo del circuito hamiltoniano
	 * @param matAdy Matriz de adyacencia
	 * @param visitado Array de visitados
	 * @param camino Camino recorrido
	 * @param pos Posicion
	 * @return true en caso de que exista una solucion
	 */
	private boolean calcularCircuito(boolean[][] matAdy, boolean[] visitado, int[] camino, int pos) {
		// Condicion de parada: todos los nodos incluidos en V
		if (pos == this.getNumNodos())
		{
			// Y si el ultimo vertice incluido es igual al primero
			if (matAdy[camino[pos-1]][camino[0]] == true)
				return true;
			else
				return false;
		}
		
		// Buscar el siguiente candidato para el circuito
		for (int i = 1; i < this.getNumNodos(); i++)
		{
			// Comprobar si se puede agregar el nodo al circuito
			if (this.esAgregable(camino[pos-1], i, matAdy, visitado))
			{
				visitado[i] = true;
				camino[pos] = i;
				// Llamada recursiva para construir el resto del circuito
				if (this.calcularCircuito(matAdy, visitado, camino, pos+1))
					return true;
				// Si el nodo que se agrego no lleva a una solucion se elimina
				visitado[i] = false;
				camino[pos] = -1;
			}
		}
	    // Si no hay mas nodos que agregar se devuelve falso
	    return false;
	}

	/**
	 * Metodo para comprobar si un nodo se puede agregar al camino segun su adyacencia
	 * @param ultimoNodo Ultimo nodo
	 * @param nodoActual Nodo actual
	 * @param matAdy Matriz de adyacencia
	 * @param visitado Array de visitados
	 * @return true en caso de que se pueda agregar
	 */
	private boolean esAgregable(int ultimoNodo, int nodoActual, boolean[][] matAdy, boolean[] visitado) {
		if (visitado[nodoActual])
			return false;
		if (matAdy[ultimoNodo][nodoActual] == false)
			return false;
	    return true;
	}

	/**
	 * Metodo para agregar un gadget
	 * @param valor Gadget
	 */
	private void addGadget(Gadget valor) {
		this.gadgets.add(valor);		
	}
    
	/**
	 * Metodo para conectar los nodos libres y los extremos libres de los gadgets
	 * @param grafo Grafo completo
	 */
    private void conGadgetsNodo(Grafo grafo) {
    	Gadget gadget;
        for (int j = 0; j < this.getGadgets().size(); j++) {
        	gadget = this.getGadgets().get(j);
        	if (gadget.getExtLibres().size() > 0)
        		while (gadget.getExtLibres().size() > 0) {
        			Nodo b = gadget.getNodos().get(gadget.conectGadgets());
        			for (int i = 0; i < grafo.getNodos().size(); i++) {
        				Arista aristaNodoGadget = new Arista();
        				aristaNodoGadget.setNodoA(grafo.getNodos().get(i));
        				aristaNodoGadget.setNodoB(b);
        				grafo.addArista(aristaNodoGadget);
        			}
        		}
        }
	}
    
    /**
     * Metodo para generar las id de los nodos del grafo completo
     */
    private void generarIdNodos() {
    	for(int i = 0;  i < this.getCompleto().getNodos().size(); i++){
            this.getIdNodos().add(this.getCompleto().getNodos().get(i).getId());
        }        
        for(int i = 0; i < this.getGadgets().size(); i++){
            for(int j = 0;  j < this.getGadgets().get(i).getNodos().size(); j++){
                this.getIdNodos().add(this.getGadgets().get(i).getNodos().get(j).getId());
            }
        }		
	}
    
    /**
     * Metodo para hallar las aristas que conectan con un nodo
     * @param idNodo Identificador del nodo
     * @return Array con las aristas
     */
    private ArrayList<Arista> getAristasNodo(String idNodo) {
    	ArrayList<Arista> todas = new ArrayList<Arista>(); // Todas las aristas, incluidas las de los gadgets
    	ArrayList<Arista> aux = new ArrayList<Arista>(); // Aristas que si contienen este nodo
    	todas = this.getCompleto().getAristas(); // Agregar primero las aristas del grafo completo
    	// Agregar luego las de los gadgets
    	for (int i = 0; i < this.getGadgets().size(); i++)
    	{
    		for (int j = 0; j < this.getGadgets().get(i).getAristas().size(); j++)
    		{
    			todas.add(this.getGadgets().get(i).getAristas().get(j));
    		}
    	}
    	// Buscar y agregar las que si estan
    	for (int i = 0; i < todas.size(); i++)
    		if (todas.get(i).contieneNodo(idNodo))
    			aux.add(todas.get(i));
    	
    	return aux;
	}
    
    /**
     * Metodo para comprobar si el nodo B se encuentra unido al nodo A por alguna arista
     * @param idNodoB Id del nodo a comprobar
     * @param aristasA Aristas del nodo A
     * @return true en caso de que si se unan, es decir, son adyacentes
     */
    private boolean adyacencia(String idNodoB, ArrayList<Arista> aristasA) {
    	for(int i = 0; i < aristasA.size(); i++){
            if(aristasA.get(i).contieneNodo(idNodoB)){
                return true;
            }
        }
        return false;
	}
    
    // Getters y Setters

	public ArrayList<Gadget> getGadgets() {
		return gadgets;
	}

	public void setGadgets(ArrayList<Gadget> gadgets) {
		this.gadgets = gadgets;
	}

	public ArrayList<String> getIdNodos() {
		return idNodos;
	}

	public void setIdNodos(ArrayList<String> idNodos) {
		this.idNodos = idNodos;
	}

	public Grafo getCompleto() {
		return completo;
	}

	public void setCompleto(Grafo completo) {
		this.completo = completo;
	}

	public int getNumNodos() {
		return numNodos;
	}

	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}

}
