package ghandler;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class getMeanEdge {

    private GraphHandler gh;
    public getMeanEdge() {
        setup();
    }

    @Before
    public void setup() {
        gh = new GraphHandler();
    }

    @Test
    public void getMeanEdgeTest01() throws Exception { //Teste contra nulidade

        Graph g = null;
        float result = gh.getMeanEdge(g);

        Assert.assertEquals(0, result, 0);

    }

    @Test
    public void getMeanEdgeTest02() throws Exception { //Teste positivo

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "0"));
        g.addEdge(new Edge("2", "1"));
        g.addEdge(new Edge("2", "3"));
        g.addEdge(new Edge("1", "3"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("5", "4"));

        float expected = ((float)(gh.getEdgeNumber(g) * 2)) / gh.getVertexNumber(g);
        float result   = gh.getMeanEdge(g);

        Assert.assertEquals(expected, result, 0);

    }

}
