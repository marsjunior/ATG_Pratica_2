package ghandler;

import ghandler.GraphHandler;
import graph.Graph;
import util.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class getVerticesNumberTest {

    private GraphHandler gh;
    private String testGraph;
    private Util util = new Util();
    private final String TEST_INPUT = util.TEST_INPUT;

    public getVerticesNumberTest() {
        setup();
    }

    @Before
    public void setup() {
        gh = new GraphHandler();
    }


    @Test
    public void getVerticesNumberTest01() { //Teste de corretude

        Graph g = null;

        try {
            g = new Graph(5, false);
            Assert.assertEquals(5, g.getVertexNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Test
    public void getVerticesNumberTest02() { //Teste de corretude

        Graph g = null;

        try {
            g = new Graph(0, false);
            Assert.assertEquals(0, g.getVertexNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getVerticesNumberTest03() { //Teste contra vertices < 0

        Graph g = null;

        try {
            g = new Graph(-5, false);
            fail();
        } catch (Exception e) {
            Assert.assertEquals(true, true);
        }
    }


}
