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
	 * @versao 1.0
	 * 
	 */

	@Test
	public void testGetVertexNumber() {
		assertEquals(vertices, Controller.getVertexNumber(grafo));
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
	}
	
	@Test
	public void testBFS() {
		
	}
	
	@Test
	public void testDFS() {
		
	}
	
	@Test
	public void testConnected() {
		
	}
	
	@Test
	public void testShortestPath() {
		
	}
	
	@Test
	public void testMst() {
		
	}
}
