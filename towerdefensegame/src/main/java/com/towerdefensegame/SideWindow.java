import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class sideWindow extends JPanel{

    final int sidePanelWidth = 400;
    final int squareSize = 48;
    final int heigth = 12*squareSize;

    gameWindow gameInstance; 
    JButton startButton , saveButton , exiButton ,upgradeButton , removeButton;
    mouseInput ms;
    texture tex;

    public String brush;
        
    public Rectangle startPoint, endPoint ,goLeft, goRight,goUp,goDown , currBrush , towerOne,towerTwo,towerThree;
    boolean showTowerUIFlag=false;
    String towerStats, towerUpgrade;
    public sideWindow(gameWindow gameInstance) {
        this.gameInstance = gameInstance; 
        tex = new texture();
        
        this.setPreferredSize(new Dimension(sidePanelWidth, heigth));
        this.setBackground(Color.green);
        this.setDoubleBuffered(true);
        
        initButtons();
        initRects();

        this.add(startButton);
        this.add(saveButton);
        this.add(exiButton);

        ms = new mouseInput(this);
        this.addMouseListener(ms);
        this.revalidate();
        this.repaint();
    }

    void initRects(){

        startPoint = new Rectangle(100, 200,squareSize, squareSize);
        endPoint = new Rectangle(sidePanelWidth-squareSize-100, 200,squareSize, squareSize);
        goDown = new Rectangle(100, 300,squareSize, squareSize);
        goUp = new Rectangle(sidePanelWidth-squareSize-100, 300,squareSize, squareSize);
        goLeft = new Rectangle(100, 400,squareSize, squareSize);
        goRight = new Rectangle(sidePanelWidth-squareSize-100, 400,squareSize, squareSize);
        currBrush = new Rectangle(250 , 100 , squareSize,squareSize);
        towerOne = new Rectangle();
        towerTwo = new Rectangle();
        towerThree = new Rectangle();

    }

    void initButtons(){
        //start button
        startButton = new JButton("New game");
        startButton.setBounds(100, 200, 100, 100);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  System.out.println(1);
                // Toggle between game states on button click
                switch (gameInstance.getState()) {
                    case startScreen:
                        gameInstance.state = gameWindow.STATE.levelBuilder;
                        break;
                    case levelBuilder:
                        //run check alogirthm
                        //save the grid
                        
                        gameInstance.getGamePanel().startNewLevel();
                        
                        

                            break;
                    case gamePlay:
                        gameInstance.gameState=gameWindow.GameState.inWave;
                    default:
                        break;
                }
            }
        });
        //saveButton
        saveButton = new JButton("Create level");
        saveButton.setBounds(100, 300, 100, 100);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between game states on button click
                switch (gameInstance.getState()) {
                    case startScreen:
                        gameInstance.state = gameWindow.STATE.levelBuilder;
                        break;
                    case levelBuilder:
                        //run check alogirthm
                        //save the grid
                        gameInstance.getGamePanel().saveLevel();

                            break;
                    case gamePlay:                    
                        switch (gameInstance.getGameState()) {
                            case inWave:
                                gameInstance.lastGameState=gameInstance.gameState;
                                gameInstance.gameState = gameWindow.GameState.paused;
                                break;
                                
                            case beforeWave:
                                gameInstance.lastGameState=gameInstance.gameState;
                                gameInstance.gameState = gameWindow.GameState.paused;
                                
                                break;
                            case paused:
                                gameInstance.gameState = gameInstance.lastGameState;
                                
                                break;
                            default:
                                break;
                        }

                    break;
                    default:
                        break;
                }
            }
        });    
        //france button
        exiButton = new JButton("Exit");
        exiButton.setBounds(100, 450, 100, 100);
        exiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between game states on button click
                
                switch (gameInstance.getState()) {
                    case startScreen:
                        System.exit(0);
                        break;
                    case levelBuilder:
                        gameInstance.state = gameWindow.STATE.startScreen;
                            break;
                    case gamePlay:
                        gameInstance.state = gameWindow.STATE.levelBuilder;
                            break;
                    default:
                        break;
                }
            }
        });
        //
        upgradeButton = new JButton("Upgrade tower");
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between game states on button click
                
            }
        });

        //
        removeButton = new JButton("Remove tower");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle between game states on button click
                
            }
        });
    }
   


    public void update() {

        switch (gameInstance.getState()) {
            case startScreen:
                startButton.setText("New game");
                saveButton.setText("Create level");
                exiButton.setText("Exit");
                startButton.setEnabled(true);
                break;
            case levelBuilder:
                startButton.setText("Play level");
                saveButton.setText("Save level");
                exiButton.setText("Back");
                startButton.setEnabled(true);
                break;
            case gamePlay:
                if(gameInstance.gameState==gameWindow.GameState.beforeWave){
                    startButton.setText("Start wave");
                    startButton.setEnabled(true);
                }else{
                    startButton.setEnabled(false);
                }
                
                if(gameInstance.gameState==gameWindow.GameState.paused){
                    saveButton.setText("Resume");
                }else{
                    saveButton.setText("Pause");
                }
                exiButton.setText("Exit");                

                removeButton.setVisible(showTowerUIFlag);
                upgradeButton.setVisible(showTowerUIFlag);
                break;
            default:
                break;
        }

    }
    public void showTowerUI(tower t) {
        showTowerUIFlag = true; // Set flag to trigger tower UI in paintComponent
        towerStats =t.toString();
        towerUpgrade =t.toString();
        this.revalidate();
        this.repaint(); // Repaint to call paintComponent
    }

    // Optionally, hide the tower UI
    public void hideTowerUI() {
        showTowerUIFlag = false;
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int halfWidth=sidePanelWidth/2;
        String helper = "STATS";
        FontMetrics metrics = getFontMetrics(g2d.getFont());
        int textLen = metrics.stringWidth(helper);
        int towerSpace = 150;
        
        if(showTowerUIFlag){
            //add diffretn tower stuff and shit and stuff and upgrade and delete and exit
            String[] lines = towerStats.split("\n");
                int lineHeight = 9;
                int y =200;
               // System.out.println(lineHeight);
                for (String line : lines) {
                    textLen = metrics.stringWidth(line);
                    g2d.drawString(line, halfWidth+(halfWidth-textLen)/2, 200+lineHeight);
                    y+=lineHeight;
                     
                }
        }else{
        switch (gameInstance.getState()) {
            case startScreen:
                break;
            case levelBuilder:
            
            initRects();
             
             g2d.drawImage(tex.getTexture("start"),startPoint.x,startPoint.y,startPoint.width,startPoint.height,this);
             
             
             g2d.drawImage(tex.getTexture("end"),endPoint.x,endPoint.y,endPoint.width,endPoint.height,this);

             
             g2d.drawImage(tex.getTexture("down"),goDown.x,goDown.y,goDown.width,goDown.height,this);

             
             g2d.drawImage(tex.getTexture("up"),goUp.x,goUp.y,goUp.width,goUp.height,this);

             
             g2d.drawImage(tex.getTexture("left"),goLeft.x,goLeft.y,goLeft.width,goLeft.height,this);
             
             
             g2d.drawImage(tex.getTexture("right"),goRight.x,goRight.y,goRight.width,goRight.height,this);

             if(ms.brush!=null){
                g2d.drawImage(tex.getTexture(ms.brush),currBrush.x,currBrush.y,currBrush.width,currBrush.height,this);
                
             }
             

            g2d.draw(startPoint);
            g2d.draw(endPoint);
            g2d.draw(goDown);
            g2d.draw(goUp);
            g2d.draw(goLeft);
            g2d.draw(goRight);
            g2d.draw(currBrush);

                break;
            case gamePlay:
                //draw rectangles//
                g2d.drawString("HP", 20, 80);
                
                g2d.drawString(String.valueOf(gameInstance.hp), 20, 100);
                g2d.drawString("Gold", 100, 80);
                g2d.drawString(String.valueOf(gameInstance.gold), 40, 100);
                g2d.drawString("Score", 200, 80);
                towerOne = new Rectangle((halfWidth- squareSize*2)/2,126+(towerSpace - squareSize*2)/2,96,96);
                g2d.drawImage(tex.getTexture("tower"),(int)towerOne.getX(),(int)towerOne.getY(),null);
                tower type1 = new tower(1);
                int yPosition = (int) towerOne.getY();
                helper = type1.toString();
                String[] lines = helper.split("\n");
                int lineHeight = 9;
               // System.out.println(lineHeight);
                for (String line : lines) {
                    textLen = metrics.stringWidth(line);
                    g2d.drawString(line, halfWidth+(halfWidth-textLen)/2, yPosition+=lineHeight);
                    yPosition+=lineHeight;
                     
                }
                

                
                towerTwo = new Rectangle((halfWidth- squareSize*2)/2,126+(towerSpace - squareSize*2)/2 + towerSpace,96,96);
                g2d.drawImage(tex.getTexture("tower"),(int)towerTwo.getX(),(int)towerTwo.getY(),null);
                tower type2 = new tower(2);
                yPosition = (int) towerTwo.getY();
                helper = type2.toString();
                lines = helper.split("\n");
                for (String line : lines) {
                    textLen = metrics.stringWidth(line);
                    g2d.drawString(line, halfWidth+(halfWidth-textLen)/2, yPosition+=lineHeight);
                    yPosition+=lineHeight;
                     
                }

               
                
                
                towerThree = new Rectangle((halfWidth- squareSize*2)/2,126+(towerSpace - squareSize*2)/2 + towerSpace*2,96,96);
                g2d.drawImage(tex.getTexture("tower"),(int)towerThree.getX(),(int)towerThree.getY(),null);
                tower type3 = new tower(3);
                yPosition = (int) towerThree.getY();
                helper = type3.toString();
                lines = helper.split("\n");
               // System.out.println(lineHeight);
                for (String line : lines) {
                    textLen = metrics.stringWidth(line);
                    g2d.drawString(line, halfWidth+(halfWidth-textLen)/2, yPosition+=lineHeight);
                    yPosition+=lineHeight;
                     
                }
                
                g2d.draw(towerOne);
                g2d.draw(towerTwo);
                g2d.draw(towerThree);
                break;
            
            default:
                break;
        }
        }
     //   g2d.dispose();

    }

    Rectangle[] getRect(){
        return new Rectangle[]{startPoint,endPoint,goDown,goUp,goLeft,goRight};
        
    }
    Rectangle[] getTowerRect(){

        return new Rectangle[]{towerOne,towerTwo,towerThree};
    }
    
    String getBrush(){

        return ms.brush;
    }


}