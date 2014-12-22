/**
 * FILENAME: GamePanel.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emery Otopalik
 * PURPOSE: This file contains the actual panel that the user will play on. Depending on which board is selected from
 * the OptionsPanel, the board will either be triangle or square. The player can jump, undo, reset, or see hints while
 * playing, and after the game is over, the user can save their score that will display on the ScoreboardPanel.
 * */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileWriter;

public class GamePanel extends JPanel {
  
  private JButton undo, hint, reset; 
  private JButton [] mmbutton; // for all the pegs
  private ImageIcon mm, circle, darkmm, mmred;
  private JLabel pegs; // label that tells you how many pegs are left
  private FullGame g;
  private boolean pegselected, slotselected, istri; // tells you if a peg or a slot, respectively, are selected 
  // istri is boolean as to whether the triangle or square board is in play
  private int saved, e; //saved is for the selected peg, e is for the empty slot
  
  /**
   * Constructor that creates a panel with the interactive playing board and three buttons to undo, get hint, and reset.
   * */
  public GamePanel(FullGame game) {
    this.setBackground(new Color(255,255,255));
    saved = -1; 
    g = game;
    istri = (g.getTotalSlots() == 15) ? true : false; // if there are 15 slots, it is a triangle board
    pegselected = false; // there is no peg selected
    slotselected = false; // there is no slot selected
    mm = new ImageIcon("images/M&M.png", "button image"); // unselected m&m is light green
    circle = new ImageIcon("images/circle.png", "circle image"); // unselected empty slot is an empty circle
    darkmm = new ImageIcon("images/M&Msel.png", "selected M&M image"); // selected m&m is dark green
    mmred = new ImageIcon("images/mmred.png", "hint M&M"); // hinted m&m is red
    e = g.getEmpty();  // gets randomized empty slot
    mmbutton = new JButton[g.getTotalSlots()]; // sets number of buttons depending on board
    add(wholeBoard());
  }
  
  /**
   * Instance method that will create and return a JPanel that includes all other panels by initializing mmbuttons
   * and then adding either triangle or square board panels, then text and button panels.
   * @return JPanel A complete panel to be added in the constructor.
   * */
  private JPanel wholeBoard() {
    JPanel whole = new JPanel();
    whole.setBackground(new Color(255,255,255));
    whole.setLayout(new BoxLayout(whole, BoxLayout.PAGE_AXIS));
    for (int i = 0; i < mmbutton.length; i++) { // creating all m&m buttons
      mmbutton[i] = new JButton();
      if (i+1 == e) { // empty button will have a circle icon
        mmbutton[i].setIcon(circle);
      } else { // all other buttons will have the light green m&m icon
        mmbutton[i].setIcon(mm);
      }
      mmbutton[i].setBorder(BorderFactory.createEmptyBorder()); // makes it appear as if the m&m itself is the button
      mmbutton[i].setPreferredSize(new Dimension(90,65)); 
      mmbutton[i].addActionListener(new ButtonListener()); // every button has same action listener
    }
    if (istri) { // if is triangle board
      whole.add(addTrianglePanel()); //add triangle board 
    } else {
      whole.add(addSquarePanel()); // add square board
    }
    whole.add(addMiddlePanel());
    whole.add(addLowerPanel());
    return whole;
  }
  
  /**
   * Instance method that will create and return a JPanel that sets up the Triangle board with 15 spots (which 
   * consists of a JPanel for each row of buttons).
   * @return JPanel A board in triangle form.
   * */
  private JPanel addTrianglePanel() {
    JPanel triPanel = new JPanel();
    triPanel.setBackground(new Color(255,255,255));
    triPanel.setLayout(new BoxLayout(triPanel, BoxLayout.PAGE_AXIS));
    triPanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    // each of the following panels is a different row
    JPanel one = new JPanel();
    JPanel two = new JPanel();
    JPanel three = new JPanel();
    JPanel four = new JPanel();
    JPanel five = new JPanel();
    one.setBackground(new Color(255,255,255));
    two.setBackground(new Color(255,255,255));
    three.setBackground(new Color(255,255,255));
    four.setBackground(new Color(255,255,255));
    five.setBackground(new Color(255,255,255));
    // adds button in pyramid formation
    one.add(mmbutton[0]);
    two.add(mmbutton[1]);
    two.add(mmbutton[2]);
    three.add(mmbutton[3]);
    three.add(mmbutton[4]);
    three.add(mmbutton[5]);
    four.add(mmbutton[6]);
    four.add(mmbutton[7]);
    four.add(mmbutton[8]);
    four.add(mmbutton[9]);
    five.add(mmbutton[10]);
    five.add(mmbutton[11]);
    five.add(mmbutton[12]);
    five.add(mmbutton[13]);
    five.add(mmbutton[14]);
    triPanel.add(one);
    triPanel.add(two);
    triPanel.add(three);
    triPanel.add(four);
    triPanel.add(five);
    return triPanel;
  }
  
