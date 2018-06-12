/**
 * 
 * Classe de teste
 * 
 * @author Marcos Junior
 * @version 1.0
 * 
 */
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;
import junit.framework.AssertionFailedError;

public class GraphTests {

	GraphHandler Controller;
	Graph grafo;
	Edge edge;
	int vertices;
	Integer[] ArrayO = new Integer[]{1, 2, 5, 4, 1};
	Integer[] ArrayD = new Integer[]{2, 5, 3, 5, 5};
	
	@Before
	public void setUp() throws Exception {
		Controller = new GraphHandler();
		vertices = 5;
		grafo = new Graph(vertices, false);
		for(int i = 0; i < ArrayO.length; i++) {
			String origem = String.valueOf(ArrayO[i]);
			String destino = String.valueOf(ArrayD[i]);
			edge = new Edge(origem, destino);
			grafo.addEdge(edge);
		}
	}
	
	/**
	 * 
	 * @author Marcos Junior
	 * @throws Exception 
	 * @versao 1.0
	 * 
	 */
	
	@Test
	public void testGraph() throws Exception {
		Graph test = new Graph(-4, false);
		Graph test2 = new Graph(-5, true);
	}
	@Test
	public void testGetVertexNumber() {
		assertEquals(vertices, Controller.getVertexNumber(grafo));
		assertEquals(0, Controller.getVertexNumber(null));
	}
	
	@Test
	public void testReadGraph() {
		Controller.readGraph("input.txt");
		Controller.readGraph(null);
	}
	
	@Test
	public void testReadWeightedGraph() {
		Controller.readWeightedGraph("test-input.txt");
		Controller.readWeightedGraph(null);
	}
	@Test
	public void testGetEdgeNull() {
		String origem = String.valueOf(ArrayO[0]);
		String destino = String.valueOf(ArrayD[0]);
		edge = new Edge(origem, destino);
		Edge edge2 = new Edge(origem, origem);
		grafo.addEdge(null);
		grafo.addEdge(edge);
		grafo.addEdge(edge);
		grafo.addEdge(edge2);
	}
	
	/**
	 * 
	 * @author Marcos Junior
	 * @versao 1.0
	 * 
	 */
	@Test
	public void testGetEdgeNumber() {
		assertEquals(ArrayO.length, Controller.getEdgeNumber(grafo));
		assertEquals(0, Controller.getEdgeNumber(null));
		
	}
	
	/**
	 * 
	 * @author Marcos Junior
	 * @versao 1.0
	 * 
	 */
	@Test
	public void testGetMeanEdge() {
		assertEquals(2.0, Controller.getMeanEdge(grafo), 0.001);
		assertEquals(0, Controller.getMeanEdge(null), 0);
	}
	
	/**
	 * 
	 * @author Marcos Junior
	 * @versao 1.0
	 * 
	 */
	@Test
	public void testGraphRepresentationAM() {
		String represetation = "  1 2 3 4 5"
							 + "\n1 0 1 0 0 1"
							 + "\n2 1 0 0 0 1"
							 + "\n3 0 0 0 0 1"
							 + "\n4 0 0 0 0 1"
							 + "\n5 1 1 1 1 0";

		assertEquals(represetation, Controller.graphRepresentation(grafo, "AM"));
		Controller.graphRepresentation(grafo, null);
	}
	
	/**
	 * 
	 * @author Marcos Junior
	 * @versao 1.0
	 * 
	 */
	@Test
	public void testGraphRepresentationAL() {
		String represetation = "1 - 2 5"
							 + "\n2 - 1 5"
							 + "\n3 - 5"
							 + "\n4 - 5"
							 + "\n5 - 1 2 3 4";

		assertEquals(represetation, Controller.graphRepresentation(grafo, "AL"));
		assertEquals("", Controller.graphRepresentation(grafo, null));
		assertEquals("", Controller.graphRepresentation(null, "AL"));
	}
	
	@Test
	public void testBFS() {
		assertEquals("1 - 0 -\n2 - 1 1\n3 - 2 5\n4 - 2 5\n5 - 1 1\n",Controller.BFS(grafo, "1"));
	}
	
	@Test
	public void testDFS() {
		Controller.DFS(grafo, "1");
	}
	
	@Test
	public void testMST() {
		Controller.MST(grafo);
	}
	
	@Test
	public void testConnected() throws Exception {
		assertEquals(true, Controller.connected(grafo));
		Graph grafo2 = new Graph(5, false);
		assertEquals(null, Controller.connected(grafo2));
	}
	
	@Test
	public void testShortestPath() {
		assertEquals("", Controller.shortestPath(null, "1", "5"));
		assertEquals("", Controller.shortestPath(grafo, null, "5"));
		assertEquals("", Controller.shortestPath(grafo, "1", null));
		assertEquals("1 2 5", Controller.shortestPath(grafo, "1", "5"));
	}
	
	@Test
	public void testMst() {
		
	}
}
