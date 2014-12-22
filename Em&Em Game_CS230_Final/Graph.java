//*******************************************************************
//  Graph.java    Author: Takis Metaxas
//  Defines a basic interface for an directed graph data structure.
//*******************************************************************
import java.util.*;

public interface Graph<Integer>
{
   /** Returns true if this graph is empty, false otherwise. */
   public boolean isEmpty();
   
   /** Returns the number of vertices in this graph. */
   public int n();

   /** Returns the number of arcs in this graph. */
   public int m();
   
   /** Returns true iff a directed edge exists b/w given vertices */
   public boolean isArc (int vertex1, int vertex2);

   /** Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph */
   public boolean isEdge (int vertex1, int vertex2);

   /** Returns true IFF the graph is undirected, that is, for every
   * pair of nodes i,j for which there is an arc, the opposite arc
   * is also present in the graph.  */
   public boolean isUndirected();

   /** Inserts an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void addArc (int vertex1, int vertex2);

   /** Removes an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void removeArc (int vertex1, int vertex2);
   
   /** Inserts an edge between two vertices of this graph,
   * if the vertices exist. Else does not change the graph. */
   public void addEdge (int vertex1, int vertex2);

   /** Removes an edge between two vertices of this graph,
   if the vertices exist, else does not change the graph. */
   public void removeEdge (int vertex1, int vertex2);
 
   /** Returns a string representation of the adjacency matrix. */
   public String toString();
   
   /** Saves the current graph into a .tgf file.
   If it cannot save the file, a message is printed. */
   public void saveTGF(String tgf_file_name);
}
