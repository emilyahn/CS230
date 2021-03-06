/**
 * FILENAME: FullGame.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emery Otopalik
 * PURPOSE: This class includes the entire game and the three main actions involved in playing: jump, undo, and reset.
 * */

import java.util.*;
import java.io.*;

public class FullGame {
  
  private GameGraph graph;
  private boolean [] pegs; // array of present pegs
  private PossibleMoves possibles; 
  private int empty; // empty start slot
  private Stack<LinkedList<Integer>> moves; // stack of moves made
  
  /**
   * Constructor that given the filename of a gameboard will create a FullGame instance.
   * @param fileName the name of the file (triangle or square) to be read in and played from.
   * */
  public FullGame(String filename) {
    graph = new GameGraph(filename);
    possibles = new PossibleMoves(filename);
    empty = (int)(Math.random() * graph.n() + 1); // randomly chooses the empty slot to start
    pegs = new boolean[graph.n()]; 
    moves = new Stack<LinkedList<Integer>>();
    for (int i = 0; i < graph.n(); i++) { // sets all values of pegs to True unless it's the empty spot
      if (i != empty - 1)
        pegs[i] = true;
    }
  }
  
  /**
   * Instance method that returns the number of pegs currently on the board.
   * @return int  The number of true pegs in the pegs[].
   * */
  public int getPegsRemaining() {
    int count = 0;
    for (int i = 0; i < pegs.length; i++) {
      if (pegs[i]) { // count every peg that is true in the pegs[]
        count++;
      }
    }
    return count;
  }
  
  /**
   * Instance method that returns the value of the first empty slot (instance variable).
   * @return int  The value of the empty slot.
   * */
  public int getEmpty() {
    return empty;
  }
  
  /**
   * Instance method that returns the # of total slots in this game.
   * @return int  The length of the pegs[].
   * */
  public int getTotalSlots() {
    return pegs.length; 
  }
  
  /**
   * Instance method that given the int of a peg, will tell if that peg exists or not.
   * @param  a  The int of the peg to be evaluated.
   * @return boolean  True if the value of the int in the pegs[] is true, false otherwise.
   * */
  public boolean getPegs(int a) {
    return pegs[a];
  }
  
  /**
   * Instance method that given the start and end pegs, will return the value of the (middle) peg to be jumped over.
   * @param  start  The int of the first peg that wants to jump.
   * @param  end  The int of the spot where the first peg wants to jump to.
   * @return int  The int value of the peg to be jumped over.
   * */
  public int getMiddle(int start, int end) {
    LinkedList<Integer> startList = possibles.getOneList(start-1); // list of moves for start peg
    int endIndex = startList.indexOf(end); // index of end peg
    int middle = startList.get(endIndex -1); //value of hopped-over peg
    return middle;
  }
  
  /**
   * Instance method that given the start and end pegs, will return whether the start peg can complete the jump.
   * @param  start  The int of the first peg that wants to jump.
   * @param  end  The int of the spot where the first peg wants to jump to.
   * @return boolean  True if the start peg can jump to the end peg, false otherwise.
   * */
  public boolean canJump(int start, int end) {
    LinkedList<Integer> startList = possibles.getOneList(start-1); // list of moves for start peg
    try {
      int endIndex = startList.indexOf(end); // index of end peg
      int middle = startList.get(endIndex -1); //value of hopped-over peg
      return (pegs[start - 1] && endIndex%2==0 && !pegs[end - 1] && pegs[middle - 1]); 
      // true if start peg exists, middle peg exists, and end peg is empty
    } catch (IndexOutOfBoundsException e) {
      return false;
    }
  }
  
  /**
   * Instance method that returns a list of pegs that exist on the board.
   * @return LinkedList<Integer> A list of integers (pegs) that exist on the board.
   * */
  public LinkedList<Integer> getTrueSpots() {
    LinkedList<Integer> result = new LinkedList<Integer>();
    for (int i = 0; i < pegs.length; i++) {
      if (pegs[i])       // add the int at whose peg is true in the pegs[]
        result.add(i+1);
    }
    return result;
  }
  
  /**
   * Instance method that returns a list of pegs that do NOT exist (are empty) on the board.
   * @return LinkedList<Integer> A list of integers (pegs) that do NOT exist on the board.
   * */
  public LinkedList<Integer> getFalseSpots() {
    LinkedList<Integer> result = new LinkedList<Integer>();
    for (int i = 0; i < pegs.length; i++) {
      if (!pegs[i])        // add the int at whose peg is false in the pegs[]
        result.add(i+1);
    }
    return result;
  }
  
