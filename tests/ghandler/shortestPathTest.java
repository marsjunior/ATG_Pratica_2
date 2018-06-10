package ghandler;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class shortestPathTest {

    private GraphHandler gh;

    public shortestPathTest() { }

    @Before
    public void setup() {
        gh = new GraphHandler();
    }

    @Test
    public void shortestPathTest01() throws Exception {

        Graph g =  null;

//        g.addEdge(new Edge("1", "2", 0.0));
//        g.addEdge(new Edge("2", "4", 0.0));
//        g.addEdge(new Edge("5", "3", 0.0));
//        g.addEdge(new Edge("2", "5", 0.0));
//        g.addEdge(new Edge("4", "1", 0.0));



        Assert.assertEquals("", gh.shortestPath(null, "1", "5"));

    }

    @Test
    public void shortestPathTest02() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 0.0));
        g.addEdge(new Edge("2", "4", 0.0));
        g.addEdge(new Edge("5", "3", 0.0));
        g.addEdge(new Edge("2", "5", 0.0));
        g.addEdge(new Edge("4", "1", 0.0));



        Assert.assertEquals("", gh.shortestPath(g, null, "5"));

    }

    @Test
    public void shortestPathTest03() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 0.0));
        g.addEdge(new Edge("2", "4", 0.0));
        g.addEdge(new Edge("5", "3", 0.0));
        g.addEdge(new Edge("2", "5", 0.0));
        g.addEdge(new Edge("4", "1", 0.0));



        Assert.assertEquals("", gh.shortestPath(g, "1", null));

    }

    @Test
    public void shortestPathTest04() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 0.0));
        g.addEdge(new Edge("2", "4", 0.0));
        g.addEdge(new Edge("5", "3", 0.0));
        g.addEdge(new Edge("2", "5", 0.0));
        g.addEdge(new Edge("4", "1", 0.0));



        Assert.assertEquals("", gh.shortestPath(g, "20", "5"));

    }

    @Test
    public void shortestPathTest05() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 0.0));
        g.addEdge(new Edge("2", "4", 0.0));
        g.addEdge(new Edge("5", "3", 0.0));
        g.addEdge(new Edge("2", "5", 0.0));
        g.addEdge(new Edge("4", "1", 0.0));



        Assert.assertEquals("1 2", gh.shortestPath(g, "1", "2"));

    }

    @Test
    public void shortestPathTest06() throws Exception {

        Graph g =  new Graph(5, false);

        g.addEdge(new Edge("1", "2", 0.0));
        g.addEdge(new Edge("2", "4", 0.0));
        g.addEdge(new Edge("5", "3", 0.0));
        g.addEdge(new Edge("2", "5", 0.0));
        g.addEdge(new Edge("4", "3", 0.0));
        g.addEdge(new Edge("1", "3", 0.0));



        Assert.assertEquals("1 3", gh.shortestPath(g, "1", "3"));

    }

    @Test
    public void shortestPathTest07() throws Exception {

        Graph g =  new Graph(5, true);

        g.addEdge(new Edge("1", "2", 3.2));
        g.addEdge(new Edge("2", "4", 5.2));
        g.addEdge(new Edge("5", "3", 1.2));
        g.addEdge(new Edge("2", "5", 0.3));
        g.addEdge(new Edge("4", "3", 0.5));
        g.addEdge(new Edge("1", "3", 4.6));



        Assert.assertEquals("1(0.0) 3(4.6)", gh.shortestPath(g, "1", "3"));

    }

    @Test
    public void shortestPathTest08() throws Exception {

        Graph g =  new Graph(5, true);

        g.addEdge(new Edge("1", "2", 3.2));
        g.addEdge(new Edge("2", "4", 5.2));
        g.addEdge(new Edge("5", "3", 1.2));
        g.addEdge(new Edge("2", "5", 0.3));
        g.addEdge(new Edge("4", "3", 0.5));
        g.addEdge(new Edge("1", "3", 4.7));



        Assert.assertEquals("1(0.0) 3(4.7)", gh.shortestPath(g, "1", "3"));

    }

    @Test
    public void shortestPathTest09() throws Exception {

        Graph g =  new Graph(5, true);

        g.addEdge(new Edge("1", "2", 3.2));
        g.addEdge(new Edge("2", "4", 5.2));
        g.addEdge(new Edge("5", "3", 1.2));
        g.addEdge(new Edge("2", "5", 0.3));
        g.addEdge(new Edge("4", "3", 0.5));
        g.addEdge(new Edge("1", "3", 4.8));



        Assert.assertEquals("1(0.0) 2(3.2) 5(3.5) 3(4.7)", gh.shortestPath(g, "1", "3"));

    }

    @Test
    public void shortestPathTest10() throws Exception {

        Graph g =  new Graph(5, true);

        g.addEdge(new Edge("1", "2", -3.2));
        g.addEdge(new Edge("2", "4", -5.2));
        g.addEdge(new Edge("5", "3", 1.2));
        g.addEdge(new Edge("2", "5", 0.3));
        g.addEdge(new Edge("4", "1", -0.5));



        Assert.assertEquals("Grafo contém ciclo negativo", gh.shortestPath(g, "1", "4"));

    }

}
