/*
Graph Triangulation
*/

package graph.triangula;

import java.util.*;

import ds.Common;
import graph.common.Triangle;
import graph.common.Graph;
import graph.common.GraphAL;

public class TriangulateGraph extends GraphAL {
	public static final int MAXV = Graph.MAXV;

	protected Triangle[] triangles = new Triangle[MAXV];
	protected int numOfTriangles;

	public static void main(String[] args){
		TriangulateGraph g = new TriangulateGraph(false);
		g.read_graph("/triangleGraph.txt");
		g.printAdjTriangle();
	}

	public TriangulateGraph(boolean _directed){
		super(_directed);
	}

	public void read_graph (String filePath){
		Scanner sc = new Scanner(TriangulateGraph.class.getResourceAsStream(filePath), "UTF-8");
		scanInput(sc, false);

		// Read triangles
		int t, x, y, z;
		numOfTriangles = sc.nextInt();
		Common.log("numOfTriangles=" + numOfTriangles);

		for(int i = 1; i <= numOfTriangles; i++) {
			t = sc.nextInt();
			x = sc.nextInt();
			y = sc.nextInt();
			z = sc.nextInt();	

			// Common.log("t =" + t + ";x=" + x + ";y=" + y + ";z=" + z);
			updateAdjTriangle(t, x);	
			updateAdjTriangle(t, y);
			updateAdjTriangle(t, z);
		}
		sc.close();		
	}

	@Override
	public void closeInputFile(Scanner sc){
		// don't close the file yet, close it manually
	}

	public void printAdjTriangle(){
		Triangle tri;
		for(int i = 1; i <= nvertices; i++){
			tri = triangles[i];
			Common.log("Vertex #"+ i);
			while(tri != null) {
				Common._log(" " + tri.t);
				tri = tri.next;
			}
			Common.log("");
		}
	}

	protected void updateAdjTriangle(int triangleNumber, int vertexNumber){
		Triangle triangle = new Triangle(triangleNumber);
		triangle.next = triangles[vertexNumber];
		triangles[vertexNumber] = triangle;	
	}


}