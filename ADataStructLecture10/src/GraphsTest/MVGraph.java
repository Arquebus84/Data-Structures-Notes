package GraphsTest;

public class MVGraph {
	/**
	 * 		Graph Interface
	 * 			public interface Graph<V, E>{
	 * 				numVertices();
	 * 				numEgdes();
	 * 
	 * 				outDegree(Vertex<V> v);
	 * 				inDegree(Vertex<V> v);
	 * 
	 * 				getEdge(Vertex<V> u, Vertex<V> v);
	 * 				endVertices(Edge<E> e);
	 * 
	 * 				insertVertex(Vertex<V> u);
	 * 				insertEdge(Edge<E> e);
	 * 
	 * 				removeVertex(Vertex<V> v);
	 * 				removeEdge(Edge<E> e);
	 * 			}
	 */
	public static void main(String[] args) {
		GraphMatrix graph = new GraphMatrix(5);
		graph.insert(0, 1); graph.insert(1, 2); graph.insert(0, 4); graph.insert(2, 4);
		graph.displayMatrix();

	}
	private static class GraphMatrix{
		int[][] matrix;
		int size;
		
		public GraphMatrix(int size) {
			this.size = size;
			matrix = new int[size][size];
		}
		
		public void insert(int vertex, int edge) {
			matrix[vertex][edge] = 1;
			matrix[edge][vertex] = 1;
		}
		
		public void displayMatrix() {
			for(int k = 0; k < matrix.length; k++)
				System.out.print(k + " ");
			System.out.println();
			for(int i = 0; i < matrix.length; i++) {
				System.out.print(i + " ");
				for(int j : matrix[i]) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
		}
	}

}
