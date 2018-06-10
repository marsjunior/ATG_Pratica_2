package ghandler;

import graph.Graph;

public interface IGraphHandler {

    /**
     * Realiza a leitura de um grafo sem pesos a partir de um arquivo de texto
     * @param inputPath Caminho do arquivo
     * @return Objeto grafo
     */
    Graph readGraph(String inputPath);

    /**
     * Realiza a leitura de um grafo com pesos a partir de um arquivo de texto
     * @param inputPath Caminho do arquivo
     * @return Objeto grafo
     */
    Graph readWeightedGraph(String inputPath);

    /**
     * Conta o número de vértices do grafo
     * @param graph Grafo a ter seus vértices contados
     * @return Número de vértices do grafo
     */
    int getVertexNumber(Graph graph);

    /**
     * Conta o número de arestas do grafo
     * @param graph Grafo a ter suas arestas contadas
     * @return Número de arestas do grafo
     */
    int getEdgeNumber(Graph graph);

    /**
     * Conta o grau médio do grafo
     * @param graph Grafo a seu grau médio contado
     * @return Grau médio do grafo
     */
    float getMeanEdge(Graph graph);

    /**
     * Imprime o grafo em sua representação type
     * @param graph Grafo a ser impresso
     * @param type Tipo de representação a ser impressa, podendo ser AM para Matriz de Adjacência, ou AL para Lista de
     *             Adjacência
     * @return String com a impressão da representação do grafo
     */
    String graphRepresentation (Graph graph, String type);

    /**
     * Imprime o menor caminho de um vértice ao outro. Se o grafo contiver pesos, estes serão impressos
     * de forma acumulada a cada vértice. Caso haja um loop negativo, o método retornará uma mensagem informando.
     * @param graph Grafo a ser utilizado
     * @param source Vértice de origem
     * @param target Vértice destino
     * @return String que contém a lista de vértices no caminho entre source e target
     */
    String shortestPath(Graph graph, String source, String target);

    /**
    * Retorna um boolean informando se o grafo é ou não conexo.
    * @param graph Grafo a ser verificado
    */
    boolean connected(Graph graph);
    
    /**
     * Realiza a busca em largura a partir do vertice informado
     * @param graph cuja busca será realizada
     * @param head Vertice inicial da busca
     */
    String BFS(Graph graph, String head);
    
    /**
     * Mostra a Busca em profundidade com base no vertice informado
     * @param graph cuja busca será realizada
     * @param head vertice inicial da busca
     */
    String DFS(Graph graph, String head);
}
