/*
Graph Triangulation
*/

package graph.triangula;

import graph.common.Triangle;
import graph.common.Graph;
import graph.common.GraphAL;

public class TriangulateGraph extends GraphAL {
	public static final int MAXV = Graph.MAXV;

	protected int[] triangles = new int[MAXV];

	public void read_graph (){
		Scanner sc = new Scanner(System.in);
		scanInput(sc, true);

		// Read triangles


		int t, x, y, z;
		t = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		z = sc.nextInt();

		Triangle triangle = new Triangle(t);
		triangle.next = triangles[x];
		triangles[x] = triangle;
		
		closeInputFile(sc);
	}


}