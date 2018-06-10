package ghandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ghandler.GraphHandler;
import graph.Graph;
import util.Util;

public class MstTest {
	private GraphHandler gh;
    private String testGraph;
    private Util util = new Util();
    private Graph g;

	@Before
	public void setUp() throws Exception {
		 gh = new GraphHandler();
		 GraphHandler gh = new GraphHandler();
         g = gh.readGraph("input.txt");
	}

	@Test
	public void getMstTest() {
		Assert.assertEquals(gh.graphRepresentation(g, "AM"), gh.MST(g));
	}

}
