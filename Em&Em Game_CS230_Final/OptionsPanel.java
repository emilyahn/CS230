/**
 * FILENAME: OptionsPanel.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emily Ahn
 * PURPOSE: This file contains the 3rd tab of the GUI, apanel where the user chooses which board
 * they want to play on. The panel has two buttons, each one leading to 
 * one of the boards (opening a new window displaying the GamePanel).
 * */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OptionsPanel extends JPanel {
  
  private ImageIcon triangle, square; // images of boards
  private JLabel infoLabel, triLabel, squLabel; // labels for title and then the two play buttons
  private JLabel blank2, blank1, blank3, blank4; // these are simply for blank space used to organize panels
                                                 // used because awkward image size makes the panels un-uniform
  private JButton triButton, squButton; // the two play buttons
  private JPanel panel, directions, tri, squ, options; 
  
  /**
   * Constructor that creates a panel with two buttons the user can click, depending on the board the user would like 
   * to play. Also sets up board images.
   * */
  public OptionsPanel() {
    this.setBackground(new Color(255,255,255));
    
    panel = new JPanel(); // overall panel; will have others overlaying
    panel.setBackground(new Color(255,255,255));
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setAlignmentX(JComponent.CENTER_ALIGNMENT); 
    
    directions = new JPanel(); // title panel
    infoLabel = new JLabel("Select your Game Board!");
    infoLabel.setFont(new Font("Cambria", Font.BOLD, 36));
    directions.add(infoLabel);
    directions.setBackground(new Color(255,255,255));
    panel.add(directions);
    
    
    blank1 = new JLabel(" "); // white space
    blank1.setBackground(new Color(255,255,255));
    panel.add(blank1);
    
    tri = new JPanel(); // panel with triangle board image
    tri.setBackground(new Color(255,255,255));
    tri.setLayout(new BoxLayout(tri, BoxLayout.PAGE_AXIS));
    tri.setAlignmentX(JComponent.CENTER_ALIGNMENT); 
    triangle = new ImageIcon("images/TriBoard.png", "Triangle Image");
    triLabel = new JLabel(triangle); // no title
    tri.add(triLabel);
    
    blank2 = new JLabel("      "); // white space
    blank2.setBackground(new Color(255,255,255));
    
    squ = new JPanel(); // panel with square board image
    squ.setBackground(new Color(255,255,255));
    squ.setLayout(new BoxLayout(squ, BoxLayout.PAGE_AXIS));
    squ.setAlignmentX(JComponent.CENTER_ALIGNMENT); 
    square = new ImageIcon("images/SquareBoard.png", "Square image");
    squLabel = new JLabel(square); // no title
    squ.add(squLabel);
    
    options = new JPanel(); // panel with two images and blank space in between
    options.setBackground(new Color(255,255,255));
    options.add(tri);
    options.add(blank2); 
    options.add(squ);
    
    panel.add(options);
    panel.add(buttonPanel()); // adding button panel
    
    add(panel);
  }
  
  /**
   * Instance method that will create and return a JPanel consisting of the two Play buttons.
   * @return JPanel A panel with a button for each board type.
   * */
  private JPanel buttonPanel() {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(255,255,255));
    buttonPanel.setAlignmentX(JComponent.RIGHT_ALIGNMENT); // want to center under both pictures
    triButton = new JButton("Play");
    squButton = new JButton("Play");
    OptionsListener listener = new OptionsListener();
    triButton.addActionListener (listener);
    squButton.addActionListener (listener);
    blank4 = new JLabel("                                                                    "); 
              // for blank space before first button
    blank3 = new JLabel("                                     "); 
              // for blank space between two buttons, precise to center under images
    buttonPanel.add(blank4);
    buttonPanel.add(triButton);
    buttonPanel.add(blank3);
    buttonPanel.add(squButton);
    return buttonPanel;
  }
  
  /**
   * Private class that listens to the two Play buttons and sets up the respective GamePanel.
   * */
  private class OptionsListener implements ActionListener {
    
    /**
     * Instance method that interprets the button-click accordingly and creates a new game in a GamePanel.
     * @param  event  The type of button that was clicked.
     * */
    public void actionPerformed (ActionEvent event) {
      
      if (event.getSource() == triButton) { // if triangle "Play" button is selected, create triangle board in new JFrame
        FullGame g = new FullGame("TriangleBoard.txt");
        JFrame frame = new JFrame("Em&Em Game");
        frame.getContentPane().add(new GamePanel(g));      
        frame.pack();
        frame.setVisible(true);
      }
      
      if (event.getSource() == squButton) { // if square "Play" button is selected, create square board in new JFrame
        FullGame g = new FullGame("SquareBoard.txt");
        JFrame frame = new JFrame("Em&Em Game");
        frame.getContentPane().add(new GamePanel(g));      
        frame.pack();
        frame.setVisible(true);
      }
      
    }
  }
}