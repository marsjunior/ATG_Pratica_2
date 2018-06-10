package edge;

/**
 * Essa classe representa uma Aresta. Ela pode possuir ou não um peso, e implementa funcionalidades básicas de consulta.
 */
public class Edge implements Comparable<Edge>{

    private String target;
    private String source;
    private double weight;

    /**
     *  Construtor de uma aresta normal, sem pesos, que liga dois vértices.
     * @param source Vértice de origem
     * @param target Vértice de destino
     */
    public Edge(String source, String target) {
        this.source = source;
        this.target = target;
    }

    /**
     *  Construtor de uma aresta com pesos.
     * @param source Vértice de origem
     * @param target Vértice de destino
     * @param weight Peso da aresta
     */
    public Edge(String source, String target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Edge.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Edge other = (Edge) obj;

        if(this.getSource().equals(other.getTarget()))
            return this.getTarget().equals(other.getSource());
        else
            return false;

    }

    @Override
    public int hashCode(){
        return this.getSource().hashCode() ^ this.getTarget().hashCode();
    }

	@Override
	public int compareTo(Edge o) {
		
		return (int) (this.getWeight()-o.getWeight());
	}

}
