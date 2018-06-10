package ghandler;

import edge.Edge;

import graph.Graph;
import mst.VertexGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class GraphHandler implements IGraphHandler {

    public GraphHandler() {
    }

    /**
     * Recebe um grafo descrito em um arquivo de texto e constrói sua representação em objeto no sistema. A primeira linha
     * informa o número de vértices do grafo e as linhas subsquentes informam as arestas, uma por linha, com vértices
     * separados por espaço.
     *
     * @param inputPath Caminho do arquivo
     * @return Um objeto Graph
     */
    @Override
    public Graph readGraph(String inputPath) {
        if (inputPath == null) {
            return null;
        }

        String line;
        Graph graph = null;
        String source, target;

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {

            String verticeNumber = br.readLine(); // lê numero de vertices
            graph = new Graph(Integer.parseInt(verticeNumber), false);


            line = br.readLine(); // lê primeira aresta

            while (line != null) {

                String[] edge = line.split(" ");

                source = edge[0];
                target = edge[1];

                graph.addEdge(new Edge(source, target));

                line = br.readLine();
            }

        } catch (IOException ex) {
            System.out.println("Erro ao ler arquivo.");
            return null;
        } catch (NumberFormatException ex) {
            System.out.println("Erro ao converter entrada");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return graph;
    }

    /**
     * Recebe um grafo descrito em um arquivo de texto e constrói sua representação em objeto no sistema. A primeira linha
     * informa o número de vértices do grafo e as linhas subsquentes informam as arestas, uma por linha, com vértices
     * separados por espaço e o terceiro elemento de cada linha sendo o peso da aresta.
     *
     * @param inputPath Caminho do arquivo
     * @return Um objeto Graph
     */
    @Override
    public Graph readWeightedGraph(String inputPath) {
        if (inputPath == null) {
            return null;
        }

        String line;
        double weight;
        Graph graph = null;
        String source, target;

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {

            String verticeNumber = br.readLine();
            graph = new Graph(Integer.parseInt(verticeNumber), true);

            line = br.readLine();
            while (line != null) {

                String[] edge = line.split(" ");

                source = edge[0];
                target = edge[1];
                weight = Double.parseDouble(edge[2]);

                graph.addEdge(new Edge(source, target, weight));

                line = br.readLine();
            }

        } catch (IOException ex) {
            System.out.println("Erro ao ler arquivo.");
            return null;
        } catch (NumberFormatException ex) {
            System.out.println("Erro ao converter entrada");
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        return graph;
    }

    /**
     * Conta o número de vértices do grafo
     *
     * @param graph Grafo a ter seus vértices contados
     * @return Número de vértices do grafo
     */
    @Override
    public int getVertexNumber(Graph graph) {
        if (graph == null)
            return 0;

        return graph.getVertexNumber();
    }

    /**
     * Conta o número de arestas do grafo
     *
     * @param graph Grafo a ter suas arestas contadas
     * @return Número de arestas do grafo
     */
    @Override
    public int getEdgeNumber(Graph graph) {
        if (graph == null)
            return 0;

        return graph.getEdgeNumber();
    }

    /**
     * Conta o grau médio do grafo
     *
     * @param graph Grafo a seu grau médio contado
     * @return Grau médio do grafo
     */
    @Override
    public float getMeanEdge(Graph graph) {
        if (graph == null)
            return 0;

        return (float) (2.0 * graph.getEdgeNumber() / graph.getVertexNumber());
    }

    /**
     * Imprime o grafo em sua representação type
     *
     * @param graph Grafo a ser impresso
     * @param type  Tipo de representação a ser impressa, podendo ser AM para Matriz de Adjacência, ou AL para Lista de
     *              Adjacência
     * @return String com a impressão da representação do grafo
     */
    @Override
    public String graphRepresentation(Graph graph, String type) {
        if (type == null || graph == null) {
            return "";
        }

        if (type.equals("AM")) {
            return matrixRepresentation(graph);
        } else if (type.equals("AL")) {
            return listRepresentation(graph);
        }

        return "";

    }

    /**
     * Método auxiliar para exibição do grafo em formato de matriz de adjacência
     *
     * @param graph Grafo a ser exibido
     * @return Uma string com a representação visual do gráfico
     */
    private String matrixRepresentation(Graph graph) {

        if (graph == null) {
            return "";
        }

        String out = " ";
        String vertices[] = graph.getAllVertices();

        if (vertices == null || vertices.length == 0) {
            return "";
        }

        Arrays.sort(vertices);
        for (String vertex : vertices) {
            out += " " + vertex;
        }

        for (int i = 0; i < vertices.length; i++) {
            out += "\n" + vertices[i];
            for (int j = 0; j < vertices.length; j++) {

                String concat = null;
                Edge connection = graph.isConnectedTo(vertices[i], vertices[j]);

                if (connection == null) {
                    concat = "0";
                } else if (connection.getWeight() == 0.0 && graph.isWeighted()) {
                    concat = "0.0";
                } else if (connection.getWeight() == 0.0 && !graph.isWeighted()) {
                    concat = "1";
                } else {
                    concat = String.valueOf(new DecimalFormat("##.##").format(connection.getWeight()));
                }

                out += " " + concat;
            }
        }

        return out;
    }

    /**
     * Método auxiliar para exibição do grafo em formato de lista
     *
     * @param graph Grafo a ser exibido
     * @return Uma string com a representação visual do gráfico
     */
    private String listRepresentation(Graph graph) {

        if (graph == null) {
            return "";
        }

        String out = "";

        String vertices[] = graph.getAllVertices();
        Arrays.sort(vertices);

        for (String vertex : vertices) {
            ArrayList<Edge> edges = graph.getVertexEdges(vertex);


            if (edges == null) {
                continue;
            }


            out += vertex + " -";
            Collections.sort(edges, Comparator.comparing(Edge::getTarget));


            for (Edge edge : edges) {
                out += " " + edge.getTarget();

                if (graph.isWeighted()) {
                    String concat = "(" + String.valueOf(new DecimalFormat("##.##").format(edge.getWeight())) + ")";

                    if (concat.equals("0.0"))
                        concat = "0";

                    out += concat;
                }

            }

            out += "\n";

        }

        return out.trim();

    }

    /**
     * Imprime o menor caminho de um vértice ao outro. Se o grafo contiver pesos, estes serão impressos
     * de forma acumulada a cada vértice. Caso haja um loop negativo, o método retornará uma mensagem informando.
     *
     * @param graph  Grafo a ser utilizado
     * @param source Vértice de origem
     * @param target Vértice destino
     * @return String que contém a lista de vértices no caminho entre source e target
     */
    @Override
    public String shortestPath(Graph graph, String source, String target) {

        if (graph == null || source == null || target == null) {
            return "";
        }

        if (!graph.containsEdges(source) || !graph.containsEdges(target)) {
            return "";
        }


        String out = "";

        Map<String, Double> distance = new HashMap<>();
        distance.put(source, 0.0);

        Map<String, String> path = new HashMap<>();
        path.put(source, source);

        for (String vertex : graph.getAllVertices())
            distance.put(vertex, Double.MAX_VALUE);

        distance.put(source, 0.0);

        for (int i = 0; i < graph.getVertexNumber(); i++) {

            for (Edge edge : graph.getAllEdges()) {

                String v1 = edge.getSource();
                String v2 = edge.getTarget();
                double w = edge.getWeight();


                if (!distance.get(v1).equals(Double.MAX_VALUE) && (distance.get(v1) + w) < distance.get(v2)) {
                    distance.put(v2, distance.get(v1) + w);
                    path.put(v2, v1);

                }

            }

        }


        for (Edge edge : graph.getAllEdges()) {

            String v1 = edge.getSource();
            String v2 = edge.getTarget();
            double w = edge.getWeight();

            if (!distance.get(v1).equals(Double.MAX_VALUE) && distance.get(v1) + w < distance.get(v2))
                out = "Grafo contém ciclo negativo\n";
        }

        if (!out.equals("Grafo contém ciclo negativo\n")) {

            String t = target;
            Stack<String> pathString = new Stack<>();


            if (!graph.isWeighted()) {

                pathString.push(target + " ");
                while (!t.equals(source)) {

                    t = path.get(t);

                    pathString.push(t + " ");

                }

            } else {

                pathString.push(target + "(" + distance.get(target) + ") ");
                while (!t.equals(source)) {

                    t = path.get(t);

                    pathString.push(t + "(" + distance.get(t) + ") ");

                }

            }

            while (!pathString.empty()) {
                out += pathString.pop();
//                System.out.println(out);
            }

        }

        return out.trim();

    }

    /**
     * Retorna um boolean informando se o grafo é ou não conexo. Delega a funcionalidade para o método de mesmo nome na
     * classe Graph.
     *
     * @param graph Grafo a ser verificado
     */
    public boolean connected(Graph graph) {
        return graph.connected();
    }

    
    /**
     * Retorna a arvore geradora minima de um dado grafo.
     *
     * @param graph Grafo a ser verificado
     */
    public String MST(Graph graph) {

        int counter = 0;
        Graph graphAux = null;
        Edge orderedEdges[];
        VertexGroup conjuntos[];
        String result;

        if (graph.isWeighted()) {
            try {
                graphAux = new Graph(graph.getVertexNumber(), true);
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            try {
                graphAux = new Graph(graph.getVertexNumber(), false);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        orderedEdges = graph.getAllEdges();

        Arrays.sort(orderedEdges);

        conjuntos = new VertexGroup[graph.getVertexNumber() + 1];

        for (int i = 0; i <= graph.getVertexNumber(); i++) {
            conjuntos[i] = new VertexGroup(0, 0, 0);
            if (i < graph.getVertexNumber()) {
                conjuntos[i].setAncestor(i);
                conjuntos[i].setStatus(0);
                conjuntos[i].setVertexData(i);
            }
        }


        while (graphAux.getAllEdges().length < graph.getVertexNumber() - 1) {

            Edge edgeAux = orderedEdges[counter];

            if (hasCycle(conjuntos, edgeAux)) {
                graphAux.addEdge(edgeAux);

                int ancestorA = identifyParent(conjuntos, Integer.valueOf(edgeAux.getSource()));
                int ancestorB = identifyParent(conjuntos, Integer.valueOf(edgeAux.getTarget()));
                joinGroups(conjuntos, ancestorA, ancestorB);
            }

            counter++;
        }

        result = this.graphRepresentation(graphAux, "AM");


        return result;

    }
    /**
     * Junta dos subgrupos de vertices.
     *
     * @param groups lista com subconjunto de vertices
     * @param sourceOne vertice do primeiro subgrupo
     * @param sourceTwo vertice do segundo subgrupo
     */

    private void joinGroups(VertexGroup groups[], int sourceOne, int sounrceTwo) {


        if (groups[identifyParent(groups, sourceOne)].getStatus() < groups[identifyParent(groups, sounrceTwo)].getStatus()) {

            groups[identifyParent(groups, sourceOne)].setAncestor(identifyParent(groups, sounrceTwo));

        } else if (groups[identifyParent(groups, sourceOne)].getStatus() > groups[identifyParent(groups, sounrceTwo)].getStatus()) {

            groups[identifyParent(groups, sounrceTwo)].setAncestor(identifyParent(groups, sourceOne));

        } else {

            groups[identifyParent(groups, sounrceTwo)].setAncestor(identifyParent(groups, sourceOne));

            groups[identifyParent(groups, sourceOne)].setStatus(groups[identifyParent(groups, sourceOne)].getStatus() + 1);
        }
    }

    /**
     * Identifica o ancestral de um vertice em um subgrupo de vertices.
     *
     * @param groups lista com subconjunto de vertices
     * @param vertex vertice a ser analisado
     */
    private int identifyParent(VertexGroup groups[], int vertex) {

        if (groups[vertex].getAncestor() != vertex) {

            groups[vertex].setAncestor(identifyParent(groups, groups[vertex].getAncestor()));


        }

        return groups[vertex].getAncestor();
    }

    /**
     * Verifica se uma aresta forma ciclo com a arvore geradora.
     *
     * @param conjuntos lista de subgrupos de vertices
     * @param edgeAux aresta a ser analisada
     */
    private boolean hasCycle(VertexGroup[] conjuntos, Edge edgeAux) {
        return (identifyParent(conjuntos, Integer.valueOf(edgeAux.getSource()))) != (identifyParent(conjuntos, Integer.valueOf(edgeAux.getTarget())));

    }

    @Override
    public String BFS(Graph graph, String head) {
        List<String> listVertex = new ArrayList<String>(Arrays.asList(graph.getAllVertices()));
        ArrayList<String> proximos = new ArrayList();
        proximos.add(head);
        return graph.BFS(new ArrayList<String>(), proximos, listVertex, 0, true);
    }

    public String DFS(Graph graph, String head) {
        List<String> listVertex = new ArrayList<String>(Arrays.asList(graph.getAllVertices()));
        ArrayList<String> proximos = new ArrayList();
        proximos.add(head);
        String[] result = graph.BFS(new ArrayList<String>(), proximos, listVertex, 0, false).split("\n");
        String toString = "";
        for(int i = result.length-1; i > -1; i--) {
            toString += result[i].substring(0, 2);
        }
        return toString;
    }

}
