/**
 * FILENAME: InfoPanel.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emily Ahn
 * PURPOSE: This file contains the 2nd tab of teh GUI, a panel that has how-to-play instructions.
 * */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class InfoPanel extends JPanel {
  
  private JLabel howToLabel, info; // title of panel, info of game
  private String infoText; // text to be put on info JLabel
  
  /**
   * Constructor that sets up all labels with detailed instructions.
   * */
  public InfoPanel() {
    this.setBackground(new Color(255,255,255));
    howToLabel = new JLabel("How To Play");
    howToLabel.setFont(new Font("Cambria", Font.BOLD, 36));
    setSize(new Dimension(100,100));
    
    infoText = "<html><br><b>Goal:</b> Have only one Em&Em remaining on the board.<br><br>First, go to the options tab and " 
      + "choose either the Triangle or Square board.<br>" +  
      "Your board will have one empty space. In order to remove an Em&Em,<br>you must jump over an adjacent Em&Em and land" +
      " in the space. The<br>jumped-over Em&Em is now removed from the board, leaving a new space.<br>You can now jump into any " 
      + "empty space. Continue this action until there are<br>no more possible moves. If you get stuck, click the <b>hint</b> button and the "
      + "possible<br>jumpers will appear in red. Make a move you don't like? Simply click "
      + "the <b>undo</b><br>button and you can rewind as much as you like.<html>";
    
    add(howToLabel);
    info = new JLabel(infoText);
    info.setFont(new Font("Helvetica", Font.PLAIN, 16));
    add(info);
    
  }
  
  // main method for testing
  public static void main(String[] args) {
    JFrame frame = new JFrame ("Info Panel");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    InfoPanel test = new InfoPanel();
    frame.getContentPane().add(test);
    
    frame.pack();
    frame.setVisible(true);
  }
  
}