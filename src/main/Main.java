package main;


import ghandler.GraphHandler;
import graph.Graph;

public class Main {

    public static void main(String[] args) {

        GraphHandler gh = new GraphHandler();
        Graph g = gh.readGraph("input.txt");

        if (g != null) {

            System.out.println(gh.BFS(g, "1"));
        }

    }

}
