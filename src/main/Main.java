package main;


import org.junit.Before;

import edge.Edge;
import ghandler.GraphHandler;
import graph.Graph;

public class Main {

    public static void main(String[] args) throws Exception {

        /*GraphHandler gh = new GraphHandler();
        Graph g = gh.readGraph("input.txt");

        if (g != null) {

            System.out.println(gh.getEdgeNumber(g));
        }*/
    	
    	Graph grafo;
    	Edge edge;
    	GraphHandler controller = new GraphHandler();
    	int vertices;
    	Integer[] ArrayO = new Integer[]{1, 2, 5, 4, 1};
    	Integer[] ArrayD = new Integer[]{2, 5, 3, 5, 5};
    	
    		//grafotxt = new GraphHandler();
    		vertices = 5;
    		grafo = new Graph(vertices, false);
    		for(int i = 0; i < ArrayO.length; i++) {
    			String origem = String.valueOf(ArrayO[i]);
    			String destino = String.valueOf(ArrayD[i]);
    			edge = new Edge(origem, destino);
    			grafo.addEdge(edge);
    		}
    		System.out.println(controller.getEdgeNumber(grafo));

    }

}
