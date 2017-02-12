/*
Graph Triangulation
*/

package graph.triangula;

import java.util.*;

import ds.Common;
import graph.common.Triangle;
import graph.common.EdgeNode;
import graph.common.Graph;
import graph.common.GraphAL;

public class TriangulateGraph extends GraphAL {
	public static final int MAXV = Graph.MAXV;

	protected Triangle[] triangles = new Triangle[MAXV + 1]; // index is a vertex
	protected int numOfTriangles;

	// the second graph (where each vertex is a triangle) using Adjacency List
	protected EdgeNode[] t2Edges = new EdgeNode[MAXV + 1];

	public static void main(String[] args){
		TriangulateGraph g = new TriangulateGraph(false);
		g.read_graph("/triangleGraph.txt"); // See Figure 5.8 (Skiena book)
		g.printAdjTriangle();
		Common.log(" **** The dual graph (dashed lines) of a triangulation : (Figure 5.8)");
		g.print_graph();
	}

	public TriangulateGraph(boolean _directed){
		super(_directed);
	}

	public void read_graph (String filePath){
		Scanner sc = new Scanner(TriangulateGraph.class.getResourceAsStream(filePath), "UTF-8");
		scanInput(sc, false);

		// Read triangles
		int t;
		int[] x = new int[3];

		numOfTriangles = sc.nextInt();
		Common.log("numOfTriangles=" + numOfTriangles);

		for(int i = 1; i <= numOfTriangles; i++) {
			t = sc.nextInt(); // triangle number 
			x[0] = sc.nextInt(); // vertex number
			x[1] = sc.nextInt();
			x[2] = sc.nextInt();

			int adjSize = 0;
			// find the commons between 3 lists
			// x0 and x1
			int commonVertex = findDup(triangles [x[0]], triangles [x[1]], t);
			
			if(commonVertex > 0){
				adjSize = insertNewAdj(t, commonVertex);
			}

			if(adjSize < 3){
				// x1 and x2
				commonVertex = findDup(triangles [x[1]], triangles [x[2]], t);	
				if(commonVertex > 0) adjSize = insertNewAdj(t, commonVertex);
			}
			
			if(adjSize < 3){
				// x0 and x2
				commonVertex = findDup(triangles [x[0]], triangles [x[2]], t);
				if(commonVertex > 0) adjSize = insertNewAdj(t, commonVertex);
			}

			// update triangle adjacency list
			boolean found;
			for(int j = 0; j < 3; j++){
				if(triangles[x[j]] == null) {
					// insert new 
					triangles[x[j]] = new Triangle(t);
				}else {
					Triangle temp = triangles[x[j]];
					found = false;
					while(temp != null){
						if(temp.t == t) { found = true; break;}
						temp = temp.next;
					}
					if(!found) {
						// insert at head
						temp = new Triangle(t);
						temp.next = triangles[x[j]];
						triangles[x[j]] = temp;
					}

				}	
			}
		}
		sc.close();		
	}

	// returns the number of adj element (Max : 3), t is the new vertex number
	public int insertNewAdj(int t, int adjValue){
		EdgeNode temp = t2Edges[t];
		int size = 0;
		if(temp == null) t2Edges[t] = new EdgeNode(adjValue);
		else {
			boolean found = false;
			while(temp != null){
				if(temp.y == adjValue) found = true;
				temp = temp.next;
				size++;
			}
			if(!found){
				// insert at head
				temp = new EdgeNode(adjValue);
				temp.next = t2Edges[t];
				t2Edges[t] = temp;
			}
		}
		return size;

	}

	// find the the first duplicate between t1 and t2, but ignore any value with 'ignoreT'
	public int findDup(Triangle t1, Triangle t2, int ignoreT){
		if(t1  == null || t2 == null) return -1;
		Map<Integer, Object> dMap = new HashMap<Integer, Object>();
		Triangle temp = t1;
		while(temp != null) {
			dMap.put(temp.t, null);
			temp = temp.next;
		}
		temp = t2;
		while(temp != null){
			if(dMap.containsKey(temp.t) && temp.t != ignoreT) return temp.t;
			temp = temp.next;
		} 
		return -1; // no dup found
	}

	@Override
	public void closeInputFile(Scanner sc){
		// don't close the file yet, close it manually
	}

	@Override
	public void print_graph(){
		EdgeNode p;
		for(int i = 1; i <= numOfTriangles; i++){
			Common._log(i + " :");
			p = t2Edges[i];
			while(p != null){
				Common._log(" " + p.y);
				p = p.next;
			}
			Common.log("");
		}
	}

	public void printAdjTriangle(){
		Triangle tri;
		for(int i = 1; i <= nvertices; i++){
			tri = triangles[i];
			Common._log("Vertex: "+ i + ": ");
			while(tri != null) {
				Common._log(" " + tri.t);
				tri = tri.next;
			}
			Common.log("");
		}
	}

}