import java.awt.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("unused")
class mainLogic extends JFrame  {
  

  JFrame mainFrame;



  mainLogic(){

    mainFrame = new JFrame();
    gameWindow game = new gameWindow();
    mainFrame.add(game);
    mainFrame.pack();
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);
    

    
    
   }
  

  
  public static void main(String[] args) {
       ArrayList<enemy> enemies = new ArrayList<enemy>();
       ArrayList<tower> towers = new ArrayList<tower>();
       ArrayList<projectile> projectiles = new ArrayList<projectile>();
       Random rand = new Random();
    
    
    new mainLogic();
  }




}