  /**
   * Instance method that will create and return a JPanel that sets up the Square board with 13 spots (which 
   * consists of a JPanel for each row of buttons).
   * @return JPanel A board in square form.
   * */
  private JPanel addSquarePanel() {
    JPanel squarePanel = new JPanel();
    squarePanel.setBackground(new Color(255,255,255));
    squarePanel.setLayout(new BoxLayout(squarePanel, BoxLayout.PAGE_AXIS));
    squarePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    // five rows
    JPanel one = new JPanel();
    JPanel two = new JPanel();
    JPanel three = new JPanel();
    JPanel four = new JPanel();
    JPanel five = new JPanel();
    one.setBackground(new Color(255,255,255));
    two.setBackground(new Color(255,255,255));
    three.setBackground(new Color(255,255,255));
    four.setBackground(new Color(255,255,255));
    five.setBackground(new Color(255,255,255));
    // adds buttons in "square" formation
    one.add(mmbutton[0]);
    one.add(mmbutton[1]);
    one.add(mmbutton[2]);
    two.add(mmbutton[3]);
    two.add(mmbutton[4]);
    three.add(mmbutton[5]);
    three.add(mmbutton[6]);
    three.add(mmbutton[7]);
    four.add(mmbutton[8]);
    four.add(mmbutton[9]);
    five.add(mmbutton[10]);
    five.add(mmbutton[11]);
    five.add(mmbutton[12]);
    squarePanel.add(one);
    squarePanel.add(two);
    squarePanel.add(three);
    squarePanel.add(four);
    squarePanel.add(five);
    return squarePanel;
  }
  
  /**
   * Instance method that will create and return a JPanel that is a label telling how many pegs remain on the board.
   * @return JPanel A panel that will be added under the playing board panel.
   * */
  private JPanel addMiddlePanel() {
    JPanel middlePanel = new JPanel();
    middlePanel.setBackground(new Color(255,255,255));
    pegs = new JLabel("Pegs Remaining: " + g.getPegsRemaining());
    middlePanel.add(pegs);
    return middlePanel;
  }
  
  /**
   * Instance method that will create and return a JPanel that includes 3 buttons: undo, hint, and reset.
   * @return JPanel A board with 3 buttons.
   * */
  private JPanel addLowerPanel() {
    JPanel lowerPanel = new JPanel();
    lowerPanel.setBackground(new Color(255,255,255));
    undo = new JButton("Undo");
    hint = new JButton("Hint");
    reset = new JButton("Reset");
    
    lowerPanel.add(undo);
    lowerPanel.add(hint);
    lowerPanel.add(reset);
    
    undo.addActionListener(new ButtonListener());
    hint.addActionListener(new ButtonListener());
    reset.addActionListener(new ButtonListener());
    return lowerPanel;
  }
  
  /**
   * Instance method that states whether any button in mmbutton[] has been selected.
   * @return boolean  True if any button has been selected (if there are any dark green buttons).
   * */
  private boolean buttonSelected() {
    for (int i = 0; i < mmbutton.length; i++) { // loops through to check for a dark green m&m, indicating selection
      if (mmbutton[i].getIcon().equals(darkmm))
        return true;
    }
    return false;
  }
  
  /**
   * Private class that listens to all buttons on the GamePanel, including the Em&Ems and the 3 buttons
   * from MiddlePanel.
   * */
  private class ButtonListener implements ActionListener {
    
