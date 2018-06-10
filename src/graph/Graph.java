package graph;

import edge.Edge;

import java.util.*;

/**
 * Implementa a representa????o em objeto de um Grafo.
 */
public class Graph implements IGraph {

	private Set<Edge> edges;
	private Set<String> vertices;
	private Map<String, ArrayList> graph;
	private boolean isWeighted;
	private int verticeNumber;

	/**
	 * Construtor do Grafo
	 * 
	 * @param verticeNumber
	 *            N??mero de v??rtices
	 * @param weighted
	 *            Boolean representando se o grafo ?? (true) ou n??o (false) um
	 *            grafo com pesos.
	 * @throws Exception
	 *             Caso o n??mero de v??rtices informado seja negativo
	 */
	public Graph(int verticeNumber, boolean weighted) throws Exception {

		if (verticeNumber < 0) {
			throw new Exception("Grafo n??o pode ter n??mero de v??rtices negativo");
		}

		this.graph = new HashMap<String, ArrayList>();
		this.edges = new HashSet<>();
		this.vertices = new HashSet<>();
		this.isWeighted = weighted;
		this.verticeNumber = verticeNumber;
	}

	public boolean isWeighted() {
		return this.isWeighted;
	}

	public int getVertexNumber() {
		return this.verticeNumber;
	}

	public int getEdgeNumber() {
		return edges.size();
	}

	/**
	 * Adiciona uma nova aresta ao , ap??s verificar a validade e n??o duplicidade
	 * desta. Adiciona tamb??m uma aresta inversa para representar que a dire????o
	 * contr??ria tamb??m ?? v??lida.
	 * 
	 * @param edge
	 *            Uma aresta representada pelo objeto Edge
	 */
	public void addEdge(Edge edge) {

		if (edge == null)
			return;

		if (edge.getTarget().equals(edge.getSource()))
			return;

		if (this.edges.contains(edge)) {
			return;
		}

		Edge inverseEdge = new Edge(edge.getTarget(), edge.getSource(), edge.getWeight());

		addEdgeAux(edge);
		addEdgeAux(inverseEdge);

	}

	/**
	 * M?todo auxiliar que adiciona de fato uma nova aresta ao grafo.
	 * 
	 * @param edge
	 */
	private void addEdgeAux(Edge edge) {

		this.edges.add(edge);
		this.vertices.add(edge.getSource());
		this.vertices.add(edge.getTarget());

		if (this.graph.get(edge.getSource()) == null) {
			ArrayList<Edge> newArray = new ArrayList();
			newArray.add(edge);

			this.graph.put(edge.getSource(), newArray);
		} else {
			this.graph.get(edge.getSource()).add(edge);
		}

	}

	/**
	 * Retorna todas as arestas do grafo em formato de lista de arestas.
	 * 
	 * @return Uma lista de objetos Edge
	 */
	public Edge[] getAllEdges() {
		return this.edges.toArray(new Edge[this.edges.size()]);
	}

	/**
	 * Retorna todos os v?rtices do grafo em formato de lista.
	 * 
	 * @return Uma lista de strings contendo a representa??o dos v?rtices
	 */
	public String[] getAllVertices() {
		return this.vertices.toArray(new String[this.vertices.size()]);
	}

	/**
	 * Verifica se dois v?rtices est?o conectados por uma aresta. Caso estejam,
	 * retorna essa aresta.
	 *
	 * @param a
	 *            V?rtice A
	 * @param b
	 *            V?rtice B
	 * @return A aresta que os conecta caso sejam conectados ou null caso contr?rio
	 */
	public Edge isConnectedTo(String a, String b) {

		ArrayList<Edge> edges = graph.get(a);

		if (edges == null)
			return null;

		for (Edge edge : edges) {
			if (edge.getTarget().equals(b)) {

				return edge;
			}
		}

		return null;

	}

