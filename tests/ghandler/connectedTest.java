package ghandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

public class connectedTest {
	private GraphHandler gh;

    @Before
    public void setup() {
        gh = new GraphHandler();
    }

    @Test
    public void singleEdgeGraphTest() throws Exception {

    	Graph g =  new Graph(3, false);

    	g.addEdge(new Edge("1", "2"));

    	Assert.assertEquals(gh.connected(g), true);
    }

    @Test
    public void multipleEdgeGraphTest() throws Exception {

        Graph g =  new Graph(3, false);

        g.addEdge(new Edge("1", "2"));
        g.addEdge(new Edge("2", "3"));
        g.addEdge(new Edge("4", "5"));

        Assert.assertEquals(gh.connected(g), false);

    }

    @Test
    public void cyclicGraphTest() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2"));
        g.addEdge(new Edge("2", "4"));
        g.addEdge(new Edge("5", "3"));
        g.addEdge(new Edge("2", "5"));
        g.addEdge(new Edge("4", "1"));

        Assert.assertEquals(gh.connected(g), true);
    }

    @Test
    public void weightedGraphTest() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 3.0));
        g.addEdge(new Edge("2", "4", 5.1));
        g.addEdge(new Edge("5", "3", 6.3));
        g.addEdge(new Edge("2", "5", 7.2));
        g.addEdge(new Edge("4", "1", 2.0));

        Assert.assertEquals(gh.connected(g), true);
    }



}