    /**
     * Instance method that interprets the button-click accordingly and changes the board appearance (based on jump,
     * undo, reset, or hint actions).
     * @param  event  The type of button that was clicked.
     * */
    public void actionPerformed(ActionEvent event) {
      
      if (event.getSource() == undo) { // if undo button is selected
        LinkedList<Integer> undone = g.undo(); // new linked list of slots involved in most recent jump, also updates FullGame g
        if (!undone.isEmpty()) { // updating board to previous move
          mmbutton[undone.get(0)-1].setIcon(mm); // original jumper gets light green m&m
          mmbutton[undone.get(1)-1].setIcon(mm); // middle gets light green m&m
          mmbutton[undone.get(2)-1].setIcon(circle); // original empty slot gets empty circle
          pegs.setText("Pegs Remaining: " + g.getPegsRemaining()); // increment pegs remaining
        } else { // if back to original board
          pegs.setText("You cannot undo anymore.");
        }
      } else if (event.getSource() == hint) { // if hint button is clicked
        LinkedList<Integer> hinters = g.getJumpers(g.getFalseSpots()); // LinkedList of all the possible jumpers
        while (!hinters.isEmpty()) { // go through list 
          mmbutton[hinters.getFirst()-1].setIcon(mmred);//and set all mmbuttons that match to red
          hinters.removeFirst();
        }
      } else if (event.getSource() == reset) { // if reset button is clicked
        g.reset(); // call reset method in full game to update data 
        for (int i = 0; i < mmbutton.length; i++) { 
          if (i == e-1) { // reset board to original
            mmbutton[i].setIcon(circle);
          } else {
            mmbutton[i].setIcon(mm);
          }
          // if played until end, these buttons were disabled, must re-enable
          mmbutton[i].setEnabled(true); 
        }
        undo.setEnabled(true); 
        hint.setEnabled(true);
        pegs.setText("Pegs Remaining: " + g.getPegsRemaining());
      } else { // action involving clicking an mmbutton
        
        // highlighting an m&m
        for (int i = 0; i < mmbutton.length; i++) { // loop through for all m&m buttons
          LinkedList<Integer> jumpers = g.getJumpers(g.getFalseSpots()); // LinkedList of all possible jumeprs
          if (event.getSource() == mmbutton[i] && jumpers.contains(i+1)) { // if a m&m button selected and it is a possible jumper
            if (!buttonSelected()) { // if no other button has already been selected
              if(mmbutton[i].getIcon().equals(mm) || mmbutton[i].getIcon().equals(mmred)) { // if button is light green or red (hinted)
                mmbutton[i].setIcon(darkmm); // (set to dark green m&m, indicating selection
                for (int k = 0; k < mmbutton.length; k++) { // any hinted m&ms (red), are reset to to light green (unselected)
                  if (mmbutton[k].getIcon().equals(mmred)) {
                    mmbutton[k].setIcon(mm);
                  }
                }
                saved = i; // selected button is saved
                break; // if selected, we can break from the loop
              }
            } else if (mmbutton[i].getIcon().equals(darkmm)) { // if we want to unselect a selected m&m
              mmbutton[i].setIcon(mm); // unselect it by changing back to light green m&m
            }  
          }
        }
        
        // executing a jump
        if (buttonSelected()) { // if an m&m was selected, we can select an empty slot
          for (int j = 0; j < mmbutton.length; j++) { // loops through all m&m buttons
            //LinkedList of all possible empty slots for selected m&m
            LinkedList<Integer> opens = g.getOpenSpots(saved+1, g.getTrueSpots());  
            if (event.getSource() == mmbutton[j] && opens.contains(j+1)) { 
              // if m&m button is selected and it's a possible empty slot
              mmbutton[j].setIcon(mm); // set empty to light green
              mmbutton[saved].setIcon(circle); // set slot jumped from to empty circle
              mmbutton[g.getMiddle(saved+1,j+1) - 1].setIcon(circle); // set slot that we jumped over to empty circle
              g.jump(saved+1,j+1); // save jump in FullGame
              pegs.setText("Pegs Remaining: " + g.getPegsRemaining()); // update label telling how many pegs remain
              
              // checking if game is over
              LinkedList<Integer> isEmpty = g.getJumpers(g.getFalseSpots()); // gets LinkedList of possible jumpers
              if (isEmpty.isEmpty()) { // if this list is empty, then game is over
                if (g.getPegsRemaining() == 1) { // if only one peg remains
                  pegs.setText("YOU ARE A WINNER!!!"); 
                } else if (g.getPegsRemaining() > 1 && g.getPegsRemaining() < 5) { // if 2 to 4 pegs remain
                  pegs.setText("You are average.");
                } else { // if 5 or more pegs remain
                  pegs.setText("You need practice.");
                  
                }
                for (int i = 0; i < mmbutton.length; i++) { // disables all buttons so that board is no longer interactive
                  mmbutton[i].setEnabled(false);
                  undo.setEnabled(false); 
                  hint.setEnabled(false);
                  // reset remains enabled in case we want to replay this specific board
                }
                
                // for the scoreboard
                try {
                  String message= JOptionPane.showInputDialog ("Please enter your name.\n\n*Note: Any spaces will be removed.\n");
                  if (message.contains(" ")) { // eliminate space in name ---> easier for reading file
                    message = message.replaceAll(" ", ""); 
                  }
                  message += " " + g.getPegsRemaining(); // add score (pegs remaining) to string
                  
                  // write to file
                  if (istri) {
                    // writes to triangle scoreboard text file
                    FileWriter printer = new FileWriter("Scoreboard.txt", true);
                    printer.append(message + "\n");
                    printer.close();
                  } else {
                    // writes to square scoreboard text file
                    FileWriter printer = new FileWriter("ScoreboardSquare.txt", true);
                    printer.append(message + "\n");
                    printer.close();
                  }
                } catch (Exception e) {
                }
              }
            } 
          }
        }
      }
    }
  }
}