package ghandler;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;

public class SearchTest {
	private GraphHandler gh;
	private Graph g1;
	private Graph g2;

	private Graph g4;


    @Before
    public void setup() throws Exception {
        gh = new GraphHandler();
    }

	@SuppressWarnings("deprecation")
	@Test

	public void testBFS() throws Exception {
		gh = new GraphHandler();
    	Graph g3 = new Graph(2, false);
    	g3.addEdge(new Edge("1", "2"));
    	Graph g2 = new Graph(6, false);
        g2.addEdge(new Edge("1", "2"));
        g2.addEdge(new Edge("2", "3"));
        g2.addEdge(new Edge("3", "4"));
        g2.addEdge(new Edge("5", "4"));
        g2.addEdge(new Edge("6", "4"));
        Graph g1 = new Graph(5, false);
        g1.addEdge(new Edge("1", "2"));
        g1.addEdge(new Edge("1", "3"));
        g1.addEdge(new Edge("1", "4"));
        g1.addEdge(new Edge("1", "5"));
        Graph g4 = new Graph(4, false);
        g4.addEdge(new Edge("1", "2"));
        g4.addEdge(new Edge("4", "6"));
		Assert.assertEquals(gh.BFS(g3, "1"), ("1 - 0 -\n2 - 1 1\n"));
		Assert.assertEquals(gh.BFS(g2, "1"), ("1 - 0 -\n" + 
				"2 - 1 1\n" + 
				"3 - 2 2\n" + 
				"4 - 3 3\n" + 
				"5 - 4 4\n" + 
				"6 - 4 4\n"));
		Assert.assertEquals(gh.BFS(g1, "1"), ("1 - 0 -\n" + 
				"2 - 1 1\n" + 
				"3 - 1 1\n" + 
				"4 - 1 1\n" + 
				"5 - 1 1\n"));	
		Assert.assertEquals(gh.BFS(g4, "1"), ("1 - 0 -\n" + 
				"2 - 1 1\n"));	
	}

	@Test
	public void testDFS() throws Exception {
		gh = new GraphHandler();
    	Graph g3 = new Graph(2, false);
    	g3.addEdge(new Edge("1", "2"));
    	Graph g2 = new Graph(6, false);
        g2.addEdge(new Edge("1", "2"));
        g2.addEdge(new Edge("2", "3"));
        g2.addEdge(new Edge("3", "4"));
        g2.addEdge(new Edge("5", "4"));
        g2.addEdge(new Edge("6", "4"));
        Graph g1 = new Graph(5, false);
        g1.addEdge(new Edge("1", "2"));
        g1.addEdge(new Edge("1", "3"));
        g1.addEdge(new Edge("1", "4"));
        g1.addEdge(new Edge("1", "5"));
        Graph g4 = new Graph(4, false);
        g4.addEdge(new Edge("1", "2"));
        g4.addEdge(new Edge("4", "6"));
		Assert.assertEquals(gh.DFS(g3, "1"), ("2 1 "));
		Assert.assertEquals(gh.DFS(g2, "1"), ("6 5 4 3 2 1 "));
		Assert.assertEquals(gh.DFS(g1, "1"), ("5 4 3 2 1 "));
		Assert.assertEquals(gh.DFS(g4, "1"), ("2 1 "));
	}

}
