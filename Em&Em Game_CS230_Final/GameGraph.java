// DELETE THE METHODS YOU DIDN'T USE
// might cancel this entire class/ 
import java.util.*;
import java.io.*;

public class GameGraph {
  
  private final int NOT_FOUND = -1;
  
  private int n;   // number of vertices in the graph
  private boolean [][] edges;   // adjacency matrix of arcs
  private int [] vertices;// values of vertices
  private PossibleMoves moves;
  
  /******************************************************************
    * GameGraph() creates a new GameGraph object with an int array
    * that represents vertices in a graph. It also creates a double
    * boolean array that represents whether there is an arc between
    * two vertices.
    ****************************************************************/
  public GameGraph(String filename) {
    
    moves = new PossibleMoves(filename); 
    n = moves.getList().size();
    vertices = new int[n];
    edges = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      vertices[i] = i+1;
      LinkedList<Integer> jumps = moves.getOneList(i);
      for (int k = 0; k < jumps.size(); k++) {
        if (k != 0 && k % 2 == 0) {
          int mark = jumps.get(k);
          edges[i][mark-1] = true;
        }
      }
    }
  }
  
  
  /** Returns true if this graph is empty, false otherwise. */
  public boolean isEmpty() {
    return (n == 0);
  }
  
  /** Returns the number of vertices in this graph. */
  public int n() {
    return n; 
  }
  
  /** Returns the number of arcs in this graph. */
/*  public int m() {
    int m = 0;
    for (int i = 0; i < n; i++) 
      for (int k = 0; k < n; k++) 
      if (edges[i][k]) m++;
    
    return m;
  }/*
  
  /** Returns true iff a directed edge exists b/w given vertices */
 /* public boolean isArc (int vertex1, int vertex2) {
    return edges[getIndex(vertex1)][getIndex(vertex2)];
  }
  
  /** Returns true iff an edge exists between two given vertices
    * which means that two corresponding arcs exist in the graph */
/*  public boolean isEdge (int vertex1, int vertex2) {
    return (isArc(vertex1, vertex2) && isArc(vertex2, vertex1));
  }
  
  /** Returns true IFF the graph is undirected, that is, for every
    * pair of nodes i,j for which there is an arc, the opposite arc
    * is also present in the graph.  */
 /* public boolean isUndirected() {
    for (int i = 0; i < n(); i++)
      for (int j = 0; j < n(); j++)
      if (edges[i][j])
      if (!edges[j][i]) 
      return false;
    return true;
  }
  
  /** Inserts an arc between two vertices of this graph,
    * if the vertices exist. Else it does not change the graph. */
/*  public void addArc (int vertex1, int vertex2) {
    if (indexIsValid(vertex1) && indexIsValid(vertex2))
      edges[vertex1][vertex2] = true;
  }
  
  /** Removes an arc between two vertices of this graph,
    * if the vertices exist. Else it does not change the graph. */
  /*public void removeArc (int vertex1, int vertex2) {
    if (indexIsValid(vertex1) && indexIsValid(vertex2))
      edges[vertex1][vertex2] = false;
  }
  
  /** Inserts an edge between two vertices of this graph,
    * if the vertices exist. Else does not change the graph. */
  /*public void addEdge (int vertex1, int vertex2) {
    // getIndex will return NOT_FOUND if a vertex does not exist,
    // and the addArc calls will not insert it
    addArc (vertex1, vertex2);
    addArc (vertex2, vertex1);
  }
  
  /** Removes an edge between two vertices of this graph,
    if the vertices exist, else does not change the graph. */
 /* public void removeEdge (int vertex1, int vertex2) {
    removeArc (getIndex(vertex1), getIndex(vertex2));
    removeArc (getIndex(vertex2), getIndex(vertex1));
  }
  
  /** Returns a string representation of the adjacency matrix. */
  public String toString() {
    String v = "Vertices:";
    for (int l = 1; l <= n; l++) {
      v += " " + l; 
    }
    
    String s = "\n\nEdges:\n  ";
    for (int j = 1; j <= n; j++) {
      if (j < 11) {
        s += " "; 
      }
      s += j; 
    }
    for (int i = 0; i < n; i++) {
      s += "\n" + (i+1); 
      if (i < 9) {
        s+= " ";
      }
      for (int k = 0; k < n; k++) {
        if (edges[i][k]) {
          s += " 1";
        } else {
          s += " -";
        } 
      }
    }
    return v + s + "\n\n";
  }
  
  /** Saves the current graph into a .tgf file.
    If it cannot save the file, a message is printed. */
  public void saveTGF(String tgf_file_name) {
    try {
      PrintWriter writer = new PrintWriter(new File(tgf_file_name));
      
      //prints vertices by iterating through array "vertices"
      for (int i = 0; i < n; i++){
        writer.print((i+1) + " " + vertices[i]);
        writer.println("");
      }
      
      writer.print("#"); // Prepare to print the edges
      writer.println("");
      
      //prints arcs by iterating through 2D array
      for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
          if (edges[i][j] == true){
            writer.print((i+1) + " " + (j+1));
            writer.println("");
          }
        }
      }
      writer.close();
    } catch (IOException ex) {
      System.out.println("***(T)ERROR*** The file could nt be written: " + ex);
    }
  }
  
  /******************************************************************
    Returns the index value of the first occurrence of the vertex.
    Returns NOT_FOUND if the key is not found.
    ******************************************************************/
  private int getIndex(int vertex)
  {
    for (int i = 0; i < n; i++) {
      if (vertices[i] == vertex)
        return i;
    }
    return NOT_FOUND;
  }
  
  /******************************************************************
    Returns true if the given index is valid. 
    ******************************************************************/
  private boolean indexIsValid(int index) { 
    return ((index < n) && (index >= 0));  
  }
  
  /******************************************************************
    Returns the vertex object that is at a certain index
    ******************************************************************/
  private int getVertex(int v) {
    return vertices[v]; 
  }
  
  public static void main (String[]args) {
    GameGraph triangle = new GameGraph("TriangleBoard.txt");
    System.out.println(triangle);
    GameGraph square = new GameGraph("SquareBoard.txt");
    System.out.println(square);
    triangle.saveTGF("triangle.tgf");
    square.saveTGF("square.tgf");
    
  }
  
  
}