  /**
   * Instance method that given the selected peg, will return a list of pegs that can the selected peg can jump to.
   * @param  selected  The int of the peg whose open spots will be returned.
   * @param  trueSpots  The list of integers whose pegs currently exist on the board.
   * @return LinkedList<Integer> A list of integers that the selected peg can jump to.
   * */
  public LinkedList<Integer> getOpenSpots(int selected, LinkedList<Integer> trueSpots) {
    LinkedList<Integer> temp = trueSpots;
    LinkedList<Integer> result = new LinkedList<Integer>();
    int len = temp.size();
    for (int j = 0; j < len; j++) {
      for (int i = 1; i <= graph.n(); i++) {
        if (canJump(selected, i) && !temp.isEmpty()) // if a jump is possible from the selected start
          result.add(i);                         // peg to an end peg i, save i in the list to be returned 
        if (i == graph.n()) 
          temp.removeFirst();            
      }
    }
    return result;
  }
  
  /**
   * Instance method that given a list of empty spots, will return a list of pegs that could possibly jump.
   * @param  falseSpots  The list of integers whose spots are currently empty.
   * @return LinkedList<Integer> A list of integers that could possibly jump.
   * */
  public LinkedList<Integer> getJumpers(LinkedList<Integer> falseSpots) {
    LinkedList<Integer> temp = falseSpots;
    LinkedList<Integer> result = new LinkedList<Integer>();
    int len = temp.size();
    for (int j = 0; j < len; j++) {
      for (int i = 1; i <= graph.n(); i++) {
        if (canJump(i, temp.getFirst()) && !temp.isEmpty()) // if a jump is possible from an int to the first falseSpot
          result.add(i);                                    // add that that int to the result list
        if (i == graph.n()) temp.removeFirst();
      }
    }
    return result;
  }
  
  /**
   * Instance method that will complete a jump from the start peg to the end slot (if possible). 
   * The pegs[] will be manipulated and this jump action will be saved into the moves Stack.
   * @param  start  The int of the peg who will jump.
   * @param  end  The int of the slot who will get jumped into.
   * */
  public void jump(int start, int end) {
    LinkedList<Integer> startList = possibles.getOneList(start-1); // list of moves for start peg
    int endIndex = startList.indexOf(end); // index of end peg
    int middle = startList.get(endIndex -1); //value of hopped-over peg
    if (canJump(start, end)) {
      pegs[start-1] = false; // start slot is made empty
      pegs[middle-1] = false; // middle slot is made empty
      pegs[end-1] = true; // end slot is made filled
      LinkedList<Integer> thisMove = new LinkedList<Integer>(); // record of this move
      thisMove.add(start);
      thisMove.add(middle);
      thisMove.add(end);
      moves.push(thisMove); //saves move in Stack, useful for undo
    } else {
      System.out.println("This jump is not possible.");
    }
  }
  
  /**
   * Instance method that will undo the jump previously made (if a jump was even made).
   * @return LinkedList<Integer> A list of integers that holds the start, middle, and end slot values of the jump just 
   * undone.
   * */
  public LinkedList<Integer> undo() {
    LinkedList<Integer> undone = new LinkedList<Integer>();
    try {
      undone = moves.pop(); // removes move from stack
      pegs[undone.get(0) - 1] = true; // resetting move
      pegs[undone.get(1) - 1] = true;
      pegs[undone.get(2) - 1] = false;
      return undone;
    } catch (EmptyStackException e) {
      System.out.println("You cannot undo anymore: " + e); 
      return undone;
    }
  }
  
  /**
   * Instance method that will reset current game to its original state (same empty slot) by undo-ing all jumps.
   * */
  public void reset() {
    while (!moves.isEmpty()) {
      undo();
    }
  }
  
  /**
   * Instance method that will create a String of this FullGame object.
   * @return String A String representation of this FullGame using T or F to depict existence of the pegs.
   * */
  public String toString() {
    String s = "";
    for (int i = 0; i < graph.n(); i++) {
      if (pegs[i]) {
        s += "T ";
      } else {
        s += "F ";
      }
    }
    return s;
  }
  
  public static void main (String[]args) {
    FullGame game = new FullGame("SquareBoard.txt");
    System.out.println(game);
    System.out.println("FS: " + game.getFalseSpots());
    System.out.println("Jumpers: " + game.getJumpers(game.getFalseSpots()));
    game.jump(1,7);
    System.out.println(game);
    game.jump(3,1);
    System.out.println(game);
    
    System.out.println("FS: " + game.getFalseSpots());
    System.out.println("Jumpers: " + game.getJumpers(game.getFalseSpots()));
  }
  
}