package mst;

public class VertexGroup {
	int ancestor;
	int status;
	int vertexData;
	
	public VertexGroup(int vertexData, int ancestor,int status) {
		this.ancestor = ancestor;
		this.vertexData = vertexData;
		this.status = status;
	}
	
	public int getVertexData() {
		return vertexData;
	}
	public void setVertexData(int vertexData) {
		this.vertexData = vertexData;
	}
	public int getAncestor() {
		return ancestor;
	}
	public void setAncestor(int ancestor) {
		this.ancestor = ancestor;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
}
