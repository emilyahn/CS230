/**
 * FILENAME: ScoreboardPanel.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emily Ahn
 * PURPOSE: This file contains the 4th tab of the GUI, a panel which displays the top ten players for each board. 
 * This information is read in from text files. It has a refresh button to update the scores each time
 * a new score is added to the scoreboard text files.
 * */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class ScoreboardPanel extends JPanel {
  
  private final int MAX_SCORE = 10; // will never have more than 10 remaining at end of game, if even 10
  LinkedList<LinkedList<String>> triList, squList; // two separate lists for each scoreboard text file
  String triScores, squScores; // string for each list
  JLabel topten, triLabel, squLabel, empty; // titles and empty space
  JButton refresh;
  JPanel top, toptenpanel, refreshbuttonpanel; // various panels for organization
  
  /**
   * Constructor that creates a panel with two columns of top-ten-players listings and a button to refresh this data.
   * */
  public ScoreboardPanel() {
    
    this.setBackground(new Color(255,255,255));
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    setAlignmentX(JComponent.CENTER_ALIGNMENT);
    
    toptenpanel = new JPanel(); // title panel
    toptenpanel.setBackground(new Color(255,255,255));
    topten = new JLabel("Top Ten Players", SwingConstants.CENTER);
    topten.setFont(new Font("Cambria", Font.BOLD, 36));
    toptenpanel.add(topten);
    
    refreshbuttonpanel = new JPanel(); // button panel
    refreshbuttonpanel.setBackground(new Color(255,255,255));
    refresh = new JButton("Refresh"); // refresh button re-loads the page to an updated list, if there are any updates
    refreshbuttonpanel.add(refresh);
    refresh.addActionListener(new ButtonListener());
    
    add(toptenpanel);
    add(refreshbuttonpanel);
    add(scoresPanel());
  }
  
  /**
   * Instance method that will create and return a JPanel consisting of two labels overlayed, & scores for each board.
   * @return JPanel A panel with a two sets of scoreboards.
   * */
  public JPanel scoresPanel() {
    top = new JPanel(); // main panel  
    top.setBackground(new Color(255,255,255));
    triList = makeList("Scoreboard.txt"); // call helper method to create sorted list of scores
    squList = makeList("ScoreboardSquare.txt");
    int numTriScores = 0; // triangle board scores counter
    int numSquScores = 0; // square board scores counter
    triScores = "<html><u><b>Triangle Board</b></u><br><br>"; // titles of two lists
    squScores = "<html><u><b>Square Board</b></u><br><br>";
    for (int i = 0; i < MAX_SCORE; i++) { // iterate in order of highest score (which is 1)
      while (!triList.get(i).isEmpty() && numTriScores < 10) { // 10 is maximum # of scores recorded/displayed
        String currentTriName = triList.get(i).remove();       // convert list to string, for triangle board
        triScores += currentTriName + "      " + (i+1) + "<br>";
        numTriScores++;
      }
      while (!squList.get(i).isEmpty() && numSquScores < 10) { // convert list to string, for square board
        String currentSquName = squList.get(i).remove();
        squScores += currentSquName + "   " + (i+1) + "<br>";
        numSquScores++;
      }
    }
    triLabel = new JLabel(triScores + "</html>", SwingConstants.CENTER);  //creates new JLabel with text 
    triLabel.setAlignmentX(Component.CENTER_ALIGNMENT);   //centers JLabel 
    squLabel = new JLabel(squScores + "</html>", SwingConstants.CENTER);  //creates new JLabel with text 
    squLabel.setAlignmentX(Component.CENTER_ALIGNMENT);   //centers JLabel 
    empty = new JLabel("         ");
    top.add(triLabel);
    top.add(empty);
    top.add(squLabel);
    return top;
  }
  
  /**
   * Instance method that reads a file and stores information (name and score) into a list of lists.
   * @param  fileName  The String of the filename to be read in.
   * @return LinkedList<LinkedList<String>> A list of String names to be included in the top-ten list.
   * */
  public LinkedList<LinkedList<String>> makeList(String fileName) {
    LinkedList<LinkedList<String>> nameList = new LinkedList<LinkedList<String>>();
    int j = 0;
    while (j < MAX_SCORE) {// initialize each linkedlist, 10 categories, one for each score
      nameList.add(new LinkedList<String>());
      j++;
    }
    try {
      Scanner scan = new Scanner(new File(fileName)); 
      while (scan.hasNext()) { // read in all names from text file 
        String currentName = scan.next();
        int currentScore = scan.nextInt();
        nameList.get(currentScore-1).add(currentName); // add name to list at the index of name's score
      }
    } catch (FileNotFoundException e) {
      System.out.println("Scoreboard file not found. " + e);
    }
    return nameList;
  }
  
  /**
   * Private class that listens to the Refresh button and reloads the scores JLabel.
   * */
  private class ButtonListener implements ActionListener {
    
    /**
     * Instance method that interprets the button-click accordingly and replaces the entire ScoreboardPanel.
     * @param  event  The type of button that was clicked.
     * */
    public void actionPerformed (ActionEvent event) {     
      if (event.getSource() == refresh) {
        ScoreboardPanel newPanel = new ScoreboardPanel();
        removeAll(); // removes all components
        add(newPanel); // replaces with updated components
      }
    }
  }
}