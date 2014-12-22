/**
 * FILENAME: TitlePanel.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emily Ahn
 * PURPOSE: This is the first tab of the GUI, displaying the game title, authors, and an image.
 * */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TitlePanel extends JPanel {
  private ImageIcon logo;
  private JLabel logoLabel, titleLabel, creators;
  
  /**
   * Constructor that sets up all labels and images.
   * */
  public TitlePanel() {
    this.setBackground(new Color(255,255,255));
    titleLabel = new JLabel("The Em&Em Game");
    titleLabel.setFont(new Font("Cambria", Font.BOLD, 40));
    logo = new ImageIcon(getClass().getResource("images/M&MTitle.jpeg"));
    logoLabel = new JLabel(logo);
    creators = new JLabel("By Emery Otopalik & Emily Ahn");
    creators.setFont(new Font("Cambria", Font.BOLD, 24));
    add(titleLabel);
    add(logoLabel); 
    add(creators);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame ("Title Panel");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    TitlePanel test = new TitlePanel();
    
    frame.getContentPane().add(test);
    frame.pack();
    frame.setVisible(true);
  }
  
}