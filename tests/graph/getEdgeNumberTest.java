package graph;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class getEdgeNumberTest {

    public getEdgeNumberTest() {

    }

    @Test
    public void getEdgeNumberTest01() throws Exception { //Teste contra nulidade

        Graph g = new Graph(5, false);

        g.addEdge(null);
        g.addEdge(new Edge("2", "1")); //1
        g.addEdge(new Edge("2", "3")); //2
        g.addEdge(new Edge("1", "3")); //3
        g.addEdge(null);
        g.addEdge(new Edge("5", "4")); //4

        Assert.assertEquals(4, g.getEdgeNumber());

    }

    @Test
    public void getEdgeNumberTest02() throws Exception { //Teste de corretude

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "0"));
        g.addEdge(new Edge("2", "1"));
        g.addEdge(new Edge("2", "3"));
        g.addEdge(new Edge("1", "3"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("5", "4"));

        Assert.assertEquals(6, g.getEdgeNumber());


    }

    @Test
    public void getEdgeNumberTest03() throws Exception { //Teste contra arestas do tipo a - a

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "1"));
        g.addEdge(new Edge("2", "2"));
        g.addEdge(new Edge("2", "3"));
        g.addEdge(new Edge("1", "3"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("5", "4"));

        Assert.assertEquals(4, g.getEdgeNumber());

    }

    @Test
    public void getEdgeNumberTest04() throws Exception { //Teste de corretude

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("2", "3"));

        Assert.assertEquals(1, g.getEdgeNumber());

    }

    @Test
    public void getEdgeNumberTest05() throws Exception { //Teste de corretude

        Graph g = new Graph(5, false);

        Assert.assertEquals(0, g.getEdgeNumber());

    }

}
