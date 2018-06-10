package ghandler;

import ghandler.GraphHandler;
import graph.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.Util;

public class readGraphTest {

    private GraphHandler gh;
    private String testGraph;
    private Util util = new Util();
    private final String TEST_INPUT = util.TEST_INPUT;

    public readGraphTest() {
        setup();
    }

    @Before
    public void setup() {
        gh = new GraphHandler();
    }

    @Test
    public void readGraphTest01() {

        testGraph = util.generateGraph(10, 5, 10, 5);
        util.createTestInput(testGraph);

        Graph graph = gh.readGraph(TEST_INPUT);

        Assert.assertNotNull(graph);

    }

    @Test
    public void readGraphTest02() {

        testGraph = util.generateGraph(100, 35, 100, 50);
        util.createTestInput(testGraph);

        Graph graph = gh.readGraph(TEST_INPUT);

        Assert.assertNotNull(graph);

    }

    @Test
    public void readWeightedGraphTest01() {

        testGraph = util.generateWeightedGraph(10, 5, 10, 5);
        util.createTestInput(testGraph);

        Graph graph = gh.readGraph(TEST_INPUT);

        Assert.assertNotNull(graph);

    }

    @Test
    public void readWeightedGraphTest02() {

        testGraph = util.generateWeightedGraph(100, 35, 100, 50);
        util.createTestInput(testGraph);

        Graph graph = gh.readGraph(TEST_INPUT);

        Assert.assertNotNull(graph);

    }

}
