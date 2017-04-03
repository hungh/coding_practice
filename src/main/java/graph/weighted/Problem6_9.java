/*
6-9. [4] Consider the problem of finding a minimum weight connected subset T of edges
from a weighted connected graph G. The weight of T is the sum of all the edge
weights in T.
(a) Why is this problem not just the minimum spanning tree problem? Hint: think
negative weight edges.
(b) Give an efficient algorithm to compute the minimum weight connected subset
T.
*/

package graph.weighted;

import java.util.*;
import ds.Common;

import graph.common.*;

/*
Answeer to (a):
---------------
This is a problem of finding a subset T of edges from G and T is connected.
Therefore, if an edge is negative, it will be included into T.
Clearly, T does not have to be an MST. 
Note that, if all edges have positive weights, then T is an MST.

Answeer to (b):
---------------
This is similar to Prims or Kruskals but it has a subtle modification is that:
Let say, we are going to use Kruskals as a base line, then
When considering an edge:
  Even the edge is in the same component S, but if its weight can help reduce the total weight of T
  then it is added.

*/
public class Problem6_9 {

}