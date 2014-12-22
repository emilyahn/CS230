/**
 * FILENAME: GamePanelGUI.java (The Em&Em Game)
 * DATE: 12.14.2013
 * @author Emery Otopalik
 * PURPOSE: This file is the overall driver for The Em&Em Game. It creates a JFrame with all interactive game content 
 * and uses TitlePanel, InfoPanel, and OptionsPanel (which can produce GaemPanel).
 * */

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.geom.Dimension2D;
import java.awt.Dimension;
import java.awt.Color;

public class GamePanelGUI {

  public static void main (String[] args) {
    // creates and shows a Frame 
    JFrame frame = new JFrame("Em&Em Game");
    frame.getContentPane().setBackground(new Color(72,61,139));
    frame.getContentPane().setPreferredSize(new Dimension(600, 400));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // makes a tabbed pane with title, instruction, options, and scoreboard panels
    JTabbedPane tp = new JTabbedPane();
    tp.addTab("Home", new TitlePanel());
    tp.addTab("How-To", new InfoPanel());
    tp.addTab("Options", new OptionsPanel());
    tp.addTab("ScoreBoard", new ScoreboardPanel());

    frame.getContentPane().add(tp);
    frame.pack();
    frame.setVisible(true);
  }
  
}