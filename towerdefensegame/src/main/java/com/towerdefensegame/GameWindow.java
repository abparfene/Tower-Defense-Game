import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class gameWindow extends JPanel implements Runnable{



    final int heigth = 12*48;
    final int FPS = 60;
    int hp = 100;
    int gold =10;
    private boolean isGameOverPopupShown = false;


    sideWindow sidePanel;
    gamePanel gamePanel;

    Thread mainThread;
    LinkedList<enemy> listOfEnemies;
    public enum STATE{
        //global for the program
        startScreen,
        levelBuilder,
        gamePlay
    }
    public enum GameState{
        //for the actuall game window
        beforeWave,
        inWave,
        paused

    }

    public GameState gameState = GameState.beforeWave;
    public GameState lastGameState;
    public STATE state = STATE.startScreen;

    public STATE getState(){

        return state;
    }
    public GameState getGameState(){

        return gameState;
    }

    public void setGameState(GameState gs){

        gameState = gs;
    }
    public gameWindow(){

        
        
        listOfEnemies = new LinkedList<enemy>();
        gamePanel = new gamePanel(this);
        sidePanel = new sideWindow(this);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(gamePanel.width +sidePanel.sidePanelWidth ,heigth));
    
        this.add(gamePanel);
        this.add(sidePanel);
      

        this.setDoubleBuffered(true);
        
       // c = new Color(rand.nextInt(255), rand.nextInt(255),rand.nextInt(255));
       
        startGame();
       
    }

    public void startGame(){

        mainThread = new Thread(this);
        mainThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastUpdate = System.nanoTime();
        long currentTime;
        
        while (mainThread != null) {
            
            currentTime = System.nanoTime();
            delta+= (currentTime-lastUpdate)/drawInterval;
    
            lastUpdate = currentTime;
      
            if(delta > 1){

            //update

//if
                update();

            //render

              //  repaint();
                render();


            delta--;
            }
            

        }
    }
    public void render(){

        sidePanel.repaint();
        gamePanel.repaint();
    }
    public void update(){

        sidePanel.update();
        if(gameState != GameState.paused){
            gamePanel.update();
        }
            if(hp<=0){
        
               if (!isGameOverPopupShown) {
                showTemporaryPopup("Game Jover! YOU SUCK", 3000);
                isGameOverPopupShown = true;
                state = STATE.startScreen;
               }
            }
    }

    public static void showTemporaryPopup(String message, int duration) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog("Attention!");
        
        // Set the dialog to close automatically after the specified duration
        new Timer(duration, e -> dialog.dispose()).start();
        dialog.setVisible(true); // Show the dialog
        
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
 
      //  g2d.dispose();

    }
    
    public gamePanel getGamePanel() {
        return gamePanel;
    }

    public sideWindow getSideWindow(){

        return sidePanel;
    }
    

}