	/**
	 * Esse m?todo verifica se o grafo ? ou n?o conexo, ou seja, se existem ou
	 * n?o arestas "soltas". Caso ele seja conexo, todos os v?rtices devem ter
	 * sido acessados ap?s efetuar uma DFS.
	 */
	public boolean connected() {

		if (getVertexNumber() == 0)
			return false;

		Map<String, Boolean> visited = new HashMap<String, Boolean>();
		String[] vertices = getAllVertices();
		for (String vertice : vertices) {
			visited.put(vertice, false);
		}

		String vertice = getAllVertices()[0];

		HashMap<String, Boolean> visitedAfterDFS = (HashMap<String, Boolean>) visitedAfterDFS(vertice, visited);

		return (visited.containsValue(false) ? false : true);
	}

	/**
	 * M?todo auxiliar que efetua uma DFS e retorna um array mostrando os elementos
	 * do grafo que foram percorridos.
	 */
	private Map<String, Boolean> visitedAfterDFS(String vertice, Map<String, Boolean> visited) {

		visited.put(vertice, true);

		ArrayList<Edge> edges = graph.get(vertice);
		for (Edge edge : edges) {
			String targetVertice = edge.getTarget();
			if (visited.get(targetVertice) == false) {
				visited = visitedAfterDFS(targetVertice, visited);
			}
		}
		return visited;
	}

	/**
	 * Verifica se um determinado v?rtice possui associa???es.
	 * 
	 * @param vertex
	 *            v?rtice de consulta
	 * @return true caso possua, false caso contr??rio
	 */
	public boolean containsEdges(String vertex) {
		return this.graph.get(vertex) != null && !this.graph.get(vertex).isEmpty();
	}

	/**
	 * Retorna as associa??es de um determinado v?rtice.
	 * 
	 * @param vertex
	 *            V?rtice que ser? acessado
	 */
	public ArrayList<Edge> getVertexEdges(String vertex) {
		return this.graph.get(vertex);
	}

	/**
	 * Verifica se dois vertices est?o ligados em primeiro grau
	 * 
	 * @param a
	 *            Primeiro vertice
	 * @param b
	 *            Segundo vertice
	 * @return true caso se verifique a liga??o, false caso n?o possuam conex?o
	 *         direta.
	 */
	public boolean areLinked(String a, String b) {
		ArrayList<Edge> edges = graph.get(a);

		for (Edge edge : edges) {
			if (edge.getTarget().equals(b) || edge.getSource().equals(b)) {

				return true;
			}
		}
		return false;
	}

	/**
	 * Com base no vertice passado como cabe?a, adquire informa??es de profundidade
	 * e parentesco de cada elemento da arvore, por ordem de profundidade
	 * 
	 * @param result
	 *            ArrayList com todas as informa??es de cada vertice do grafo
	 * @param proximos
	 *            ArrayList onde ser?o colocados os pr?ximos elementos a serem
	 *            percorridos
	 * @param listVertex
	 *            Lista contendo todos os vertices do grafo
	 * @param level
	 *            Contador que come?a do 0 indicando a dist?ncia em rela??o ao
	 *            v?rtice cabe?a
	 *@param needsOrder boolean que decide se resposta deve estar ordenada
	 * @return toString da lista de resultados
	 */
	public String BFS(ArrayList<String> result, List<String> proximos, List<String> listVertex, int level, boolean needsOrder) {
        boolean found = false;
        if (proximos.size() < 1) {
            String toString = "";
            if (needsOrder) {
            	Collections.sort(result);
            	}

            for (String s : result) {
                toString += s;
            }
            return toString;
        }
        for (int i = 0; i < listVertex.size(); i++) {
            if (listVertex.get(i) == null) {
            } else if (listVertex.get(i).equals(proximos.get(0))) {
                result.add(proximos.get(0) + " - " + level + " -\n");
                listVertex.set(i, null);
            } else if (areLinked(proximos.get(0), listVertex.get(i))) {
                found = true;
                proximos.add(listVertex.get(i));
                result.add(listVertex.get(i) + " - " + (level + 1) + " " + proximos.get(0) + "\n");
                listVertex.set(i, null);
            }

        }
        proximos.remove(0);
        if (found)
            level++;
        return BFS(result, proximos, listVertex, level, needsOrder);


    }



}
