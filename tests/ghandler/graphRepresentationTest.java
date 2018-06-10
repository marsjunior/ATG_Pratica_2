package ghandler;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class graphRepresentationTest {

    private GraphHandler gh;

    public graphRepresentationTest() {
        setup();
    }

    @Before
    public void setup() {
        gh = new GraphHandler();
        Locale.setDefault(new Locale("EN","US"));
    }

    @Test
    public void graphRepresentationTest01() { //Teste contra nulidade AM

        Graph g = null;

        String result = gh.graphRepresentation(g, "AM");
        Assert.assertEquals("", result);

    }

    @Test
    public void graphRepresentationTest02() { //Teste contra nulidade AL

        Graph g = null;

        String result = gh.graphRepresentation(g, "AL");
        Assert.assertEquals("", result);

    }

    @Test
    public void graphRepresentationTest04() throws Exception { //Teste contra nulidade type

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "2"));
        g.addEdge(new Edge("2", "4"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("4", "3"));
        g.addEdge(new Edge("4", "1"));
        g.addEdge(new Edge("5", "2"));

        String result = gh.graphRepresentation(g, null);
        Assert.assertEquals("", result);

    }


    @Test
    public void graphRepresentationTest05() throws Exception { //Teste  AM

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "2"));
        g.addEdge(new Edge("2", "4"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("4", "3"));
        g.addEdge(new Edge("4", "1"));
        g.addEdge(new Edge("5", "2"));

        String expected = "  1 2 3 4 5\n" +
                "1 0 1 0 1 0\n" +
                "2 1 0 0 1 1\n" +
                "3 0 0 0 1 0\n" +
                "4 1 1 1 0 0\n" +
                "5 0 1 0 0 0";

        Assert.assertEquals(expected, gh.graphRepresentation(g, "AM"));

    }

    @Test
    public void graphRepresentationTest06() throws Exception { //Teste AL

        Graph g = new Graph(5, false);

        g.addEdge(new Edge("1", "2"));
        g.addEdge(new Edge("2", "4"));
        g.addEdge(new Edge("3", "4"));
        g.addEdge(new Edge("4", "3"));
        g.addEdge(new Edge("4", "1"));
        g.addEdge(new Edge("5", "2"));

        String expected = "1 - 2 4\n" +
                "2 - 1 4 5\n" +
                "3 - 4\n" +
                "4 - 1 2 3\n" +
                "5 - 2";

        Assert.assertEquals(expected, gh.graphRepresentation(g, "AL"));

    }

    
    @Test
    public void graphRepresentationTest07() throws Exception { //Teste  AM com peso

        Graph g = new Graph(5, true);

        g.addEdge(new Edge("1", "2", 1.2));
        g.addEdge(new Edge("2", "4", 3.4));
        g.addEdge(new Edge("3", "4", 4.1));
        g.addEdge(new Edge("4", "1", 2.2));
        g.addEdge(new Edge("5", "2", -0.9));

        String expected = "  1 2 3 4 5\n" +
                "1 0 1.2 0 2.2 0\n" +
                "2 1.2 0 0 3.4 -0.9\n" +
                "3 0 0 0 4.1 0\n" +
                "4 2.2 3.4 4.1 0 0\n" +
                "5 0 -0.9 0 0 0";

        Assert.assertEquals(expected, gh.graphRepresentation(g, "AM"));

    }

    @Test
    public void graphRepresentationTest08() throws Exception { //Teste AL

        Graph g = new Graph(5, true);

        g.addEdge(new Edge("1", "2", 1.2));
        g.addEdge(new Edge("2", "4", 3.4));
        g.addEdge(new Edge("3", "4", 4.1));
        g.addEdge(new Edge("4", "1", 2.2));
        g.addEdge(new Edge("5", "2", -0.9));

        String expected = "1 - 2(1.2) 4(2.2)\n" +
                "2 - 1(1.2) 4(3.4) 5(-0.9)\n" +
                "3 - 4(4.1)\n" +
                "4 - 1(2.2) 2(3.4) 3(4.1)\n" +
                "5 - 2(-0.9)";

        Assert.assertEquals(expected, gh.graphRepresentation(g, "AL"));

    }
    

}
