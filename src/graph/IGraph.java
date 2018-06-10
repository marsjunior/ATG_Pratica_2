package graph;

import edge.Edge;

import java.util.ArrayList;
import java.util.List;

public interface IGraph {

	/**
	 * Informa o número de vértices do grafo.
	 * 
	 * @return número de vértices do grafo
	 */
	int getVertexNumber();

	/**
	 * Informa o número de arestas do grafo.
	 * 
	 * @return número de arestas do grafo
	 */
	int getEdgeNumber();

	/**
	 * Adiciona uma nova aresta ao grafo.
	 * 
	 * @param edge
	 *            Aresta que será adicionada
	 */
	void addEdge(Edge edge);

	/**
	 * Informa em representação de lista todas as arestas do grafo.
	 * 
	 * @return Lista contendo todas as arestas do grafo
	 */
	Edge[] getAllEdges();

	/**
	 * Informa em representação de lista todos os vértices do grafo.
	 * 
	 * @return Lista contendo todos os vértices do grafo
	 */
	String[] getAllVertices();

	/**
	 * Verifica quais são as arestas associadas a um determinado vértice e as
	 * retorna em um ArrayList
	 * 
	 * @param vertex
	 *            Vértice que será consultado
	 * @return Um ArrayList contendo as arestas associadas, caso existam.
	 */
	ArrayList<Edge> getVertexEdges(String vertex);

	/**
	 * Informa se um determinado vértice possui ou não arestas associadas.
	 * 
	 * @param vertex
	 *            Vértice que será consultado
	 * @return true caso possua arestas associadas, false caso contrário
	 */
	boolean containsEdges(String vertex);

	/**
	 * Verifica se o grafo é ou não conexo, ou seja, se é possivel acessar a
	 * partir de um vértice qualquer outro vértice do grafo.
	 * 
	 * @return true caso seja conexo, falso caso contrário
	 */
	boolean connected();

	/**
	 * Verifica se dois vertices são ligados em primeira ordem (de maneira direta).
	 * Diferente do connected, que testa se os vértices possuem uma ligação
	 * independente do tamanho, neste apenas será retornado true se forem vizinhos.
	 * 
	 * @param vertex1
	 *            1o vertice
	 * @param vertex2
	 *            2o vertice
	 * @return true caso sejam ligados diretamente
	 */
	boolean areLinked(String vertex1, String vertex2);

	/**
	 * Com base no vértice passado como cabeça, adquire informações de profundidade
	 * e parentesco de cada elemento da arvore, por ordem de profundidade
	 * 
	 * @param result
	 *            ArrayList com todas as informações de cada vertice do grafo
	 * @param proximos
	 *            ArrayList onde serão colocados os próximos elementos a serem
	 *            percorridos
	 * @param listVertex
	 *            Lista contendo todos os vertices do grafo
	 * @param level
	 *            Contador que começa do 0 indicando a dist�ncia em relação ao
	 *            vértice cabeça
	 * @return toString da lista de resultados
	 */
	String BFS(ArrayList<String> result, List<String> proximos, List<String> listVertex, int level, boolean needsOrder);

	// String listRepresentation();

}
