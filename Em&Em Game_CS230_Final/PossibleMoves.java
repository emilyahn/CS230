/**
 * FILENAME: PossibleMoves.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emily Ahn
 * PURPOSE: This class creates a LinkedList out of a text file of possible moves for each vertex. This LinkedList is
 * used in FullGame.java.
 * */

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class PossibleMoves {
  
  private LinkedList<LinkedList<Integer>> vertices;
  private final int DEFAULT_CAPAICITY = 10; // maximum number of slots for each leg's list of moves
  private int n; // total number of slots in this board
  
  /**
   * Constructor that given the filename of a gameboard will create a PossibleMoves instance. A scanner is used to 
   * insert integers into a list of each peg's possible moves.
   * @param fileName the name of the file (triangle or square) to be read in.
   * */
  public PossibleMoves(String fileName) {
    vertices = new LinkedList<LinkedList<Integer>>();
    try {
      Scanner scan = new Scanner(new File(fileName));
      n = scan.nextInt(); // first int in text file is the total number of slots
      
      for (int k = 0; k < n; k++) {
        LinkedList<Integer> line = new LinkedList<Integer>();
        while (scan.hasNext()) {
          int next = scan.nextInt();
          if (next == 0) { // a 0 in the text file indicates end of the line
            break;
          }
          line.add(next); // save integer into this slot's list
        }
        vertices.add(line); // save this int list into the bigger LinkedList
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e);
    }
  }
  
  /**
   * Instance method that returns this current list of possible moves (vertices, this instance variable)
   * @return LinkedList<LinkedList<Integer>> A list of integers (pegs) that exist on the board.
   * */
  public LinkedList<LinkedList<Integer>> getList() {
    return vertices; 
  }
  
  /**
   * Instance method that returns a single list of possible moves for a given index.
   * @param  index  The int whose list will be returned.
   * @return LinkedList<Integer> A list of integers (pegs) that exist on the board.
   * */
  public LinkedList<Integer> getOneList(int index) {
    return vertices.get(index); 
  }
  
  /**
   * Instance method that will create a String of this PossibleMoves object.
   * @return String A String representation of this PossibleMoves, similar to text file format.
   * */
  public String toString() {
    String s = "";
    for (int i = 0; i < n; i++)
      s += vertices.get(i) + "\n";
    return s;
  }
  
  public static void main (String [] args) {
    PossibleMoves test = new PossibleMoves("TriangleBoard.txt");
    System.out.println(test);           
  }
  
}