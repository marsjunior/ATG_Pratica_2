package util;

import edge.Edge;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Util {

    private Random random;
    private HashSet<Edge> edges;
    public static final String TEST_INPUT = "test-input.txt";

    public Util() {
        random = new Random();
        edges = new HashSet<>();
    }


    public String generateGraph(int vertexMax, int vertexMin, int edgeMax, int edgeMin) {

        String out = "";

        vertexMax = (vertexMax - vertexMin) + 1;
        edgeMax = Math.min((edgeMax * (edgeMax - 1)) / 2, (edgeMax - edgeMin) + 1);
        edgeMin = Math.min(Math.max(1, edgeMin), edgeMax);

        int vertexCount = random.nextInt(vertexMax) + vertexMin;
        int edgeCount = random.nextInt(edgeMax) + edgeMin;

        out += String.valueOf(vertexCount) + "\n";
        for (int i = 0; i < edgeCount; i++) {

            String v = String.valueOf(random.nextInt(vertexCount) + 1);
            String u = String.valueOf(random.nextInt(vertexCount) + 1);

            while (v.equals(u)) {
                u = String.valueOf(random.nextInt(vertexCount) + 1);
            }

            if (edges.contains(new Edge(v, u)) || edges.contains(new Edge(u, v))) {
                continue;
            }

            edges.add(new Edge(v, u));
            edges.add(new Edge(u, v));

            out += String.valueOf(v) + " " + String.valueOf(u) + "\n";

        }

        return out;

    }

    public String generateWeightedGraph(int vertexMax, int vertexMin, int edgeMax, int edgeMin) {

        String out = "";

        vertexMax = (vertexMax - vertexMin) + 1;
        edgeMax = Math.min((edgeMax * (edgeMax - 1)) / 2, (edgeMax - edgeMin) + 1);
        edgeMin = Math.min(Math.max(1, edgeMin), edgeMax);

        int vertexCount = random.nextInt(vertexMax) + vertexMin;
        int edgeCount = random.nextInt(edgeMax) + edgeMin + (vertexCount / 2);

        out += String.valueOf(vertexCount) + "\n";
        for (int i = 0; i < edgeCount; i++) {

            String v = String.valueOf(random.nextInt(vertexCount) + 1);
            String u = String.valueOf(random.nextInt(vertexCount) + 1);
            double w = random.nextDouble() * 2 - 1;

            while (v.equals(u)) {
                u = String.valueOf(random.nextInt(vertexCount) + 1);
            }

            if (edges.contains(new Edge(v, u, w)) || edges.contains(new Edge(u, v, w))) {
                continue;
            }

            edges.add(new Edge(v, u, w));
            edges.add(new Edge(u, v, w));

            out += String.valueOf(v) + " " + String.valueOf(u) + " " +
                    String.valueOf(new DecimalFormat("##.##").format(w)) + "\n";

        }

        return out;

    }

    public boolean createTestInput(String graphText) {


        List<String> lines = Arrays.asList(graphText.split("\n"));
        Path file = Paths.get(TEST_INPUT);

        try {

            Files.delete(file);
            Files.write(file, lines, Charset.forName("UTF-8"));
            return true;

        } catch (IOException ex) {
            return false;
        }

    }

}
